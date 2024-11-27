package testcases;

import java.lang.reflect.InvocationTargetException;

import org.testng.annotations.Test;

import pages.CreateLeadPage;

public class TestClass {
	
	@Test
	public void test() throws SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		String simpleName = TestClass.class.getName();
		String clp = CreateLeadPage.class.getName();
		TestClass.class.getName();
		System.out.println(simpleName);
		System.out.println(clp);
		
		Class<?> forName = Class.forName(clp);
		Object newInstance = forName.getDeclaredConstructor(String.class).newInstance("Dynamic Instance");
		
	}
	
	
	public void sample()
	{
		System.out.println("me from Sample cdes");
	}

}
