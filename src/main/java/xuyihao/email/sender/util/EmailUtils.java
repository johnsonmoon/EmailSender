package xuyihao.email.sender.util;

import xuyihao.email.sender.entity.EmailCredential;
import xuyihao.email.sender.entity.EmailMultiMediaMessage;
import xuyihao.email.sender.entity.EmailTextMessage;
import xuyihao.email.sender.entity.dit.EmailProtocol;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <pre>
 *     Utilities for sending email message.
 *     To be continued.(Normal message sending & Message sending with files)
 * </pre>
 *
 * Created by xuyh at 2017/7/5 11:30.
 */
public class EmailUtils {
	/**
	 * Send one message to one receiver.
	 *
	 * @param host mail server address
	 * @param port mail server port
	 * @param protocol protocol for sending message
	 * @param user mail account
	 * @param password mail account authentication password
	 * @param from sender address
	 * @param to receiver address
	 * @param subject title for the message
	 * @param contentText content for the message
	 * @throws MessagingException
	 */
	public static void sendTextEmail(String host, int port, EmailProtocol protocol, String user, String password,
			String from, String to, String subject, String contentText) throws MessagingException {
		EmailCredential credential = new EmailCredential(host, port, protocol, user, password);
		EmailTextMessage textMessage = new EmailTextMessage(from, new String[] { to }, subject, contentText);
		Session session = createSession(credential);
		sendMessage(session, createTextMessage(session, textMessage), credential);
	}

	/**
	 * Send messages to many receivers.
	 *
	 * @param credential Credential information.
	 * @param textMessage Email text message.
	 * @throws MessagingException
	 */
	public static void sendTextEmail(EmailCredential credential, EmailTextMessage textMessage) throws MessagingException {
		Session session = createSession(credential);
		sendMessage(session, createTextMessage(session, textMessage), credential);
	}

	/**
	 * Send multimedia message to many receivers.
	 * 
	 * @param credential Credential information.
	 * @param multiMediaMessage Email multimedia message.
	 * @throws MessagingException
	 */
	public static void sendMultiMediaEmail(EmailCredential credential, EmailMultiMediaMessage multiMediaMessage)
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
	private static Message createTextMessage(Session session, EmailTextMessage textMessage) throws MessagingException {
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
	private static Message createMultiMediaMessage(Session session, EmailMultiMediaMessage multiMediaMessage)
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
		for (EmailMultiMediaMessage.ContentFile contentFile : multiMediaMessage.getContentFileList()) {
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
	private static void sendMessage(Session session, Message message, EmailCredential credential)
			throws MessagingException {
		Transport tran = session.getTransport();
		tran.connect(credential.getHost(), credential.getPort(), credential.getUser(), credential.getPassword());//邮箱服务器
		tran.sendMessage(message, message.getAllRecipients());//设置邮件接收人
		tran.close();
	}

	/**
	 * Create session of email connection.
	 * 
	 * @param credential
	 * @return
	 */
	private static Session createSession(EmailCredential credential) {
		Properties properties = new Properties();
		switch (credential.getProtocol()) {
		case IMAP:
			properties.setProperty("mail.store.protocol", EmailProtocol.IMAP.getName());
			properties.setProperty("mail.imap.host", credential.getHost());
			properties.setProperty("mail.imap.port", String.valueOf(credential.getPort()));
			break;
		case POP3:
			properties.setProperty("mail.store.protocol", EmailProtocol.POP3.getName());
			properties.setProperty("mail.pop3.host", credential.getHost());
			properties.setProperty("mail.pop3.port", String.valueOf(credential.getPort()));
			break;
		case SMTP:
			properties.setProperty("mail.transport.protocol", EmailProtocol.SMTP.getName());
			properties.setProperty("mail.smtp.auth", "true");
			break;
		}
		return Session.getInstance(properties);
	}
}
