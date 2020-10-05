package com.michin.ai.chat.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;

import com.michin.ai.chat.model.Chat;
import com.michin.ai.chat.model.ChatList;
import com.michin.ai.chat.model.Check;
import com.michin.ai.common.ApiResult;
import com.michin.ai.common.Result;

import lombok.Data;

public class ChatResponse {

	public static ResponseEntity<ApiResult> build(List<ChatList> chatList) {
		return Result.ok(ApiResult.blank().add("chatList",
				chatList.stream().map(c -> new ChatListDto(c)).collect(Collectors.toList())));
	}

	public static ResponseEntity<ApiResult> build(ChatList chatList) {
		return Result.ok(ApiResult.blank().add("chatList", new ChatListDto(chatList)));
	}

	@Data
	public static class ChatListDto {
		private String userBotKey;
		private LocalDate date;
		private double score;

		private List<ChatDto> chats = new ArrayList<>();

		public ChatListDto(ChatList chatList) {
			this.userBotKey = chatList.getUserBotKey();
			this.date = chatList.getDate();
			this.score = chatList.getScore();
			this.chats = Optional.ofNullable(chatList.getChats()).map(Collection::stream).orElseGet(Stream::empty)
					.map(c -> new ChatDto(c)).collect(Collectors.toList());
		}

	}

	@Data
	private static class ChatDto {
		private String msg;
		private String sender;
		private LocalTime time;
		private List<CheckDto> check;

		public ChatDto(Chat chat) {
			this.msg = chat.getMsg();
			this.sender = chat.getSender();
			this.time = chat.getTime();
//			this.check = chat.getCheck().stream().map(c -> new CheckDto(c)).collect(Collectors.toList());
			this.check = Optional.ofNullable(chat.getCheck()).map(Collection::stream).orElseGet(Stream::empty)
					.map(c -> new CheckDto(c)).collect(Collectors.toList());

		}
	}

	@Data
	private static class CheckDto {
		private int fromPos;
		private int toPos;
		private List<String> recommends;
		private String category;

		public CheckDto(Check check) {
			this.fromPos = check.getFromPos();
			this.toPos = check.getToPos();
			this.recommends = check.getRecommends();
			this.category = check.getCategory();

		}

	}

}
