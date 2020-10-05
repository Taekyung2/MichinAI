package com.michin.ai.chat.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Async;

import com.michin.ai.chat.dto.ChatsDeletePayload;
import com.michin.ai.chat.model.Chat;
import com.michin.ai.chat.model.ChatList;

public class ChatRepositoryImpl implements ChatRepositoryCustom {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addChat(String userBotKey, Chat chat) {
		mongoTemplate.findAndModify(
				new Query(new Criteria().andOperator(Criteria.where("userBotKey").is(userBotKey),
						Criteria.where("date").is(LocalDate.now()))),
				new Update().addToSet("chats", chat), FindAndModifyOptions.options().upsert(true), ChatList.class);
	}

	@Override
	public void deleteChatsByIds(ChatsDeletePayload payload) {
		Query query = new Query(Criteria.where("chats.id").in(payload.getIds()));
		Update update = new Update().pull("chats", query);
		System.out.println(update);
		System.out.println(mongoTemplate.find(query, ChatList.class));
		System.out.println(mongoTemplate.upsert(query, update, ChatList.class));
	}

	@Async
	@Override
	public void updateChatScore(List<ChatList> chatList) {
		for (ChatList chat : chatList) {
			mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(chat.getId())),
					Update.update("score", chat.getScore()), ChatList.class);
		}
	}

}
