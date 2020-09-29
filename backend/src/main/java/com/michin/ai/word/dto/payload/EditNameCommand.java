package com.michin.ai.word.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditNameCommand {
	private String wordbook_id;
	private String name;
}
