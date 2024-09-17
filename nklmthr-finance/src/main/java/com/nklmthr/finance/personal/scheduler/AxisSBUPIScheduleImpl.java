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

import io.micrometer.common.util.StringUtils;

@Configuration
@EnableScheduling
public class AxisSBUPIScheduleImpl extends ScheduledTask {

	private static final Logger logger = LoggerFactory.getLogger(AxisSBUPIScheduleImpl.class);

	public static void main(String[] args) throws GeneralSecurityException, IOException, ParseException, InvalidMessageException {
		AxisSBUPIScheduleImpl a = new AxisSBUPIScheduleImpl();
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
		String amountStr = email.substring(email.indexOf("Dear Nikhil Mathur, ") + "Dear Nikhil Mathur, ".length(),
				email.indexOf(" has been debited from A/c no. XX2804 on"));
		String description = email.substring(email.indexOf(". Info-") + ". Info-".length(),
				email.indexOf(". For any concerns regarding this transaction"));
		String currency = amountStr.substring(0, 3);
		String amountValue = amountStr.substring(currency.length()+1, amountStr.length());
		BigDecimal amount = new BigDecimal(amountValue);
		Transaction transaction = new Transaction();
		transaction.setCurrency(currency);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setTransactionType(TransactionType.DEBIT);
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
			String description = html.substring(html.indexOf("Info-") + "Info-".length(),
					html.length()).trim();
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
			transaction.setTransactionType(TransactionType.DEBIT);
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
		String subject = "Debit Notification from Axis Bank";
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
