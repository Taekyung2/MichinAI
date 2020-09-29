package com.michin.ai.word.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddWordCommand {
	private String eng;
	private String kor;
	private String wordbook_id;
}
