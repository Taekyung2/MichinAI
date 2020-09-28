package com.michin.ai.kakao.dto.response.component;

import java.util.List;

import com.michin.ai.kakao.dto.response.common.CarouselHeader;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Carousel extends Component {
	private String type;
	private List<BasicCard> items;
	private CarouselHeader header;

	public Carousel(List<BasicCard> items) {
		this.type = "basicCard";
		this.items = items;
	}

	@Override
	public String getComponentType() {
		return "carousel";
	}

}
