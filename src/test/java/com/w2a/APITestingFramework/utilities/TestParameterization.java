package com.w2a.APITestingFramework.utilities;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.w2a.APITestingFramework.rough.Constants;

public class TestParameterization {

	@DataProvider
	public Object[][] getData() {

		ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\ManagerSuiteTest.xlsx");

		int rows = excel.getRowCount(Constants.DATA_SHEET);
		System.out.println(rows);

//		For getting the row number where the test case is getting started

		String testName = "AddCustomerTest";

		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;
		}
		System.out.println("Test Case starts from row no: " + testCaseRowNum);

//		Checking total rows in test case

		int dataStartsRowNum = testCaseRowNum + 2;

		int testRows = 0;

		while (!excel.getCellData(Constants.DATA_SHEET, 0, dataStartsRowNum + testRows).equals("")) {
			testRows++;
		}
		System.out.println("Total set of data are: " + testRows);

		// Checking total columns in test cases

		int colStartColNum = testCaseRowNum + 1;
		int testCols = 0;

		while (!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")) {

			testCols++;
		}

		System.out.println("Total cols are: " + testCols);

//		For printing the data

		Object[][] data = new Object[testRows][testCols];
		
		int i = 0;
		for (int rNum = dataStartsRowNum; rNum < (dataStartsRowNum + testRows); rNum++) {
			
			Hashtable<String, String> table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < testCols; cNum++) {

//				System.out.println(excel.getCellData(Constants.DATA_SHEET, cNum, rNum));
				String testData = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
				String colName = excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);
				
				table.put(colName, testData);
			}
			
			data[i][0] = table;
			i++;
			
		}
		
		return data;

	}
}
