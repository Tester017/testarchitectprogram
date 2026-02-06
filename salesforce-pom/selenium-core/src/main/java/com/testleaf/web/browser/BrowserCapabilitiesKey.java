package com.testleaf.web.browser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.testleaf.constants.BrowserType;
import org.openqa.selenium.Capabilities;

import java.util.*;

public class BrowserCapabilitiesKey {
    private final BrowserType browserType;
    private final String capabilitiesKey;

    public BrowserCapabilitiesKey(BrowserType browserType, Capabilities capabilities) {
        this.browserType = browserType;
        this.capabilitiesKey = serializeCapabilities(browserType, capabilities);
    }

    private String serializeCapabilities(BrowserType browserType, Capabilities capabilities) {
        Gson gson = new Gson();
        JsonObject json = new JsonObject();

        switch (browserType) {
            case CHROME:
                serializeBrowserOptions(
                    capabilities,
                    "goog:chromeOptions",
                    json,
                    gson
                );
                break;

            case FIREFOX:
                serializeBrowserOptions(
                    capabilities,
                    "moz:firefoxOptions",
                    json,
                    gson
                );
                break;

            default:
                serializeGeneralCapabilities(capabilities, json, gson);
                break;
        }

        return gson.toJson(json);
    }

    @SuppressWarnings("unchecked")
    private void serializeBrowserOptions(
        Capabilities capabilities,
        String optionsKey,
        JsonObject mainJson,
        Gson gson
    ) {
        Map<String, Object> browserOptionsMap = (Map<String, Object>) capabilities.getCapability(optionsKey);
        if (browserOptionsMap != null) {
            JsonObject browserJson = new JsonObject();

            List<String> args = (List<String>) browserOptionsMap.getOrDefault("args", Collections.emptyList());
            List<String> sortedArgs = new ArrayList<>(args);
            Collections.sort(sortedArgs);
            JsonArray argsArray = new JsonArray();
            for (String arg : sortedArgs) {
                argsArray.add(arg);
            }
            browserJson.add("args", argsArray);
            mainJson.add(optionsKey, browserJson);
        }
    }

    private void serializeGeneralCapabilities(
        Capabilities capabilities,
        JsonObject mainJson,
        Gson gson
    ) {
        Map<String, Object> capsMap = new TreeMap<>(capabilities.asMap());
        capsMap.remove("goog:chromeOptions");
        capsMap.remove("moz:firefoxOptions");

        JsonObject generalJson = new JsonObject();
        for (Map.Entry<String, Object> entry : capsMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof List) {
                JsonArray array = new JsonArray();
                for (Object item : (List<?>) value) {
                    array.add(gson.toJsonTree(item));
                }
                generalJson.add(key, array);
            } else {
                generalJson.add(key, gson.toJsonTree(value));
            }
        }

        mainJson.add("generalCapabilities", generalJson);
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public String getCapabilitiesKey() {
        return capabilitiesKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrowserCapabilitiesKey that = (BrowserCapabilitiesKey) o;

        if (browserType != that.browserType) return false;
        return capabilitiesKey.equals(that.capabilitiesKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(browserType, capabilitiesKey);
    }

    @Override
    public String toString() {
        return "BrowserCapabilitiesKey{" +
                "browserType=" + browserType +
                ", capabilitiesKey='" + capabilitiesKey + '\'' +
                '}';
    }
}
