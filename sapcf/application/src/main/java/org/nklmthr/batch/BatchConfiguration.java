package org.nklmthr.batch;

import java.util.List;

import org.nklmthr.model.MoneyTransaction;
import org.nklmthr.models.AccountType;
import org.nklmthr.models.FileUploadModel;
import org.nklmthr.processor.ICICIProcesor;
import org.nklmthr.repositories.FileUploadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {

	Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	FileUploadRepository fuRep;

	@Scheduled(cron = "*/5 * * * * *")
	public void statememtProcessorJob() {
		System.out.println("Repeat");
		FileUploadModel file = null;
		if (fuRep.findFirstStatement().isPresent()) {
			file = fuRep.findFirstStatement().get().get(0);
		}
		if (file != null && file.getFileType().getName().equalsIgnoreCase("CSV")
				&& file.getAccountType().getName().equalsIgnoreCase("CREDIT_CARD")
				&& file.getInstitution().getName().equalsIgnoreCase("ICICI")) {
			ICICIProcesor processor = new ICICIProcesor();
			List<MoneyTransaction> trans = processor.processCreditCardStatement(file);

		}
		System.out.println(file);
	}

}
