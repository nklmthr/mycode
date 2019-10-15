package com.sap.ariba.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sap.ariba.model.Document;
import com.sap.ariba.model.DocumentResult;
import com.sap.ariba.repository.DocumentRepository;

import opennlp.tools.tokenize.WhitespaceTokenizer;

@Service
public class DocumentService {
	@Autowired
	private DocumentRepository repository;

	@Value(value = "${invoice.retrycount.max}")
	private String maxRetryCount;

	private Logger logger = Logger.getLogger(DocumentService.class);

	public Document store(MultipartFile file) {
		Document document = null;
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			byte[] rawData = file.getBytes();
			String cotentType = file.getContentType();
			logger.info("Recieved fileName=" + fileName + ",cotentType=" + cotentType);
			document = new Document(fileName, cotentType, rawData);
			logger.debug(document);
			repository.save(document);
		} catch (IOException e) {
			logger.error(e, e);
		}
		return document;
	}

	public void processOneDocument() {
		Document document = repository.getUnprocessedRecord();
		Integer maxRetryCountLong = Integer.parseInt(maxRetryCount);
		if (document != null) {
			if (document.getRetryCount() < maxRetryCountLong) {
				logger.info("Start Processing " + document.getIdentifier());
				document.setStatus("PROCESSING");
				repository.save(document);
				try {
					logger.info("Call OCR for document" + document.getIdentifier());
					getLeonardoOCRResult(document);
					document.setStatus("PROCESSED");
					logger.info("OCR call completed for " + document.getIdentifier());
				} catch (IOException e) {
					logger.error(e, e);
					document.setStatus("NEW");
					document.setRetryCount(document.getRetryCount() + 1);
					logger.error("OCR call failed for " + document.getIdentifier() + " , requeueing document.");
				}
				repository.save(document);
				logger.info("Completed Processing " + document.getIdentifier());
			} else {
				document.setStatus("FAILED");
				repository.save(document);
				logger.info("Max retries reached for document. Setting status to failed. " + document.getIdentifier());
			}
		} else {
			logger.info("No Documents to Process...");
		}
	}

	private void getLeonardoOCRResult(Document document) throws ClientProtocolException, IOException {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			ContentType type = ContentType.parse(document.getContentType());

			HttpEntity data = MultipartEntityBuilder.create()
					.addBinaryBody("files", document.getDocumentData(), type, document.getFileName()).build();

			HttpUriRequest request = RequestBuilder.post("https://sandbox.api.sap.com/ml/ocr/ocr").setEntity(data)
					.setHeader("APIKey", "0QgaMuEYihizAp9s3eu2c0wgp7dId4Cc").build();

			logger.info("URL:" + request.getRequestLine());

			ResponseHandler<String> responseHandler = response -> {
				int status = response.getStatusLine().getStatusCode();
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					String responseStr = EntityUtils.toString(entity);
					return entity != null ? responseStr : null;
				} else {
					throw new ClientProtocolException("Unexpected response status: " + status);
				}
			};
			String responseBody = httpclient.execute(request, responseHandler);
			logger.info(responseBody);
			document.setOcrResult(responseBody);
		}
	}

	public Document getDocument(String identifier) {
		logger.info("get document=" + identifier);
		return repository.findByIdentifier(identifier);
	}

	public List<DocumentResult> getDocumentResults(String identifier) {
		List<DocumentResult> results = new ArrayList<DocumentResult>();
		Document document = repository.findByIdentifier(identifier);
		logger.info(document.getOcrResult());
		String ocrText = document.getOcrResult();
		WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;

		String tokens[] = tokenizer.tokenize(ocrText);
		for (String token : tokens)
			results.add(new DocumentResult(token, token));
		return results;
	}

	public List<String> getDocuments() {
		logger.info("listAllDocuments ");
		return repository.listAllDocuments();
	}
}