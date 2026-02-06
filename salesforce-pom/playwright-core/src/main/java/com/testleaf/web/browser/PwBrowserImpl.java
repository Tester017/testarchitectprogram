package com.testleaf.web.browser;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import com.testleaf.constants.BrowserType;
import com.testleaf.constants.LocatorType;
import com.testleaf.web.api.APIClient;
import com.testleaf.web.api.PwResponseImpl;
import com.testleaf.web.api.ResponseAPI;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Element;
import com.testleaf.web.element.Link;
import com.testleaf.web.element.PwButtonImpl;
import com.testleaf.web.element.PwEditImpl;
import com.testleaf.web.element.PwElementImpl;
import com.testleaf.web.element.PwLinkImpl;

public class PwBrowserImpl implements Browser, APIClient{

    private Playwright playwright;
    private com.microsoft.playwright.Browser pwBrowser;
    private BrowserContext context;
    private Page page;

    public PwBrowserImpl(BrowserType browserType) {
        playwright = Playwright.create();
        switch (browserType) {
            case CHROME:
                pwBrowser = playwright.chromium().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));
                break;
            case FIREFOX:
                pwBrowser = playwright.firefox().launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                throw new IllegalArgumentException("Unsupported BrowserType: " + browserType);
        }
        context = pwBrowser.newContext();
        page = context.newPage();
      
    }

    @Override
    public void navigateTo(String url) {
        page.navigate(url);
    }

    @Override
    public void closeBrowser() {
        if (context != null) {
            context.close();
        }
        if (pwBrowser != null) {
            pwBrowser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    private String buildSelector(LocatorType locatorType, String locator) {
        switch (locatorType) {
            case ID:
                return "#" + locator;
            case NAME:
                return "[name='" + locator + "']";
            case CLASS:
                return "." + locator;
            case LINK_TEXT:
                return "xpath=//a[text()='" + locator + "']";
            case XPATH:
                return "xpath=" + locator;
            default:
                throw new IllegalArgumentException("Unsupported LocatorType: " + locatorType);
        }
    }

    @Override
    public Element locateElement(LocatorType locatorType, String locator) {
        return new PwElementImpl(page.locator(buildSelector(locatorType, locator)).first());
    }

    @Override
    public Button locateButton(LocatorType locatorType, String locator) {
        return new PwButtonImpl(page.locator(buildSelector(locatorType, locator)).first());
    }

    @Override
    public Edit locateEdit(LocatorType locatorType, String locator) {
        return new PwEditImpl(page.locator(buildSelector(locatorType, locator)).first());
    }

    @Override
    public Link locateLink(LocatorType locatorType, String locator) {
        return new PwLinkImpl(page.locator(buildSelector(locatorType, locator)).first());
    }

    @Override
    public void maximize() {
        page.setViewportSize(1920, 1080);
    }

    @Override
    public String getTitle() {
        return page.title();
    }
    
    @Override
    public ResponseAPI get(String endPoint, String token) {
        APIResponse apiResponse = page.request().get(endPoint, RequestOptions
				.create()
				.setHeader("Authorization", "Bearer "+token)
				.setHeader("content-type", "application/json"));
        return new PwResponseImpl(apiResponse);
    }
    
    @Override
    public ResponseAPI post(String endPoint, String token, Object body) {
        APIResponse apiResponse = page.request()
        		.post(endPoint, RequestOptions
        				.create()
        				.setHeader("Authorization", "Bearer "+token)
        				.setHeader("content-type", "application/json")
        				.setData(body));
        return new PwResponseImpl(apiResponse);
    }

    @Override
    public ResponseAPI put(String endPoint, String token, Object body) {
        APIResponse apiResponse = page.request().put(endPoint, RequestOptions
				.create()
				.setHeader("Authorization", "Bearer "+token)
				.setHeader("content-type", "application/json")
				.setData(body));
        return new PwResponseImpl(apiResponse);
    }

    @Override
    public ResponseAPI delete(String endPoint, String token) {
        APIResponse apiResponse = page.request().delete(endPoint, RequestOptions
				.create()
				.setHeader("Authorization", "Bearer "+token)
				.setHeader("content-type", "application/json"));
        return new PwResponseImpl(apiResponse);
    }


}
