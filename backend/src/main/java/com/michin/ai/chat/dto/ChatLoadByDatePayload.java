package com.michin.ai.chat.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
public class ChatLoadByDatePayload {

	private String userBotKey;
	@ApiParam(defaultValue = "2020-09-28")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
}
