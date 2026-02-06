package auth;

import config.EnvironmentConfig;
import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OAuthStrategy implements AuthStrategy {

    @Override
    public String getToken() {
        EnvironmentConfig envConfig = ConfigManager.getEnvironmentConfig();

        String tokenUrl = "https://login.salesforce.com/services/oauth2/token";

        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("client_id", envConfig.salesforceClientId())
                .formParam("client_secret", envConfig.salesforceClientSecret())
                .formParam("username", envConfig.salesforceUsername())
                .formParam("password", envConfig.salesforcePassword())
                .formParam("grant_type", envConfig.salesforceGrantType())
                .when()
                .post(tokenUrl)
                .then()
                .extract()
                .response();

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to get OAuth token. Status code: " 
                    + response.getStatusCode() + ". Response: " + response.asString());
        }

        // Extract the access token from the JSON response.
        String accessToken = response.jsonPath().getString("access_token");
        return accessToken;
    }
}
