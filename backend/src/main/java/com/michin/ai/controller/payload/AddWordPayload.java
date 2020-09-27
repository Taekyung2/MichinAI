package com.michin.ai.controller.payload;

import com.michin.ai.controller.command.AddWordCommand;

import lombok.Data;

@Data
public class AddWordPayload {
	private String wordbook_id;
	private String eng;
	private String kor;
	
	public AddWordCommand toCommand() {
		return new AddWordCommand(eng, kor, wordbook_id);
	}
}
