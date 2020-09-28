package com.michin.ai.kakao.dto.response.component;

import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class SimpleText extends Component {
	private String text;

	@Override
	public String getComponentType() {
		return "simpleText";
	}

}
