package xuyihao.email.sender.entity;

import xuyihao.email.sender.entity.dict.EmailProtocol;

/**
 * Credentials for sending email.
 *
 * Created by xuyh at 2017/7/6 11:49.
 */
public class EmailCredential {
	/**
	 * email server address
	 */
	private String host;
	/**
	 * email server port number
	 */
	private int port;
	/**
	 * email server protocol choose (smtp, pop3, etc.)
	 */
	private EmailProtocol protocol;
	/**
	 * user account for authentication
	 */
	private String user;
	/**
	 * user password for authentication
	 */
	private String password;

	public EmailCredential() {
	}

	public EmailCredential(String host, int port, EmailProtocol protocol, String user, String password) {
		this.host = host;
		this.port = port;
		this.protocol = protocol;
		this.user = user;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public EmailProtocol getProtocol() {
		return protocol;
	}

	public void setProtocol(EmailProtocol protocol) {
		this.protocol = protocol;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
