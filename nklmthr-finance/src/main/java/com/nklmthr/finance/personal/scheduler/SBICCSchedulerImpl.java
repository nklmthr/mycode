package com.nklmthr.finance.personal.scheduler;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.common.io.BaseEncoding;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.exception.InvalidMessageException;
import com.nklmthr.finance.personal.service.TransactionType;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class SBICCSchedulerImpl extends ScheduledTask {
	private static final Logger logger = LoggerFactory.getLogger(SBICCSchedulerImpl.class);

	@Override
	protected String getEmailSubject() {
		String subject = "Transaction Alert from SBI Card";
		return subject;
	}

	@Override
	protected String getJSOUPXPathQuery() {
		return "/html/body/table/tbody/tr[3]/td/table/tbody/tr[2]/td";
	}

	@Override
	protected Transaction getTransactionFromContent(String html) throws ParseException {
		Transaction transaction = new Transaction();
		String amountStr = html.substring(0, html.indexOf("spent on your SBI Credit Card")).trim();
		amountStr = amountStr.replaceAll(",", "");
		logger.debug(amountStr);
		String description = html
				.substring(html.indexOf(" at ") + 3, html.indexOf(" on ",
						html.indexOf("spent on your SBI Credit Card") + "spent on your SBI Credit Card".length()))
				.trim();
		String currency = amountStr.substring(0, 3);
		if (currency.equalsIgnoreCase("Rs.")) {
			currency = "INR";
		}
		String amountValue = amountStr.substring(3, amountStr.length());
		logger.debug("currency" + currency + ", value=" + amountValue);
		BigDecimal amount = new BigDecimal(amountValue);
		logger.debug(description);
		transaction.setCurrency(currency);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		return transaction;
	}

	protected void doScheduledSubTask() {
		logger.info("Current Time" + System.currentTimeMillis());

	}

	@Override
	protected Transaction getTransactionFromOverRidingContent(String html) throws ParseException {
		return null;
	}

	@Override
	protected boolean hasOverRidingContent(String email) {
		return false;
	}

	@Override
	protected String getEmailContentFromMessage(Message message) throws IOException, InvalidMessageException {
		MessagePart part = message.getPayload();
		String emailEncoded;
		String subject = part.getHeaders().stream().filter(s -> s.getName().equals("Subject")).map(s -> s.getValue())
				.collect(Collectors.joining(""));
		logger.info("Subject:" + subject);
		if (subject.equalsIgnoreCase(getEmailSubject())) {
			emailEncoded = part.getParts().get(0).getBody().getData().toString();
			byte[] emaildecoded = BaseEncoding.base64Url().decode(emailEncoded);
			String email = new String(emaildecoded).trim();
			return email;
		} else {
			throw new InvalidMessageException("OTP message");
		}
	}

	@Override
	protected String getAccountName() {
		return "SBIB-CCA-Signature";
	}
}
