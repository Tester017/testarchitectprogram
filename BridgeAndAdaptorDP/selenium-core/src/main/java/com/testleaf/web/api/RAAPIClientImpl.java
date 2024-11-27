package com.testleaf.web.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RAAPIClientImpl implements APIClient{
	
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
