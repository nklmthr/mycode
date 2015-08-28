package com.intuit.cto.fds.dash.web.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.intuit.cto.fds.dash.web.client.service.ProviderService;
import com.intuit.cto.fds.dash.web.shared.exception.DASHServiceException;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ChannelType;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ChannelVO;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.Ofx2VO;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ProviderDTO;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ServiceVO;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.WebIntegrationVO;

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
			if (providerDTO.getChannels() == null) {
				providerDTO.setChannels(new ArrayList<ChannelVO>());
			}
			ChannelVO channel1 = new Ofx2VO();
			channel1.setChannelType("Ofx2");
			channel1.setUrl("https://fiurlofx.com");
			channel1.setUsesMFA(true);
			channel1.setPreference(1);
			List<ServiceVO> services1 = new ArrayList<ServiceVO>();
			ServiceVO service1 = new ServiceVO();
			service1.setName("1098-INT");
			service1.setType("TAX");
			services1.add(service1);
			ServiceVO service2 = new ServiceVO();
			service2.setName("1099-B");
			service2.setType("TAX");
			services1.add(service2);
			channel1.setServices(services1);
			providerDTO.getChannels().add(channel1);

			ChannelVO channel2 = new WebIntegrationVO();
			channel2.setChannelType("WebIntegration");
			channel2.setUrl("https://fiurlweb.com");
			channel2.setUsesMFA(false);
			channel2.setPreference(2);
			List<ServiceVO> services2 = new ArrayList<ServiceVO>();
			ServiceVO service3 = new ServiceVO();
			service3.setName("1095");
			service3.setType("TAX");
			services2.add(service3);
			ServiceVO service4 = new ServiceVO();
			service4.setName("ACCOUNT");
			service4.setType("AVS");
			services2.add(service4);
			channel1.setServices(services2);
			providerDTO.getChannels().add(channel2);
			System.out.println("provider = " + providerDTO);
		} else {
			throw new DASHServiceException("Provider Id can not be Null");
		}
		return providerDTO;
	}

}
