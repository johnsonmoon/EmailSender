package xuyihao.email.sender.entity.send;

/**
 * Created by xuyh at 2017/7/6 17:03.
 */
public class Message {
	/**
	 * Email address for sender.
	 */
	private String from;
	/**
	 * Email address for receivers.
	 */
	private String[] to;
	/**
	 * Subject for the message sending.
	 */
	private String subject;

	public Message() {
	}

	public Message(String from, String[] to, String subject) {
		this.from = from;
		this.to = to;
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
