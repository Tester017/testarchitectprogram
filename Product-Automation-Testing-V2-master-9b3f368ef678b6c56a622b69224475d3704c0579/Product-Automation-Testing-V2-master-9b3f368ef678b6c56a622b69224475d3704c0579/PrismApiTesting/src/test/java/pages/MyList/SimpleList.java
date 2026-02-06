package pages.MyList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opentest4j.AssertionFailedError;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.microsoft.playwright.APIResponse;

import common.utils.PlayWrightApiUtils;
import common.utils.Reporter;
import constant.ProductConstant;
import pages.contactTearSheet.EditContactPages;

public class SimpleList extends Reporter {
	
	/**
	 * This method used to get Mylist Page Json Oject
	 * Mylist Module 
	 * @author lakshmi
	 * @return JsonObject
	 */
	public static JsonObject getMyListModulePage() {
		try {
			String path= "ws/listmgmtv1/getmylist";
		    Map<String, String> data = new HashMap<>();
		    data.put("listsort", "newFirst");
		    // data.put("order", "[{\"column\":7,\"dir\":\"desc\",\"name\":\"contactName\"}]");
		    APIResponse newIssue2 = PlayWrightApiUtils.doPostRequest(path, data);
		    //get All response to json object
		    JsonObject jsonObj =JsonParser.parseString(newIssue2.text()).getAsJsonObject();
		    
		    return jsonObj;
			
		}catch (Exception e) {
			reportStep("Mylist module - table api call failed", "fail");
			e.printStackTrace();
		} 
		return null;
	}
	
	
	/**
	 * This method used to get Mylist Page - Specific list Json Oject
	 * Mylist Module - specific list
	 * @author lakshmi
	 * @return JsonObject
	 */
	public static JsonObject getParticularListPage(String listType, String listID) {
		try {
			String path= "ws/listmgmtv1/searchbycriteria";
		    Map<String, String> data = new HashMap<>();
		    
		    data.put("query", "[\"\"]");
		    data.put("type", listType);
		    data.put("listid", listID);
		    data.put("criteria", "");
		    data.put("criterialist", "");
//		    
//		    data.put("showcovaccts", "0");
//		    data.put("acctConJson", "{\"keyname\":\"advfilter\",\"value\":{}}");
//		    data.put("savesearch", "false");
		 
		    APIResponse newIssue2 = PlayWrightApiUtils.doPostRequest(path, data);
		    //get All response to json object
		    JsonObject jsonObj =JsonParser.parseString(newIssue2.text()).getAsJsonObject();
		    
		    return jsonObj;
			
		}catch (Exception e) {
			reportStep("Mylist module - List ID : " + listID + " table api call failed", "fail");
			e.printStackTrace();
		} 
		return null;
	}
	
	
	
	
	public Map<String, String> getStaticListsInMylist() {
		Map<String,String> simpleList = new HashMap<String, String>();
		String listName = "";
		String listID = "";
		JsonObject jobj = getMyListModulePage();
		JsonArray arr = jobj.get("myList").getAsJsonArray();
		for( int i=0; i<arr.size(); i++ ) {
			JsonObject eachRow = JsonParser.parseString(arr.get(i).toString()).getAsJsonObject();
			String listType = getObjectValueAsString(eachRow, "list_type").trim();
			if(listType.equalsIgnoreCase("static")) {
				listName = getObjectValueAsString(eachRow, "list_name");
				listID = getObjectValueAsString(eachRow, "id");
				simpleList.put(listName, listID);
			}
			
			
		}
		return simpleList;
	}
	
	
	public Map<String, String> getDynamicListsInMylist() {
		Map<String,String> simpleList = new HashMap<String, String>();
		String listName = "";
		String listID = "";
		JsonObject jobj = getMyListModulePage();
		JsonArray arr = jobj.get("myList").getAsJsonArray();
		for( int i=0; i<arr.size(); i++ ) {
			JsonObject eachRow = JsonParser.parseString(arr.get(i).toString()).getAsJsonObject();
			String listType = getObjectValueAsString(eachRow, "list_type").trim();
			if(listType.equalsIgnoreCase("Dynamic")) {
				listName = getObjectValueAsString(eachRow, "list_name");
				listID = getObjectValueAsString(eachRow, "id");
			}
			
			simpleList.put(listName, listID);
		}
		return simpleList;
	}
	
	
	//V5 - Check Mylist for newly added colmns and validation in CTS
	public void checkStaticListsWithCTSData() {
		
		EditContactPages editContact = new EditContactPages();
		
		HashMap<String, String> colwithValue = new HashMap<String, String>();
		Map<String, String> staticList = getStaticListsInMylist();
		int index = staticList.size();
		int count=0;
		if(!staticList.isEmpty()) {
			for(Map.Entry m : staticList.entrySet()){ 
				count++;
				String list = (String) m.getKey();
				String id = (String) m.getValue();
				Reporter.createReportNode("List Name : " + list + " ~~  List ID : " + id);
				JsonObject obj = getParticularListPage("static", id);
				if(checkListHasData(obj)) {
					JsonObject dataObj = obj.get("data").getAsJsonObject();
					JsonArray colArr = dataObj.get("column").getAsJsonArray();
					JsonArray rowArr = dataObj.get("data").getAsJsonArray();
					for(int i=0; i<rowArr.size(); i++) {
						if(i==4) break;
						JsonArray row = rowArr.get(i).getAsJsonArray();
						for(int j=0; j<colArr.size(); j++) {
							String value = row.get(j).getAsString();
							String key = colArr.get(j).getAsString();
							colwithValue.put(key, value);
						}
						
						String contId = colwithValue.get("contact_id");
						String contEmail = colwithValue.get("contact_email");
						String contName = colwithValue.get("contact_name");
						String contResEmail = colwithValue.get("researchEmail");
						String contSecEmail = colwithValue.get("secondaryEmail");
						String contPersEmail = colwithValue.get("personalEmail");
						if(contPersEmail.equals("-")) contPersEmail = "";
						String contOther = colwithValue.get("otherHome");
						String contPh1 = colwithValue.get("contact_phone1");
						if(contPh1.equals("-")) contPh1 = "";
						String contPhone = colwithValue.get("contact_phone2");
						if(contPhone.equals("-")) contPhone = "";
						//String contPhoneType1 = colwithValue.get("phone_type1");
						
						JsonObject contactObj = editContact.getContactInfoPage(contId);
						JsonArray dataArr = contactObj.get("data").getAsJsonArray();
						JsonObject contDataObj = dataArr.get(0).getAsJsonObject();
						
						String ctsName = getObjectValueAsString(contDataObj, "contactName");
						String ctsEmail = getObjectValueAsString(contDataObj, "contactEmail");
						String ctsmob = getObjectValueAsString(contDataObj, "contactMobile");
						String ctsPH = getObjectValueAsString(contDataObj, "contactPH");
						String ctsResMail = getObjectValueAsString(contDataObj, "researchMail");
						
						JsonObject crmDetails = contDataObj.get("crmDet").getAsJsonObject();
						JsonArray emailLabel = crmDetails.get("emailLabel").getAsJsonArray();
						JsonArray emailValues = crmDetails.get("email").getAsJsonArray();
						
						HashMap<String, String> mailWithValue = new HashMap<String, String>();
						for(int c=0; c<emailLabel.size(); c++) {
							String label = emailLabel.get(c).getAsString();
							String value = emailValues.get(c).getAsString();
							mailWithValue.put(label, value);
						}
						
						String ctsEmail1 = getObjectValueAsString(crmDetails, "contactEmail");
						
						Reporter.reportStep("\b CONTACT NAME : " + contName, "info");
						
						compareTwoString(contName, ctsName, "Contact Name");
						//compareTwoString(contPhone, ctsmob, "Contact Phone");
						
						
						//Comparing Mail values
						if(mailWithValue.containsKey("Personal E-mail")) {
							compareTwoString(contPersEmail, mailWithValue.get("Personal E-mail"), "Personal Email");
						}
						
						if(mailWithValue.containsKey("Secondary E-mail")) {
							compareTwoString(contSecEmail, mailWithValue.get("Secondary E-mail"), "Secondary Email");
						}
						
						if(mailWithValue.containsKey("Primary E-mail")) {
							compareTwoString(contEmail, mailWithValue.get("Primary E-mail"), "Primary Email");
						}
						
						compareTwoString(contResEmail, ctsResMail, "Contact Research Email");
						
						//Comparing Phone values
						JsonArray phoneLabel = crmDetails.get("phoneLabel").getAsJsonArray();
						JsonArray phoneValues = crmDetails.get("phone").getAsJsonArray();
						
						HashMap<String, String> phoneWithValue = new HashMap<String, String>();
						for(int k=0; k<phoneLabel.size(); k++) {
							String label = phoneLabel.get(k).getAsString();
							String value = phoneValues.get(k).getAsString();
							phoneWithValue.put(label, value);
						}
						
						if(phoneWithValue.containsKey("Desk/Work")) {
							compareTwoString(contPh1, phoneWithValue.get("Desk/Work"), "Desk/Work");
						}
						
						if(phoneWithValue.containsKey("Mobile")) {
							compareTwoString(contPhone, phoneWithValue.get("Mobile"), "Mobile");
						}
						
						if(phoneWithValue.containsKey("Home")) {
							compareTwoString(contOther, phoneWithValue.get("Home"), "Home");
						}
						
						
					}
				}
				if(count==6) break;
				
			}
		}else {
			Reporter.reportStep("No Static Lists in this environment", "info");
		}
		
	}
	
	
	public boolean checkListHasData(JsonObject particularListObj) {
		
		int totRecords = particularListObj.get("totRecords").getAsInt();
		if(totRecords>0) {
			Reporter.reportStep("List has data/contacts", "pass");
			return true;
		}
		return false;
		
	}
	
		
	/**
	 * This method used to get Mylist Page Json Oject
	 * Mylist Module 
	 * @author lakshmi
	 * @return JsonObject
	 */
	public static JsonObject getMyListStaticListColumnSort(String testdata, String listID, String colname) {
		try {
			String path= "ws/tableconfig/settblfields";
		    Map<String, String> data = new HashMap<>();
		    data.put("tblid", "table-id-"+listID+"v1");
		    data.put("settings", testdata);
		    data.put("sorting", "[{\"columnId\":\""+colname+"\",\"sortAsc\":true}]");
		    APIResponse newIssue2 = PlayWrightApiUtils.doPostRequest(path, data);
		    //get All response to json object
		    JsonObject jsonObj =JsonParser.parseString(newIssue2.text()).getAsJsonObject();
		    
		    return jsonObj;
			
		}catch (Exception e) {
			reportStep("Mylist module - table api call failed", "fail");
			e.printStackTrace();
		} 
		return null;
	}
	
	public JsonObject getStaticListSortingJson() {
		JsonObject sortJson= null;
		try {
			sortJson = (JsonObject) JsonParser.parseReader(new FileReader("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/MylistSortStaticList.json"));
		
	 	} catch (JsonIOException e) {
	 		reportStep(e.getMessage(), "fail");
		e.printStackTrace();
	 	} catch (JsonSyntaxException e) {
		reportStep(e.getMessage(), "fail");
		e.printStackTrace();
	 	} catch (FileNotFoundException e) {
		reportStep(e.getMessage(), "fail");
		e.printStackTrace();
	 	}
	
	 	if (sortJson.isJsonNull()) {
	 		reportStep("Test Data not Found. Please check MylistSortStaticList.json file", "fail");
	 		throw new AssertionFailedError("Test Data not Found. Please check MylistSortStaticList.json file");
	 	}
	
	return sortJson;
	}
	
	
	public String getSortJsonAsString(JsonObject obj) {
		String testDataStr = obj.toString();
		return testDataStr;
	}


	
}
