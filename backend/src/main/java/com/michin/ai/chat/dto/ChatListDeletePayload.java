package com.michin.ai.chat.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ChatListDeletePayload {
	List<String> ids;
}
