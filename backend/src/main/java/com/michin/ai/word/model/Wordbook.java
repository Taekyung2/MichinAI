package com.michin.ai.word.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "wordbooks")
public class Wordbook {
	@Id
	private ObjectId id;
	
	private Long user_id;
	
	private String name;
	
	private List<Word> words = new ArrayList<Word>();
	
	public static Wordbook create(Long user_id, String name) {
		Wordbook wb = new Wordbook();
		wb.setUser_id(user_id);
		wb.setName(name);
		return wb;
	}
}