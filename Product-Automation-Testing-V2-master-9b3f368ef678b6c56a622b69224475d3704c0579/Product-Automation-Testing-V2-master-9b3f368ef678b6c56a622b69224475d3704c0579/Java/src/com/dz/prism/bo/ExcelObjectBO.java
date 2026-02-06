package com.dz.prism.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelObjectBO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, List<Map<String,String>>> outerMap;
	private XSSFWorkbook xssfWorkbook;
	
	private String sheetName;
	private String value;
	private int rowNumber;
	private int lastcolumnNumber;
	private String path;
	
	public Map<String, List<Map<String, String>>> getOuterMap() {
		return outerMap;
	}
	public void setOuterMap(Map<String, List<Map<String, String>>> outerMap) {
		this.outerMap = outerMap;
	}
	public XSSFWorkbook getXssfWorkbook() {
		return xssfWorkbook;
	}
	public void setXssfWorkbook(XSSFWorkbook xssfWorkbook) {
		this.xssfWorkbook = xssfWorkbook;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getLastcolumnNumber() {
		return lastcolumnNumber;
	}
	public void setLastcolumnNumber(int lastcolumnNumber) {
		this.lastcolumnNumber = lastcolumnNumber;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	

}
