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
import com.nklmthr.finance.personal.service.TransactionType;

@Configuration
@EnableScheduling
public class AxisSBCreditScheduleImpl extends ScheduledTask {

	private static final Logger logger = LoggerFactory.getLogger(AxisSBCreditScheduleImpl.class);

	public static void main(String[] args)
			throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		AxisSBCreditScheduleImpl a = new AxisSBCreditScheduleImpl();
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
		String amountStr = email.substring(
				email.indexOf("We wish to inform you that your A/c no. XX2804 has been credited with ") + "We wish to inform you that your A/c no. XX2804 has been credited with ".length(),
				email.indexOf(" on"));
		String description = email.substring(email.indexOf(" by ") + " by ".length(),
				email.indexOf(". To check your available balance"));
		String currency = amountStr.substring(0, 3);
		String amountValue = amountStr.substring(currency.length() + 1, amountStr.length());
		BigDecimal amount = new BigDecimal(amountValue);
		Transaction transaction = new Transaction();
		transaction.setCurrency(currency);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setTransactionType(TransactionType.CREDIT);
		return transaction;
	}

	@Override
	protected boolean hasOverRidingContent(String email) {
		return !email.startsWith("<html>");
	}

	@Override
	protected Transaction getTransactionFromContent(String html) throws ParseException {
		try {
			String amountStr = html.substring(
					html.indexOf("We wish to inform you that ") + "We wish to inform you that ".length(),
					html.indexOf(" has been debited from your A/c no. XX592804 on"));
			String description = html.substring(html.indexOf(" at ") + " at ".length(),
					html.indexOf(". Available balance:"));
			String currency = amountStr.substring(0, 3);
			String amountValue = amountStr.substring(currency.length() + 1, amountStr.length());
			BigDecimal amount = new BigDecimal(amountValue);
			Transaction transaction = new Transaction();
			transaction.setCurrency(currency);
			transaction.setAmount(amount);			
			transaction.setDescription(description);			
			return transaction;
		} catch (Exception e) {
			logger.error(html);
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	protected String getJSOUPXPathQuery() {
		return "/html/body/table[1]/tbody/tr/td/table/tbody/tr[3]/td/table[1]/tbody/tr[1]/td/span[4]";
	}

	@Override
	protected String getEmailSubject() {
		String subject = "Credit transaction alert for Axis Bank A/c";
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
