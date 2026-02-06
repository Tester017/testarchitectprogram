package common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;

import bo.PeriodFilterBo;
import bo.ShowHideFilterBO;
import common.utils.PlayWrightApiUtils;
import common.utils.Reporter;
import pages.activity.ActivityPage;

public class ShowAndHideFilter extends Reporter {
     public void mandatoryBOFieldCheck(List<ShowHideFilterBO> filter ) {
		
		for (int j = 0; j < filter.size(); j++) {
			if(filter.get(j).getFilterName().isEmpty()) {
				Reporter.reportStep("The filter field Name is entered by empty value", "fail");
				throw new RuntimeException();
			}
			if(filter.get(j).getIsInclude() != true && filter.get(j).getIsInclude() != false) {
				Reporter.reportStep("The filters are entered by empty value", "fail");
				throw new RuntimeException();
			}
			if(filter.get(j).getTableColumnName().isEmpty()) {
				Reporter.reportStep("The TableColumn Names are entered by empty value", "fail");
				throw new RuntimeException();
			}
			if(filter.get(j).getTestDataRowCount()==0){
				Reporter.reportStep("The TestDataRowCount are entered by empty value", "fail");
				throw new RuntimeException();
			}
			if(filter.get(j).getPayloadFilterColumnName(null).isEmpty()) {
				Reporter.reportStep("The payLoad Filter Column Names are entered by empty value", "fail");
				throw new RuntimeException();
			}
	      }
	 }
     
     /**
      * This method is used to filter the data
      * @param filter
      * @param payloadDataFilterMap
      * @param period
      */
     public void getActivityFilteredDate(List<ShowHideFilterBO> filter,Map<String, String>payloadDataFilterMap, PeriodFilterBo period,Boolean isMultiFilterSearch) {
     	PlayWrightApiUtils api = new PlayWrightApiUtils();
     	
     	for (int i = 0; i < filter.size(); i++) { 
     		Reporter.createReportNode("Filter name : "+filter.get(i).getFilterName());
     		
     	     if(payloadDataFilterMap.size()==0) { 
     	    	 JsonObject activityPage =ActivityPage.getActivityPage(period);
     	    	 JsonArray ActivityTableData = getObjectAsJsonArray(activityPage, "data");
     	    	 List<String> uniqueAccName = api.getParticularKey(ActivityTableData, filter.get(i).getResponceFilterColumnName(), 10);
     	    	 JsonArray uniqueColumnData = new JsonArray();
     	    	 for(String data: uniqueAccName) {
     	    		 addFilterDataInList(uniqueColumnData,data);
     	    	 }
     	    	
     	    	 filter.get(i).setFilterData(uniqueColumnData);
     	    	 Reporter.reportStep("input data : "+uniqueAccName.toString()+"<b> Is Include filter:"+filter.get(i).getIsInclude()+"</b>", "info");
     	    	 //convert to payload formate
     	    	 JsonObject obj = new JsonObject();
     			 obj.add("value", uniqueColumnData);
     			 if(filter.get(i).getIsInclude()) {
     				 obj.addProperty("filter", "include");
     			 }else {
     				 obj.addProperty("filter", "exclude");
     			 }
     			 payloadDataFilterMap.put(filter.get(i).getPayloadFilterColumnName(), new Gson().toJson(obj));
     	    	 JsonArray restult =  filteredActivityTableData(payloadDataFilterMap,period);
     	    	 compareFilterData(filter.get(i), restult,filter.get(i).getResponceFilterColumnName(), filter.get(i).getIsInclude());
     	    	 if(!isMultiFilterSearch) {
     	    		payloadDataFilterMap.clear();
     	    	 }
     	     }else {
     	    	 
     	    	 JsonArray restult =  filteredActivityTableData(payloadDataFilterMap,period);
     	    	 List<String> uniqueData =null;
     	    	 if(filter.get(i).getIsInclude()) {
     	    	     uniqueData = api.getParticularKey(restult, filter.get(i).getResponceFilterColumnName(), 10);
     	    	 }else {
     	    		 uniqueData = api.getParticularKey(restult, filter.get(i).getResponceFilterColumnName(), 2);
     	    	 }
     	    	 JsonArray uniqueColumnData = new JsonArray();
     	    	 Set<String> uniqueTicker = new HashSet<>();
     	    	 for(String data: uniqueData) {
     	    		 //Contact tearsheet get contact id from the responce (html tag)
     	    		 if(filter.get(i).getFilterName().equalsIgnoreCase("Contact Name")) {
     	    			 String value = getValueByJsoupAttr(data, "a", "data-acc_id");
     	    			 addFilterDataInList(uniqueColumnData,value);
     	    		 //Ticker filter in split by (,)
     	    		 }else if(filter.get(i).getFilterName().equalsIgnoreCase("Ticker")) {
     	    			if(data.contains(",")) {
     	    				String [] value=data.split(",");
     	    				for(String splitedTicker: value) {
     	    					 if(uniqueColumnData.size()<10) {
     	    						 uniqueTicker.add(splitedTicker);
     	    					 }
     	    				 }
     	    			}
     	    			else {
     	    				if(!data.trim().isEmpty()) {
     	    					 uniqueTicker.add(data);
     	    				}
     	    			}
     	    			 
     		    	}else {
     		    	    addFilterDataInList(uniqueColumnData,data);
     		    	}
     	    	 }
     	    	 //adding ticker value in filter
     	    	 if(uniqueTicker.size()!=0) {
     	    		 for(String tick: uniqueTicker) {
     	    				uniqueColumnData.add(tick);
     	    			}
     	    	 }
     	    	 filter.get(i).setFilterData(uniqueColumnData);
     	    	 //convert to payload formate
     	    	 JsonObject obj = new JsonObject();
     			 obj.add("value", uniqueColumnData);
     			 if(filter.get(i).getIsInclude()) {
     				 obj.addProperty("filter", "include");
     			 }else {
     				 obj.addProperty("filter", "exclude");
     			 }
     			 payloadDataFilterMap.put(filter.get(i).getPayloadFilterColumnName(), new Gson().toJson(obj));
     			 JsonArray restultData =  filteredActivityTableData(payloadDataFilterMap,period);
     			 
     			 for(int k=0;k<filter.size();k++) {
     				 if(filter.get(k).getFilterData()!=null && filter.get(k).getFilterData().size()!=0) {
     					 Reporter.reportStep("Filter name "+filter.get(k).getFilterName() +" -  input data : "+filter.get(k).getFilterData()+"<b> Is Include filter:"+filter.get(k).getIsInclude()+"</b>", "info");
     					 compareFilterData(filter.get(k), restultData,filter.get(k).getResponceFilterColumnName(), filter.get(k).getIsInclude());
     				 }
     			 }
     	     }
     	}
     	
     	 
     	
     }
     		
