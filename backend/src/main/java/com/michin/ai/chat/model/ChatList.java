package com.michin.ai.chat.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class ChatList {

	@Id
	@Builder.Default
	private ObjectId id = new ObjectId();

	private String userBotKey;
	private LocalDate date;
	@Builder.Default
	@Field
	private double score = 0;

	private List<Chat> chats;
	
	

}