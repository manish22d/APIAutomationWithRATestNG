package com.RA.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Class for all config related operation
 * 
 * @author Manish
 *
 */
public class ConfigProvider {
	/**
	 * method to initialise and load config file 
	 * @return
	 */
	public static Config getConfig() {
		return ConfigFactory.load("config/application.conf");
	}
}
