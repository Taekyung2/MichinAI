package com.michin.ai.kakao.model.response.common;

public enum ButtonAction {
	WEBLINK {
		@Override
		public Button create(String label, String field) {
			return new Button(label, "webLink", field, null, null, null);
		}

	},
	MESSAGE {
		@Override
		public Button create(String label, String field) {
			return new Button(label, "message", null, field, null, null);

		}

	},
	BLOCK {
		@Override
		public Button create(String label, String field) {
			return new Button(label, "block", null, null, null, field);

		}

	},
	PHONE {
		@Override
		public Button create(String label, String field) {
			return new Button(label, "phone", null, null, field, null);

		}

	},
	SHARE {
		@Override
		public Button create(String label, String field) {
			return new Button(label, "share", null, null, null, null);

		}

	};

	public abstract Button create(String label, String field);

}
