package com.michin.ai.word.service;

import java.util.List;

import com.michin.ai.word.dto.payload.AddWordCommand;
import com.michin.ai.word.dto.payload.ChangeCheckCommand;
import com.michin.ai.word.dto.payload.ChangeContentCommand;
import com.michin.ai.word.dto.payload.CreateWordbookCommand;
import com.michin.ai.word.dto.payload.EditNameCommand;
import com.michin.ai.word.model.Wordbook;

public interface WordService {
	
	List<Wordbook> getWordbook(String user_id);
	
	Wordbook getWordbook(String user_id, String name);
	
	Wordbook createWordbook(CreateWordbookCommand command);
	
	Wordbook addWord(AddWordCommand command);
	
	Wordbook editName(EditNameCommand command);
	
	void delWord(String wordbook_id, String word_id);
	
	Wordbook changeCheck(ChangeCheckCommand command);
	
	List<Wordbook> deleteWordbook(String wordbook_id);
	
	Wordbook changeContent(ChangeContentCommand command);
	
}