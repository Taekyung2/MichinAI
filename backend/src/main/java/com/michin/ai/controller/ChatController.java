package com.michin.ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.chat.dto.ChatListDeletePayload;
import com.michin.ai.chat.dto.ChatResponse;
import com.michin.ai.chat.dto.ChatsDeletePayload;
import com.michin.ai.chat.model.ChatList;
import com.michin.ai.chat.service.ChatService;
import com.michin.ai.common.Result;

@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;

	@GetMapping("/")
	public ResponseEntity getUserChatHistory(String userId) {
		List<ChatList> chatList = chatService.loadChatList(userId);
		return ChatResponse.build(chatList);
	}

	@DeleteMapping("/")
	public ResponseEntity deleteChats(ChatsDeletePayload payload) {
		chatService.deleteChats(payload);
		return Result.ok();
	}

	@DeleteMapping("/list")
	public ResponseEntity deleteChatlist(ChatListDeletePayload payload) {
		chatService.deleteChatList(payload.getIds());
		return Result.ok();
	}
}
