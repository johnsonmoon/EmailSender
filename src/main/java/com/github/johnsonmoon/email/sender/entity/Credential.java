package com.github.johnsonmoon.email.sender.entity;

/**
 * Credentials for sending email.
 * <p>
 * Created by xuyh at 2017/7/6 11:49.
 */
public class Credential {
	/**
	 * email server address
	 */
	private String host;
	/**
	 * email server port number
	 */
	private int port;
	/**
	 * user account for authentication
	 */
	private String user;
	/**
	 * user password for authentication
	 */
	private String password;

	public Credential() {
	}

	public Credential(String host, int port, String user, String password) {
		this.host = host;
		this.port = port;
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
