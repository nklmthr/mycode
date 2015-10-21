package com.twosides.social.service;

import com.twosides.social.model.Questions;
import com.twosides.social.model.SortType;

public interface QuestionService {
	Questions getQuestions(long start, long limit, SortType sort[]);
}
