package com.michin.ai.kakao.dto.response.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class SimpleImage extends Component {
	private String imageUrl;
	private String altText;

	@Override
	public String getComponentType() {
		return "simpleImage";
	}
}
