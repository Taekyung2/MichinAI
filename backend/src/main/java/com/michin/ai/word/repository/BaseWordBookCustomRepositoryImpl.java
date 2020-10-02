package com.michin.ai.word.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.michin.ai.word.model.Basewordbook;
import com.michin.ai.word.model.Word;

public class BaseWordBookCustomRepositoryImpl implements BaseWordBookCustomRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
		
	@Override
	public void addWord(String wordbook_id, Word word) {
		Query query = new Query(Criteria.where("id").is(wordbook_id));
		Update update = new Update().addToSet("words", word);
		mongoTemplate.findAndModify(query,  update,
				FindAndModifyOptions.options().upsert(true),Basewordbook.class);
	}
	
}
