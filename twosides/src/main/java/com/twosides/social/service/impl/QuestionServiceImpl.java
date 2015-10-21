package com.twosides.social.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;
import com.twosides.social.model.SortType;
import com.twosides.social.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {
	Logger logger = Logger.getLogger(QuestionServiceImpl.class);

	public Questions getQuestions(long start, long limit, SortType[] sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");

		List<Question> questionList = new ArrayList<Question>();
		Questions questions = new Questions();

		Question q1 = new Question();
		q1.setQuestionId(1L);
		q1.setQuestion("Is PM narendra modi working in the right direction for the benefit of the nation?");
		questionList.add(q1);

		Question q2 = new Question();
		q2.setQuestionId(2L);
		q2.setQuestion("Is @bdutt wrong?");
		questionList.add(q2);

		questions.setQuestions(questionList);
		logger.info(questions);
		return questions;
	}

}
