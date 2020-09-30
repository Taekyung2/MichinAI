package com.michin.ai.chat.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document
public class ChatList {

	@Id
	@Builder.Default
	private ObjectId id = new ObjectId();

	private String userId;
	private LocalDate date;

	private List<Chat> chats = new ArrayList<>();

}