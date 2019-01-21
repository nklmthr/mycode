package com.nklmthr.projects.utilities.thisdaythatyearmedia.media;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.MediaInfo;
import com.dropbox.core.v2.files.Metadata;
import com.nklmthr.projects.utilities.thisdaythatyearmedia.content.MediaContainer;
import com.nklmthr.projects.utilities.thisdaythatyearmedia.content.MediaContent;
import com.nklmthr.projects.utilities.thisdaythatyearmedia.exception.MediaManagerException;

@Service
public class DropBoxMediaManager implements MediaManager {
	private static Logger logger = Logger.getLogger(DropBoxMediaManager.class);
	private static DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
	private static DbxClientV2 client = new DbxClientV2(config,
			"JJCo-IawnzAAAAAAAAKZ1ZuFRouPjVPxsolv09jmHPop6DtwEoPaz4aWS-cIAQ2J");

	@Value("${dropbox.media.home}")
	private String dropBoxMediaHome;

	@Value("${dropbox.media.spanInDays}")
	private String dropBoxSpanInDays;

	@Value("${dropbox.authorization.header}")
	private String dropBoxAuthorizationHeader;

	@Value("${dropbox.local.root}")
	private String localRoot;

	public List<MediaContainer> getMediaContainers() throws MediaManagerException {
		List<MediaContainer> directories = new ArrayList<MediaContainer>();
		List<MediaContainer> subDirectories = new ArrayList<MediaContainer>();
		int count = 0;
		try {
			logger.debug("dropBoxMediaHome=" + dropBoxMediaHome);
			subDirectories.add(new MediaContainer(dropBoxMediaHome));
			directories.addAll(subDirectories);
			while (subDirectories.size() > 0) {
				MediaContainer current = subDirectories.get(0);
				logger.info(++count + " Starting:" + current.getPath());
				ListFolderResult result;
				result = client.files().listFolder(current.getPath());
				List<Metadata> entries = result.getEntries();
				for (Metadata metadata : entries) {
					if (metadata instanceof FolderMetadata) {
						if (!metadata.getPathDisplay().equals(dropBoxMediaHome)) {
							subDirectories.add(new MediaContainer(metadata.getPathDisplay()));
							logger.debug(metadata.getPathDisplay());
							directories.add(new MediaContainer(metadata.getPathDisplay()));
						}
					}
				}
				subDirectories.remove(current);
			}
		} catch (DbxException e) {
			logger.error(e, e);
			throw new MediaManagerException(e.getMessage(), e);
		}
		return directories;
	}

	@Override
	public void filterFilesForTime(MediaContainer container) throws MediaManagerException {
		List<MediaContent> contents = new ArrayList<MediaContent>();
		try {
			ListFolderResult result;
			result = client.files().listFolderBuilder(container.getPath()).withIncludeDeleted(false)
					.withRecursive(false).withIncludeMediaInfo(true).start();
			for (Metadata metadata : result.getEntries()) {
				if (metadata instanceof FileMetadata && contents.size() < 2) {
					MediaInfo info = ((FileMetadata) metadata).getMediaInfo();
					Date date = null;
					if (info != null) {
						date = info.getMetadataValue().getTimeTaken();
						if (date != null) {
							if (compareDatesWithinRange(date)) {
								MediaContent content = new MediaContent();
								content.setPath(metadata.getPathDisplay());
								content.setContentDate(date);
								content.setLocalPath(localRoot);
								contents.add(content);
							}
						}
					}
				}
			}
		} catch (DbxException e) {
			logger.error(e, e);
			throw new MediaManagerException(e.getMessage(), e);
		}
		container.setContents(contents);
	}

	@Override
	public void porpogateMedia(MediaContainer container) throws MediaManagerException {
		for (MediaContent content : container.getContents()) {
			BufferedInputStream fin = null;
			BufferedOutputStream fout = null;
			CloseableHttpClient client = null;
			try {
				client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet("https://content.dropboxapi.com/2/files/download");
				request.addHeader("Authorization", dropBoxAuthorizationHeader);
				request.addHeader("Dropbox-API-Arg", "{\"path\": \"" + content.getPath() + "\"}");
				HttpResponse response = client.execute(request);
				HttpEntity entity = response.getEntity();
				int responseCode = response.getStatusLine().getStatusCode();
				logger.trace("Dropbox: path=" + container.getPath() + ",response Code: " + responseCode);
				File file = new File(content.getLocalPath());
				boolean createdDirectories = file.mkdirs();
				logger.trace("created directories" + createdDirectories);

				fin = new BufferedInputStream(entity.getContent());
				fout = new BufferedOutputStream(
						new FileOutputStream(content.getLocalPath() + content.getExifName() + content.getExtension()));
				byte[] buff = new byte[64 * 1024];
				int len;
				while ((len = fin.read(buff)) > 0)
					fout.write(buff, 0, len);
				logger.info("dropbox path=" + container.getPath() + ",response=" + responseCode + ", file="
						+ content.getLocalPath() + content.getExifName() + content.getExtension());
			} catch (ClientProtocolException e) {
				logger.error(e, e);
				throw new MediaManagerException(e.getMessage(), e);
			} catch (UnsupportedOperationException e) {
				logger.error(e, e);
				throw new MediaManagerException(e.getMessage(), e);
			} catch (IOException e) {
				logger.error(e, e);
				throw new MediaManagerException(e.getMessage(), e);
			} finally {
				try {
					fin.close();
					fout.close();
					client.close();
				} catch (IOException e) {
					logger.error(e, e);
					throw new MediaManagerException(e.getMessage(), e);
				}

			}
		}
	}

	private boolean compareDatesWithinRange(Date date) {
		int tolerance = Integer.valueOf(dropBoxSpanInDays);
		LocalDate mediaDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate now = LocalDate.now();
		LocalDate nowForward = LocalDate.of(mediaDate.getYear(), now.getMonth(), now.getDayOfMonth())
				.plusDays(tolerance);
		LocalDate nowBack = LocalDate.of(mediaDate.getYear(), now.getMonth(), now.getDayOfMonth()).minusDays(tolerance);
		if (mediaDate.isBefore(nowForward) && mediaDate.isAfter(nowBack))
			return true;
		return false;
	}

	public void sendMail(MediaContainer container) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("niksrish.sync@gmail.com", "piyuishi3008");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setSubject("This Day that year...");
			message.setFrom(new InternetAddress("niksrish.sync@gmail.com", "Nikhil"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("nklmthr@gmail.com"));
			// StringBuilder textContent = new StringBuilder();
			Multipart multipart = new MimeMultipart("related");
			for (MediaContent content : container.getContents()) {
				logger.info("Adding attachment to mail:" + content.getPath());
				StringBuilder textContent = new StringBuilder();
				textContent.append("<H1>Hello</H1><img src=\"" + content.getPath() + "\">");
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(textContent.toString(), "text/html");
				String file = content.getLocalPath() + content.getExifName() + content.getExtension();
				String fileName = content.getExifName();
				DataSource source = new FileDataSource(file);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(fileName);
				messageBodyPart.setHeader("Content-ID", "<" + content.getPath() + ">");
				multipart.addBodyPart(messageBodyPart);
			}
			// textContent.append("</table>");
			// message.setText(textContent.toString());
			message.setContent(multipart);
			logger.info("Start sending mail...");
			Transport.send(message);
			logger.info("Mail Sent...");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
