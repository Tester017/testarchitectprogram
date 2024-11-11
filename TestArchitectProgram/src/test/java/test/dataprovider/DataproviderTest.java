package test.dataprovider;

import org.testng.annotations.Test;

import datafactory.DataFactory;
import datafactory.DataSourceType;

public class DataproviderTest {
	
	@Test
	public void testExcelDataProvider() {
		
		String data = DataFactory.testDataProvider(DataSourceType.EXCEL).getData("username");
		System.out.println(data);
		
	}
	
	@Test
	public void testFakerDataProvider() {
		
		String data = DataFactory.testDataProvider(DataSourceType.FAKER).getData("username");
		System.out.println(data);
		
	}
	
	@Test
	public void testDBDataProvider() {
		
		String data = DataFactory.testDataProvider(DataSourceType.DB).getData("username");
		System.out.println(data);
		
	}
	
	@Test
	public void testHardcodedDataProvider() {
		
		String data = DataFactory.testDataProvider(DataSourceType.HARDCODED).getData("username");
		System.out.println(data);
		
	}

}
