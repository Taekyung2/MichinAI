package com.michin.ai.chat.repository;

import com.michin.ai.chat.model.Chat;

public interface ChatRepositoryCustom {

	void addChat(String userId, Chat chat);
}
