package org.nklmthr.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.nklmthr.model.MoneyTransaction;
import org.nklmthr.models.FileUploadModel;

public class ICICIProcesor implements InstitutionProcessor {

	@Override
	public List<MoneyTransaction> processCreditCardStatement(FileUploadModel file) {
		List<MoneyTransaction> results = new ArrayList<MoneyTransaction>();
		PDFParser parser = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		PDFTextStripper pdfStripper;
		String parsedText;
		try {
			parser = new PDFParser(new RandomAccessBuffer(file.getContents()));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
			System.out.println(parsedText.replaceAll("[^A-Za-z0-9. ]+", ""));
		} catch (Exception e) {
		}
		return results;
	}

}
