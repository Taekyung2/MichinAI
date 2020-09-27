package com.michin.ai.kakao.model.response.output;

import java.util.List;

import com.michin.ai.kakao.model.response.common.Button;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ListCard extends Output {
	private ListItem header;
	private List<ListItem> items;
	private List<Button> buttons;

	public ListCard(ListItem header, List<ListItem> items) {
		this.header = header;
		this.items = items;
	}

	@Override
	public String getType() {
		return "listCard";
	}

}
