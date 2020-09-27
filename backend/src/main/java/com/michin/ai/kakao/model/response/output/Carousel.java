package com.michin.ai.kakao.model.response.output;

import java.util.List;

import com.michin.ai.kakao.model.response.common.CarouselHeader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Carousel extends Output {
	private String type;
	private List<BasicCard> items;
	private CarouselHeader header;

	public Carousel(String type, List<BasicCard> items) {
		this.type = type;
		this.items = items;
	}

}
