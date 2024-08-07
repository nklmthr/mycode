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
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class AxisBankCCSchedulerImpl extends ScheduledTask {
    private static final Logger logger = LoggerFactory.getLogger(AxisBankCCSchedulerImpl.class);

    @Override
    protected String getEmailSubject() {
        String subject = "Transaction alert on Axis Bank Credit Card no. XX2107";
        return subject;
    }

    @Override
    protected String getJSOUPXPathQuery() {
        return "/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/table[1]/tbody/tr[1]/td/span[3]";
    }

    @Override
    protected Transaction getTransactionFromContent(String html) throws ParseException {
        if (StringUtils.isNotBlank(html) && !html.contains("declined")) {
            Transaction transaction = new Transaction();
            String amountStr = html.substring(html.indexOf("Thank you for using your credit card no. XX2107 for")
                    + "Thank you for using your credit card no. XX2107 for".length(), html.indexOf(" at ")).trim();
            amountStr = amountStr.replaceAll(",", "");
            logger.debug(amountStr);
            String description = html.substring(html.indexOf(" at ") + 4, html.indexOf("on ")).trim();
            String currency = amountStr.substring(0, 3);
            String amountValue = amountStr.substring(4, amountStr.length());
            logger.info("currency" + currency + ", value=" + amountValue);
            BigDecimal amount = new BigDecimal(amountValue);
            logger.info(description);
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
