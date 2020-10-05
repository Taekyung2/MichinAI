package com.michin.ai.chat.repository;

import java.util.List;

import com.michin.ai.chat.dto.ChatsDeletePayload;
import com.michin.ai.chat.model.Chat;
import com.michin.ai.chat.model.ChatList;

public interface ChatRepositoryCustom {

	void addChat(String userBotKey, Chat chat);

	void deleteChatsByIds(ChatsDeletePayload payload);

	void updateChatScore(List<ChatList> chatList);
}
