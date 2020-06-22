package com.platform.project.commons;

//EVERYTHING IN COMMONS IS PUBLIC, EXCEPT FOR THE WEB DRIVER MANAGER.
//IF THIS CLASS WAS STATIC, THEN YOU WOULD NOT BE ABLE TO CREATE MULTIPLE INDIVIDUAL 
//INSTANCES OF INDIVIDUAL WEB DRIVERS. IF IT WAS STATIC, THEN A CHANGE TO ONE DRIVER WOULD BE SEEN 
//IN ALL THE OTHER DRIVERS. 
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverManager { //SPECIFIC CLASS FOR CHOSING THE DRIVER
	
	private WebDriver driver;
	private String osName = System.getProperty("os.name");
	private Logger logger = Logger.getLogger(WebDriverManager.class);
	
	//creating a driver for each browser and for various operating system
	private WebDriver createDriver(String browser) { //returning a web-browser
		if (osName.toLowerCase().contains("windows")) {
			logger.info("Windows OS is detected");
			
			if (browser.equalsIgnoreCase("chrome")) {
				logger.info("Chrome browser detected");
				System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				logger.info("Firefox browser detected");
				System.setProperty("webdriver.gecko.driver", "src//test//resources//drivers//gecko.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("ie")) {
				logger.info("IE browser detected");
				System.setProperty("webdriver.ie.driver", "src//test//resources//drivers//ie.exe");
				driver = new InternetExplorerDriver();
			} else {
				logger.info("Default browser detected");
				System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			}
		} else if (osName.toLowerCase().contains("mac")) {
			logger.info("MAC OS is detected");
			
			if (browser.equalsIgnoreCase("chrome")) {
				logger.info("Chrome browser detected");
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				logger.info("Firefox browser detected");
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/gecko");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("safari")) {
				logger.info("Safari browser detected");
				System.setProperty("webdriver.safari.driver", "src/test/resources/drivers/safari");
				driver = new SafariDriver();
			} else {
				logger.info("Default browser detected");
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		} else if (osName.toLowerCase().contains("linux")) {
			logger.info("LINUX OS is detected");
			
			if (browser.equalsIgnoreCase("chrome")) {
				logger.info("Chrome browser detected");
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				logger.info("Firefox browser detected");
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/gecko");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("safari")) {
				logger.info("Safari browser detected");
				System.setProperty("webdriver.safari.driver", "src/test/resources/drivers/safari");
				driver = new SafariDriver();
			} else {
				logger.info("Default browser detected");
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
				driver = new ChromeDriver();
			}
		}
		
		return driver;
	}
	 
	//GET method for the above private method
	//this methods allows the user to choose a specific browser
	public WebDriver getDriver(String browser) {
		if (driver == null) { //when a driver has not been created
			try {
				driver = createDriver(browser);
				logger.info("Driver initialization is successful");
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.info("Couldn't initialize the driver");
			}
		} else { //when you have a driver already
			logger.info("Driver is already initialized");
		}
		
		return driver;
	}
	
	//this gives the user the ability to choose the default value in the config file
	public WebDriver getDriver() { 
		//reading from the property file
		//return getDriver(ReadPropertyFile.getConfigPropertyVal("browser"));
		//reading from the terminal and the second parameter is the default value; reading from the property file
		return getDriver(Commons.createEnvVariable("browser", ReadPropertyFile.getConfigPropertyVal("browser")));
	}
}


//