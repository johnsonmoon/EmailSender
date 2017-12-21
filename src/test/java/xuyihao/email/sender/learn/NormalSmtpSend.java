package xuyihao.email.sender.learn;

import org.junit.Test;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by xuyh at 2017/12/21 9:48.
 */
public class NormalSmtpSend {
	@Test
	public void test() {
		String host = "smtp.host.com";
		int port = 25;
		String user = "yourEmailAddress@xx.com";
		String password = "password";
		String receiver = "xxx@xx.com";
		String subject = "Test normal way of sending email...";
		String content = "Succeeded!";

		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipient(Message.RecipientType.CC, new InternetAddress(user));
			InternetAddress[] internetAddresses = new InternetAddress[] { new InternetAddress(receiver) };
			message.setRecipients(Message.RecipientType.TO, internetAddresses);
			message.setSubject(subject);
			message.setContent(content, "text/html;charset=utf-8");
			message.saveChanges();

			Transport tran = session.getTransport();
			tran.connect(host, port, user, password);
			tran.sendMessage(message, internetAddresses);
			tran.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