     	/**
     	 * This method used to ignore the -,""
     	 * @param uniqueColumnData
     	 * @param value
     	 */
     	private void addFilterDataInList(JsonArray uniqueColumnData, String value) {
     	      value = value.trim();
     		if(!value.isEmpty() && !value.equalsIgnoreCase("-") ) {
     			uniqueColumnData.add(value);
     		}
     	
     }
     	/**
     	 * This method used to get the filter responce result
     	 * @param payloadDataFilter - it contain filter payload data in map
     	 * @param period - based on period filter
     	 * @return
     	 */
     	public JsonArray filteredActivityTableData( Map<String, String> payloadDataFilter, PeriodFilterBo period) {
     		try {
     			String path = "ws/interaction/activitytable";
     			Map<String, String> data = new HashMap<>();
     			data.put("enablelink", "true");
     			data.put("period", period.getPeriod());
     			data.put("startdate", "");
     			data.put("enddate", "");
     			data.put("isactivitytearsheetopen", "false");
     			data.put("issuperview", "0");
     			data.put("length", "150");
     			for(Map.Entry<String, String> filter : payloadDataFilter.entrySet() ) {
     				data.put(filter.getKey(), filter.getValue());
     			}
     			
     			APIResponse newIssue2 = PlayWrightApiUtils.doPostRequest(path, data);
     			// get All response to json object
     			JsonObject jsonObj = JsonParser.parseString(newIssue2.text()).getAsJsonObject();
     			JsonArray filterResult = getObjectAsJsonArray(jsonObj, "data");
     			
     			//compareFilterData(inputFilterData, filterResult, "accountid", true);
     			return filterResult;

     		} catch (Exception e) {
     			reportStep("Activity module - table api call failed", "fail");
     			e.printStackTrace();
     		}
     		return null;

     	}
     	/**
     	 * This method used to compare the filter responce in table
     	 * @param showHideFilterBO  details of filter fields
     	 * @param filterResult  Api Responce value
     	 * @param columnName  Api responce column name
     	 * @param isInclude   include or exclued
     	 */
     	public void compareFilterData(ShowHideFilterBO showHideFilterBO, JsonArray filterResult, String columnName ,boolean isInclude) {

     		Set<String> inputfilterDataInArrayList = new HashSet<>();
     		//ArrayList<String> filterResultDataInArrayList = new ArrayList<String>();
     		if (showHideFilterBO != null) {

     			for (int i = 0; i < showHideFilterBO.getFilterData().size(); i++) {
         			inputfilterDataInArrayList.add(showHideFilterBO.getFilterData().get(i).getAsString().trim());
     			}
     		} else {
     			System.out.println("input filter have no data");
     		}
     		if (filterResult != null) {
     			for (int j = 0; j < filterResult.size(); j++) {
     				 // filterResultDataInArrayList.add(filterResult.get(j).getAsString());
     					//System.out.println("Each filter result " + filterResultDataInArrayList.get(j));
     			    JsonObject rowData = filterResult.get(j).getAsJsonObject();
     				//String value =rowData.get(columnName).getAsString();
     				String value=getObjectValueAsString(rowData, columnName);
     				String accountId = getObjectValueAsString(rowData, "accountid");
     				accountId = getValueByJsoupAttr(accountId, "a", "data-acc_id");
     				if(showHideFilterBO.getFilterName().equalsIgnoreCase("Contact Name")) {
     					value = getValueByJsoupAttr(value, "a", "data-acc_id");
     				}
     				//ticker 
     				JsonArray uniqueColumnData = new JsonArray();
     				if(showHideFilterBO.getFilterName().equalsIgnoreCase("Ticker")) {
     	    			if(value.contains(",")) {
     	    				String [] ticList=value.split(",");
     	    				 for(String splitedTicker: ticList) {
     	    					 addFilterDataInList(uniqueColumnData,splitedTicker);
     	    				 }
     	    			}
     	    			else {
     	    				if(!value.trim().isEmpty()) {
     	    					 addFilterDataInList(uniqueColumnData,value);
     	    				}
     	    			}
     	    			for(JsonElement tick : uniqueColumnData) {
     	    				logShowFilterReport(inputfilterDataInArrayList,tick.getAsString().trim(),isInclude,accountId);
     	    			}
     	    			
     		    	}else {
     		    		logShowFilterReport(inputfilterDataInArrayList,value,isInclude,accountId);
     		    	}
     			}
     			
     		} else {
     			System.out.println("filter Result have no data");
     		}

     	}

