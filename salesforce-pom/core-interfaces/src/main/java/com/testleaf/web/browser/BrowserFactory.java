package com.testleaf.web.browser;

import com.testleaf.constants.BrowserType;

public interface BrowserFactory {
    Browser createBrowser(BrowserType browserType);
}
