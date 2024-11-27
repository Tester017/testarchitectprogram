package com.testleaf.web.api;

public interface APIClient {

	ResponseAPI get(String endPoint);
	ResponseAPI post(String endPoint, Object object);
	ResponseAPI put(String endPoint, Object object);
	ResponseAPI delete(String endPoint);
}
