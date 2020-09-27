package com.michin.ai.kakao.payload;

import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserReqeuest {
	String timezone;// 사용자의 시간대를 반환합니다.한국에서 보낸 요청이라면 “Asia/Seoul”를 갖습니다.
	Map<String, String> params;
	Block block;// 사용자의 발화에 반응한 블록의 정보입니다.블록의 id와 name을 포함합니다.
	String utterance;// 봇 시스템에 전달된 사용자의 발화입니다.
	String lang;// 사용자의 언어를 반환합니다.한국에서 보낸 요청이라면 “kr”를 갖습니다.
	User user;// 사용자의 정보입니다.
}
