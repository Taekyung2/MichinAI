package com.michin.ai.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserCommand {
	private long id; 
	private String name;
	private String botKey;
}
