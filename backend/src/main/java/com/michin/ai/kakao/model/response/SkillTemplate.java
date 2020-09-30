package com.michin.ai.kakao.model.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.michin.ai.kakao.dto.response.common.QuickReply;
import com.michin.ai.kakao.dto.response.component.Component;

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

	public SkillTemplate addQuickReplies(List<QuickReply> quickReplies) {
		this.quickReplies = quickReplies;
		return this;
	}

	public SkillTemplate addQuickReply(QuickReply quickReply) {
		quickReplies.add(quickReply);
		return this;
	}
}
