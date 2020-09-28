package com.michin.ai.word.service;

import java.util.List;


import com.michin.ai.word.dto.payload.AddWordCommand;
import com.michin.ai.word.dto.payload.CreateWordbookCommand;
import com.michin.ai.word.dto.payload.EditNameCommand;
import com.michin.ai.word.model.Wordbook;

public interface WordService {
	
	List<Wordbook> getWordbook(String user_id);
	
	Wordbook createWordbook(CreateWordbookCommand command);
	
	Wordbook addWord(AddWordCommand command);
	
	Wordbook editName(EditNameCommand command);
	
	Wordbook delWord(String wordbook_id, String word_id);
	
	Wordbook changeCheck(String wordbook_id, String word_id);
	
	List<Wordbook> deleteWordbook(String wordbook_id);
}