package com.michin.ai.conversation.service;

import java.time.LocalDate;
import java.util.List;

import com.michin.ai.conversation.model.Conversation;
import com.michin.ai.conversation.model.ConversationDto;

public interface ConversationService {

	List<Conversation> scrapingConversation(LocalDate date);

	List<ConversationDto> loadConversation(LocalDate date);

	boolean saveConversation(List<Conversation> list);

}
