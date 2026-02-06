package com.dz.prism.bo;

import java.util.LinkedHashMap;
import java.util.List;

public class BulkMoveBO {
	private String contactId;
	private String contactName;
	private String email;
	private LinkedHashMap<String, String> contactTableData;
	private String interestCount;
	private String subscriptionCount;
	private List<String> activityId;
	private List<String> interestTickerSymbols;
	private List<String> tickerSubscrData;
	private List<String> sectorSubscrData;
	private List<String> productSubscrData;
	private List<String> seniorAnalystSubscrData;
	private String accountName;
	private String newAccountName;
	private String address1;
	private String address2;
	private String jobTitle;
	private String relationship;
	private String phoneNo;
	private String interactedDateTime;
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getInteractedDateTime() {
		return interactedDateTime;
	}
	public void setInteractedDateTime(String interactedDateTime) {
		this.interactedDateTime = interactedDateTime;
	}
	public List<String> getTickerSubscrData() {
		return tickerSubscrData;
	}
	public void setTickerSubscrData(List<String> tickerSubscrData) {
		this.tickerSubscrData = tickerSubscrData;
	}
	public List<String> getSectorSubscrData() {
		return sectorSubscrData;
	}
	public void setSectorSubscrData(List<String> sectorSubscrData) {
		this.sectorSubscrData = sectorSubscrData;
	}
	public List<String> getSeniorAnalystSubscrData() {
		return seniorAnalystSubscrData;
	}
	public void setSeniorAnalystSubscrData(List<String> seniorAnalystSubscrData) {
		this.seniorAnalystSubscrData = seniorAnalystSubscrData;
	}
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LinkedHashMap<String, String> getContactTableData() {
		return contactTableData;
	}
	public void setContactTableData(LinkedHashMap<String, String> contactTableData) {
		this.contactTableData = contactTableData;
	}
	public String getInterestCount() {
		return interestCount;
	}
	public void setInterestCount(String interestCount) {
		this.interestCount = interestCount;
	}
	public String getSubscriptionCount() {
		return subscriptionCount;
	}
	public void setSubscriptionCount(String subscriptionCount) {
		this.subscriptionCount = subscriptionCount;
	}
	public List<String> getActivityId() {
		return activityId;
	}
	public void setActivityId(List<String> activityId) {
		this.activityId = activityId;
	}
	public List<String> getInterestTickerSymbols() {
		return interestTickerSymbols;
	}
	public void setInterestTickerSymbols(List<String> interestTickerSymbols) {
		this.interestTickerSymbols = interestTickerSymbols;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getNewAccountName() {
		return newAccountName;
	}
	public void setNewAccountName(String newAccountName) {
		this.newAccountName = newAccountName;
	}
	public List<String> getProductSubscrData() {
		return productSubscrData;
	}
	public void setProductSubscrData(List<String> productSubscrData) {
		this.productSubscrData = productSubscrData;
	}
	
}
