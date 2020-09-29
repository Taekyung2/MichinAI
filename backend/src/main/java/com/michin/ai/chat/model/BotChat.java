package com.michin.ai.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BotChat {
	private String id;
	private boolean episode_done;
	private String text;

}
