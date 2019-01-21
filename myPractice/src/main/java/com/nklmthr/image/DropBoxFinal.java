package com.nklmthr.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.MediaInfo;
import com.dropbox.core.v2.files.Metadata;

public class DropBoxFinal {
	private static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
	private static DbxClientV2 client = new DbxClientV2(config,
			"JJCo-IawnzAAAAAAAAKZ1ZuFRouPjVPxsolv09jmHPop6DtwEoPaz4aWS-cIAQ2J");

	public static void main(String[] args) throws ListFolderErrorException, DbxException {
		String path = "/nmathur/allway/hemanta/NikSrish";
		List<String> directories = getDirectories(path);
		directories.stream().forEach(s -> System.out.println(s));
		// System.out.println("Total folders =" + directories.size());
		for (String directory : directories) {
			System.out.println("Scanning: " + directory);
			checkFilesDateAndCreate(directory);
		}
	}

	private static void checkFilesDateAndCreate(String directory) throws ListFolderErrorException, DbxException {
		// ListFolderResult result = client.files().listFolder(directory);
		ListFolderResult result = client.files().listFolderBuilder(directory).withIncludeDeleted(false)
				.withRecursive(false).withIncludeMediaInfo(true).start();
		for (Metadata metadata : result.getEntries()) {
			if (metadata instanceof FileMetadata) {
				MediaInfo info = ((FileMetadata) metadata).getMediaInfo();
				Date date = null;
				if (info != null) {
					date = info.getMetadataValue().getTimeTaken();
					if (date != null) {
						if (compareDatesWithinRange(date)) {
							checkFileDateTime(metadata.getPathDisplay(), date);
						}
					}
				}
			}
		}
	}

	private static boolean compareDatesWithinRange(Date date) {
		int tolerance = 15;
		LocalDate mediaDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = LocalDate.now();
		LocalDate nowForward = LocalDate.of(mediaDate.getYear(), now.getMonth(), now.getDayOfMonth())
				.plusDays(tolerance);
		LocalDate nowBack = LocalDate.of(mediaDate.getYear(), now.getMonth(), now.getDayOfMonth()).minusDays(tolerance);
		if (mediaDate.isBefore(nowForward) && mediaDate.isAfter(nowBack))
			return true;
		return false;
	}

	private static void checkFileDateTime(String pathDisplay, Date date) {
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://content.dropboxapi.com/2/files/download");
			request.addHeader("Authorization",
					"Bearer JJCo-IawnzAAAAAAAAKZ1ZuFRouPjVPxsolv09jmHPop6DtwEoPaz4aWS-cIAQ2J");
			request.addHeader("Dropbox-API-Arg", "{\"path\": \"" + pathDisplay + "\"}");

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			int responseCode = response.getStatusLine().getStatusCode();
			// System.out.println("Dropbox: " + pathDisplay);
			// System.out.println("Response Code: " + responseCode);

			InputStream is = entity.getContent();

			String filePath = "/Users/i344377/Desktop" + pathDisplay;
			String directories = "/Users/i344377/Desktop" + pathDisplay.substring(0, pathDisplay.lastIndexOf("/"));
			File file = new File(directories);
			boolean result = file.mkdirs();
			String ext = pathDisplay.substring(pathDisplay.lastIndexOf("."), pathDisplay.length());
			LocalDateTime mediaDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			FileOutputStream fos = new FileOutputStream(new File(directories + "/" + mediaDate.getYear() + "-"
					+ String.format("%02d",mediaDate.getMonth().getValue()) + "-"
					+ String.format("%02d", mediaDate.getDayOfMonth()) + " "
					+ String.format("%02d", mediaDate.getHour()) + "." 
					+ String.format("%02d", mediaDate.getMinute())
					+ ext));
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

	private static List<String> getDirectories(String path) throws ListFolderErrorException, DbxException {
		List<String> directories = new ArrayList<String>();
		List<String> subDirectories = new ArrayList<String>();
		int count = 0;
		subDirectories.add(path);
		while (subDirectories.size() > 0) {
			String current = subDirectories.get(0);
			System.out.println(++count + " Checking:" + current);
			ListFolderResult result = client.files().listFolder(current);
			List<Metadata> entries = result.getEntries();
			for (Metadata metadata : entries) {
				if (metadata instanceof FolderMetadata) {
					if (!metadata.getPathDisplay().equals(path)) {
						// System.out.println("Add:" +
						// metadata.getPathDisplay());
						directories.add(metadata.getPathDisplay());
						subDirectories.add(metadata.getPathDisplay());
					}
				}
			}
			subDirectories.remove(current);
		}
		return directories;
	}

}
