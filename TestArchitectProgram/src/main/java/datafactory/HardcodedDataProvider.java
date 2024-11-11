package datafactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class HardcodedDataProvider implements DataProvider{

	private static Logger logger = Logger.getLogger(HardcodedDataProvider.class.getName());
    private Map<String, String> dataMap;

    HardcodedDataProvider(){
		dataMap = new HashMap<String, String>();
        dataMap.put("username", "hardcoded_user");
        dataMap.put("password", "hardcoded_pass");
	}
    
	public String getData(String key) {
		logger.info("getting data for "+key+" from HardcodedDataProvider");
		return dataMap.get(key);
	}

}
