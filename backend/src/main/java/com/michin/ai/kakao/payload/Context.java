package com.michin.ai.kakao.payload;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Context {
	String name;
	int lifeSpan;
	Map<String, Object> params;
}