package com.twosides.social.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.twosides.social.dao.QuestionDAO;
import com.twosides.social.model.Favors;
import com.twosides.social.model.LinkedContent;
import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;

public class QuestionDAOImpl implements QuestionDAO {

	private DataSource dataSource;

	Logger logger = Logger.getLogger(QuestionDAOImpl.class);

	public Questions getQuestions() throws SQLException {
		List<Question> questionList = new ArrayList<Question>();
		Questions questions = new Questions();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT QUESTION_ID, QUESTION_TEXT FROM QUESTION");
			rs = ps.executeQuery();
			while (rs.next()) {
				Question q = new Question();
				q.setQuestionId(rs.getLong("QUESTION_ID"));
				q.setQuestion(rs.getString("QUESTION_TEXT"));
				questionList.add(q);
			}
			questions.setQuestions(questionList);
			return questions;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void addQuestion(Question question) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO QUESTION(QUESTION_ID, QUESTION_TEXT) " + " VALUES (question_ID_SEQ.NEXTVAL, ?)");
			ps.setString(1, question.getQuestion());
			ps.executeUpdate();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

	public List<LinkedContent> getLinkedContent(String questionId, String contentType) throws SQLException {
		List<LinkedContent> contentList = new ArrayList<LinkedContent>();
		Favors favors = new Favors();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("SELECT CONTENT_ID, CONTENT_LINK, CONTENT_TEXT " + "FROM LINK_CONTENT "
					+ "WHERE QUESTION_ID=? AND CONTENT_TYPE=?");
			ps.setString(1, questionId);
			ps.setString(2, contentType);
			rs = ps.executeQuery();
			while (rs.next()) {
				LinkedContent q = new LinkedContent();
				q.setContentId(rs.getLong("CONTENT_ID"));
				q.setLink(rs.getString("CONTENT_LINK"));
				q.setText(rs.getString("CONTENT_TEXT"));
				contentList.add(q);
			}

			return contentList;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void addLinkedContent(String questionId, String contentType, LinkedContent content) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("INSERT INTO LINK_CONTENT(CONTENT_ID, QUESTION_ID,"
					+ "CONTENT_TYPE,CONTENT_LINK,CONTENT_TEXT)" + " VALUES (LINK_CONTENT_ID_SEQ.NEXTVAL, ?, ?, ?, ?)");
			ps.setString(1, questionId);
			ps.setString(2, contentType);
			ps.setString(3, content.getLink());
			ps.setString(4, content.getText());
			ps.executeUpdate();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
