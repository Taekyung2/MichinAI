package com.michin.ai.word.service;

import java.util.List;

import com.michin.ai.word.dto.payload.AddWordCommand;
import com.michin.ai.word.dto.payload.ChangeCheckCommand;
import com.michin.ai.word.dto.payload.ChangeContentCommand;
import com.michin.ai.word.dto.payload.CreateWordbookCommand;
import com.michin.ai.word.dto.payload.EditNameCommand;
import com.michin.ai.word.model.Basewordbook;
import com.michin.ai.word.model.Wordbook;

public interface WordService {
	
	List<Wordbook> getWordbook(Long user_id);
	
	Wordbook getWordbook(Long user_id, String name);
	
	Wordbook createWordbook(CreateWordbookCommand command);
	
	Wordbook addWord(AddWordCommand command);
	
	Wordbook editName(EditNameCommand command);
	
	void delWord(String wordbook_id, String word_id);
	
	Wordbook changeCheck(ChangeCheckCommand command);
	
	List<Wordbook> deleteWordbook(String wordbook_id);
	
	Wordbook changeContent(ChangeContentCommand command);
	
	void addBasewordbook(int level);
	
	void addWordtoBasewordbook(AddWordCommand command);
	
	List<Basewordbook> getBasewordbook();
}