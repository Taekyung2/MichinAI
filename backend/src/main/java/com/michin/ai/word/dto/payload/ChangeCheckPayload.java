package com.michin.ai.word.dto.payload;

import java.util.List;

import com.michin.ai.word.model.Word;

import lombok.Data;

@Data
public class ChangeCheckPayload {
	private String wordbook_id;
	private List<Word> words;
	
	public ChangeCheckCommand toCommand() {
		return new ChangeCheckCommand(wordbook_id, words);
	}
}
