package com.testleaf.web.api;

import java.util.Map;
import java.util.stream.Collectors;

import io.restassured.response.Response;

public class RAResponseImpl implements ResponseAPI{
	
	private Response response;
	public RAResponseImpl(Response response) {
		this.response = response;
	}

	@Override
	public int getStatusCode() {
		return response.getStatusCode();
	}

	@Override
	public String getStatusMessage() {
		return response.getStatusLine();
	}

	@Override
	public String getBody() {
		return response.getBody().asString();
	}

	@Override
	public Map<String, String> getHeaders() {
		return response.getHeaders().asList().stream().collect(Collectors.toMap(h -> h.getName(), h -> h.getValue()));
	}

}
