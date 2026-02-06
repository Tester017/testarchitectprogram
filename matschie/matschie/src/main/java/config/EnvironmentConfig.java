package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/config.properties")
public interface EnvironmentConfig extends Config {
    @Key("base.url")
    String baseUrl();

    @Key("api.key")
    String apiKey();

    @Key("salesforce.client_id")
    String salesforceClientId();

    @Key("salesforce.client_secret")
    String salesforceClientSecret();

    @Key("salesforce.username")
    String salesforceUsername();

    @Key("salesforce.password")
    String salesforcePassword();

    @Key("salesforce.grant_type")
    @DefaultValue("password")
    String salesforceGrantType();
}
