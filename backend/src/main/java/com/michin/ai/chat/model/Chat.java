package com.michin.ai.chat.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document
public class Chat {
	@Id
	@Builder.Default
	private ObjectId id = new ObjectId();

	private String msg;
	private String sender;
	private LocalDateTime time;
	private List<Check> check;
}
