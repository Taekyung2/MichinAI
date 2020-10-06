package com.michin.ai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michin.ai.chat.dto.ChatListDeletePayload;
import com.michin.ai.chat.dto.ChatLoadByDatePayload;
import com.michin.ai.chat.dto.ChatResponse;
import com.michin.ai.chat.dto.ChatsDeletePayload;
import com.michin.ai.chat.model.ChatList;
import com.michin.ai.chat.service.ChatService;
import com.michin.ai.common.Result;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;

	@GetMapping("/")
	@ApiOperation(value = "유저 모든 채팅 불러오기")
	public ResponseEntity getUserChatHistory(String userBotKey) {
		List<ChatList> chatList = chatService.loadChatList(userBotKey);
		return ChatResponse.build(chatList);
	}

	@GetMapping("/date")
	@ApiOperation(value = "유저의 하루 채팅 불러오기")
	public ResponseEntity getUserChatHistoryByDate(ChatLoadByDatePayload payload) {
		List<ChatList> chatList = chatService.loadChatListByDate(payload);
		return ChatResponse.build(chatList);
	}

	@GetMapping("/score")
	@ApiOperation(value = "유저의 채팅 점수 불러오기")
	public ResponseEntity getUserChatScore(String userBotKey) {
		List<ChatList> chatList = chatService.loadChatScore(userBotKey);
		return ChatResponse.build(chatList);
	}

	@DeleteMapping("/")
	@ApiOperation(value = "채팅들 지우기")
	public ResponseEntity deleteChats(ChatsDeletePayload payload) {
		chatService.deleteChats(payload);
		return Result.ok();
	}

	@DeleteMapping("/list")
	@ApiOperation(value = "하루의 채팅들 지우기")
	public ResponseEntity deleteChatlist(ChatListDeletePayload payload) {
		chatService.deleteChatList(payload.getIds());
		return Result.ok();
	}
}
