package common;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.codoid.products.exception.FilloException;
import com.opencsv.exceptions.CsvException;

import common.utils.PlayWrightApiUtils;
import constant.ProductConstant;
import utils.FilloExcelDataReader;

public class XMLCreate {

	private static XmlSuite suiteB;
	private static XmlTest test;

	public static void main(String[] args) throws IOException, CsvException {
		getProdProperty();
		initXML();
		generateXMLSuite();
		String xmlD = suiteB.toXml();
		writeToXMLD(xmlD);
	}

	private static void initXML() {

		try {
			GeneralUtils.getAllClassAndMethodNames();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		suiteB = new XmlSuite();
		suiteB.setName("Suite");
//		suiteB.setParallel("classes");
		suiteB.setVerbose(3);
		suiteB.setDataProviderThreadCount(12);
		//suiteB.setParallel(ParallelMode.METHODS);
		//suiteB.setThreadCount(20);
		suiteB.addListener("listeners.DriverListener");
	}

	private static void generateXMLSuite() throws IOException, CsvException {
		test = new XmlTest(suiteB);

		test.setName("Test");
		test.setParallel(ParallelMode.METHODS);
		test.setThreadCount(20);
		
		List<XmlClass> classes = new ArrayList<XmlClass>();

		String sheetPath = ".\\src\\test\\resources\\xmlData\\scenarios.xlsx";
		String sheetName = "Scenarios";
		String classCol = "Modules";
		String methodCol = "Method";
		try {
			LinkedHashMap<String,List<String>> classAndMethod =  FilloExcelDataReader.getMethodsToRun(sheetPath, sheetName, classCol, methodCol);
			//Iterator<String> classAndMethodsIt = classAndMethod.iterator();
			classAndMethod.forEach((key, value) -> {
				
				List<XmlInclude> xmlIncludes = new ArrayList<XmlInclude>();
				
				value.forEach(e -> {
					XmlInclude xmlInclude = new XmlInclude(e);
					xmlIncludes.add(xmlInclude);
					  
					});
				XmlClass xmlClass = new XmlClass("test." + key);
				xmlClass.setIncludedMethods(xmlIncludes);
				classes.add(xmlClass);
			});
			
		} catch (FilloException e) {
			e.printStackTrace();
		}
		
		test.setClasses(classes);
	}

	private static void writeToXMLD(String xml) {
		try {
			@SuppressWarnings("resource")
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("TestNgD.xml"), "utf-8"));
			writer.write(xml);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void getProdProperty() throws IOException {
		Properties productProperty = PlayWrightApiUtils
				.getConfigProprty("./src/test/resources/productConfig.properties");
		// set Environment name
		if (productProperty.getProperty("EnviromentName") != null
				&& productProperty.getProperty("EnviromentName") != "") {
			ProductConstant.environmentName = productProperty.getProperty("EnviromentName");
		} else {
			System.out.print(
					"Product config property file in Environment Namne key is empty or null . please kindly check product config");
		}
	}
}
