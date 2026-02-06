package api;

import io.restassured.response.Response;
import pojos.CreateResponse;
import pojos.BaseResponse;
import pojos.CreateEmployeeRequest;

public class EmployeeAPI extends BaseAPI {
    private static final String RESOURCE_ENDPOINT = "/master/employee";

    // Create Resource
    public CreateResponse createResource(CreateEmployeeRequest request) {
        String endpoint = baseUrl + RESOURCE_ENDPOINT;
        Response response = post(endpoint, request, getAuthHeaders());
        try {
            CreateResponse createResponse = mapper.readValue(response.asString(), CreateResponse.class);
            createResponse.setStatusCode(response.getStatusCode());
            return createResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Resource response", e);
        }
    }

    // Delete Resource (Assumed to be supported even though not present in YAML)
    public BaseResponse deleteResource(String resourceId) {
        String endpoint = baseUrl + RESOURCE_ENDPOINT + "/" + resourceId;
        Response response = delete(endpoint, getAuthHeaders());
        try {
            BaseResponse deleteResponse = new BaseResponse();
            deleteResponse.setStatusCode(response.getStatusCode());
            return deleteResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Delete Resource response", e);
        }
    }
}
