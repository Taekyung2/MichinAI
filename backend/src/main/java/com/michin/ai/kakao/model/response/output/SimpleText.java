package com.michin.ai.kakao.model.response.output;

import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class SimpleText extends Output {
	private String text;

	@Override
	public String getType() {
		return "simpleText";
	}

}
