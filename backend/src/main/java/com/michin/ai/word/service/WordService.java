package com.michin.ai.word.service;

import java.util.List;

import com.michin.ai.controller.command.AddWordCommand;
import com.michin.ai.controller.command.CreateWordbookCommand;
import com.michin.ai.word.model.Wordbook;

public interface WordService {
	
	List<Wordbook> getWordbook(String user_id);
	
	Wordbook createWordbook(CreateWordbookCommand command);
	
	Wordbook addWord(AddWordCommand command);
	
//	List<BaseWordbook> 
}