package xuyihao.email.sender;

import xuyihao.email.sender.entity.EmailCredential;
import xuyihao.email.sender.entity.EmailMultiMediaMessage;
import xuyihao.email.sender.entity.EmailTextMessage;
import xuyihao.email.sender.entity.dit.EmailProtocol;
import xuyihao.email.sender.util.EmailUtils;

/**
 * Created by xuyh at 2017/7/5 10:26.
 */
public class Main {
	private static String verifyInformation = "Hello! Johnson!";

	public static void main(String... args) throws Exception {
		test1();
		test2();
		test3();
	}

	public static void test1() throws Exception {
		EmailCredential credential = new EmailCredential("", 25, EmailProtocol.SMTP, "",
				"");
		EmailTextMessage textMessage = new EmailTextMessage("", new String[] { "" },
				"", verifyInformation);
		EmailUtils.sendTextEmail(credential, textMessage);
	}

	public static void test2() throws Exception {
		EmailCredential credential = new EmailCredential("to be modified", 25, EmailProtocol.IMAP, "to be modified",
				"to be modified");
		EmailTextMessage textMessage = new EmailTextMessage("to be modified", new String[] { "to be modified" },
				"hello", verifyInformation);
		EmailUtils.sendTextEmail(credential, textMessage);
	}

	public static void test3() throws Exception {
		EmailCredential credential = new EmailCredential("to be modified", 25, EmailProtocol.IMAP, "to be modified",
				"to be modified");
		EmailMultiMediaMessage multiMediaMessage = new EmailMultiMediaMessage();
		multiMediaMessage.setFrom("to be modified");
		multiMediaMessage.setTo(new String[] { "to be modified" });
		multiMediaMessage.setSubject("Test multimedia email...");
		multiMediaMessage.setContentText("<html>\n" +
				"\t<head>\n" +
				"\t\t<title>内含附件,图文并茂的邮件测试</title>\n" +
				"\t</head>\n" +
				"\n" +
				"\t<body>\n" +
				"\t\t<pre>\n" +
				"\t\t\t你好\n" +
				"\t\t\t<img src = \"cid:photo1\">\n" +
				"\t\t\t<img src = \"cid:photo2\">\n" +
				"\t\t\t<img src = \"cid:photo3\">\n" +
				"\t\t</pre>\n" +
				"\t</body>\n" +
				"</html>");
		multiMediaMessage.addContentFile("photo1", "C:\\Users\\Administrator\\Desktop\\test.png");
		multiMediaMessage.addContentFile("photo2", "C:\\Users\\Administrator\\Desktop\\test2.png");
		multiMediaMessage.addContentFile("photo3", "C:\\Users\\Administrator\\Desktop\\test3.png");
		multiMediaMessage.addAttachFile("C:\\Users\\Administrator\\Desktop\\test.doc");
		multiMediaMessage.addAttachFile("C:\\Users\\Administrator\\Desktop\\test2.doc");
		multiMediaMessage.addAttachFile("C:\\Users\\Administrator\\Desktop\\test3.doc");
		EmailUtils.sendMultiMediaEmail(credential, multiMediaMessage);
	}
}
