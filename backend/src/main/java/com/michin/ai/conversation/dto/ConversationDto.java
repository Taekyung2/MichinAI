package com.michin.ai.conversation.dto;

import java.time.LocalDate;

import com.michin.ai.conversation.model.Conversation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDto {
	private long id;
	private LocalDate date;
	private String speaker;
	private String eng;
	private String kor;

	public ConversationDto(Conversation c) {
		this(c.getId(), c.getDate(), c.getSpeaker(), c.getEng(), c.getKor());
	}
}
