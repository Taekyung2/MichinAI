package com.michin.ai.world.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorldAskMessage<T> {
	String type;
	T content;
}
