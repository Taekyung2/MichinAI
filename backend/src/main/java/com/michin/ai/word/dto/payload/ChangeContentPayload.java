package com.michin.ai.word.dto.payload;

import java.util.List;

import com.michin.ai.word.model.Word;

import lombok.Data;

@Data
public class ChangeContentPayload {
	private String wordbook_id;
	List<Word> words;
	
	public ChangeContentCommand toCommand() {
		return new ChangeContentCommand(wordbook_id, words);
	}
}
