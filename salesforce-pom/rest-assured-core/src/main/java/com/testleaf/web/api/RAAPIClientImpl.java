package com.testleaf.web.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RAAPIClientImpl implements APIClient {
    
	private RequestSpecification given(String endPoint, String token) {
		return RestAssured.given()
        .header("Authorization", "Bearer " + token)
        .contentType(ContentType.JSON);
	}
	
    @Override
    public ResponseAPI get(String endPoint, String token) {
        Response response = given(endPoint, token).get(endPoint);
        return new RAResponseImpl(response);
    }

    @Override
    public ResponseAPI post(String endPoint, String token, Object body) {
        Response response = given(endPoint, token)
                .body(body)
                .post(endPoint);
        return new RAResponseImpl(response);
    }

    @Override
    public ResponseAPI put(String endPoint, String token, Object body) {
        Response response = given(endPoint, token)
                .body(body)
                .put(endPoint);
        return new RAResponseImpl(response);
    }

    @Override
    public ResponseAPI delete(String endPoint, String token) {
        Response response = given(endPoint, token).delete(endPoint);
        return new RAResponseImpl(response);
    }
}
