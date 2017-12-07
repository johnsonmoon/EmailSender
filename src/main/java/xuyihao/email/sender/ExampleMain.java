package xuyihao.email.sender;

import xuyihao.email.sender.core.EmailSender;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by xuyh at 2017/7/5 10:26.
 */
public class ExampleMain {
	public static void main(String... args) throws Exception {
		test1();
	}

	private static void test1() throws Exception {
		EmailSender.send(
				EmailSender.createCredential("host", 25, "user", "password"),
				EmailSender.createTextMessage("from", new String[] { "to" }, "subject", "content"));

		EmailSender.send(
				EmailSender.createCredential("host", 25, "user", "password"),
				EmailSender.createMultiMediaMessage("from", new String[] { "to" }, "subject",
						Arrays.asList("attachFilepathName1", "attachFilePath2"),
						"contentText", new ArrayList<>()));
	}
}
