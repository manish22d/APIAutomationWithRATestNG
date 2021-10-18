package com.RA.Constant;

/**
 * Constant class to store path to all files
 * 
 * @author Manish
 *
 */
public class Constants {

	/**
	 * Chrome Driver Path
	 */
	public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/chromedriver.exe";
	/**
	 * IE driver path
	 */
	public static final String INTERNET_EXPLORER_DRIVER_PATH = System.getProperty("user.dir")
			+ "/Drivers/IEDriverServer.exe";
	/**
	 * Firefox driver path
	 */
	public static final String FIREFOX_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/geckodriver.exe";

	/**
	 * PhantomJS driver path
	 */
	public static final String PHANTOM_JS_DRIVER_PATH = System.getProperty("user.dir") + "/Drivers/phantomjs.exe";

	
	/**
	 * Page load time out quantity
	 */
	public static final long PAGE_LOAD_TIMEOUT = 30;

	/**
	 * quantity for Implicit wait
	 */
	public static final long IMPLICIT_WAIT = 15;

	/**
	 * quantity for explicit wait
	 */
	public static final long EXPLICIT_WAIT = 15;

	/**
	 * path of excel sheet
	 */
	public static final String TEST_DATA_SHEET_PATH = "src\\test\\resources\\testdataSheet\\testdata.xlsx";
	/**
	 * path of config property
	 */
	public static final String CONFIG_PROPERTY = "src\\main\\resources\\Configuration.properties";
}
