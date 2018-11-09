package test;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

public class SendEmail {

	static String username = "rahulzqwerty7@gmail.com";
	static String password = "08moth42";

	static String receiverEmail = "rahulzqwerty6@gmail.com";

	// static String ccReceivers = Commons.ccReceivers;//Commented this code to
	// block cc address

	public static void send(String subject, String errorMessage, String screenshot, String photoName) {

		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", true); // added this line
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);

		Session session = Session.getInstance(props, null);
		MimeMessage message = new MimeMessage(session);

		System.out.println("Port: " + session.getProperty("mail.smtp.port"));

		// Create the email addresses involved
		try {
			InternetAddress from = new InternetAddress("no-reply@abc.com");
			message.setSubject(subject);
			message.setFrom(from);
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
			/*
			 * String address = ccReceivers;//This code is used to add CC member while
			 * sending emails InternetAddress[] iAdressArray =
			 * InternetAddress.parse(address);
			 * message.setRecipients(Message.RecipientType.CC, iAdressArray);
			 */

			// Create a multi-part to combine the parts
			Multipart multipart = new MimeMultipart("alternative");

			// Create your text message part
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Script failure");

			// Add the text part to the multipart
			multipart.addBodyPart(messageBodyPart);

			// Code for attaching screenshot
			messageBodyPart = new MimeBodyPart();
			String filename = screenshot;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(photoName + ".png");
			multipart.addBodyPart(messageBodyPart);

			//MimeMultipart mmultipart = new MimeMultipart("related");
			BodyPart mmessageBodyPart = new MimeBodyPart();
			String htmlText = "<H1>Raghava chary</H1>" + "<img src=\"cid:"+screenshot+"\">";
			mmessageBodyPart.setContent(htmlText, "text/html");

			multipart.addBodyPart(mmessageBodyPart);
			// Create the html part
			messageBodyPart = new MimeBodyPart();
			String htmlMessage = errorMessage;
			messageBodyPart.setContent(htmlMessage, "text/html");

			
			// Add html part to multi part
			multipart.addBodyPart(messageBodyPart);

			// Associate multi-part with message
			message.setContent(multipart);

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", username, password);
			System.out.println("Transport: " + transport.toString());
			transport.sendMessage(message, message.getAllRecipients());

			System.out.println("Email successfully sent to: " + receiverEmail);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sendBulk(String bulkSenderEmail, String bulkSenderPassword, String bulkReceiver, String subject,
			String errorMessage, String ccReceivers) {

		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", true); // added this line
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.user", bulkSenderEmail);
		props.put("mail.smtp.password", bulkSenderPassword);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", true);

		Session session = Session.getInstance(props, null);
		MimeMessage message = new MimeMessage(session);

		System.out.println("Port: " + session.getProperty("mail.smtp.port"));

		// Create the email addresses involved
		try {
			InternetAddress from = new InternetAddress("no-reply@jenkins.com");
			message.setSubject(subject);
			message.setFrom(from);
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(bulkReceiver));
			if (ccReceivers.length() > 0 || ccReceivers.isEmpty() == false) {
				String address = ccReceivers;
				InternetAddress[] iAdressArray = InternetAddress.parse(address);
				message.setRecipients(Message.RecipientType.CC, iAdressArray);
			}

			// Create a multi-part to combine the parts
			Multipart multipart = new MimeMultipart("alternative");

			// Create your text message part
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Script failure");

			// Add the text part to the multipart
			multipart.addBodyPart(messageBodyPart);

			// Code for attaching screenshot

			// Create the html part
			messageBodyPart = new MimeBodyPart();
			String htmlMessage = errorMessage;
			messageBodyPart.setContent(htmlMessage, "text/html");

			// Add html part to multi part
			multipart.addBodyPart(messageBodyPart);

			// Associate multi-part with message
			message.setContent(multipart);

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", bulkSenderEmail, bulkSenderPassword);
			System.out.println("Transport: " + transport.toString());
			transport.sendMessage(message, message.getAllRecipients());

			System.out.println("Email successfully sent to: " + bulkReceiver);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
