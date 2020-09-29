package com.michin.ai.kakao.model.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SkillResponse {
	private String version = "2.0";
	private SkillTemplate template;
	private Context context;

	public SkillResponse() {
		context = new Context();
	}

	public SkillResponse(SkillTemplate template) {
		this();
		this.template = template;
	}

	public SkillResponse addContext(ContextValue contextValue) {
		context.add(contextValue);
		return this;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

}

@Getter
@ToString
class Context {
	List<ContextValue> values;

	public Context() {
		this.values = new ArrayList<ContextValue>();
	}

	public void add(ContextValue contextValue) {
		values.add(contextValue);
	}

}