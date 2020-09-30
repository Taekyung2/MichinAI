package com.michin.ai.chat.service;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Async;

import com.michin.ai.chat.model.BotChat;
import com.michin.ai.chat.model.ChatList;

public interface ChatService {

	public BotChat interactBot(String userBotKey, String msg);

	@Async
	public void saveChat(String userBotKey, String sender, String msg);
	
	public ChatList loadChatList(String userId, LocalDate date);
}
