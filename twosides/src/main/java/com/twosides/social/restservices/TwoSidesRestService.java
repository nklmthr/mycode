package com.twosides.social.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;

@Path("/v1/twosides")
public class TwoSidesRestService {

	private static final Logger logger = Logger.getLogger(TwoSidesRestService.class);

	@GET
	@Path("/questions")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQuestions() {
		logger.info("questions");
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
		return Response.ok(questions).build();
	}

}
