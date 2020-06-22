package com.platform.project.commons;

//AGAIN, EVERYTING IN COMMONS IS STATIC

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Commons {
	
private static Logger logger = Logger.getLogger(Commons.class); //the getLogger() will take the class that it is currently in, this case the Commons class

	//this method allows you to set your browser from the command line
	//envVariableName is the key
	public static String createEnvVariable(String envVariableName, String defaultValue) {
		//getting the value for the key..allow the command line to read the key and get the value
		String value = System.getProperty(envVariableName);
		logger.info("Environment value for " + envVariableName + " is " + value);
		//to protect against the value being null. 
		return value!=null ? value : defaultValue; //passed in as a parameter 
	}
	
	//the combination of time and date provides uniqueness
	public static void screenShot(WebDriver driver, String className, String methodName) {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss_").format(new Date());
		String fileName = timeStamp + className + "_" + methodName + ".png";
		File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(ss, new File( "./screenshots/" + fileName));
			logger.info("Screenshot " + fileName + " taken");
		} catch (IOException ioe) {
			logger.info("Unable to take screenshot " + fileName);
			ioe.printStackTrace();
		}
	}

}

//command line: test -Dbrowser=chrome
//the -D tells the java of a key/value pair upcoming. 
//browser is the key or the envVariableName