package com.twosides.social.dao;

import java.sql.SQLException;
import java.util.List;

import com.twosides.social.model.LinkedContent;
import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;

public interface QuestionDAO {

	Questions getQuestions() throws SQLException;

	void addQuestion(Question question) throws SQLException;

	List<LinkedContent> getLinkedContent(String questionId, String contentType) throws SQLException;

	void addLinkedContent(String questionId, String contentType, LinkedContent content) throws SQLException;
}
