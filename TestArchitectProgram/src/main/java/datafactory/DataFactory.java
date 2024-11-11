package datafactory;

public class DataFactory {

	public static DataProvider testDataProvider(DataSourceType dataSourceType) {

		switch (dataSourceType) {
		case EXCEL:
			return new ExcelDataProvider();
		case FAKER:
			return new FakerDataProvider();
		case DB:
			return new DatabaseDataProvider();
		case HARDCODED:
			return new HardcodedDataProvider();
		default:
			throw new IllegalArgumentException("Incorrect datasource type");
		}

	}

}
