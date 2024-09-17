package com.nklmthr.finance.personal.scheduler;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.common.io.BaseEncoding;
import com.nklmthr.finance.personal.dao.Transaction;
import com.nklmthr.finance.personal.service.TransactionType;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class YesBankCCSchedulerImpl extends ScheduledTask {
	private static final Logger logger = LoggerFactory.getLogger(YesBankCCSchedulerImpl.class);

	@Override
	protected String getEmailSubject() {
		String subject = "YES BANK - Transaction Alert";
		return subject;
	}

	@Override
	protected String getJSOUPXPathQuery() {
		return "/html/body/table/tbody/tr[2]/td/span";
	}

	@Override
	protected Transaction getTransactionFromContent(String html) throws ParseException {
		Transaction transaction = new Transaction();
		String amountStr = html.substring(html.indexOf("<br><br>") + 9,
				html.indexOf(" has been spent on your YES BANK Credit Card ending with ")).trim();
		amountStr = amountStr.replaceAll(",", "");
		logger.debug(amountStr);
		String description = html.substring(html.indexOf("at ") + 3,
				html.indexOf("on ", html.indexOf(" has been spent on your YES BANK Credit Card ending with") + 56))
				.trim();
		String currency = amountStr.substring(0, 3);
		String amountValue = amountStr.substring(4, amountStr.length() - 1);
		logger.debug("currency" + currency + ", value=" + amountValue);
		BigDecimal amount = new BigDecimal(amountValue);
		logger.debug(description);
		transaction.setCurrency(currency);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transaction.setTransactionType(TransactionType.DEBIT);
		return transaction;
	}

	protected void doScheduledSubTask() {
		System.out.println("Current Time" + System.currentTimeMillis());

	}

	@Override
	protected Transaction getTransactionFromOverRidingContent(String html) throws ParseException {
		Transaction transaction = new Transaction();
		String amountStr = html.substring(html.indexOf("Dear Cardmember,") + "Dear Cardmember,".length(),
				html.indexOf(" has been spent on your YES BANK Credit Card ending")).trim();
		amountStr = amountStr.replaceAll(",", "");
		logger.debug(amountStr);
		String description = html
				.substring(
						html.indexOf("at ") + "at ".length(), html
								.indexOf("on ",
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
		transaction.setDescription(description);
		transaction.setTransactionType(TransactionType.DEBIT);
		return transaction;
	}

	@Override
	protected boolean hasOverRidingContent(String email) {
		return email.trim().startsWith("Dear Cardmember,");
	}

	protected String getEmailContentFromMessage(Message message) throws IOException {
		String emailEncoded = message.getPayload().getParts().get(0).getBody().getData().toString();
		byte[] emailDecoded = BaseEncoding.base64Url().decode(emailEncoded);
		String email = new String(emailDecoded).trim();
		return email;
	}

	@Override
	protected String getAccountName() {
		return "YESB-CCA-Exclusive";
	}

}
