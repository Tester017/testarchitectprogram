package api;

import io.restassured.response.Response;
import pojos.CreateContactRequest;
import pojos.CreateResponse;
import pojos.BaseResponse;

public class ContactAPI extends BaseAPI {
    private static final String RESOURCE_ENDPOINT = "/rmailuserpsc/contact";

    // Create Contact
    public CreateResponse createResource(CreateContactRequest request) {
        String endpoint = baseUrl + RESOURCE_ENDPOINT;
        Response response = post(endpoint, request, getAuthHeaders());
        try {
            CreateResponse createResponse = mapper.readValue(response.asString(), CreateResponse.class);
            createResponse.setStatusCode(response.getStatusCode());
            return createResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Contact response", e);
        }
    }

    // Delete Contact
    public BaseResponse deleteResource(String resourceId) {
        String endpoint = baseUrl + RESOURCE_ENDPOINT + "/" + resourceId;
        Response response = delete(endpoint, getAuthHeaders());
        try {
            BaseResponse deleteResponse = new BaseResponse();
            deleteResponse.setStatusCode(response.getStatusCode());
            return deleteResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Delete Contact response", e);
        }
    }
}
