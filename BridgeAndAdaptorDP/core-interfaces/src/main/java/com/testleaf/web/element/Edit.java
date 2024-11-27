package com.testleaf.web.element;

public interface Edit extends Element {

	void type(String text);
	String getValue();
	void clear();
}
