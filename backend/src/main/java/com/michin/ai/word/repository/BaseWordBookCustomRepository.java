package com.michin.ai.word.repository;

import com.michin.ai.word.model.Word;

public interface BaseWordBookCustomRepository {
	
	void addWord(String wordbook_id, Word word);
	
}
