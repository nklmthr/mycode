package com.nklmthr.finance.personal.scheduler;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.common.io.BaseEncoding;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.exception.InvalidMessageException;
import com.nklmthr.finance.personal.service.TransactionType;
import io.micrometer.common.util.StringUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class ICICIAmazonCCSchedulerImpl extends ScheduledTask {

	private static final Logger logger = LoggerFactory.getLogger(ICICIAmazonCCSchedulerImpl.class);

	public static void main(String[] args) throws GeneralSecurityException, IOException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
	}

	@Override
	protected String getEmailContentFromMessage(Message message)
			throws JSONException, IOException, InvalidMessageException {
		MessagePart part = message.getPayload();
		String subject = part.getHeaders().stream().filter(s -> s.getName().equals("Subject")).map(s -> s.getValue())
				.collect(Collectors.joining(""));
		logger.debug("Subject:" + subject);
		String emailEncoded = part.getParts().get(0).getBody().getData();
		byte[] emaildecoded = BaseEncoding.base64Url().decode(emailEncoded);
		String email = new String(emaildecoded).trim();
		logger.debug(email);
		return email;
	}

	@Override
	protected Transaction getTransactionFromOverRidingContent(String email) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean hasOverRidingContent(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Transaction getTransactionFromContent(String html) throws ParseException {
		if (StringUtils.isNotBlank(html) && !html.contains("declined")) {
			Transaction transaction = new Transaction();
			int amountStrIndex = html.indexOf("Your ICICI Bank Credit Card XX9040 has been used for a transaction of ")
					+ "Your ICICI Bank Credit Card XX9040 has been used for a transaction of ".length();
			String amountStr = html.substring(amountStrIndex, html.indexOf(" on ")).trim();
			amountStr = amountStr.replaceAll(",", "");
			logger.info(amountStr);
			String description = html.substring(html.indexOf("Info: ") + "Info: ".length(),
					html.indexOf(".", html.indexOf("Info: ") + "Info: ".length())).trim();
			String currency = amountStr.substring(0, 3);
			if (currency.equalsIgnoreCase("Rs.")) {
				currency = "INR";
			}
			String amountValue = amountStr.substring(4, amountStr.length());
			logger.info("currency" + currency + ", value=" + amountValue);
			BigDecimal amount = new BigDecimal(amountValue);
			logger.info(description);
			transaction.setCurrency(currency);
			transaction.setAmount(amount);
			transaction.setAccount(accountService.findAccountByName("ICICI-CCA-Amazon"));
			transaction.setDescription(description);
			transaction.setTransactionType(TransactionType.DEBIT);
			return transaction;
		}
		return null;
	}

	@Override
	protected String getJSOUPXPathQuery() {
		return "/html/body/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[2]/td/font";
	}

	@Override
	protected String getEmailSubject() {
		String subject = "Transaction alert for your ICICI Bank Credit Card";
		return subject;
	}

	@Override
	protected void doScheduledSubTask() {
		logger.info("Current Time" + System.currentTimeMillis());
	}

}
