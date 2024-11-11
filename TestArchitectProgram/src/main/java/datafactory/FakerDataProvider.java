package datafactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FakerDataProvider implements DataProvider{

	private static Logger logger = Logger.getLogger(FakerDataProvider.class.getName());
    private Map<String, String> dataMap;

    FakerDataProvider(){
		dataMap = new HashMap<String, String>();
        dataMap.put("username", "faker_user");
        dataMap.put("password", "faker_pass");
	}
    
	public String getData(String key) {
		logger.info("getting data for "+key+" from FakerDataProvider");
		return dataMap.get(key);
	}

}
