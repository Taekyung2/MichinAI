package com.michin.ai.kakao.model.response.common;

public enum QuickReplyAction {
	MESSAGE {
		@Override
		public QuickReply create(String label, String field) {
			return new QuickReply(label, "message", field, null, null);
		}
	},
	BLOCK {
		@Override
		public QuickReply create(String label, String field) {
			return new QuickReply(label, "message", null, field, null);
		}

		public QuickReply create(String label, String field, Object extra) {
			return new QuickReply(label, "message", null, field, extra);
		}
	};

	abstract public QuickReply create(String label, String field);
}
