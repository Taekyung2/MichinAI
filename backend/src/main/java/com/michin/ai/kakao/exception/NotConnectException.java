package com.michin.ai.kakao.exception;

import lombok.Getter;

@Getter
public class NotConnectException extends RuntimeException {
	private static final long serialVersionUID = 1000L;
	String userBotKey;

	public NotConnectException(String userBotKey) {
		super();
		this.userBotKey = userBotKey;
	}

}