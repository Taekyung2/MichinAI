package com.michin.ai.word.dto.payload;

import lombok.Data;

@Data
public class CreateWordbookPayload {
	private String user_id;
	private String name;
	
	public CreateWordbookCommand toCommand() {
		return new CreateWordbookCommand(user_id, name);
	}
}
