package com.nklmthr.finance.personal.scheduler;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.common.io.BaseEncoding;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.exception.InvalidMessageException;

import io.micrometer.common.util.StringUtils;

@Configuration
@EnableScheduling
public class AxisSBTransactionScheduleImpl extends ScheduledTask {

	private static final Logger logger = LoggerFactory.getLogger(AxisSBTransactionScheduleImpl.class);

	public static void main(String[] args)
			throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		AxisSBTransactionScheduleImpl a = new AxisSBTransactionScheduleImpl();
		a.getEmailContent();
	}

	@Override
	protected String getEmailContentFromMessage(Message message)
			throws JSONException, IOException, InvalidMessageException {
		MessagePart part = message.getPayload();
		String emailEncoded = part.getParts().get(0).getBody().getData();
		byte[] emaildecoded = BaseEncoding.base64Url().decode(emailEncoded);
		String email = new String(emaildecoded).trim();
		logger.debug(email);
		return email;
	}

	@Override
	protected Transaction getTransactionFromOverRidingContent(String email) throws ParseException {
		Transaction transaction = null;
		try {
			String amountStr = email.substring(email.indexOf(
					"Dear Nikhil Mathur, Thank you for banking with us. We wish to inform you that your A/c no. XX2804 has been debited with ")
					+ "Dear Nikhil Mathur, Thank you for banking with us. We wish to inform you that your A/c no. XX2804 has been debited with "
							.length(),
					email.indexOf(" on "));
			String description = email.substring(email.indexOf(" by ") + " by ".length(),
					email.indexOf(". To check your available"));
			String currency = amountStr.substring(0, 3);
			String amountValue = amountStr.substring(currency.length() + 1, amountStr.length());
			BigDecimal amount = new BigDecimal(amountValue);
			transaction = new Transaction();
			transaction.setCurrency(currency);
			transaction.setAmount(amount);
			transaction.setDescription(description);
			return transaction;
		} catch (StringIndexOutOfBoundsException e) {
			logger.info(email);
		}
		return transaction;
	}

	@Override
	protected boolean hasOverRidingContent(String email) {
		return !email.startsWith("<html>");
	}

	@Override
	protected Transaction getTransactionFromContent(String html) throws ParseException {
		if (StringUtils.isNotBlank(html) && !html.contains("declined")) {
			Transaction transaction = new Transaction();
			String amountStr = html.substring(0, html.indexOf("has been debited from A/c no. XX2804 on")).trim();
			amountStr = amountStr.replaceAll(",", "");
			logger.debug(amountStr);
			String description = html.substring(html.indexOf("Info-") + "Info-".length(), html.length()).trim();
			String currency = amountStr.substring(0, 3);
			if (currency.equalsIgnoreCase("Rs.")) {
				currency = "INR";
			}
			String amountValue = amountStr.substring(4, amountStr.length());
			logger.debug("currency" + currency + ", value=" + amountValue);
			BigDecimal amount = new BigDecimal(amountValue);
			logger.debug(description);
			transaction.setCurrency(currency);
			transaction.setAmount(amount);
			transaction.setDescription(description);
			return transaction;
		}
		return null;
	}

	@Override
	protected String getJSOUPXPathQuery() {
		return "/html/body/table/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[1]/td/span[3]";
	}

	@Override
	protected String getEmailSubject() {
		String subject = "Debit transaction alert for Axis Bank A/c";
		return subject;
	}

	@Override
	protected void doScheduledSubTask() {
		logger.info("Current Time" + System.currentTimeMillis());
	}

	@Override
	protected String getAccountName() {
		return "Axis Salary Acc";
	}

}
