package api;

import io.restassured.response.Response;
import pojos.CreateActivityRequest;
import pojos.CreateResponse;
import pojos.BaseResponse;

public class ActivityAPI extends BaseAPI {
    private static final String RESOURCE_ENDPOINT = "/rmailuserpsc/activity";

    // Create Activity
    public CreateResponse createResource(CreateActivityRequest request) {
        String endpoint = baseUrl + RESOURCE_ENDPOINT;
        Response response = post(endpoint, request, getAuthHeaders());
        try {
        	if (response.getStatusCode() != 200) {
        	    throw new RuntimeException("API failed with status code: " + response.getStatusCode() +
        	                               "\nResponse body:\n" + response.getBody().asString());
        	}
            CreateResponse createResponse = mapper.readValue(response.asString(), CreateResponse.class);
            createResponse.setStatusCode(response.getStatusCode());
            return createResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Activity response", e);
        }
    }

    // Delete Activity
    public BaseResponse deleteResource(String resourceId) {
        String endpoint = baseUrl + RESOURCE_ENDPOINT + "/" + resourceId;
        Response response = delete(endpoint, getAuthHeaders());
        try {
            BaseResponse deleteResponse = new BaseResponse();
            deleteResponse.setStatusCode(response.getStatusCode());
            return deleteResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Delete Activity response", e);
        }
    }
}
