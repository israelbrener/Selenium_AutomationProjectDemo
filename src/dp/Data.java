package dp;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Data {
	
	@DataProvider(name = "Details")
	public static Object[][] details() {
		return new Object[][] {{"name 1", "email1@test.com", "ssss1"},{"name 2", "email2@test.com", "ssss2"},{"name 3", "email3@test.com", "ssss3"}};
	}

	
	@DataProvider(name = "Details_int")
	public static Object[][] details_int() {
		return new Object[][] { { 1, 2, 3 }, { 11, 12, 13 }, { 100, 22, 300 } };
	}
	
	@DataProvider(name = "dataFromExcel")
	public static String[][] dataFromExcel() throws IOException {
		return Excel.getDataFromExcelToObject("Book1.xlsx", "sheet1");
	}

}
