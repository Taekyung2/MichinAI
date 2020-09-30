package com.michin.ai.user.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
	private Long id;
	private String name;
	private String botKey;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.botKey = user.getBotKey();
	}

}
