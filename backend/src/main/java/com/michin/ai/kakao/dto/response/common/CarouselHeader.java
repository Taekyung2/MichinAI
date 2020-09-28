package com.michin.ai.kakao.dto.response.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CarouselHeader {
	private String title;
	private String description;
	private Thumbnail thumbnail;
}
