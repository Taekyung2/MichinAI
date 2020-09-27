package com.michin.ai.word.model;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Word {

	@Id
	private ObjectId id = new ObjectId();
	
	private String eng;
	
	private String kor;
	
	private boolean check;
	
	public static Word create(String eng, String kor) {
		Word word = new Word();
		word.setEng(eng);
		word.setKor(kor);
		word.setCheck(false);
		return word;
	}
}