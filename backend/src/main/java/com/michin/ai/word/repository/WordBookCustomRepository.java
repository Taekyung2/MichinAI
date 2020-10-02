package com.michin.ai.word.repository;

import java.util.List;

import com.michin.ai.word.model.Word;
import com.michin.ai.word.model.Wordbook;

public interface WordBookCustomRepository {
	
	List<Wordbook> customfindAll();
	
	void addWord(Word word, String wordbook_id);
	
	Wordbook findByNameAndUserId(Long user_id, String name);
	
	void delWord(String wordbook_id, String word_id);
	
	void changeCheck(String wordbook_id, Word word);
	
	void changeContent(String wordbook_id, Word word);
}
