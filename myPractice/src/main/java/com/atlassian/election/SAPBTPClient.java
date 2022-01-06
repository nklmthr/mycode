package com.atlassian.election;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth.HttpMethod;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class SAPBTPClient {

	public static void main(String[] args) {
		try {
			OAuthClient client = new OAuthClient(new URLConnectionClient());
			TokenRequestBuilder reqBuilder = OAuthClientRequest
					.tokenLocation("https://spmproviderpoc-mllayeho.authentication.sap.hana.ondemand.com/oauth/token")
					.setGrantType(GrantType.CLIENT_CREDENTIALS)
					.setClientId("sb-clone-fa5694f2-98a2-4bff-b73e-59339c1a74d9!b33941|workflow!b2746").setClientSecret(
							"1ad5e884-40f9-4eb2-8d37-7d1e5d99c78f$12Mbf-Rz1TwYYwWWHtLEPZxGmGFUkVpVj1tlUmEQu7A=");

			OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(reqBuilder.buildBodyMessage(),
					OAuthJSONAccessTokenResponse.class);
			String accessToken = oAuthResponse.getAccessToken();
			OAuthBearerClientRequest request = new OAuthBearerClientRequest(
					"https://api.workflow-sap.cfapps.sap.hana.ondemand.com/workflow-service/rest/v1/workflow-instances");
			OAuthClientRequest req = request.buildBodyMessage();
			req.setBody("{\"definitionId\": \"riskreview\"}");
			req.addHeader("Authorization", "Bearer " + accessToken);
			req.addHeader("Content-Type", "application/json");

			OAuthResourceResponse res = client.resource(req, HttpMethod.POST, OAuthResourceResponse.class);

			System.out.println(res.getBody());
			String s = " \"riskDetails\": {\r\n" + "        \"supplier\": \"Merck\",\r\n"
					+ "        \"riskCategory\": \"Sustainability\",\r\n"
					+ "        \"riskDescription\": \"Risk due to environment\",\r\n"
					+ "        \"supplierEmail\": \"chandrahasa.mutyala@sap.com\",\r\n"
					+ "        \"findingId\": \"1c1712ac-d68c-4797-a51f-83e3909c1316\"\r\n" + "    }";

		} catch (Exception exn) {
			exn.printStackTrace();
		}

	}

}
