package com.nklmthr.image.thisdaythatyear;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailSendMail extends Authenticator {
	public static void main(String[] args) {

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
			message.setFrom(new InternetAddress("niksrish.sync@gmail.com", "Nikhil"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("nklmthr@gmail.com"));
			message.setSubject("");
			message.setContent("", "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
