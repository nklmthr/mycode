package com.nklmthr.finance.personal.scheduler;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.stream.Collectors;

import org.json.JSONException;
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
public class AxisBankCCSchedulerImpl extends ScheduledTask {
	private static final Logger logger = LoggerFactory.getLogger(AxisBankCCSchedulerImpl.class);

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
		AxisBankCCSchedulerImpl impl = new AxisBankCCSchedulerImpl();
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
		String subject = "Transaction alert on Axis Bank Credit Card no. XX2107";
		String query = subject + afterQuery + currentQuery;
		logger.info(query);
		return query;
	}

	@Override
	protected String getJSOUPXPathQuery() {
		return "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/table[1]/tbody/tr[1]/td/p/span[2]";
	}

	@Override
	protected Transaction getTransactionFromContent(String html) throws ParseException {
		if (StringUtils.isNotBlank(html) && !html.contains("declined")) {
			Transaction transaction = new Transaction();
			String amountStr = html.substring(html.indexOf("Thank you for using your Card no. XX2107 for")
					+ "Thank you for using your Card no. XX2107 for".length(), html.indexOf(" at ")).trim();
			amountStr = amountStr.replaceAll(",", "");
			logger.debug(amountStr);
			String description = html.substring(html.indexOf(" at ") + 4, html.indexOf("on ")).trim();
			String currency = amountStr.substring(0, 3);
			String amountValue = amountStr.substring(4, amountStr.length());
			logger.debug("currency" + currency + ", value=" + amountValue);
			BigDecimal amount = new BigDecimal(amountValue);
			logger.debug(description);
			transaction.setCurrency(currency);
			transaction.setAmount(amount);
			transaction.setAccount(accountService.findAccountByName("AXIS-CCA-Airtel"));
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
		return null;
	}

	@Override
	protected boolean hasOverRidingContent(String email) {
		return false;
	}

	protected String getEmailContentFromMessage(Message message) throws IOException {
		MessagePart part = message.getPayload();
		String subject = part.getHeaders().stream().filter(s -> s.getName().equals("Subject")).map(s -> s.getValue())
				.collect(Collectors.joining(""));
		logger.debug("Subject:" + subject);
		String emailEncoded = part.getParts().get(0).getBody().getData().toString();
		byte[] emaildecoded = BaseEncoding.base64Url().decode(emailEncoded);
		String email = new String(emaildecoded).trim();
		logger.debug(email);
		return email;
	}

}
