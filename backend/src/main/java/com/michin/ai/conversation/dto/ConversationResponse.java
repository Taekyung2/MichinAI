package com.michin.ai.conversation.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import com.michin.ai.common.ApiResult;
import com.michin.ai.common.Result;
import com.michin.ai.conversation.model.Conversation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

public class ConversationResponse {

	public static ResponseEntity<ApiResult> build(List<Conversation> convList) {
		return Result.ok(ApiResult.blank().add("convList",
				convList.stream().map(c -> new ConversationDto(c)).collect(Collectors.toList())));
	}

	@Getter
	@ToString
	@AllArgsConstructor
	private static class ConversationDto {
		private long id;
		private LocalDate date;
		private String speaker;
		private String eng;
		private String kor;

		public ConversationDto(Conversation c) {
			this(c.getId(), c.getDate(), c.getSpeaker(), c.getEng(), c.getKor());
		}
	}
}
