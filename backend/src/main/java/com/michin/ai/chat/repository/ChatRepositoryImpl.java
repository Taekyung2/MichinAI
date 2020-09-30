package com.michin.ai.chat.repository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.michin.ai.chat.model.Chat;
import com.michin.ai.chat.model.ChatList;

public class ChatRepositoryImpl implements ChatRepositoryCustom {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addChat(String userId, Chat chat) {
		mongoTemplate.findAndModify(
				new Query(new Criteria().andOperator(Criteria.where("userId").is(userId),
						Criteria.where("date").is(LocalDate.now()))),
				new Update().addToSet("chats", chat), FindAndModifyOptions.options().upsert(true), ChatList.class);
	}

}
