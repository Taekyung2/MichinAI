package com.michin.ai.conversation.service;

import java.time.LocalDate;
import java.util.List;

import com.michin.ai.conversation.model.Conversation;

public interface ConvService {

	List<Conversation> scrapingConversation(LocalDate date);

	List<Conversation> loadConversation(LocalDate date);

	void saveConversation(List<Conversation> list);

}
