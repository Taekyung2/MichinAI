package com.michin.ai.kakao.model.response.common;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Thumbnail {
	private String imageUrl; // 이미지의 url입니다.
	private Link link; // 이미지 클릭시 작동하는 link입니다.

	// true: 이미지 영역을 1:1 비율로 두고 이미지의 원본 비율을 유지합니다. 이미지가 없는 영역은 흰색으로 노출합니다.
	// false: 이미지 영역을 2:1 비율로 두고 이미지의 가운데를 크롭하여 노출합니다.
	// 기본값: false
	private boolean fixedRadio;

	// fixedRatio를 true로 설정할 경우 필요한 값입니다.
	// 실제 이미지 사이즈와 다른 값일 경우 원본이미지와 다르게 표현될 수 있습니다.
	private int width;
	private int height;

	public Thumbnail(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Thumbnail(String imageUrl, Link link) {
		this.imageUrl = imageUrl;
		this.link = link;
	}

	public Thumbnail(String imageUrl, int width, int height) {
		this.imageUrl = imageUrl;
		this.fixedRadio = true;
		this.width = width;
		this.height = height;
	}

	public Thumbnail(String imageUrl, Link link, boolean fixedRadio, int width, int height) {
		this.imageUrl = imageUrl;
		this.link = link;
		this.fixedRadio = fixedRadio;
		this.width = width;
		this.height = height;
	}

}
