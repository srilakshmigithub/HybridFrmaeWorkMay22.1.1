package com.qa.facebook.util;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;

public class ExcelUtils {
	private Logger log = Logger.getLogger(ExcelUtils.class);

	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;

	/**
	 * this Method is used to read the data from excelsheets
	 * 
	 * @param fpath
	 * @param sheetName
	 * @return
	 * @throws InvalidFormatException
	 * @throws IOException
	 */

	public Object[][] getTestdata(String path, String sheetname) throws IOException {

		log.debug("Specify the path of file" + path);
		File srcfile = new File(path);
		log.debug("read the file");
		fis = new FileInputStream(srcfile);
		log.debug("load workbook");
		workbook = new XSSFWorkbook(fis);
		// load the sheet - first sheet take
		sheet = workbook.getSheet(sheetname);
		log.debug("fetch the row count");
		int rowCount = sheet.getLastRowNum();

		log.debug("number of rows in xl sheet" + rowCount);

		int colCount = sheet.getRow(0).getLastCellNum();
		log.debug("number of columns in xl shhet:" + colCount);

		Object[][] data = new Object[rowCount][colCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();

			}
		}

		return data;

	}

}
