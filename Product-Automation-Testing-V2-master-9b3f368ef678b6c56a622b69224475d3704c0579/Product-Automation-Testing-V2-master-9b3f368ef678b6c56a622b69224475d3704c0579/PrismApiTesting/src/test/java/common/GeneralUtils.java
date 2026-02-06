package common;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;
import org.testng.internal.ClassHelper;
import org.testng.internal.PackageUtils;

import common.utils.ScenarioSheetCreate;

public class GeneralUtils {
	
	
	public static String getCurrentYear() {
		return  String.valueOf(Year.now().getValue()).trim();
	}

	public static String getPrevYear() {
		return  String.valueOf(Year.now().getValue()-1).trim();
	}

	public static LinkedHashMap<String,List<String>> getAllClassAndMethodNames() throws IOException, ClassNotFoundException {
	//public static void main(String[] args) throws IOException, ClassNotFoundException {
		String[] testClasses =
				PackageUtils.findClassesInPackage("test", new
				ArrayList<String>(), new ArrayList<String>());
		LinkedHashMap<String,List<String>> testMethodAndClassInCode = new LinkedHashMap<String, List<String>>();
		
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		for (String eachClass : testClasses){
			Class currentClass = cl.loadClass(eachClass);
			Set<Method> allMethods = ClassHelper.getAvailableMethods(currentClass);
			Iterator<Method> iMethods = allMethods.iterator();
			while (iMethods.hasNext()){
				Method eachMethod = iMethods.next();
				Test test = eachMethod.getAnnotation(Test.class);
				if (test != null){
					String methodName = eachMethod.getName();
					String className = eachMethod.getDeclaringClass().getName();
					if (testMethodAndClassInCode.get(className) == null) {
						testMethodAndClassInCode.put(className, new ArrayList<String>());
					}
					testMethodAndClassInCode.get(className).add(className + "~~" + methodName);
					
					//System.out.println("Method Name = " + eachMethod.getName());
					//System.out.println("Its class = " + eachMethod.getDeclaringClass().getName());
				}
			}
		}
		//System.out.println("Class with method name = " + testMethodAndClassInCode);
		ScenarioSheetCreate.getAllTestMethods(testMethodAndClassInCode);
		return testMethodAndClassInCode;
	}
}
