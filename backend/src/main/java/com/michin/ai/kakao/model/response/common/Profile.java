package com.michin.ai.kakao.model.response.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Profile {
	private String nickname;
	private String imageUrl;

	public Profile(String nickname) {
		this.nickname = nickname;
	};

}
