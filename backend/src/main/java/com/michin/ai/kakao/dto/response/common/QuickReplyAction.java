package com.michin.ai.kakao.dto.response.common;

public enum QuickReplyAction {
	MESSAGE {
		@Override
		public QuickReply create(String label, String field) {
			return new QuickReply(label, "message", field, null, null);
		}

		@Override
		public QuickReply create(String label, String field, Object extra) {
			return new QuickReply(label, "message", field, null, extra);
		}
	},
	BLOCK {
		@Override
		public QuickReply create(String label, String field) {
			return new QuickReply(label, "block", null, field, null);
		}

		@Override
		public QuickReply create(String label, String field, Object extra) {
			return new QuickReply(label, "block", null, field, extra);
		}
	};

	abstract public QuickReply create(String label, String field);

	abstract public QuickReply create(String label, String field, Object extra);

}
