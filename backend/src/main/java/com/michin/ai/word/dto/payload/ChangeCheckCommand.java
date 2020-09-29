package com.michin.ai.word.dto.payload;

import java.util.List;

import com.michin.ai.word.model.Word;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeCheckCommand {
	public String wordbook_id;
	public List<Word> words;
}
