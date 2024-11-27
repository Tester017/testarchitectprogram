package com.testleaf.web.browser;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.testleaf.constants.LocatorType;
import com.testleaf.web.element.Button;
import com.testleaf.web.element.Dropdown;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Element;
import com.testleaf.web.element.Link;
import com.testleaf.web.element.PwButtonImpl;
import com.testleaf.web.element.PwDropdown;
import com.testleaf.web.element.PwEditImpl;
import com.testleaf.web.element.PwElementImpl;
import com.testleaf.web.element.PwLinkImpl;

public class PwBrowserImpl implements Browser {

    private Playwright playwright;
    private com.microsoft.playwright.Browser pwBrowser;
    private BrowserContext context;
    private Page page;

    public PwBrowserImpl() {
        playwright = Playwright.create();
        pwBrowser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = pwBrowser.newContext();
        page = context.newPage();
    }

    @Override
    public void navigateTo(String url) {
        page.navigate(url);
    }


    @Override
    public void closeBrowser() {
        context.close();
        pwBrowser.close();
        playwright.close();
    }

    private String buildSelector(LocatorType locatorType, String locator) {
        switch(locatorType) {
            case ID:
                return "#" + locator;
            case NAME:
                return "[name='" + locator + "']";
            case CLASS:
                return "." + locator;
            case XPATH:
                return "xpath=" + locator;
            case TEXT:
            	return locator;
            default:
                throw new IllegalArgumentException("Unsupported LocatorType: " + locatorType);
        }
    }
    
    @Override
    public Element locateElement(LocatorType locatorType, String locator) {
        return new PwElementImpl(page.locator(buildSelector(locatorType, locator)));
    }

    @Override
    public Button locateButton(LocatorType locatorType, String locator) {
        return new PwButtonImpl(page.locator(buildSelector(locatorType, locator)));
    }

    @Override
    public Edit locateEdit(LocatorType locatorType, String locator) {
        return new PwEditImpl(page.locator(buildSelector(locatorType, locator)));
    }
    
    @Override
    public Link locateLink(LocatorType locatorType, String locator) {
        return new PwLinkImpl(page.locator(buildSelector(locatorType, locator)));
    }

	@Override
	public void maximize() {
		
	}

	@Override
	public String getTitle() {
		return page.title();
	}

	@Override
	public Dropdown locateDropdown(LocatorType locatorType, String locator) {
		return new PwDropdown(page.locator(buildSelector(locatorType, locator)));
	}

}

