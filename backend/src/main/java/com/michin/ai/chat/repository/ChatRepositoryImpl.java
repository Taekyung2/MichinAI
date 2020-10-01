package com.michin.ai.chat.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import com.michin.ai.chat.dto.ChatsDeletePayload;
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

	@Override
	public void deleteChatsByIds(ChatsDeletePayload payload) {
		Query query = new Query(Criteria.where("chats.id").in(payload.getIds()));
		Update update = new Update().pull("chats", Query.query(Criteria.where("id").in(payload.getIds())));
		mongoTemplate.updateMulti(query, update, ChatList.class);
	}
}
