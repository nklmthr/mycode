package com.twosides.social.service.impl;

import com.twosides.social.service.HealthService;

public class HealthServiceImpl implements HealthService {
	private static final String HEALTH_CHECK_OK = "Health Check Ok";

	public String getHealth() {
		return HEALTH_CHECK_OK;
	}

}
