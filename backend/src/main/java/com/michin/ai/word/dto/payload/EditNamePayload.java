package com.michin.ai.word.dto.payload;

import lombok.Data;

@Data
public class EditNamePayload {
	private String wordbook_id;
	private String name;
	
	public EditNameCommand toCommand() {
		return new EditNameCommand(wordbook_id, name);
	}
}