package xuyihao.email.sender.core;

import xuyihao.email.sender.entity.Credential;
import xuyihao.email.sender.entity.send.ContentFile;
import xuyihao.email.sender.entity.send.Message;
import xuyihao.email.sender.entity.send.MultiMediaMessage;
import xuyihao.email.sender.entity.send.TextMessage;
import xuyihao.email.sender.util.SMTPUtils;

import javax.mail.MessagingException;
import java.util.List;

/**
 * email sender
 * <p>
 * Created by xuyh at 2017/12/7 14:21.
 */
public class EmailSender {
	/**
	 * get email sending verify object (Credential object)
	 *
	 * @param host     mail host address
	 * @param port     mail host port
	 * @param user     mail user name
	 * @param password mail user password
	 * @return Credential object
	 */
	public static Credential createCredential(String host, int port, String user, String password) {
		return new Credential(host, port, user, password);
	}

	/**
	 * get email sending normal text message object (TextMessage object)
	 *
	 * @param from        sender address
	 * @param to          receiver addresses
	 * @param subject     subject of the mail message
	 * @param contentText text content of the mail message
	 * @return TextMessage object
	 */
	public static TextMessage createTextMessage(String from, String[] to, String subject, String contentText) {
		return new TextMessage(from, to, subject, contentText);
	}

	/**
	 * get email sending multimedia message object
	 *
	 * @param from                   sender address
	 * @param to                     receiver addresses
	 * @param subject                subject of the mail message
	 * @param attachFilePathNameList attached file path names of the attached files
	 * @param contentText            text content of the mail message
	 * @param contentFileList        content file list of the content files
	 * @return MultiMediaMessage object
	 */
	public static MultiMediaMessage createMultiMediaMessage(String from, String[] to, String subject,
			List<String> attachFilePathNameList,
			String contentText, List<ContentFile> contentFileList) {
		return new MultiMediaMessage(from, to, subject, attachFilePathNameList, contentText, contentFileList);
	}

	/**
	 * send email
	 *
	 * @param credential Credential object
	 * @param message    Message object [TextMessage, MultiMediaMessage]
	 */
	public static void send(Credential credential, Message message) throws MessagingException {
		if (message instanceof TextMessage) {
			SMTPUtils.sendTextEmail(credential, (TextMessage) message);
		} else if (message instanceof MultiMediaMessage) {
			SMTPUtils.sendMultiMediaEmail(credential, (MultiMediaMessage) message);
		}
	}
}
