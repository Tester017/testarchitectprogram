package com.testleaf.web.api;

public interface APIClient {

	ResponseAPI get(String endPoint, String token);
	ResponseAPI post(String endPoint, String token, Object body);
	ResponseAPI put(String endPoint, String token, Object body);
	ResponseAPI delete(String endPoint, String token);
}
