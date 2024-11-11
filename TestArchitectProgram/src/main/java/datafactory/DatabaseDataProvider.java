package datafactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DatabaseDataProvider implements DataProvider{
	
	private static Logger logger = Logger.getLogger(DatabaseDataProvider.class.getName());
    private Map<String, String> dataMap;

	DatabaseDataProvider(){
		dataMap = new HashMap<String, String>();
        dataMap.put("username", "db_user");
        dataMap.put("password", "db_pass");
	}

	public String getData(String key) {
		logger.info("getting data for "+key+" from DatabaseDataProvider");
		return dataMap.get(key);
	}

}
