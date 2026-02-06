package com.testleaf.web.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RAAPIClientImpl implements APIClient{
	
	private String token;
	
	public RAAPIClientImpl(String token) {
		this.token = token;
	}
	
	public RequestSpecification request() {
		return RestAssured.given().header("Authorization", token).contentType(ContentType.JSON);
	}
	
	@Override
	public ResponseAPI get(String endPoint) {
		return new RAResponseImpl(RestAssured.get(endPoint));
	}	

	@Override
	public ResponseAPI post(String endPoint, Object body) {
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body).when().post(endPoint);
		return new RAResponseImpl(response);
	}

	@Override
	public ResponseAPI put(String endPoint, Object body) {
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body).when().put(endPoint);
		return new RAResponseImpl(response);
	}

	@Override
	public ResponseAPI delete(String endPoint) {
		Response response = RestAssured.delete(endPoint);
		return new RAResponseImpl(response);
	}

}
