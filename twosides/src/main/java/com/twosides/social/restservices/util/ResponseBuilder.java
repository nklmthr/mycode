package com.twosides.social.restservices.util;

import javax.ws.rs.core.Response;

public class ResponseBuilder {

	public static Response buildSuccessResponse(Object entity) {
		return Response.ok(entity).header("ts_tid", WebContext.getInstance()).build();
	}

}
