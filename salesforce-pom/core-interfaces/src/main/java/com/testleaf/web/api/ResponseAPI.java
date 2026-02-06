package com.testleaf.web.api;

import java.util.Map;

public interface ResponseAPI {

	int getStatusCode();
	String getStatusMessage();
	String getBody();
	Map<String, String> getHeaders();
}
