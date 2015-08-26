package com.intuit.cto.fds.dash.web.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.intuit.cto.fds.dash.web.shared.exception.DASHServiceException;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ProviderDTO;

public interface ProviderServiceAsync extends RemoteService {
	void getProviderDetails(String providerId, AsyncCallback<ProviderDTO> provider);

}
