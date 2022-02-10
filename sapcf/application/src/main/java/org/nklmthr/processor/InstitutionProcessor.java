package org.nklmthr.processor;

import java.util.List;

import org.nklmthr.model.MoneyTransaction;
import org.nklmthr.models.FileUploadModel;

public interface InstitutionProcessor {
	List<MoneyTransaction> processCreditCardStatement(FileUploadModel file);
}
