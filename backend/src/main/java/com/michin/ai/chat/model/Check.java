package com.michin.ai.chat.model;

import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.languagetool.rules.RuleMatch;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Check {
	@Id
	private ObjectId id = new ObjectId();

	private int fromPos;
	private int toPos;
	private List<String> recommends;
	private String category;

	public Check(RuleMatch match) {
		this.category = match.getShortMessage();
		this.recommends = match.getSuggestedReplacements();
		this.toPos = match.getToPos();
		this.fromPos = match.getFromPos();
	}
}
