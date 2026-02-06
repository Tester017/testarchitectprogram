
package api;

import io.restassured.response.Response;
import pojos.CreateOpportunityRequest;
import pojos.CreateResponse;

public class OpportunityAPI extends BaseAPI {
    private static final String RESOURCE_ENDPOINT = "/Opportunity";
    
    public CreateResponse createOpportunity(CreateOpportunityRequest request) {
        String endpoint = baseUrl + RESOURCE_ENDPOINT;
        Response response = post(endpoint, request, getAuthHeaders());
        try {
            CreateResponse createResponse = mapper.readValue(response.asString(), CreateResponse.class);
            createResponse.setStatusCode(response.getStatusCode());
            return createResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Opportunity response", e);
        }
    }
}
