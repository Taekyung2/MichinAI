package com.michin.ai.chat.repository;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.michin.ai.chat.model.ChatList;

@Repository
public interface ChatRepository extends MongoRepository<ChatList, ObjectId>, ChatRepositoryCustom {

	List<ChatList> findByUserBotKeyOrderByDateDesc(String userBotKey);

	List<ChatList> findByUserBotKeyAndDate(String userBotKey, LocalDate date);

	void deleteByIdIn(List<String> list);


}
