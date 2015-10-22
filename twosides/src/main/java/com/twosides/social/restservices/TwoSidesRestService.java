package com.twosides.social.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.twosides.social.model.Favors;
import com.twosides.social.model.LinkedContent;
import com.twosides.social.model.Question;
import com.twosides.social.model.Questions;
import com.twosides.social.model.Refutals;
import com.twosides.social.model.SortType;
import com.twosides.social.restservices.util.SpringContextLoader;
import com.twosides.social.service.QuestionService;

@Path("/v1/twosides")
public class TwoSidesRestService {

	private static final Logger logger = Logger.getLogger(TwoSidesRestService.class);
	QuestionService questionService = SpringContextLoader.getContext().getBean(QuestionService.class);

	@GET
	@Path("/questions")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQuestions(@QueryParam("start") String start, @QueryParam("limit") String limit,
			@QueryParam("sort") String sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");
		Questions questions = null;
		try {
			long startLong = 1L;
			if (null != start) {
				startLong = Long.valueOf(start);
			}
			long limitLong = 20L;
			if (null != limit) {
				limitLong = Long.valueOf(limit);
			}
			List<SortType> sortTypeList = new ArrayList<SortType>();
			if (null != sort) {
				String[] sortString = sort.split("\\,");
				for (String sortType : sortString) {
					SortType sortTypeEnum = SortType.valueOf(sortType);
					sortTypeList.add(sortTypeEnum);
				}
			}
			logger.info("Query [startLong=" + startLong + ", limitLong=" + limitLong + ", sortTypeList=" + sortTypeList
					+ "]");
			questions = questionService.getQuestions(startLong, limitLong, null);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").entity(e.getMessage()).build();
		}
		logger.info(questions);
		return Response.ok(questions).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@GET
	@Path("/questions/{questionId}/favors")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQuestionFavors(@PathParam("questionId") String questionId, @QueryParam("start") String start,
			@QueryParam("limit") String limit, @QueryParam("sort") String sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");
		Favors favors = null;
		try {
			favors = questionService.getQuestionFavors(questionId, start, limit, sort);
		} catch (Exception e) {
			Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").entity(e.getMessage()).build();
		}
		return Response.ok(favors).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@GET
	@Path("/questions/{questionId}/refutals")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getQuestionRefutals(@PathParam("questionId") String questionId, @QueryParam("start") String start,
			@QueryParam("limit") String limit, @QueryParam("sort") String sort) {
		logger.info("Query [start=" + start + ", limit=" + limit + ", sort=" + sort + "]");
		Refutals refutals = null;
		try {
			refutals = questionService.getQuestionRefutals(questionId, start, limit, sort);
		} catch (Exception e) {
			Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").entity(e.getMessage()).build();
		}
		return Response.ok(refutals).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/questions")
	public Response addQuestion(Question question) {
		logger.info(question);
		questionService.addQuestion(question);
		return Response.status(Status.CREATED).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/questions/{questionId}/favors")
	public Response addFavor(@PathParam("questionId") String questionId, LinkedContent content) {
		logger.info(content);
		questionService.addFavor(questionId, content);
		return Response.status(Status.CREATED).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/questions/{questionId}/refutals")
	public Response addRefutal(@PathParam("questionId") String questionId, LinkedContent content) {
		logger.info(content);
		questionService.addRefutal(questionId, content);
		return Response.status(Status.CREATED).header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
	}
}
