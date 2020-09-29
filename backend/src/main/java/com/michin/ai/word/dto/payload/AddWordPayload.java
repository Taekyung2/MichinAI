package com.michin.ai.word.dto.payload;

import java.util.List;

import lombok.Data;

@Data
public class AddWordPayload {
	private String wordbook_id;
	List<Word_content> contents;
	
	public AddWordCommand toCommand() {
		return new AddWordCommand(wordbook_id, contents);
	}
}
