package datafactory;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ExcelDataProvider implements DataProvider{

	private static Logger logger = Logger.getLogger(ExcelDataProvider.class.getName());
    private Map<String, String> dataMap;

	ExcelDataProvider(){
		dataMap = new HashMap<String, String>();
        dataMap.put("username", "excel_user");
        dataMap.put("password", "excel_pass");
	}
	
	public String getData(String key) {
		logger.info("getting data for "+key+" from ExcelDataProvider");
		return dataMap.get(key);
	}

}
