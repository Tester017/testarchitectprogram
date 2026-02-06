
package api;

import java.util.Map;

import io.restassured.response.Response;
import pojos.AccountRequest;
import pojos.CreateContactRequest;
import pojos.CreateResponse;

public class AccountAPI extends BaseAPI {
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
    
    public CreateResponse createAccount(AccountRequest request) {
        String endpoint = baseUrl + "/rmailuserpsc/account";
        Map<String, String> authHeaders = getAuthHeaders();
        System.err.println(endpoint);
        System.err.println(request);

        System.err.println(authHeaders);

        Response response = post(endpoint, request, authHeaders);
        try {
            CreateResponse createResponse = mapper.readValue(response.asString(), CreateResponse.class);
            createResponse.setStatusCode(response.getStatusCode());
            return createResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Create Contact response", e);
        }
    }

    
}
