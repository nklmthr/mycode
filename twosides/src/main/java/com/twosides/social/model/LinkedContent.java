package com.twosides.social.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.map.annotate.JsonRootName;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("content")
public class LinkedContent {
	private long contentId;
	private String link;
	private String text;
	public long getContentId() {
		return contentId;
	}
	public void setContentId(long contentId) {
		this.contentId = contentId;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "LinkedContent [contentId=" + contentId + ", link=" + link + ", text=" + text + "]";
	}
	
}
