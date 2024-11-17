package test.objectpoolbrowser;

import org.testng.annotations.Test;

import objectpool.DriverInstance;

public class ObjectPoolBrowserTest {
	
	DriverInstance di; 

	@Test
	public void test1() {
		
		di = new DriverInstance();
		
		di.getDriver();

	}

	@Test
	public void test2() {

	}

}
