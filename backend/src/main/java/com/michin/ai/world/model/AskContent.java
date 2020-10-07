package com.michin.ai.world.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AskContent {
	
	private String sender;
	private String recipient;
	private Message message;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Message{
		
		private String text;
		private String mid;
		
	}
	
}
