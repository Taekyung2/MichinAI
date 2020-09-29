package com.michin.ai.kakao.model.response;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ContextValue {
	private String name;
	private int lifeSpan;
	private Map<String, String> params;

	public ContextValue(String name, int lifeSpan) {
		this.name = name;
		this.lifeSpan = lifeSpan;
	}

}
