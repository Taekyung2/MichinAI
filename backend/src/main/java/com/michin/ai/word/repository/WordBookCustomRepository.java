package com.michin.ai.word.repository;

import java.util.List;

import com.michin.ai.word.model.Wordbook;

public interface WordBookCustomRepository {
	
	List<Wordbook> customfindAll();
	
	Wordbook findByNameAndUserId(String user_id, String name);
}
