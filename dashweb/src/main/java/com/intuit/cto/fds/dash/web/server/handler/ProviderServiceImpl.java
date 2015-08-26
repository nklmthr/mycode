package com.intuit.cto.fds.dash.web.server.handler;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.intuit.cto.fds.dash.web.client.service.ProviderService;
import com.intuit.cto.fds.dash.web.shared.exception.DASHServiceException;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ProviderDTO;

public class ProviderServiceImpl extends RemoteServiceServlet implements ProviderService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ProviderDTO getProviderDetails(String providerId) throws DASHServiceException {
		ProviderDTO providerDTO = null;
		if (null != providerId && !"".equalsIgnoreCase(providerId.trim())) {
			providerDTO = new ProviderDTO();
			providerDTO.setId("8313e9f2-b53b-4de3-a178-61e84d6e3b83");
			providerDTO.setName("Bank Of America");
			providerDTO.setActive(true);
			providerDTO.setCanPersistResponse(true);
			providerDTO.setLegacyId("2135");
			providerDTO.setType("InTuit Hosted");
			System.out.println("provider = " + providerDTO);
		} else {
			throw new DASHServiceException("Provider Id can not be Null");
		}
		return providerDTO;
	}

}
