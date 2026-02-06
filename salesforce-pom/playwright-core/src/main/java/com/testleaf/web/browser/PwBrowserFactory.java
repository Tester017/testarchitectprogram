package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;

public class PwBrowserFactory implements BrowserFactory {
    @Override
    public Browser createBrowser(BrowserType browserType) {
        return new PwBrowserImpl(browserType);
    }
}
