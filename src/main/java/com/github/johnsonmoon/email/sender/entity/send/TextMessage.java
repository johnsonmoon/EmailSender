package com.github.johnsonmoon.email.sender.entity.send;

/**
 * Message details for sending email.(Text message)
 *
 * Created by xuyh at 2017/7/6 11:43.
 */
public class TextMessage extends Message {
	/**
	 * Text content for the message sending.
	 */
	private String contentText;

	public TextMessage() {
		super();
	}

	public TextMessage(String from, String[] to, String subject, String contentText) {
		super(from, to, subject);
		this.contentText = contentText;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
}
