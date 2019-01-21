package com.nklmthr.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.MediaInfo;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.itextpdf.text.log.SysoCounter;

public class TestDropBox {
	private static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
	private static DbxClientV2 client = new DbxClientV2(config,
			"JJCo-IawnzAAAAAAAAKZ1ZuFRouPjVPxsolv09jmHPop6DtwEoPaz4aWS-cIAQ2J");

	public static void main(String[] args) throws Exception {

		// Get current account info
		FullAccount account = client.users().getCurrentAccount();
		System.out.println(account.getName().getDisplayName());
		List<String> results = walk("/nmathur/allway/hemanta/NikSrish", true);
		results.stream().forEach(s -> System.out.println(s));

	}

	public static List<String> walk(String path, boolean listDirectoriesOnly) throws Exception {
		List<String> results = new ArrayList<String>();
		try {
			int directories = 0;

			ListFolderResult result = client.files().listFolderBuilder(path).withIncludeDeleted(false)
					.withRecursive(true).withIncludeMediaInfo(true).start();
			while (true) {
				List<Metadata> entries = result.getEntries();
				String currentDirectory = null;
				int files = 0;
				for (Metadata metadata : entries) {
					if (metadata instanceof FolderMetadata && listDirectoriesOnly) {
						System.out.println(++directories + "Scanning folder=" + metadata.getPathDisplay());
						if (!metadata.getPathDisplay().equals(path)) {
							results.add(metadata.getPathDisplay());
						}

					} else if (metadata instanceof FileMetadata) {
						++files;
						MediaInfo info = ((FileMetadata) metadata).getMediaInfo();
						Date date = null;
						if (info != null) {
							date = info.getMetadataValue().getTimeTaken();
							if (date != null) {
								int todayMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
								int mediaDateMonth = date.getMonth() + 1;
								int todayDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1;
								int mediaDate = date.getDay() + 1;
								if (todayMonth == mediaDateMonth && todayDate == mediaDate) {
//									System.out.println("Match" + metadata.getPathDisplay() + " : "
//											+ info.getMetadataValue().getTimeTaken());
									checkFileDateTime(metadata.getPathDisplay());
								}
							}
						}
					}
				}
				if (result.getHasMore() || !results.isEmpty()) {
					currentDirectory = results.get(0);
					result = client.files().listFolderBuilder(currentDirectory).withIncludeDeleted(false).withRecursive(true)
							.withIncludeMediaInfo(true).start();
					results.remove(currentDirectory);
				} else {
					System.out.println("Listing:" + currentDirectory + " completed with:" + files + " files");
					break; // no more entries to process
				}
			}
		} catch (DbxException exception) {
			throw new Exception("Não foi possivel obter dados do diretório.");
		}
		return results;
	}

	private static void checkFileDateTime(String pathDisplay) {
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://content.dropboxapi.com/2/files/download");
			request.addHeader("Authorization",
					"Bearer JJCo-IawnzAAAAAAAAKZ1ZuFRouPjVPxsolv09jmHPop6DtwEoPaz4aWS-cIAQ2J");
			request.addHeader("Dropbox-API-Arg", "{\"path\": \"" + pathDisplay + "\"}");

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			int responseCode = response.getStatusLine().getStatusCode();
			//System.out.println("Dropbox: " + pathDisplay);
			// System.out.println("Response Code: " + responseCode);

			InputStream is = entity.getContent();

			String filePath = "/Users/i344377/Desktop" + pathDisplay;
			String directories = "/Users/i344377/Desktop" + pathDisplay.substring(0, pathDisplay.lastIndexOf("/"));
			File file = new File(directories);
			boolean result = file.mkdirs();
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			int inByte;
			while ((inByte = is.read()) != -1) {
				fos.write(inByte);
			}
			System.out.println("Success File:" + filePath);
			is.close();
			fos.close();
			client.close();
			// System.out.println("File Download Completed!!!");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
