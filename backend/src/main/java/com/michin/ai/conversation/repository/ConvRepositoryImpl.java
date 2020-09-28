package com.michin.ai.conversation.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.michin.ai.conversation.model.Conversation;

@Transactional(readOnly = true)
public class ConvRepositoryImpl extends QuerydslRepositorySupport implements ConvRepositoryCustom {

	public ConvRepositoryImpl() {
		super(Conversation.class);
	}

}
