package com.nklmthr.finance.personal.scheduler;

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
import java.text.ParseException;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class CitiSBScheduleImpl extends ScheduledTask {

    private static final Logger logger = LoggerFactory.getLogger(SBICCSchedulerImpl.class);

    @Override
    protected String getEmailContentFromMessage(Message message)
            throws JSONException, IOException, InvalidMessageException {
        MessagePart part = message.getPayload();
        String subject = part.getHeaders().stream().filter(s -> s.getName().equals("Subject")).map(s -> s.getValue())
                .collect(Collectors.joining(""));
        logger.debug("Subject:" + subject);
        String emailEncoded = part.getBody().getData();
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
            String amountStr = html.substring(html.indexOf("Your Citibank A/c has been debited with ")
                    + "Your Citibank A/c has been debited with ".length(), html.indexOf(" on ")).trim();
            amountStr = amountStr.replaceAll(",", "");
            logger.debug(amountStr);
            String description = html.substring(html.indexOf("and account ") + "and account ".length(),
                    html.indexOf("has been credited.")).trim();
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
            transaction.setAccount(accountService.findAccountByName("CITIB-SVG-Salary"));
            transaction.setDescription(description);
            transaction.setTransactionType(TransactionType.DEBIT);
            return transaction;
        }
        return null;
    }

    @Override
    protected String getJSOUPXPathQuery() {
        return "/html/body/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td/table/tbody/tr/td[2]/table/tbody/tr/td[1]/table/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[2]/td";
    }

    @Override
    protected String getEmailSubject() {
        String subject = "CitiAlert - UPI Fund Transfer Acknowledgement";
        return subject;
    }

    @Override
    protected void doScheduledSubTask() {
        logger.info("Current Time" + System.currentTimeMillis());
    }

}
