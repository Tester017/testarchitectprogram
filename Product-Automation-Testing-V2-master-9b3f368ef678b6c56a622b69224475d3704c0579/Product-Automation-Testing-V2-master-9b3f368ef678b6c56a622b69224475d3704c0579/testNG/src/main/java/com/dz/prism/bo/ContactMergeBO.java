package com.dz.prism.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ContactMergeBO {
	
	String contactMasterEmail="";
	String contactChild1Email="";
	String contactChild2Email="";

	String contactMasterName="";
	String contactChild1Name="";
	String contactChild2Name="";
	
	int activityTotalCouunt = 0;
	int interestListTotalCount = 0;
	int mylistTotalCount = 0;
	int recentNotesCount = 0;
	
	public int getRecentNotesCount() {
		return recentNotesCount;
	}
	public void setRecentNotesCount(int recentNotesCount) {
		this.recentNotesCount = recentNotesCount;
	}
	 
	
	Set<String> Activity = new TreeSet<>();
	Set<String> TickerNames = new TreeSet<>();
	Set<String> Mylists = new HashSet<String>();
	List<String> RecentNotes = new ArrayList<String>();
	
	public List<String> getRecentNotes() {
		return RecentNotes;
	}
	public void setRecentNotes(List<String> recentNotes) {
		RecentNotes = recentNotes;
	}
	public Set<String> getMylists() {
		return Mylists;
	}
	public void setMylists(Set<String> mylists) {
		Mylists = mylists;
	}
	public String getContactMasterEmail() {
		return contactMasterEmail;
	}
	public void setContactMasterEmail(String contactMasterEmail) {
		this.contactMasterEmail = contactMasterEmail;
	}
	public String getContactChild1Email() {
		return contactChild1Email;
	}
	public void setContactChild1Email(String contactChild1Email) {
		this.contactChild1Email = contactChild1Email;
	}
	public String getContactChild2Email() {
		return contactChild2Email;
	}
	public void setContactChild2Email(String contactChild2Email) {
		this.contactChild2Email = contactChild2Email;
	}
	public String getContactMasterName() {
		return contactMasterName;
	}
	public void setContactMasterName(String contactMasterName) {
		this.contactMasterName = contactMasterName;
	}
	public String getContactChild1Name() {
		return contactChild1Name;
	}
	public void setContactChild1Name(String contactChild1Name) {
		this.contactChild1Name = contactChild1Name;
	}
	public String getContactChild2Name() {
		return contactChild2Name;
	}
	public void setContactChild2Name(String contactChild2Name) {
		this.contactChild2Name = contactChild2Name;
	}
	public int getActivityTotalCouunt() {
		return activityTotalCouunt;
	}
	public void setActivityTotalCouunt(int activityTotalCouunt) {
		this.activityTotalCouunt = activityTotalCouunt;
	}
	public int getInterestListTotalCount() {
		return interestListTotalCount;
	}
	public void setInterestListTotalCount(int interestListTotalCount) {
		this.interestListTotalCount = interestListTotalCount;
	}
	public int getMylistTotalCount() {
		return mylistTotalCount;
	}
	public void setMylistTotalCount(int mylistTotalCount) {
		this.mylistTotalCount = mylistTotalCount;
	}
	public Set<String> getActivity() {
		return Activity;
	}
	public void setActivity(Set<String> activity) {
		Activity = activity;
	}
	public Set<String> getTickerNames() {
		return TickerNames;
	}
	public void setTickerNames(Set<String> tickerNames) {
		TickerNames = tickerNames;
	}

}
