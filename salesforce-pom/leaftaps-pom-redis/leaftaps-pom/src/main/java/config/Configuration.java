package config;

import org.aeonbits.owner.*;

//@LoadPolicy(LoadType.MERGE)
@Config.Sources({
	"classpath:application.properties",
	"classpath:grid.properties"
})
public interface Configuration extends Config{

	// Key - value pair
	@Key("app.user.name")
	String userName();
	
	@Key("app.user.password")
	String password();
	
	@Key("app.url")
	String url();
	
	@Key("db.url")
	String dbUrl();
	
	@Key("db.user")
	String dbUser();
	
	@Key("db.password")
	String dbPassword();
	
}
