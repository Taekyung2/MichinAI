package com.michin.ai.kakao.payload;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Bot {
	String id;// 봇의 고유한 식별자입니다.
	String name;// 설정된 봇의 이름입니다.
}
