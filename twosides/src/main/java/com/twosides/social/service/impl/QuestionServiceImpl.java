package com.twosides.social.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.twosides.social.dao.QuestionDAO;
import com.twosides.social.model.Favors;
import com.twosides.social.model.LinkedContent;
import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;
import com.twosides.social.model.Refutals;
import com.twosides.social.model.SortType;
import com.twosides.social.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {
	Logger logger = Logger.getLogger(QuestionServiceImpl.class);

	QuestionDAO questionDAO;

	public Questions getQuestions(long start, long limit, SortType[] sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");
		Questions questions = null;
		try {
			questions = questionDAO.getQuestions();
		} catch (SQLException e) {
			logger.error(e);
		}
		return questions;
	}

	public Favors getQuestionFavors(String questionId, String start, String limit, String sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");
		Favors favors = new Favors();
		List<LinkedContent> favorsList = null;
		try {
			favorsList = questionDAO.getLinkedContent(questionId, "FAVOR");
		} catch (SQLException e) {
			logger.error(e);
		}
		favors.setContents(favorsList);
		return favors;
	}

	public Refutals getQuestionRefutals(String questionId, String start, String limit, String sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");
		Refutals refutals = new Refutals();
		List<LinkedContent> refutalList = null;
		try {
			refutalList = questionDAO.getLinkedContent(questionId, "REFUTAL");
		} catch (SQLException e) {
			logger.error(e);
		}
		refutals.setContents(refutalList);
		return refutals;
	}

	public void addQuestion(Question question) {
		try {
			questionDAO.addQuestion(question);
		} catch (SQLException e) {
			logger.error(e);
		}

	}

	public void addFavor(String questionId, LinkedContent content) {
		try {
			questionDAO.addLinkedContent(questionId, "FAVOR", content);
		} catch (SQLException e) {
			logger.error(e);
		}

	}

	public void addRefutal(String questionId, LinkedContent content) {
		try {
			questionDAO.addLinkedContent(questionId, "REFUTAL", content);
		} catch (SQLException e) {
			logger.error(e);
		}

	}

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}
}
