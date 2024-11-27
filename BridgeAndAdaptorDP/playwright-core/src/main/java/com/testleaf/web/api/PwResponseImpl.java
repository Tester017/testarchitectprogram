package com.testleaf.web.api;

import java.util.Map;

import com.microsoft.playwright.APIResponse;

public class PwResponseImpl implements ResponseAPI{
	private APIResponse response;
	public PwResponseImpl(APIResponse response) {
		this.response = response;
	}

	@Override
	public int getStatusCode() {
		return response.status();
	}

	@Override
	public String getStatusMessage() {
		return response.statusText();
	}

	@Override
	public String getBody() {
		return response.body().toString();
	}

	@Override
	public Map<String, String> getHeaders() {
		return response.headers();
	}

}
