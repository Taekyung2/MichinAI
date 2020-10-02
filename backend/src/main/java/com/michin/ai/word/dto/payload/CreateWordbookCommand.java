package com.michin.ai.word.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateWordbookCommand {
	private Long user_id;
	private String name;
}
