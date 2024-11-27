package com.testleaf.web.element;

public interface Element {
	
	boolean isEnabled();
	boolean isVisible();
	String getAttribute(String attribute);
	
	// add more methods after analysis of both playwright and selenium implementations

}
