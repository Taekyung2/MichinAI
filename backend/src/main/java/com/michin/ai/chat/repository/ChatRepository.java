package com.michin.ai.chat.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.michin.ai.chat.model.ChatList;

@Repository
public interface ChatRepository extends MongoRepository<ChatList, ObjectId>, ChatRepositoryCustom {

	List<ChatList> findByUserIdOrderByDateDesc(String userId);

	void deleteByIdIn(List<String> list);

}