     	private void logShowFilterReport(Set<String> inputfilterDataInArrayList, String value, boolean isInclude, String accountId) {
     		if(isInclude==true) {
     			if(inputfilterDataInArrayList.contains(value)) {
     				//Reporter.reportStep("Filter type : <b>Include </b>  input: "+inputfilterDataInArrayList.toString(), "info");
     				Reporter.reportStep("The values are displayed Correctly Actuall : "+value, "pass");
     			} else {
     				Reporter.reportStep(accountId + " Filter type : <b>Include </b>  The values are MisMatched expected"+inputfilterDataInArrayList+"Acutal value :"+value, "fail");
     			}
     		}
     		else {
     			if(inputfilterDataInArrayList.contains(value)) {
     				Reporter.reportStep(accountId +"  Filter type : <b> Exclude </b> The values are displayed wrongly As not expected :"+inputfilterDataInArrayList+"Acutal value :"+value, "fail");
     			} else {
     				//Reporter.reportStep("  input: "+inputfilterDataInArrayList.toString(), "info");
     				Reporter.reportStep(accountId +"  Filter type : <b> Exclude </b> The values are As expected"+inputfilterDataInArrayList+"Acutal value :"+value, "pass");
     			}
     		}
     		
     	}

     	public void getsingleIncludeFilteredData(List<ShowHideFilterBO> filter,Map<String, String>payloadDataFilterMap, PeriodFilterBo period, boolean b) {
    		PlayWrightApiUtils api = new PlayWrightApiUtils();
    		
    		for (int i = 0; i < filter.size(); i++) { 
    			Reporter.createReportNode("Filter name : "+filter.get(i).getFilterName());
    			
    		     if(payloadDataFilterMap.size()==0) {
    		    	 
    		    	 JsonObject activityPage = getActivityPage(period);
    		    	 JsonArray ActivityTableData = getObjectAsJsonArray(activityPage, "data");
    		    	 List<String> uniqueAccName = api.getParticularKey(ActivityTableData, filter.get(i).getResponceFilterColumnName(), 10);
    		    	 JsonArray uniqueColumnData = new JsonArray();
    		    	 for(String data: uniqueAccName) {
    		    		 uniqueColumnData.add(data);
    		    	 }
    		    	 filter.get(i).setFilterData(uniqueColumnData);
    		    	 Reporter.reportStep("input data : "+uniqueAccName.spliterator()+" Is Include filter:"+filter.get(i).getIsInclude(), "info");
    		    	 //convert to payload formate
    		    	 JsonObject obj = new JsonObject();
    				 obj.add("value", uniqueColumnData);
    				 if(filter.get(i).getIsInclude()) {
    					 obj.addProperty("filter", "include");
    				 }else {
    					 obj.addProperty("filter", "exclude");
    				 }
    				 payloadDataFilterMap.put(filter.get(i).getPayloadFilterColumnName(), new Gson().toJson(obj));
    		    	 JsonArray restult =  filteredActivityTableData(payloadDataFilterMap,period);
    		    	 compareFilterData(filter.get(i), restult,filter.get(i).getResponceFilterColumnName(), filter.get(i).getIsInclude());
    		    	 payloadDataFilterMap.clear();
    		     }
    		}
    	}
    	
