package code.project.india.projects.ppp;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class TransactionUploader {

	static String[] files = new String[] { "Home Expense 2022 - Apr.csv", "Home Expense 2022 - August.csv",
			"Home Expense 2022 - Jul.csv", "Home Expense 2022 - Jun.csv", "Home Expense 2022 - May.csv",
			"Home Expense 2022 - October.csv", "Home Expense 2022 - September.csv" };

	public static void main(String[] args) throws Exception {
		for (String file : files) {
			System.out.println("Starting file" + file);
			Path path = Paths.get(ClassLoader.getSystemResource(file).toURI());
			List<Transaction> transactions = new ArrayList<Transaction>();
			transactions.addAll(readAllLines(path));
			//System.out.println(transactions);
			// Thread.sleep(15000);
			updateTOdatabase(transactions);
		}

	}

	private static void updateTOdatabase(List<Transaction> transactions) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfinance", "root", "adapter");
			// here sonoo is database name, root is username and password
			PreparedStatement stmt = con.prepareStatement("INSERT INTO `myfinance`.`transaction`\r\n" + "("
					+ "`date`,\r\n" + "`account_id`,\r\n" + "`category_id`,\r\n" + "`is_debit`,\r\n" + "`amount`,\r\n"
					+ "`description`)\r\n" + "VALUES\r\n"

					+ "(?,?,?,?,?,?"

					+ ");\r\n");
			// Transaction transaction = transactions.get(0);
			for (Transaction transaction : transactions) {
				Date date = transaction.getDate();
				date.setYear(122);
				transaction.setDate(date);
				stmt.setDate(1, transaction.getDate());
				stmt.setInt(2, transaction.getAccountId());
				stmt.setInt(3, transaction.getCategoryID());
				stmt.setInt(4, 0);
				stmt.setDouble(5, transaction.getAmount());
				stmt.setString(6, transaction.getDescription());
				System.out.println(stmt);
				stmt.execute();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Transaction> readAllLines(Path filePath) throws Exception {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try (Reader reader = Files.newBufferedReader(filePath)) {
			CsvToBean<Transaction> cb = new CsvToBeanBuilder<Transaction>(reader).withIgnoreQuotations(false)
					.withOrderedResults(true).withType(Transaction.class).build();
			Iterator<Transaction> itr = cb.iterator();
			while (itr != null && itr.hasNext()) {
				transactions.add(itr.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactions;
	}

}
