package com.nklmthr.games.game29.restservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.nklmthr.games.game29.context.SpringApplicationContext;
import com.nklmthr.games.game29.service.Game29Service;

@Path("/game")
public class SectionRestService {
	private Logger logger = Logger.getLogger(SectionRestService.class);

	@GET
	@Path("sections/{sectionId}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getSection(@PathParam("sectionId") int sectionId, @QueryParam("playerId") int playerId) {
		String response = "";
		try {
			Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
			service.initialize();

			switch (sectionId) {
			case 11:
				response = service.getSection11(playerId);
				break;
			case 12:
				response = service.getSection12(playerId);
				break;
			case 13:
				response = service.getSection13(playerId);
				break;
			case 21:
				response = service.getSection21(playerId);
				break;
			case 22:
				response = service.getSection22(playerId);
				break;
			case 23:
				response = service.getSection23(playerId);
				break;
			case 31:
				response = service.getSection31(playerId);
				break;
			case 32:
				response = service.getSection32(playerId);
				break;
			}
			logger.info(playerId + ":" + sectionId + "=" + response);
		} catch (Exception e) {
			logger.error("Error:" + playerId + ":" + sectionId + "=" + response);
			e.printStackTrace();
		}
		return Response.ok(response).build();
	}

	@GET
	@Path("/deal")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response newDeal() {
		Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
		service.initialize();
		return Response.ok(service.newDeal()).build();
	}

	@GET
	@Path("/challenge")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response setNewChallengePointsByPlayer(@QueryParam("points") int points,
			@QueryParam("isDouble") boolean isDouble, @QueryParam("isPass") boolean isPass,
			@QueryParam("playerId") int playerId) {
		Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
		logger.info("playerId=" + playerId + ",points=" + points + "isPass,=" + isPass + "isDouble,=" + isDouble);
		service.initialize();
		return Response.ok(service.setNewChallenge(playerId, points, isPass, isDouble)).build();
	}

	@GET
	@Path("/reDoublechallenge")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response setNewChallengePointsByPlayer(@QueryParam("isReDouble") boolean isReDouble,
			@QueryParam("isPass") boolean isPass, @QueryParam("playerId") int playerId) {
		Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
		logger.info(
				"setNewChallengePointsByPlayer:playerId=" + playerId + "isPass,=" + isPass + "isDouble,=" + isReDouble);
		service.initialize();
		return Response.ok(service.setReDoubleChallenge(playerId, isPass, isReDouble)).build();
	}

	@GET
	@Path("/setTrump")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response setTrump(@QueryParam("trump") int trump, @QueryParam("playerId") int playerId) {
		Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
		logger.info("setTrump:playerId=" + playerId + "trump,=" + trump);
		service.initialize();
		return Response.ok(service.setTrump(playerId, trump)).build();
	}

	@GET
	@Path("/openTrump")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response showTrump(@QueryParam("playerId") int playerId) {
		Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
		logger.info("showTrump:playerId=" + playerId);
		service.initialize();
		return Response.ok(service.openTrump(playerId)).build();
	}

	@GET
	@Path("/makeMove")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response makeMove(@QueryParam("playerId") int playerId, @QueryParam("suite") int suite,
			@QueryParam("rank") int rank) {
		Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
		logger.info("showTrump:playerId=" + playerId);
		service.initialize();
		return Response.ok(service.makeMove(playerId, suite, rank)).build();
	}

	@PUT
	@Path("/users")
	@Consumes({ MediaType.TEXT_PLAIN })
	@Produces({ MediaType.TEXT_PLAIN })
	public Response updateUser(String payLoad) {
		Game29Service service = SpringApplicationContext.getSpringContext().getBean(Game29Service.class);
		boolean isUpdated = service.updateUser(payLoad);
		if (isUpdated) {
			return Response.ok("Registered").build();
		} else {
			return Response.status(Status.FORBIDDEN).build();
		}

	}
}
