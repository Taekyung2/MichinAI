package com.michin.ai.chat.model;

import java.util.List;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.languagetool.rules.RuleMatch;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class Check {

	private int fromPos;
	private int toPos;
	private List<String> recommends;
	private String category;

	public Check(RuleMatch match) {
		this.category = match.getRule().getCategory().toString();
		this.recommends = match.getSuggestedReplacements();
		this.toPos = match.getToPos();
		this.fromPos = match.getFromPos();
	}
}
