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
import com.michin.ai.kakao.model.response.common.QuickReply;
import com.michin.ai.kakao.model.response.output.BasicCard;
import com.michin.ai.kakao.model.response.output.Carousel;
import com.michin.ai.kakao.model.response.output.ListCard;
import com.michin.ai.kakao.model.response.output.Output;
import com.michin.ai.kakao.model.response.output.SimpleImage;
import com.michin.ai.kakao.model.response.output.SimpleText;

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

	public SkillTemplate addOutputs(Output output) {
		outputs.add(output.toJsonObjcet());
		return this;

	}
}
