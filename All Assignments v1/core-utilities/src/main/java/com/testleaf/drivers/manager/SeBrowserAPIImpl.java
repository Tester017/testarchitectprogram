package com.testleaf.drivers.manager;

import com.testleaf.constants.BrowserType;
import com.testleaf.constants.LocatorType;
import com.testleaf.web.api.APIClient;
import com.testleaf.web.api.RAAPIClientImpl;
import com.testleaf.web.api.ResponseAPI;
import com.testleaf.web.browser.Browser;
import com.testleaf.web.browser.BrowserFactory;
import com.testleaf.web.browser.SeBrowserFactory;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Element;
import com.testleaf.web.element.Link;

public class SeBrowserAPIImpl implements Browser, APIClient {

    private Browser browser;
    private RAAPIClientImpl apiClient;

    public SeBrowserAPIImpl(BrowserType browserType) {
        BrowserFactory seFactory = new SeBrowserFactory();
        this.browser = seFactory.createBrowser(browserType);
        this.apiClient = new RAAPIClientImpl("00D5g00000LNGD5!AR0AQPcS7aLY.7d47XvlCbmlGORbeom6TctmL9xpFSJMHJRy4AyndOIxFgTRz._sRFuxHx2o39Qn_IchPnsOIszO2vI6FjIH");
        
        //this.browser = new SeBrowserImpl();
    }

    @Override
    public void navigateTo(String url) {
        browser.navigateTo(url);
    }

    @Override
    public void maximize() {
        browser.maximize();
    }

    @Override
    public void closeBrowser() {
        browser.closeBrowser();
    }

    @Override
    public String getTitle() {
        return browser.getTitle();
    }

    @Override
    public Element locateElement(LocatorType locatorType, String locator) {
        return browser.locateElement(locatorType, locator);
    }

    @Override
    public Edit locateEdit(LocatorType locatorType, String locator) {
        return browser.locateEdit(locatorType, locator);
    }

    @Override
    public Button locateButton(LocatorType locatorType, String locator) {
        return browser.locateButton(locatorType, locator);
    }

    @Override
    public Link locateLink(LocatorType locatorType, String locator) {
        return browser.locateLink(locatorType, locator);
    }

    // API-specific methods
    @Override
    public ResponseAPI get(String endpoint) {
        return apiClient.get(endpoint);
    }

    @Override
    public ResponseAPI post(String endpoint, Object body) {
        return apiClient.post(endpoint, body);
    }

    @Override
    public ResponseAPI put(String endpoint, Object body) {
        return apiClient.put(endpoint, body);
    }

    @Override
    public ResponseAPI delete(String endpoint) {
        return apiClient.delete(endpoint);
    }
}
