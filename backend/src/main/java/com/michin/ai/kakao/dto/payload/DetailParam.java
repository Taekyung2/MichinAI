package com.michin.ai.kakao.dto.payload;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DetailParam {
	String origin;
	Object value;
	String groupName;
}