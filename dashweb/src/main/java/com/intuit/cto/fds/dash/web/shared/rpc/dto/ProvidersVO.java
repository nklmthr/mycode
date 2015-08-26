package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;
import java.util.List;


public class ProvidersVO implements Serializable {

	private static final long serialVersionUID = 1L;

	
	protected List<ProviderDTO> providers;

	public List<ProviderDTO> getProviders() {
		return providers;
	}

	public void setProviders(List<ProviderDTO> providers) {
		this.providers = providers;
	}

	//To avoid printing of all providers , print only size of providers returned
	@Override
	public String toString() {
		String noOfProviders = "Total Numbers of providers returned : " + Integer.toString(providers.size());
		return noOfProviders;
	}
}
