package com.michin.ai.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table
public class User {
	
	@Id
	@Column(nullable = false)
	private Long id;
	
	@Setter
	@Column(nullable = false, length = 50)
	private String name;
	
	@Setter
	private String botKey;
	
	public User(String name, Long id) {
		this.name = name;
		this.id = id;
	}
	
}
