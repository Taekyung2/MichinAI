package com.michin.ai.word.model;

import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "basewordbooks")
public class Basewordbook {
	
	@Id
	private ObjectId id;
	
	private int level;
	
	private List<Word> words;
	
}