package com.michin.ai.kakao.model.response;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SkillResponse {
	private String version = "2.0";
	private SkillTemplate template;

	public SkillResponse(SkillTemplate template) {
		this.template = template;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

}
