package com.sap.ariba.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DocumentResult {
	public DocumentResult(String key, String value) {
		this.key = key;
		this.value = value;

	}

	private String key;
	private String value;
}
