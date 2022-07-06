package customer.risk;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth.HttpMethod;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskController {

	@GetMapping("/hello")
	public String helloWorld() {
		System.out.println("Hello World!!!!");
		return "Hello World!!!!";
	}

	private static void test(Entity entity) {
		try {

			OAuthClient client = new OAuthClient(new URLConnectionClient());
			System.out.println("Start sending request...." + entity);
			TokenRequestBuilder reqBuilder = OAuthClientRequest
					.tokenLocation("https://lbn-canary.authentication.sap.hana.ondemand.com/oauth/token?grant_type=client_credentials")
					.setGrantType(GrantType.CLIENT_CREDENTIALS)
					.setClientId("sb-lbn-reminderservice-sb-dev!b468").setClientSecret(
							"UjWSzoG3e4FDgfBtk1xEvUzWQVg=");

			OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(reqBuilder.buildBodyMessage(),
					OAuthJSONAccessTokenResponse.class);
			String accessToken = oAuthResponse.getAccessToken();
			System.out.println("accessToken=" + accessToken);
			OAuthBearerClientRequest request = new OAuthBearerClientRequest(
					"https://api.workflow-sap.cfapps.sap.hana.ondemand.com/workflow-service/rest/v1/workflow-instances");
			OAuthClientRequest req = request.buildBodyMessage();
			String jsonContext = "{ \"riskDetails\": {" + "        \"supplier\":\" " + entity.getSupplier() + "\","
					+ "        \"riskCategory\":\" " + entity.getAreaOfInfluence() + "\","
					+ "        \"riskDescription\":\" " + entity.getDescription() + "\","
					+ "        \"supplierEmail\":\" " + entity.getSupplier() + "\"," + "        \"findingId\": \" "
					+ entity.getFindingId() + "\"" + "    }}";
			System.out.println("jsonContext=" + jsonContext);
			req.setBody("{\"definitionId\": \"riskreview\", \"context\":" + jsonContext + "}");
			req.addHeader("Authorization", "Bearer " + accessToken);
			req.addHeader("Content-Type", "application/json");

			OAuthResourceResponse res = client.resource(req, HttpMethod.POST, OAuthResourceResponse.class);

			System.out.println(res.getBody());			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		Entity entity = new Entity("Ebay0", "Sewage", "DEscription", "n.m@sap.com", "findingidgfuid");
		String jsonContext = "{ \"riskDetails\": {" + "        \"supplier\":\" " + entity.getSupplier() + "\","
				+ "        \"riskCategory\":\" " + entity.getAreaOfInfluence() + "\",\r\n"
				+ "        \"riskDescription\":\" " + entity.getDescription() + "\",\r\n"
				+ "        \"supplierEmail\":\" " + entity.getSupplier() + "\",\r\n" + "        \"findingId\": \" "
				+ entity.getFindingId() + "\"\r\n" + "    }";
		String s = "{\"definitionId\": \"riskreview\", \"context\":" + jsonContext + "}";
		System.out.println(s);
		test(entity);
	}

}

class Entity {
	String supplier;
	String areaOfInfluence;
	String description;;
	String supplierEmail;
	String findingId;

	public Entity(String supplier, String areaOfInfluence, String description, String supplierEmail, String findingId) {
		// TODO Auto-generated constructor stub
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getAreaOfInfluence() {
		return areaOfInfluence;
	}

	public void setAreaOfInfluence(String areaOfInfluence) {
		this.areaOfInfluence = areaOfInfluence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}

	public String getFindingId() {
		return findingId;
	}

	public void setFindingId(String findingId) {
		this.findingId = findingId;
	}

}