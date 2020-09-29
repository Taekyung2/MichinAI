package com.michin.ai.word.dto.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddWordCommand {
	private String wordbook_id;
	private List<Word_content> contents;
}
