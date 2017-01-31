package com.flipkart.logger.message;

import com.flipkart.logger.level.Level;

public class Message {
	private Level level;
	private String content;
	private String namespace;
	
	public Message(String namespace, Level level, String content) {
		super();
		this.level = level;
		this.content = content;
		this.namespace = namespace;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
}
