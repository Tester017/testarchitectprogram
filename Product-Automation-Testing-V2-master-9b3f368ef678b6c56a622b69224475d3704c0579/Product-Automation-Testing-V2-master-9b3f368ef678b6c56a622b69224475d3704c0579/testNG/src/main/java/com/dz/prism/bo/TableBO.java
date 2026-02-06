package com.dz.prism.bo;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

public class TableBO {
	private String tableName ="";
	private String tableHeaderXpath ="";
	private String tableHeaderColumnName ="";
	private String tableBodyXpath ="";
	private String clickAndSortColumnName ="";
	private String tableHyperLinkColumnName ="";
	private String searchFieldColumnName ="";
	private String excelColumnName ="";
	private By tableSearchField = null;
	private By tableColumnVisiblity = null;
	private By tableColumnVisiblityDropDown = null;
	private LinkedHashMap<String,Integer> headerWithPosition = new LinkedHashMap<>();
	private List<Map<String, String>> tableData = new LinkedList<Map<String,String>>();
	
	
	public String getExcelColumnName() {
		return excelColumnName;
	}
	public void setExcelColumnName(String excelColumnName) {
		this.excelColumnName = excelColumnName;
	}
	public String getSearchFieldColumnName() {
		return searchFieldColumnName;
	}
	public String setSearchFieldColumnName(String searchFieldColumnName) {
		return this.searchFieldColumnName = searchFieldColumnName;
	}
	public String getTableHyperLinkColumnName() {
		return tableHyperLinkColumnName;
	}
	public List<Map<String, String>> getTableData() {
		return tableData;
	}
	public void setTableData(List<Map<String, String>> tableData) {
		this.tableData = tableData;
	}
	public void setTableHyperLinkColumnName(String tableHyperLinkColumnName) {
		this.tableHyperLinkColumnName = tableHyperLinkColumnName;
	}
	public String getClickAndSortColumnName() {
		return clickAndSortColumnName;
	}
	public void setClickAndSortColumnName(String clickAndSortColumnName) {
		this.clickAndSortColumnName = clickAndSortColumnName;
	}
	
	public String getTableHeaderXpath() {
		return tableHeaderXpath;
	}
	
	public void setTableHeaderXpath(String tableHeaderXpath) {
		this.tableHeaderXpath = tableHeaderXpath;
	}
	public LinkedHashMap<String, Integer> getHeaderWithPosition() {
		return headerWithPosition;
	}
	public void setHeaderWithPosition(LinkedHashMap<String, Integer> headerWithPosition) {
		this.headerWithPosition = headerWithPosition;
	}
	public String getTableBodyXpath() {
		return tableBodyXpath;
	}
	public void setTableBodyXpath(String tableBodyXpath) {
		this.tableBodyXpath = tableBodyXpath;
	}
	public By getTableSearchField() {
		return tableSearchField;
	}
	public void setTableSearchField(By tableSearchField) {
		this.tableSearchField = tableSearchField;
	}
	public By getTableColumnVisiblity() {
		return tableColumnVisiblity;
	}
	public void setTableColumnVisiblity(By tableColumnVisiblity) {
		this.tableColumnVisiblity = tableColumnVisiblity;
	}
	public By getTableColumnVisiblityDropDown() {
		return tableColumnVisiblityDropDown;
	}
	public void setTableColumnVisiblityDropDown(By tableColumnVisiblityDropDown) {
		this.tableColumnVisiblityDropDown = tableColumnVisiblityDropDown;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableHeaderColumnName() {
		return tableHeaderColumnName;
	}
	public void setTableHeaderColumnName(String tableHeaderColumnName) {
		this.tableHeaderColumnName = tableHeaderColumnName;
	}
	
	
}
