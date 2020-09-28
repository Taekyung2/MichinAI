package com.michin.ai.kakao.dto.response.component;

import java.util.ArrayList;
import java.util.List;

import com.michin.ai.kakao.dto.response.common.Button;
import com.michin.ai.kakao.dto.response.common.Profile;
import com.michin.ai.kakao.dto.response.common.Thumbnail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BasicCard extends Component {
	private String title;
	private String description;
	private Thumbnail thumbnail;
	private Profile profile;
	private List<Button> buttons;

	@Override
	public String getComponentType() {
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
