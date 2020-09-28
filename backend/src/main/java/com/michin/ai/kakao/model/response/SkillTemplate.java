package com.michin.ai.kakao.model.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.michin.ai.kakao.dto.response.common.QuickReply;
import com.michin.ai.kakao.dto.response.component.BasicCard;
import com.michin.ai.kakao.dto.response.component.Carousel;
import com.michin.ai.kakao.dto.response.component.Component;
import com.michin.ai.kakao.dto.response.component.ListCard;
import com.michin.ai.kakao.dto.response.component.SimpleImage;
import com.michin.ai.kakao.dto.response.component.SimpleText;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SkillTemplate {
	private List<JsonObject> outputs;
	private List<QuickReply> quickReplies;

	public SkillTemplate() {
		outputs = new ArrayList<>();
		quickReplies = new ArrayList<>();
	}

	public SkillTemplate addOutputs(Component output) {
		outputs.add(output.toJsonObjcet());
		return this;

	}
}
