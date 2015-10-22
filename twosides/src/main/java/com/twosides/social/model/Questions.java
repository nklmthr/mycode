package com.twosides.social.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement(name = "questions")

@XmlAccessorType(XmlAccessType.FIELD)
public class Questions extends BaseVO {

	@XmlElementWrapper(name = "list")
	@XmlElement(name = "question")
	@JsonProperty("questions")
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Questions [questions=" + questions + "]";
	}

}
