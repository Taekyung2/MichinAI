package com.michin.ai.kakao.model.response.output;

import java.util.ArrayList;
import java.util.List;

import com.michin.ai.kakao.model.response.common.Button;
import com.michin.ai.kakao.model.response.common.Profile;
import com.michin.ai.kakao.model.response.common.Thumbnail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BasicCard extends Output {
	private String title;
	private String description;
	private Thumbnail thumbnail;
	private Profile profile;
	private List<Button> buttons;

	@Override
	public String getType() {
		return "basicCard";
	}

	public BasicCard(String title, String description, Thumbnail thumbnail, Profile profile, Button button) {
		this(title, description, thumbnail, profile, new ArrayList<>());
		this.buttons.add(button);
	}

	public BasicCard(String title, String description, Thumbnail thumbnail) {
		this.title = title;
		this.description = description;
		this.thumbnail = thumbnail;
	}

}
