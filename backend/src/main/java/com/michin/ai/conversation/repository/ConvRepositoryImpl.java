package com.michin.ai.conversation.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.michin.ai.conversation.model.Conversation;

public class ConvRepositoryImpl extends QuerydslRepositorySupport implements ConvRepositoryCustom {

	public ConvRepositoryImpl() {
		super(Conversation.class);
	}

}
