package api;

import io.restassured.response.Response;
import utils.RestClientWrapper;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import config.ConfigManager;

public abstract class BaseAPI {

    protected RestClientWrapper restClient;
    protected final String baseUrl;
    protected final String apiKey;
    protected final ObjectMapper mapper;
    

    // Helper method to build common headers
    protected Map<String, String> getAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        return headers;
    }

    public BaseAPI() {
        restClient = new RestClientWrapper();
        this.baseUrl = ConfigManager.getBaseUrl();
        this.apiKey = ConfigManager.getApiKey();
        this.mapper = new ObjectMapper();
    }

    protected Response get(String endpoint, Map<String, String> headers) {
        return restClient.get(endpoint, headers);
    }

    protected Response post(String endpoint, Object body, Map<String, String> headers) {
        return restClient.post(endpoint, body, headers);
    }

    protected Response patch(String endpoint, Object body, Map<String, String> headers) {
        return restClient.patch(endpoint, body, headers);
    }

    protected Response delete(String endpoint, Map<String, String> headers) {
        return restClient.delete(endpoint, headers);
    }
}
