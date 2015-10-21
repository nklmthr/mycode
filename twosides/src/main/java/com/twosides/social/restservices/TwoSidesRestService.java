package com.twosides.social.restservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.twosides.social.model.Questions;
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
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		logger.info(questions);
		return Response.ok(questions).build();

	}

}
