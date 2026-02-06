
package api;

import io.restassured.response.Response;
import pojos.CreateContactRequest;
import pojos.CreateResponse;

public class ContactAPI extends BaseAPI {
    private static final String CONTACT_ENDPOINT = "/Contact";
    
    public CreateResponse createContact(CreateContactRequest request) {
        String endpoint = baseUrl + CONTACT_ENDPOINT;
        Response response = post(endpoint, request, getAuthHeaders());
        try {
            CreateResponse createResponse = mapper.readValue(response.asString(), CreateResponse.class);
            createResponse.setStatusCode(response.getStatusCode());
            return createResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Contact response", e);
        }
    }

    
}
