package com.twosides.social.service;

import com.twosides.social.model.Favors;
import com.twosides.social.model.LinkedContent;
import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;
import com.twosides.social.model.Refutals;
import com.twosides.social.model.SortType;

public interface QuestionService {
	Questions getQuestions(long start, long limit, SortType sort[]);

	Favors getQuestionFavors(String questionId, String start, String limit, String sort);

	Refutals getQuestionRefutals(String questionId, String start, String limit, String sort);

	void addQuestion(Question question);

	void addFavor(String questionId, LinkedContent content);

	void addRefutal(String questionId, LinkedContent content);
}
