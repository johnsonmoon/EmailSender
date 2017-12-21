package xuyihao.email.sender.util;

import xuyihao.email.sender.entity.Credential;
import xuyihao.email.sender.entity.send.ContentFile;
import xuyihao.email.sender.entity.send.MultiMediaMessage;
import xuyihao.email.sender.entity.send.TextMessage;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <pre>
 *     Utilities for sending email message.
 *     To be continued.(Normal message sending & Message sending with files)
 * </pre>
 * <p>
 * Created by xuyh at 2017/7/5 11:30.
 */
public class SMTPUtils {
	/**
	 * Send messages to many receivers.
	 *
	 * @param credential  Credential information.
	 * @param textMessage Email text message.
	 * @throws MessagingException
	 */
	public static void sendTextEmail(Credential credential, TextMessage textMessage) throws MessagingException {
		Session session = createSession(credential);
		sendMessage(session, createTextMessage(session, textMessage), credential);
	}

	/**
	 * Send multimedia message to many receivers.
	 *
	 * @param credential        Credential information.
	 * @param multiMediaMessage Email multimedia message.
	 * @throws MessagingException
	 */
	public static void sendMultiMediaEmail(Credential credential, MultiMediaMessage multiMediaMessage)
			throws MessagingException {
		Session session = createSession(credential);
		sendMessage(session, createMultiMediaMessage(session, multiMediaMessage), credential);
	}

	/**
	 * Generate text message for sending.
	 *
	 * @param session
	 * @param textMessage
	 * @return
	 * @throws MessagingException
	 */
	private static Message createTextMessage(Session session, TextMessage textMessage) throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(textMessage.getFrom()));//设置发送人
		message.setRecipient(Message.RecipientType.CC, new InternetAddress(textMessage.getFrom()));
		InternetAddress[] internetAddresses = new InternetAddress[textMessage.getTo().length];
		for (int i = 0; i < textMessage.getTo().length; i++) {
			internetAddresses[i] = new InternetAddress(textMessage.getTo()[i]);
		}
		message.setRecipients(Message.RecipientType.TO, internetAddresses);
		message.setSubject(textMessage.getSubject());
		message.setContent(textMessage.getContentText(), "text/html;charset=utf-8");
		message.saveChanges();
		return message;
	}

	/**
	 * Generate multimedia message for sending.
	 *
	 * @param session
	 * @param multiMediaMessage
	 * @return
	 * @throws MessagingException
	 */
	private static Message createMultiMediaMessage(Session session, MultiMediaMessage multiMediaMessage)
			throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(multiMediaMessage.getFrom()));
		message.setRecipient(Message.RecipientType.CC, new InternetAddress(multiMediaMessage.getFrom()));
		InternetAddress[] internetAddresses = new InternetAddress[multiMediaMessage.getTo().length];
		for (int i = 0; i < multiMediaMessage.getTo().length; i++) {
			internetAddresses[i] = new InternetAddress(multiMediaMessage.getTo()[i]);
		}
		message.setRecipients(Message.RecipientType.TO, internetAddresses);
		message.setSubject(multiMediaMessage.getSubject());
		//添加附件
		List<MimeBodyPart> attachmentFilePartList = new ArrayList<>();
		for (String attachmentFilePath : multiMediaMessage.getAttachFilePathNameList()) {
			MimeBodyPart attachmentFilePart = new MimeBodyPart();
			FileDataSource fileDataSource = new FileDataSource(attachmentFilePath);
			attachmentFilePart.setDataHandler(new DataHandler(fileDataSource));
			attachmentFilePart.setFileName(fileDataSource.getName());
			attachmentFilePartList.add(attachmentFilePart);
		}
		//正文
		MimeBodyPart contentPart = new MimeBodyPart();
		//正文文本部分
		MimeMultipart contentMultiPart = new MimeMultipart();
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(multiMediaMessage.getContentText(), "text/html;charset=utf-8");
		contentMultiPart.addBodyPart(textBody);
		//正文图片部分
		for (ContentFile contentFile : multiMediaMessage.getContentFileList()) {
			MimeBodyPart fileBody = new MimeBodyPart();
			FileDataSource fileDataSource1 = new FileDataSource(contentFile.getContentFilePathName());
			fileBody.setDataHandler(new DataHandler(fileDataSource1));
			fileBody.setContentID(contentFile.getContentId());
			contentMultiPart.addBodyPart(fileBody);
		}
		//正文
		contentPart.setContent(contentMultiPart);
		//组装邮件
		MimeMultipart allPart = new MimeMultipart("mixed");
		allPart.addBodyPart(contentPart);
		for (MimeBodyPart attachmentPart : attachmentFilePartList) {
			allPart.addBodyPart(attachmentPart);
		}
		//形成邮件消息
		message.setContent(allPart);
		message.saveChanges();
		return message;
	}

	/**
	 * Send email message.
	 *
	 * @param session
	 * @param message
	 * @param credential
	 * @throws MessagingException
	 */
	private static void sendMessage(Session session, Message message, Credential credential)
			throws MessagingException {
		Transport tran = session.getTransport();
		tran.connect(credential.getHost(), credential.getPort(), credential.getUser(), credential.getPassword());//邮箱服务器
		tran.sendMessage(message, message.getAllRecipients());//设置邮件接收人
		tran.close();
	}

	/**
	 * Create smtp session of email connection.
	 *
	 * @param credential
	 * @return
	 */
	private static Session createSession(Credential credential) {
		return Session.getInstance(createProperties(credential));
	}

	@SuppressWarnings("restriction")
	private static Properties createProperties(Credential credential) {
		Properties properties = new Properties();
		switch (credential.getPort()) {
		case 25://Normal way of sending email with SMTP protocol
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.smtp.auth", "true");
			break;
		case 465://SSL way of sending email with SMTP protocol
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.setProperty("mail.smtp.socketFactory.fallback", "false");
			properties.setProperty("mail.smtp.port", String.valueOf(credential.getPort()));
			properties.setProperty("mail.smtp.socketFactory.port", String.valueOf(credential.getPort()));
			properties.setProperty("mail.smtp.auth", "true");
			break;
		default:
			properties.setProperty("mail.transport.protocol", "smtp");
			properties.setProperty("mail.smtp.auth", "true");
		}
		return properties;
	}
}