    	public void getsingleExcludeFilteredData(List<ShowHideFilterBO> filter,Map<String, String>payloadDataFilterMap, PeriodFilterBo period) {
    		PlayWrightApiUtils api = new PlayWrightApiUtils();
    		
    		for (int i = 0; i < filter.size(); i++) { 
    			Reporter.createReportNode("Filter name : "+filter.get(i).getFilterName());
    			
    		     if(payloadDataFilterMap.size()==0) {
    		    	 
    		    	 JsonObject activityPage = getActivityPage(period);
    		    	 JsonArray ActivityTableData = getObjectAsJsonArray(activityPage, "data");
    		    	 List<String> uniqueAccName = api.getParticularKey(ActivityTableData, filter.get(i).getResponceFilterColumnName(), 10);
    		    	 JsonArray uniqueColumnData = new JsonArray();
    		    	 for(String data: uniqueAccName) {
    		    		 uniqueColumnData.add(data);
    		    	 }
    		    	 filter.get(i).setFilterData(uniqueColumnData);
    		    	 Reporter.reportStep("input data : "+uniqueAccName.spliterator()+" Is Include filter:"+filter.get(i).getIsInclude(), "info");
    		    	 //convert to payload formate
    		    	 JsonObject obj = new JsonObject();
    				 obj.add("value", uniqueColumnData);
    				 if(filter.get(i).getIsInclude()) {
    					 obj.addProperty("filter", "include");
    				 }else {
    					 obj.addProperty("filter", "exclude");
    				 }
    				 payloadDataFilterMap.put(filter.get(i).getPayloadFilterColumnName(), new Gson().toJson(obj));
    		    	 JsonArray restult =  filteredActivityTableData(payloadDataFilterMap,period);
    		    	 compareFilterData(filter.get(i), restult,filter.get(i).getResponceFilterColumnName(), filter.get(i).getIsInclude());
    		    	 payloadDataFilterMap.clear();
    		     }
    		}
    	}
     
    	public static JsonObject getActivityPage(PeriodFilterBo period) {
    		try {
    			String path = "ws/interaction/activitytable";
    			Map<String, String> data = new HashMap<>();
    			data.put("enablelink", "true");
    			data.put("period", period.getPeriod());
    			data.put("startdate", period.getStartdate());
    			data.put("enddate", period.getEnddate());
    			data.put("isactivitytearsheetopen", "false");
    			data.put("issuperview", "0");
    			data.put("length", "50");
    			// data.put("order",
    			// "[{\"column\":7,\"dir\":\"desc\",\"name\":\"contactName\"}]");
    			APIResponse newIssue2 = PlayWrightApiUtils.doPostRequest(path, data);
    			// get All response to json object
    			JsonObject jsonObj = JsonParser.parseString(newIssue2.text()).getAsJsonObject();

    			return jsonObj;

    		} catch (Exception e) {
    			reportStep("Activity module - table api call failed", "fail");
    			e.printStackTrace();
    		}
    		return null;
    	}	
    	
}
