package com.michin.ai.chat.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ChatsDeletePayload {
	List<String> ids;
}
