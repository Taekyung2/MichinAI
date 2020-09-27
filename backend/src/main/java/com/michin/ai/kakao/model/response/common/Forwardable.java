package com.michin.ai.kakao.model.response.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Forwardable {
	private boolean forwardable;// 말풍선에 전달하기 아이콘을 노출합니다.
								// 전달하기 아이콘을 스킬을 통해 출력하기 위해서는 말풍선이 단일형이면서 버튼이 포함되지 않은 경우에만 설정 가능합니다.

}
