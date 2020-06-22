package com.platform.project.commons;

import java.io.FileInputStream;
import java.io.IOException;
//the import that reads config files
import java.util.Properties;

import org.apache.log4j.Logger;

//this is to read the config file(s), which stores info for driver and url
public class ReadPropertyFile {
	
	//this is under the assumption that there is one property file
	//the .(dot) refers the project, in this case Selenium
	private static String configFileLocation = "./src/test/resources/config.properties";
	private static Logger logger = Logger.getLogger(ReadPropertyFile.class);
	
	
	//if this was public, the user would have to add the file location on line 14
	private static String readFile(String file, String key) {
		String value = null; //the value that will be returned
		
		try {
			//
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(file);
			prop.load(in);
			
			value = prop.getProperty(key);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			logger.info("Couldn't locate the property file");
		}
		
		logger.info("Value in property file for " + key + " is " + value);
		return value;
	}
	
	//GET method for the above private method
	public static String getConfigPropertyVal(final String key) { //the final keyword is optional
		String configPropertyVal = readFile(configFileLocation, key);
		
//		if (configPropertyVal!=null) {
//			return configPropertyVal.trim();
//		} else {
//			return configPropertyVal;
//		}
		
		//either return the value or null..not null.trim() cause that will throw an exception
		return configPropertyVal!=null ? configPropertyVal.trim() : configPropertyVal;
	} 

}
