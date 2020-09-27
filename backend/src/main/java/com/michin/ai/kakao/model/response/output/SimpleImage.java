package com.michin.ai.kakao.model.response.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class SimpleImage extends Output {
	private String imageUrl;
	private String altText;

	@Override
	public String getType() {
		return "simpleImage";
	}
}
