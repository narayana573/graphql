package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class DataGenerator {

	@DataProvider(name = "Excel")
	public static Object[][] testDataGenerator(Method met) throws IOException
	// Passing data from excel

	{
		if (met.getName().equalsIgnoreCase("testdataprovider")) {
			FileInputStream file = new FileInputStream("./TestData/testdata.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet st = wb.getSheet("Login");
			int numberOfData = st.getPhysicalNumberOfRows();
			Object[][] testData = new Object[numberOfData][st.getRow(0).getPhysicalNumberOfCells()];
			for (int i = 0; i < numberOfData; i++) {
				XSSFRow row1 = st.getRow(i);
				int column = row1.getPhysicalNumberOfCells();
				for (int j = 0; j < column; j++) {
					XSSFCell column1 = row1.getCell(j);
					testData[i][j] = column1.getStringCellValue();
				}
			}
			return testData;
		} /*
			 * else { Object[][] testData = new Object[1][0]; return testData; }
			 */

		else if (met.getName().equalsIgnoreCase("isMedplusUser")) {
			FileInputStream file = new FileInputStream("./TestData/testdata.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet st = wb.getSheet("medplusnumbers");
			int numberOfData = st.getPhysicalNumberOfRows();
			Object[][] testData = new Object[numberOfData][st.getRow(0).getPhysicalNumberOfCells()];

			for (int i = 0; i < numberOfData; i++) {
				XSSFRow row1 = st.getRow(i);
				int column = row1.getPhysicalNumberOfCells();
				for (int j = 0; j < column; j++) {
					XSSFCell column1 = row1.getCell(j);
					testData[i][j] = column1.getStringCellValue();
				}
			}
			return testData;
		} else {
			Object[][] testData = new Object[1][0];
			return testData;
		}

	}
}
