package com.michin.ai.kakao.dto.response.component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public abstract class Component {

	public abstract String getComponentType();

	public JsonObject toJsonObjcet() {
		JsonObject obj = new JsonObject();
		obj.add(getComponentType(), new Gson().toJsonTree(this));

		return obj;
	}

}
