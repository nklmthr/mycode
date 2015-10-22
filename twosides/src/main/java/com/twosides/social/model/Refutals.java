package com.twosides.social.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
@XmlRootElement(name = "refutals")

@XmlAccessorType(XmlAccessType.FIELD)
public class Refutals {
	@XmlElementWrapper(name = "list")
	@XmlElement(name = "refutal")
	@JsonProperty("refutals")
	private List<LinkedContent> contents;

	public List<LinkedContent> getContents() {
		return contents;
	}

	public void setContents(List<LinkedContent> contents) {
		this.contents = contents;
	}
	
	
}
