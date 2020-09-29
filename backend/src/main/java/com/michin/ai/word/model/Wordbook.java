package com.michin.ai.word.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "wordbooks")
@CompoundIndex(def = "{'user_id': 1, 'name': -1}", unique = true)
public class Wordbook {
	
	@Id
	private ObjectId id;
	
	private String user_id;
	
	private String name;
	
	private List<Word> words = new ArrayList<Word>();
	
	public static Wordbook create(String user_id, String name) {
		Wordbook wb = new Wordbook();
		wb.setUser_id(user_id);
		wb.setName(name);
		return wb;
	}
	
}