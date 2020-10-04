package com.michin.ai.chat.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Async;

import com.michin.ai.chat.dto.ChatLoadByDatePayload;
import com.michin.ai.chat.dto.ChatsDeletePayload;
import com.michin.ai.chat.model.BotChat;
import com.michin.ai.chat.model.ChatList;

public interface ChatService {

	BotChat interactBot(String userBotKey, String msg);

	BotChat startBot(String userBotKey);

	@Async
	void saveChat(String userBotKey, String sender, String msg);

	List<ChatList> loadChatList(String userBotKey);

	void deleteChatList(List<String> ids);

	void deleteChats(ChatsDeletePayload payload);

	List<ChatList> loadChatListByDate(ChatLoadByDatePayload payload);

}
