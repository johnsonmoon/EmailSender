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
		test3();
	}

	public static void test1() throws Exception {
		EmailCredential credential = new EmailCredential("", 25, EmailProtocol.SMTP, "",
				"");
		EmailTextMessage textMessage = new EmailTextMessage("", new String[] { "" },
				"", verifyInformation);
		EmailUtils.sendTextEmail(credential, textMessage);
	}

	public static void test3() throws Exception {
		EmailCredential credential = new EmailCredential("", 25, EmailProtocol.IMAP, "", "");
		EmailMultiMediaMessage multiMediaMessage = new EmailMultiMediaMessage();
		multiMediaMessage.setFrom("");
		multiMediaMessage.setTo(new String[] { "" });
		multiMediaMessage.setSubject("Test multimedia email...");
		multiMediaMessage.setContentText("");
		multiMediaMessage.addContentFile("", "");
		multiMediaMessage.addAttachFile("");
		EmailUtils.sendMultiMediaEmail(credential, multiMediaMessage);
	}
}
