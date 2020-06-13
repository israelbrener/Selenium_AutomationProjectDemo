																									package dp;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	public static void main(String[] args) throws IOException {
		String[][] myDataObject = getDataFromExcelToObject("Book1.xlsx", "sheet1");
		System.out.println("break");
	}

	// METHOD EXCEL
	public static String[][] getDataFromExcelToObject(String fileName, String sheetName) throws IOException {
		String tableArray[][] = null;
		
		//Get excel file within the resources folder
		fileName = System.getProperty("user.dir") +"\\resources\\"+fileName;
				
		try {
			// Get the Excel
			FileInputStream excelFile = new FileInputStream(fileName);
			XSSFWorkbook ExcelWBook = new XSSFWorkbook(excelFile);
			XSSFSheet ExcelWSheet = ExcelWBook.getSheet(sheetName);

			// Range Of data
			int totalRow = 4;
			int totalCol = 3;

			tableArray = new String[totalRow][totalCol];
			for (int i = 0; i < totalRow; i++) {
				for (int j = 0; j < totalCol; j++) {

					// create method of reading from Excel
					 tableArray[i][j] = ExcelWSheet.getRow(i+1).getCell(j+1).getStringCellValue();
					System.out.println("insert: " + tableArray[i][j]);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return tableArray;
	}

}
