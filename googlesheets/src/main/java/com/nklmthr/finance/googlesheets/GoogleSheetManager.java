package com.nklmthr.finance.googlesheets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.nklmthr.finance.Account;
import com.nklmthr.finance.Application;
import com.nklmthr.finance.Category;
import com.nklmthr.finance.Transaction;
import com.nklmthr.finance.TransactionUtils;

public class GoogleSheetManager {

	private static final String APPLICATION_NAME = "nklmthr-finance-java-app";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	private static final List<String> SCOPES = new ArrayList<String>();

	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
	protected SimpleDateFormat GoogleSheetDateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = Application.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public List<Transaction> getTransactions() throws Exception {
		List<Transaction> transactions = new ArrayList<Transaction>();
		SCOPES.addAll(SheetsScopes.all());
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1HWNyOo5ojTDvcSRcjsehm8c8ZHiJVe4--LHGpKUCOm8";
		final String range = "JunNew!A:F";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			values = values.subList(1, values.size());
			for (List<Object> row : values) {
				Transaction transaction = new Transaction();
				Date transactionDate = GoogleSheetDateFormatter.parse(row.get(0).toString());
				transaction.setDate(transactionDate);
				transaction.setAccount(Account.valueOf(row.get(1).toString()));
				transaction.setDescription(row.get(2).toString());
				transaction.setAmount(new BigDecimal(row.get(3).toString()));
				if (row.size() > 4) {
					if (EnumUtils.isValidEnum(Category.class, row.get(4).toString())) {
						transaction.setCategory(Category.valueOf(row.get(4).toString()));
					}
					if (row.size() > 5) {
						transaction.setReference(row.get(5).toString());
					}
				}
				transactions.add(transaction);
				// System.out.println(transactions);
			}
		}
		return transactions;
	}

	public void updateTransactions(List<Transaction> transactions) throws Exception {
		SCOPES.addAll(SheetsScopes.all());
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1HWNyOo5ojTDvcSRcjsehm8c8ZHiJVe4--LHGpKUCOm8";
		final String range = "JunNew!A2:G";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		service.spreadsheets().get(spreadsheetId).clear();
		
		// List<List<Object>> valuesR = Arrays.asList(Arrays.asList("Nikhil"));
		ValueRange body = new ValueRange().setValues(TransactionUtils.getTransactionsAsValuesList(transactions));
		service.spreadsheets().values().update(spreadsheetId, range, body).setValueInputOption("RAW").execute();

	}

	public void test() throws  Exception {
		String spreadsheetId = "my-spreadsheet-id"; // TODO: Update placeholder value.

		// A list of updates to apply to the spreadsheet.
		// Requests will be applied in the order they are specified.
		// If any request is not valid, no requests will be applied.
		List<Request> requests = new ArrayList<>(); // TODO: Update placeholder value.

		// TODO: Assign values to desired fields of `requestBody`:
		BatchUpdateSpreadsheetRequest requestBody = new BatchUpdateSpreadsheetRequest();
		
		requestBody.setRequests(requests);

		Sheets sheetsService = createSheetsService();
		Sheets.Spreadsheets.BatchUpdate request = sheetsService.spreadsheets().batchUpdate(spreadsheetId, requestBody);

		BatchUpdateSpreadsheetResponse response = request.execute();

		// TODO: Change code below to process the `response` object:
		System.out.println(response);
	}

	public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

		// TODO: Change placeholder below to generate authentication credentials. See
		// https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
		//
		// Authorize using one of the following scopes:
		// "https://www.googleapis.com/auth/drive"
		// "https://www.googleapis.com/auth/drive.file"
		// "https://www.googleapis.com/auth/spreadsheets"
		GoogleCredential credential = null;

		return new Sheets.Builder(httpTransport, jsonFactory, credential).setApplicationName(APPLICATION_NAME).build();
	}
}
