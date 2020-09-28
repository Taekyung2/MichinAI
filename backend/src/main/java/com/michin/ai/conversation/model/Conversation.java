package com.michin.ai.conversation.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDate date;

	@Column(length = 20)
	private String speaker;

	@Column(length = 300)
	private String eng;
	@Column(length = 300)
	private String kor;

	public Conversation(LocalDate date, String speaker, String eng, String kor) {
		this.date = date;
		this.speaker = speaker;
		this.eng = eng;
		this.kor = kor;
	}

}
