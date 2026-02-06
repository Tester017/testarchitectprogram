
package api;

import io.restassured.response.Response;
import pojos.CreateResourceRequest;
import pojos.CreateResponse;

public class CampaignAPI extends BaseAPI {
    private static final String CAMPAIGN_ENDPOINT = "/Campaign";
    
    // Create Campaign
    public CreateResponse createCampaign(CreateResourceRequest request) {
        String endpoint = baseUrl + CAMPAIGN_ENDPOINT;
        Response response = post(endpoint, request, getAuthHeaders());
        try {
            CreateResponse createResponse = mapper.readValue(response.asString(), CreateResponse.class);
            createResponse.setStatusCode(response.getStatusCode());
            return createResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Campaign response", e);
        }
    }
}
