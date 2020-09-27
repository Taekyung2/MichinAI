package com.michin.ai.kakao.model.response.output;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class Output {

	public abstract String getType();

	public JsonObject toJsonObjcet() {
		JsonObject obj = new JsonObject();
		obj.add(getType(), new Gson().toJsonTree(this));

		return obj;
	}

}
