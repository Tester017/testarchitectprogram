package com.dz.prism.bo;

import java.util.HashMap;
import java.util.List;

public class AccountMergeBO {

	private HashMap<String, HashMap<String, String>> coverageTable;
	private String accountName ;
	private HashMap<String, String> accMergeTableData ;
	private HashMap<String, String> accMasterData ;
	private String accountID ;
	private int accountContactCount;
	private String activityInteractionID;
	private int activityCount;
	private List<String> accHistory;
	
	public List<String> getAccHistory() {
		return accHistory;
	}

	public void setAccHistory(List<String> accHistory) {
		this.accHistory = accHistory;
	}

	public int getAccountContactCount() {
		return accountContactCount;
	}

	public void setAccountContactCount(int accountContactCount) {
		this.accountContactCount = accountContactCount;
	}
	public int getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(int activityCount) {
		this.activityCount = activityCount;
	}

	public String getActivityInteractionID() {
		return activityInteractionID;
	}

	public void setActivityInteractionID(String activityInteractionID) {
		this.activityInteractionID = activityInteractionID;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public HashMap<String, String> getAccMergeTableData() {
		return accMergeTableData;
	}

	public void setAccMergeTableData(HashMap<String, String> accMergeTableData) {
		this.accMergeTableData = accMergeTableData;
	}

	public HashMap<String, String> getAccMasterData() {
		return accMasterData;
	}

	public void setAccMasterData(HashMap<String, String> accMasterData) {
		this.accMasterData = accMasterData;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public HashMap<String, HashMap<String, String>> getCoverageTable() {
		return coverageTable;
	}

	public void setCoverageTable(HashMap<String, HashMap<String, String>> coverageTable) {
		this.coverageTable = coverageTable;
	}
	
}
