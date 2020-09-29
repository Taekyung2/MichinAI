package com.michin.ai.word.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.michin.ai.word.model.Word;
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
	
	@Override
	public void delWord(String wordbook_id, String word_id) {
		Query query = new Query(Criteria.where("id").is(wordbook_id));
		Update update = new Update().pull("words", Query.query(Criteria.where("id").is(word_id)));
		mongoTemplate.updateFirst(query, update, Wordbook.class);
	}
	
	
	@Override
	public void addWord(Word word, String wordbook_id) {
		Query query = new Query(Criteria.where("id").is(wordbook_id));
		Update update = new Update().addToSet("words", word);
		mongoTemplate.findAndModify(query, update,
				FindAndModifyOptions.options().upsert(true), Wordbook.class);
	}

	@Override
	public void changeCheck(String wordbook_id, Word word) {
		word.setCheck(!word.isCheck());
		Query query = new Query(new Criteria().andOperator(
								Criteria.where("id").is(wordbook_id),
								Criteria.where("words").elemMatch(Criteria.where("id").is(word.getId().toString()))));
		Update update = new Update().set("words.$", word);
		mongoTemplate.findAndModify(query, update,
				FindAndModifyOptions.options().upsert(true), Wordbook.class);
	}

	@Override
	public void changeContent(String wordbook_id, Word word) {
		Query query = new Query(new Criteria().andOperator(
				Criteria.where("id").is(wordbook_id),
				Criteria.where("words").elemMatch(Criteria.where("id").is(word.getId().toString()))));
		Update update = new Update().set("words.$", word);
		mongoTemplate.findAndModify(query, update,
				FindAndModifyOptions.options().upsert(true), Wordbook.class);
	}
	
}