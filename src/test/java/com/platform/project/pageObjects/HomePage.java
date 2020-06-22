package com.platform.project.pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.platform.project.commons.ReadPropertyFile;

public class HomePage {
	//Page Factory:
	@FindBy(xpath="//div[@id='bodyContent']//h1")
	WebElement homePageTitle; //line 10 & 11 is one statement..the colons after line 11
	
	@FindBy(id="tdb3")
	WebElement accountButton; //line 13 & 14 is one statement..the colons after line 14
	
	//instantiating all web elements that will be used.    
	WebDriver driver;
	private Logger logger = Logger.getLogger(HomePage.class);
	
	//the constructor method, takes a web-driver as a parameter
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); //now all Page Factory elements will have access to the driver, 
												//which is need to get a hold of all the elements. w/o it, tests will fail. 
												//"this" is referring to the Class HomePage
	}
	
	public void openHomePage() {
		//print statements to keep track of progress
		//System.out.println("opening home page");
		//driver.get("http://107.170.213.234/catalog/");
		logger.info("Opening Home Page");
		driver.get(ReadPropertyFile.getConfigPropertyVal("homePageURL"));
		
	}
	
	//this will return a string that will be used in the assertion
	public String getHomePageTitle() {
		//instantiate a web element and have the driver get it
		//WebElement homePageTitle = driver.findElement(By.xpath("//div[@id='bodyContent']//h1"));
		//System.out.println("Home page title text " + homePageTitle.getText());
		logger.info("Home Page Title text is: " + homePageTitle.getText());
		return homePageTitle.getText();//homePageTitle is a PageFactory Element, see above. 
	}
	
	public void clickAccountButton() {
		logger.info("Clicking on Account Button");
		//System.out.println("Clicking on account button");
		//driver.findElement(By.id("tdb3")).click(); this was saved into a Page Factory Element, see above.
		accountButton.click();
		
	}
	
	
	
	
	
	
	

}
