package com.michin.ai.word.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.michin.ai.word.model.Wordbook;

public class WordBookCustomRepositoryImpl implements WordBookCustomRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Wordbook> customfindAll() {
		return mongoTemplate.findAll(Wordbook.class);
	}

	@Override
	public Wordbook findByNameAndUserId(String user_id, String name) {
		Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(user_id).andOperator(Criteria.where("name").is(name)));
        return mongoTemplate.findOne(query,Wordbook.class);
	}
	
}
