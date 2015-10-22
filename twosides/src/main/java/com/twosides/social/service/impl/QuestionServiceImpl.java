package com.twosides.social.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.twosides.social.model.Favors;
import com.twosides.social.model.LinkedContent;
import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;
import com.twosides.social.model.Refutals;
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

	public Favors getQuestionFavors(String questionId, String start, String limit, String sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");

		List<LinkedContent> favorsList = new ArrayList<LinkedContent>();
		Favors favors = new Favors();
		LinkedContent c1 = new LinkedContent();
		c1.setContentId(100);
		c1.setLink(
				"http://www.ndtv.com/andhra-pradesh-news/pm-modi-to-lay-foundation-stone-for-mobile-facility-in-andhra-pradesh-1234978?utm_source=taboola");
		favorsList.add(c1);

		LinkedContent c2 = new LinkedContent();
		c2.setContentId(101);
		c2.setLink(
				"http://scroll.in/article/736845/first-person-the-story-behind-how-sanjay-gandhi-slapped-indira-six-times-at-a-dinner-party?utm_source=taboola&utm_medium=ndtv");
		favorsList.add(c2);

		favors.setContents(favorsList);
		return favors;
	}

	public Refutals getQuestionRefutals(String questionId, String start, String limit, String sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");

		List<LinkedContent> refutalsList = new ArrayList<LinkedContent>();
		Refutals refutals = new Refutals();
		LinkedContent c1 = new LinkedContent();
		c1.setContentId(100);
		c1.setLink(
				"http://www.ndtv.com/andhra-pradesh-news/pm-modi-to-lay-foundation-stone-for-mobile-facility-in-andhra-pradesh-1234978?utm_source=taboola");
		refutalsList.add(c1);

		LinkedContent c2 = new LinkedContent();
		c2.setContentId(101);
		c2.setLink(
				"http://scroll.in/article/736845/first-person-the-story-behind-how-sanjay-gandhi-slapped-indira-six-times-at-a-dinner-party?utm_source=taboola&utm_medium=ndtv");
		refutalsList.add(c2);

		refutals.setContents(refutalsList);
		return refutals;
	}

}
