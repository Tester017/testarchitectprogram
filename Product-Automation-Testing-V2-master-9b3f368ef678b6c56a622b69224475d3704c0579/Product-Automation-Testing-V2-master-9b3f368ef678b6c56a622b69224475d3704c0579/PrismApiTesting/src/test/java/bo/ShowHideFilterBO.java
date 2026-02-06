package bo;

import java.util.Map;

import com.google.gson.JsonArray;

public class ShowHideFilterBO {
  private int testDataRowCount=0;
  private boolean isInclude=true;
  private String filterName="";
  private String tableColumnName="";
  private String payloadFilterColumnName="";
  private String responceFilterColumnName="";
  private JsonArray filterData =null;
   
public String getPayloadFilterColumnName(Map<String, String> data) {
	return payloadFilterColumnName;
}
public void setPayloadFilterColumnName(String payloadFilterColumnName) {
	this.payloadFilterColumnName = payloadFilterColumnName;
}
public int getTestDataRowCount() {
	return testDataRowCount;
}
public void setTestDataRowCount(int testDataRowCount) {
	this.testDataRowCount = testDataRowCount;
}
 
public boolean getIsInclude() {
	return isInclude;
}
public void setInclude(boolean isInclude) {
	this.isInclude = isInclude;
}
public String getFilterName() {
	return filterName;
}
public void setFilterName(String filterName) {
	this.filterName = filterName;
}
public String getTableColumnName() {
	return tableColumnName;
}
public void setTableColumnName(String tableColumnName) {
	this.tableColumnName = tableColumnName;
}
public String getResponceFilterColumnName() {
	return responceFilterColumnName;
}
public void setResponceFilterColumnName(String responceFilterColumnName) {
	this.responceFilterColumnName = responceFilterColumnName;
}
public String getPayloadFilterColumnName() {
	return payloadFilterColumnName;
}
public JsonArray getFilterData() {
	return filterData;
}
public void setFilterData(JsonArray filterData) {
	this.filterData = filterData;
}
 

}
