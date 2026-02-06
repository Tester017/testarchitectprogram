package com.testleaf.web.element;

public interface Element {
	
	boolean isEnabled();
	boolean isVisible();
	String getAttribute(String attribute);
	String getText();

}
