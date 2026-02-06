
package api;

import io.restassured.response.Response;
import pojos.LeadRequest;
import pojos.BaseResponse;
import pojos.CreateResponse;

public class LeadAPI extends BaseAPI {
    private static final String LEAD_ENDPOINT = "/Lead";
    
    public CreateResponse createLead(LeadRequest request) {
        String endpoint = baseUrl + LEAD_ENDPOINT;
        Response response = post(endpoint, request, getAuthHeaders());
        try {
        	CreateResponse leadResponse = mapper.readValue(response.asString(), CreateResponse.class);
            leadResponse.setStatusCode(response.getStatusCode());
            return leadResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Lead response", e);
        }
    }
    
    public BaseResponse deleteLead(String leadId) { 
        String endpoint = baseUrl + LEAD_ENDPOINT + "/" + leadId; 
        Response response = delete(endpoint, getAuthHeaders());
        try {
            BaseResponse deleteResponse = new BaseResponse();
            deleteResponse.setStatusCode(response.getStatusCode());
            return deleteResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Delete Lead response", e);
        }
    }
}
