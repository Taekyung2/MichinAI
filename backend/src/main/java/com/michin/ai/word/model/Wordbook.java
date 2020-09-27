package com.michin.ai.word.model;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "wordbooks")
public class Wordbook {
	
	@Id
	private String id;
	
	private String user_id;
	
	private List<Word> words;
	
}
