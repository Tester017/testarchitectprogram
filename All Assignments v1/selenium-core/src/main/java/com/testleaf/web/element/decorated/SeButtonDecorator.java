package com.testleaf.web.element.decorated;

import java.util.logging.Logger;
import com.testleaf.web.element.Button;

public class SeButtonDecorator implements Button {

    private final Button button;
    private static final Logger logger = Logger.getLogger(SeButtonDecorator.class.getName());

    public SeButtonDecorator(Button button) {
        this.button = button;
    }

    @Override
    public void click() {
        logger.info("About to click on button");
        button.click();
        logger.info("Click on button is done");
    }

    @Override
    public boolean isEnabled() {
        return button.isEnabled();
    }

    @Override
    public boolean isVisible() {
        return button.isVisible();
    }

    @Override
    public String getAttribute(String attribute) {
        return button.getAttribute(attribute);
    }

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
}
