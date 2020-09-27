package com.michin.ai.kakao.model.response.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class QuickReply {
	private String label; // 사용자에게 노출될 바로가기 응답의 표시
	private String action; // 바로가기 응답의 기능
	private String messageText;// 사용자 측으로 노출될 발화
	private String blockId;// 바로가기 응답이 블록일때, 연결될 블록의 id

	@Setter
	private Object any;// 블록 호출 시 추가적으로 제공하는 정보

	protected QuickReply(String label, String action, String messageText, String blockId, Object any) {
		this.label = label;
		this.action = action;
		this.messageText = messageText;
		this.blockId = blockId;
		this.any = any;
	}

}
