package com.RA.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.RA.Constant.Constants;
import com.RA.testBase.TestBase;

/**
 * Helper class for all excel related operation
 * 
 * @author Manish
 *
 */
public class ExcelUtility {

	public Workbook book;
	public Sheet sheet;
	public Properties property;
	public Logger log = LogManager.getLogger(TestBase.class.getClass());

	public ExcelUtility() {
		try {
			book = WorkbookFactory.create(new FileInputStream(Constants.TEST_DATA_SHEET_PATH));
		} catch (FileNotFoundException e) {
			log.error("excel file was not found at -> " + Constants.TEST_DATA_SHEET_PATH);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * reads excel sheet and return map form of selected data
	 * 
	 * @param terminalid
	 * @return
	 */
	public HashMap<String, String> getTestData(String terminalid) {

		sheet = book.getSheetAt(0);
		HashMap<String, String> data = new HashMap<String, String>();

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			for (int k = 1; k < sheet.getRow(0).getLastCellNum(); k++) {
				if (sheet.getRow(0).getCell(k).toString().equals(terminalid)
						&& !sheet.getRow(i).getCell(k).getStringCellValue().equals("-")) {
					data.put(sheet.getRow(i).getCell(0).toString(), sheet.getRow(i).getCell(k).toString());
				}
			}
		}
		return data;
	}

	/**
	 * Read excel data for given terminal id
	 * 
	 * @param terminalid
	 * @return
	 */
	public HashMap<String, List<String>> getTerminalDetailsData(String terminalid) {
		sheet = book.getSheetAt(1);
		HashMap<String, List<String>> data = new HashMap<String, List<String>>();

		for (int i = 1; i < sheet.getRow(0).getLastCellNum(); i++)
			data.put(sheet.getRow(0).getCell(i).toString(), new ArrayList<String>());

		List<String> keys = new ArrayList<String>(data.keySet());

		for (int row = 0; row < sheet.getLastRowNum() + 1; row++) {
			final Integer innerRow = new Integer(row);
			if (sheet.getRow(row).getCell(0).toString().equals(terminalid)) {
				keys.forEach(key -> {
					for (int i = 1; i < sheet.getRow(0).getLastCellNum(); i++)
						if (sheet.getRow(0).getCell(i).toString().equals(key)
								&& !sheet.getRow(innerRow).getCell(i).toString().equals("-")) {
							String item = sheet.getRow(innerRow).getCell(i).toString();
							List<String> test = new ArrayList<String>();
							test.addAll(data.get(key));
							test.add(item);
							data.put(key, test);
						}
				});
			}
		}
		return data;
	}

	/**
	 * read Status code details for given string
	 * 
	 * @param code
	 * @return
	 */
	public HashMap<String, String> getStatusDetails(String code) {
		sheet = book.getSheetAt(2);
		LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

		for (int i = 0; i < sheet.getLastRowNum() + 1; i++)
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++)
				if (sheet.getRow(0).getCell(k).toString().equals(code))
					if (sheet.getRow(i).getCell(k).getStringCellValue().equals("-"))
						data.put(sheet.getRow(i).getCell(0).toString(), "");
					else
						data.put(sheet.getRow(i).getCell(0).toString(), sheet.getRow(i).getCell(k).toString());

		return data;
	}
}
