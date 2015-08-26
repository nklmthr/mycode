package com.intuit.cto.fds.dash.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.intuit.cto.fds.dash.web.shared.exception.DASHServiceException;
import com.intuit.cto.fds.dash.web.shared.rpc.dto.ProviderDTO;
@RemoteServiceRelativePath("providerService")
public interface ProviderService extends RemoteService {
	ProviderDTO getProviderDetails(String providerId) throws DASHServiceException;
}
