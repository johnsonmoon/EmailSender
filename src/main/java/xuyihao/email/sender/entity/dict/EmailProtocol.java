package xuyihao.email.sender.entity.dict;

/**
 * Created by xuyh at 2017/7/5 11:39.
 */
public enum EmailProtocol {
	SMTP("smtp"), POP3("pop3"), IMAP("imap");

	/**
	 * 协议名称
	 */
	private String name;

	EmailProtocol(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
