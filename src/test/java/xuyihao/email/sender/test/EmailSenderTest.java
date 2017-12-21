package xuyihao.email.sender.test;

import xuyihao.email.sender.core.EmailSender;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

/**
 * Created by xuyh at 2017/7/5 10:26.
 */
public class EmailSenderTest {
	@Test
	public void testNormalEmailSending() throws Exception {
		EmailSender.send(
				EmailSender.createCredential("host", 25, "user", "password"),
				EmailSender.createTextMessage("from", new String[] { "to" }, "subject", "content"));

		EmailSender.send(
				EmailSender.createCredential("host", 25, "user", "password"),
				EmailSender.createMultiMediaMessage("from", new String[] { "to" }, "subject",
						Arrays.asList("attachFilepathName1", "attachFilePath2"),
						"contentText", new ArrayList<>()));
	}

	@Test
	public void testSSLEmailSending() throws Exception {
		EmailSender.send(
				EmailSender.createCredential("host", 25, "user", "password"),
				EmailSender.createTextMessage("from", new String[] { "to" }, "subject", "content"));
	}
}
