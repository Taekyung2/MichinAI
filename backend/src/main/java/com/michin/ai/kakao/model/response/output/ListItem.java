package com.michin.ai.kakao.model.response.output;

import com.michin.ai.kakao.model.response.common.Link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ListItem extends Output {
	private String title;
	private String description;
	private String imageUrl;
	private Link link;

	public ListItem(String title) {
		this.title = title;
	}

	@Override
	public String getType() {
		return "listItem";
	}

}
