package bo;

import com.google.gson.JsonArray;

public class DynamicListBO {
	
	private String listId ;
	private String listName ;
	private String criteriaType ;
	private JsonArray criteriaList ;
	private JsonArray keywordList;
	
	public String getListId() {
		return listId;
	}
	public void setListId(String listId) {
		this.listId = listId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getCriteriaType() {
		return criteriaType;
	}
	public void setCriteriaType(String criteriaType) {
		this.criteriaType = criteriaType;
	}
		
	public JsonArray getCriteriaList() {
		return criteriaList;
	}
	public void setCriteriaList(JsonArray criteriaList) {
		this.criteriaList = criteriaList;
	}
	public JsonArray getKeywordList() {
		return keywordList;
	}
	public void setKeywordList(JsonArray keywordList) {
		this.keywordList = keywordList;
	}
	

}
