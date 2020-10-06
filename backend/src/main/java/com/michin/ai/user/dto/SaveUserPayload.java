package com.michin.ai.user.dto;

import lombok.Data;

@Data
public class SaveUserPayload {
	private long userId;
	private String userName;
	private String userBotKey;
	
	public SaveUserCommand toCommand() {
		return new SaveUserCommand(userId, userName, userBotKey);
	}
	

}
