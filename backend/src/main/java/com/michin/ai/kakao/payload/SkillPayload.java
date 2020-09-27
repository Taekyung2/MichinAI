package com.michin.ai.kakao.payload;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SkillPayload {
	public UserReqeuest userRequest;
	List<Context> contexts;
	Bot bot;
	Action action;
}
