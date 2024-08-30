package com.nklmthr.finance.personal.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.common.base.Stopwatch;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.exception.InvalidMessageException;
import com.nklmthr.finance.personal.service.AccountService;
import com.nklmthr.finance.personal.service.CategoryService;
import com.nklmthr.finance.personal.service.CategoryType;
import com.nklmthr.finance.personal.service.TransactionService;
import com.nklmthr.finance.personal.service.TransactionType;

import io.micrometer.common.util.StringUtils;

@Controller
public abstract class ScheduledTask {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
	protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	protected static final String APPLICATION_NAME = "My Finance App";
	protected static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	protected static final String TOKENS_DIRECTORY_PATH = "tokens";
	protected static final List<String> SCOPES = Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM);
	protected static final String CREDENTIALS_FILE_PATH = "/credentials.json";
	@Autowired
	AccountService accountService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TransactionService transactionService;

	protected static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = ScheduledTask.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
		logger.info("Credentials obtained");
		return credential;
	}

	protected void getEmailContent()
			throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		Stopwatch watch = Stopwatch.createStarted();
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		logger.info("Gmail credential fetch time =" + watch.elapsed(TimeUnit.MILLISECONDS));
		watch.reset();
		logger.info("***********************************************************************");
		logger.info("Service started:" + service.getApplicationName());
		String user = "me";
		String query = getGMailAPIQuery();
		logger.info("query=" + query);
		ListMessagesResponse listMessagesResponse = service.users().messages().list("me").setQ(query).execute();
		List<Message> messages = listMessagesResponse.getMessages();
		if (messages == null || messages.isEmpty()) {
			logger.info("No Messages Found");
		} else {
			logger.info("response size: " + messages.size());
			for (Message mess : messages) {
				Message message = service.users().messages().get(user, mess.getId()).setFormat("full").execute();
				message.getPayload().getHeaders().forEach(s -> logger.debug(s.getName() + ":" + s.getValue()));
				MessagePart part = message.getPayload();
				String subject = part.getHeaders().stream().filter(s -> s.getName().equals("Subject"))
						.map(s -> s.getValue()).collect(Collectors.joining(""));
				logger.debug("Subject:" + subject);
				Date recievedTime = new Date(getReceivedTime(message).getTime());
				logger.info("message.getThreadId()=" + message.getThreadId() + ",getReceivedTime(message).getTime()="
						+ recievedTime + ",source_time=" + recievedTime.getTime());
				if (subject.equals(getEmailSubject())) {
					Transaction transaction = null;
					if (transactionService != null) {
						transaction = transactionService.findTransactionsBySource(message.getThreadId(),
								recievedTime.getTime());
					}
					if (transaction == null) {
						logger.info("Transaction not found in database.. Adding Transaction:" + message.getThreadId()
								+ ", sourceTime:" + getReceivedTime(message).getTime() + ", Time:"
								+ new Date(getReceivedTime(message).getTime()));
						String email;
						try {
							email = getEmailContentFromMessage(message);
						} catch (InvalidMessageException e) {
							continue;
						}
						logger.info("hasOverRidingContent(email) " + hasOverRidingContent(email));
						if (hasOverRidingContent(email)) {
							if (StringUtils.isNotBlank(email) && !email.contains("declined")
									&& !email.contains("reversed")) {
								transaction = getTransactionFromOverRidingContent(email);
								logger.info("Overriding transaction=" + transaction);
							} else {
								logger.info(email);
								logger.warn("Not transaction email");
							}
						} else {
							Document doc = Jsoup.parse(email);
							logger.debug(doc.html());
							String xPathQuery = getJSOUPXPathQuery();
							logger.debug("xPathQuery=" + xPathQuery);
							Elements content = doc.selectXpath(xPathQuery);
							logger.debug(content.html());
							if (StringUtils.isNotBlank(content.html()) && !content.html().contains("declined")
									&& !content.html().contains("reversed")) {
								transaction = getTransactionFromContent(content.html());
							} else {
								logger.info(content.html());
								logger.warn("Not transaction email");
							}

						}
						transaction.setTransactionType(TransactionType.DEBIT);
						transaction.setSource(message.getThreadId());
						transaction.setSourceTime(getReceivedTime(message).getTime());
						transaction.setDate(getReceivedTime(message));
						transaction.setId(UUID.randomUUID().toString());
						logger.info(transaction.toString());
						if (transactionService != null) {
							transaction.setCategory(categoryService.getParentCategoryByType(CategoryType.NOT_CLASSIFIED));
							transaction.setAccount(accountService.findAccountByName(getAccountName()));							
							transactionService.saveTransaction(transaction);
						} else {
							logger.info("Transaction:"+transaction);
						}
					} else {
						logger.info("Transaction already found in database.." + transaction);
					}
				}
			}
		}
		logger.info("Elapsed time in processing Email:" + watch.elapsed(TimeUnit.MILLISECONDS));
	}

	protected String getGMailAPIQuery() {
		LocalDate localDate = LocalDate.now().plusDays(1);
		String beforeDate = String.format(" before:  %d-%02d-%02d", localDate.getYear(), localDate.getMonthValue(),
				localDate.getDayOfMonth());
		LocalDate oneMonthBeforeDate = localDate.minusMonths(1);
		String afterDate = String.format(" after: %d-%02d-%02d", oneMonthBeforeDate.getYear(),
				oneMonthBeforeDate.getMonthValue(), oneMonthBeforeDate.getDayOfMonth());
		String subject = getEmailSubject();
		String query = "subject: " + subject + afterDate + beforeDate;
		logger.debug(query);
		return query;
	}

	protected abstract String getEmailSubject();

	private Date getReceivedTime(Message message) throws JSONException, IOException, ParseException {

		String receivedTime = message.getPayload().getHeaders().stream().filter(s -> s.getName().equals("Date"))
				.map(s -> s.getValue()).collect(Collectors.joining());
		SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");
		Date recievedDate = dateFormat.parse(receivedTime);
		logger.debug("receivedTime:" + receivedTime + ", " + recievedDate.getTime());
		return recievedDate;
	}

	protected abstract String getEmailContentFromMessage(Message message)
			throws JSONException, IOException, InvalidMessageException;

	protected abstract Transaction getTransactionFromOverRidingContent(String email)
			throws ParseException, InvalidMessageException;

	protected abstract boolean hasOverRidingContent(String email);

	protected abstract Transaction getTransactionFromContent(String html) throws ParseException;

	protected abstract String getJSOUPXPathQuery();

	@Scheduled(cron = "${sbi.cc.cron.expression}")
	public void doScheduledTask()
			throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		getEmailContent();
		doScheduledSubTask();
	}

	protected abstract void doScheduledSubTask();

	protected abstract String getAccountName();
}
