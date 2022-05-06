package myfinance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SheetsQuickstart {
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = SheetsQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
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

	/**
	 * Prints the names and majors of students in a sample spreadsheet:
	 * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
	 * 
	 * @throws ParseException
	 */
	public static void main(String... args) throws IOException, GeneralSecurityException, ParseException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1OLHv4pd5UK-QI_kLpAePVPcXzZ8FO2d-84TM7GhO19c";
		final String range = "Summary!A2:K100";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		List<Transaction> transactions = new ArrayList<Transaction>();
		DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			// System.out.println("Name, Major");
			for (List row : values) {
				Transaction trans = new Transaction();
				Calendar cal = Calendar.getInstance();
				cal.setTime(format.parse(row.get(0).toString()));
				cal.add(Calendar.DATE, 1);
				trans.setDate(cal.getTime());
				trans.setActivity(row.get(1).toString());

				trans.setFi(row.get(2).toString());
				trans.setVehicle(row.get(3).toString());

				trans.setPrice(Double.valueOf(row.get(4).toString().replaceAll(",", "")));
				trans.setQuantity(Double.valueOf(row.get(5).toString().replaceAll(",", "")));

				trans.setPrice(Double.valueOf(row.get(6).toString().replaceAll(",", "")));
				trans.setEurToInrRate(Double.valueOf(row.get(7).toString().replaceAll(",", "")));

				trans.setValue(Double.valueOf(row.get(8).toString().replaceAll(",", "")));
				//System.out.println(trans);
				transactions.add(trans);

			}
		}
		doCalculations(transactions);
	}

	private static void doCalculations(List<Transaction> transactions) {
		Double match = 0.0;
		Double purchase = 0.0;
		Date startDate = new Date(120, 11, 30);
		for (Transaction t : transactions) {
			if (t.getDate().after(startDate)) {
				System.out.println("Including transaction for date" + t.getDate());
				if (t.getActivity().equalsIgnoreCase("Match")) {
					//System.out.println(t.getCurrPrice());
					match += t.getValue();
				} else if (t.getActivity().equalsIgnoreCase("Purchase")) {
					//System.out.println(t.getCurrPrice());
					purchase += t.getValue();
				}
			}
		}
		System.out.println("Match=" + match + ",purchase=" + purchase);

	}
}
