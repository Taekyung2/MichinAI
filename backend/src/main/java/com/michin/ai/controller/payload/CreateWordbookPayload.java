package com.michin.ai.controller.payload;

import com.michin.ai.controller.command.CreateWordbookCommand;

import lombok.Data;

@Data
public class CreateWordbookPayload {
	private String user_id;
	
	public CreateWordbookCommand toCommand() {
		return new CreateWordbookCommand(user_id);
	}
}
