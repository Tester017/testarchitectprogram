package test;

import org.testng.annotations.Test;

import annotation.Annotations;
import pages.MyList.DynamicListPage;
import pages.MyList.SimpleList;

public class MyList extends Annotations {
	
	SimpleList simpleList = new SimpleList();
	DynamicListPage dynamicList = new DynamicListPage();
	
	//V5 - PST 9607 - Newly added cols in - Mylist - CTS
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Mylist -> Static list -> Table data in Contact Tear Sheet - EditContact - Contact Info")
	public void MyListStaticListWithContactTearSheet() {
		simpleList.checkStaticListsWithCTSData();
	}
	

	//V5 - PST 9607 - Newly added cols in - Mylist - CTS
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Mylist -> Dynamic list -> Table data in Contact Tear Sheet - EditContact - Contact Info")
	public void MyListDynamicListWithContactTearSheet() {
		dynamicList.checkDynamicListsWithCTSData();
	}
}
