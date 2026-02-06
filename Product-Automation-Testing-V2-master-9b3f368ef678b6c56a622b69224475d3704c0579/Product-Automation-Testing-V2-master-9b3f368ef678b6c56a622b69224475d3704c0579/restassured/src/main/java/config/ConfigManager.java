package config;

import org.aeonbits.owner.ConfigFactory;

import auth.OAuthStrategy;

public class ConfigManager {
    private static final EnvironmentConfig config = ConfigFactory.create(EnvironmentConfig.class);
    private static String cachedApiKey;
    private static long tokenExpiryTime;

    public static String getBaseUrl() {
        return config.baseUrl();
    }

    public static EnvironmentConfig getEnvironmentConfig() {
        return config;
    }
    

    public static String getApiKey() {
        if (cachedApiKey == null || System.currentTimeMillis() >= tokenExpiryTime) {
            cachedApiKey = new OAuthStrategy().getToken();
            tokenExpiryTime = System.currentTimeMillis() + (60 * 60 * 1000);
        }
        return cachedApiKey;
    }
}
