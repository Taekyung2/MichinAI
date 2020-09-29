package com.michin.ai.chat.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;

public class Chat {
	@Id
	private ObjectId id= new ObjectId();
	
	private String msg;
	private String sender;
	private LocalTime time;
	private List<Check> check;
}
