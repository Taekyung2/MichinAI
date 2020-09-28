package com.michin.ai.kakao.dto.response.component;

import java.util.ArrayList;
import java.util.List;

import com.michin.ai.kakao.dto.response.common.Button;
import com.michin.ai.kakao.dto.response.common.Link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ListCard extends Component {
	private ListItem header;
	private List<ListItem> items;
	private List<Button> buttons;

	public ListCard() {
		items = new ArrayList<>();
		buttons = new ArrayList<>();
	}

	public ListCard(ListItem header) {
		this();
		this.header = header;
	}

	public ListCard addListItem(String title, String description, String imageUrl, Link link) {
		items.add(new ListItem(title, description, imageUrl, link));

		return this;
	}
	
	public ListCard addListItem(String title) {
		items.add(new ListItem(title));

		return this;
	}

	@Override
	public String getComponentType() {
		return "listCard";
	}

}

@Getter
@ToString
@AllArgsConstructor
class ListItem {
	private String title;
	private String description;
	private String imageUrl;
	private Link link;

	public ListItem(String title) {
		this.title = title;
	}

}
