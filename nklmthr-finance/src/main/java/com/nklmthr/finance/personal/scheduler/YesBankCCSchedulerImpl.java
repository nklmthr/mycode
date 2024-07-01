package com.nklmthr.finance.personal.scheduler;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.common.io.BaseEncoding;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.service.TransactionType;

import io.micrometer.common.util.StringUtils;
@Configuration
@EnableScheduling
public class YesBankCCSchedulerImpl extends ScheduledTask {
	private static final Logger logger = LoggerFactory.getLogger(YesBankCCSchedulerImpl.class);

	public static void main(String[] args) throws ParseException {
		String email = "Dear Cardmember,\r\n" + "INR 2,115.00 has been spent on your YES BANK Credit Card ending\r\n"
				+ "with 2985 at EXPLOREX TECHNOLOGIES on 01-06-2024 at 02:28:01 pm.\r\n"
				+ "Avl Bal INR 147,885.00. In case of suspicious transaction, to block\r\n"
				+ "your card, SMS BLKCC {Space}{Last 4 digits of card number} to\r\n"
				+ "9840909000 from registered mobile number.\r\n"
				+ "You can also write to us at yestouchcc@yesbank.in. If you\r\n"
				+ "would like to view any other details regarding your account, please\r\n"
				+ "login to YES BANK NetBanking service at www.yesbank.in\r\n"
				+ "This is a system generated message. Please do not reply to this\r\n" + "e-mail.\r\n" + "\r\n"
				+ "www.yesbank.in";
		YesBankCCSchedulerImpl impl = new YesBankCCSchedulerImpl();
		System.out.println(impl.getTransactionFromOverRidingContent(email));
	}

	@Override
	protected String getGMailAPIQuery() {
		Date currentDateTime = Date.from(ZonedDateTime.now().toInstant());
		String currentQuery = String.format(" before: %s-%02d-%02d", currentDateTime.getYear() + 1900,
				currentDateTime.getMonth() + 1, currentDateTime.getDate());
		logger.info(currentQuery);
		Date afterDateTime = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		String afterQuery = String.format(" after: %s-%02d-%02d", afterDateTime.getYear() + 1900,
				afterDateTime.getMonth() + 1, afterDateTime.getDate());

		logger.info(afterQuery);
		String subject = "YES BANK - Transaction Alert";
		String query = subject + afterQuery + currentQuery;
		logger.info(query);
		return query;
	}

	@Override
	protected String getJSOUPXPathQuery() {
		return "/html/body/table/tbody/tr[2]/td/span";
	}

	@Override
	protected Transaction getTransactionFromContent(String html) throws ParseException {
		if (StringUtils.isNotBlank(html) && !html.contains("declined")) {
			Transaction transaction = new Transaction();
			String amountStr = html.substring(html.indexOf("<br><br>") + 9,
					html.indexOf(" has been spent on your YES BANK Credit Card ending with ")).trim();
			amountStr = amountStr.replaceAll(",", "");
			logger.debug(amountStr);
			String description = html
					.substring(html.indexOf("at ") + 3,
							html.indexOf("on ",
									html.indexOf(" has been spent on your YES BANK Credit Card ending with") + 56))
					.trim();
			String currency = amountStr.substring(0, 3);
			String amountValue = amountStr.substring(4, amountStr.length() - 1);
			logger.debug("currency" + currency + ", value=" + amountValue);
			BigDecimal amount = new BigDecimal(amountValue);
			logger.debug(description);
			String date = html.substring(html.indexOf(" on ", html.indexOf(description)) + 3,
					html.indexOf(" at ", html.indexOf(description))).trim();
			logger.debug(date);
			String time = html.substring(html.indexOf(" at ", html.indexOf(date)) + 4, html.indexOf(". Avl Bal INR"))
					.trim();
			logger.debug(time);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
			Date tranDate = format.parse(date + " " + time);
			logger.debug(tranDate.toString());
			transaction.setDate(tranDate);
			transaction.setCurrency(currency);
			transaction.setAmount(amount);
			transaction.setAccount(accountService.findAccountByName("YESB-CCA-Exclusive"));
			transaction.setDescription(description);
			transaction.setTransactionType(TransactionType.DEBIT);
			return transaction;
		}
		return null;
	}

	protected void doScheduledSubTask() {
		System.out.println("Current Time" + System.currentTimeMillis());

	}

	@Override
	protected Transaction getTransactionFromOverRidingContent(String html) throws ParseException {
		if (StringUtils.isNotBlank(html) && !html.contains("declined")) {
			Transaction transaction = new Transaction();
			String amountStr = html.substring(html.indexOf("Dear Cardmember,") + "Dear Cardmember,".length(),
					html.indexOf(" has been spent on your YES BANK Credit Card ending")).trim();
			amountStr = amountStr.replaceAll(",", "");
			logger.debug(amountStr);
			String description = html
					.substring(html.indexOf("at ") + "at ".length(),
							html.indexOf("on ",
									html.indexOf(" has been spent on your YES BANK Credit Card ending with")
											+ " has been spent on your YES BANK Credit Card ending with".length()))
					.trim();
			String currency = amountStr.substring(0, 3);
			String amountValue = amountStr.substring(4, amountStr.length() - 1);
			logger.debug("currency" + currency + ", value=" + amountValue);
			BigDecimal amount = new BigDecimal(amountValue);
			logger.debug(description);
			transaction.setCurrency(currency);
			transaction.setAmount(amount);
			transaction.setAccount(accountService.findAccountByName("YESB-CCA-Exclusive"));
			transaction.setDescription(description);
			transaction.setTransactionType(TransactionType.DEBIT);
			return transaction;
		}
		return null;
	}

	@Override
	protected boolean hasOverRidingContent(String email) {
		return email.trim().startsWith("Dear Cardmember,");
	}

	protected String getEmailContentFromMessage(Message message) throws IOException {
		MessagePart part = message.getPayload();
		String subject = part.getHeaders().stream().filter(s -> s.getName().equals("Subject")).map(s -> s.getValue())
				.collect(Collectors.joining(""));
		logger.debug("Subject:" + subject);
		String emailEncoded = part.getParts().get(0).getBody().getData().toString();
		byte[] emailDecoded = BaseEncoding.base64Url().decode(emailEncoded);
		String email = new String(emailDecoded).trim();
		return email;
	}

}
