package com.michin.ai.chat.service;

import org.springframework.scheduling.annotation.Async;

import com.michin.ai.chat.model.BotChat;

public interface ChatService {

	public BotChat interactBot(String userBotKey, String msg);

	@Async
	public void saveChat(String sender, String msg);
}
