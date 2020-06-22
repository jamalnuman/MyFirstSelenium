package com.platform.project.pageObjects;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	//Page Factory:
	@FindBy(name="email_address")
	WebElement emailAddressField;
	
	@FindBy(name="password")
	WebElement passwordField;
	
	@FindBy(id="tdb5")
	WebElement signinButton;
	
	@FindBy(className="messageStackError")
	List<WebElement> errors;
	
	@FindBy(className="ui-button-text")
	WebElement enterButton;
	
	@FindBy(xpath="//div[@id='bodyContent']//h1")
	WebElement titleResponse;
	
	@FindBy(xpath="//a[1]//u[1]")
	WebElement logyourselfin;
	
	//instantiate the web-driver on every page..Page Factory
	WebDriver driver;
	private Logger logger = Logger.getLogger(LoginPage.class);
	
	//using the constructor to assign the web-driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private void enterEmailAddress(String email) { //setting this to private, to force the loginInWithCredentials() to be used
		logger.info("Entering email address: " + email);
		//System.out.println("entering email address");
		//driver.findElement(By.name("email_address")).sendKeys(email);
		emailAddressField.sendKeys(email);
	}
	
	private void enterPassword(String password) { //setting this to private, to force the loginInWithCredentials() to be used
		logger.info("Entering password: " + password);
		//System.out.println("entering password");
		//driver.findElement(By.name("password")).sendKeys(password);
		passwordField.sendKeys(password);
	}
	
	private void clickSigninButton() { //setting this to private, to force the loginInWithCredentials() to be used
		logger.info("Clicking on Sign In Button");
		//System.out.println("Clicking the Sing-in Button");
		//driver.findElement(By.id("tdb5")).click();
		signinButton.click();
	}
	
	public String getErrorText() {
		logger.info("Error text is: " + errors);
		//System.out.println("Getting error message");
		//String errorText = driver.findElements(By.className("messageStackError")).get(1).getText();
		String errorText = errors.get(1).getText();
		return errorText;
	}
	
	public void loginWithCredentials(String email, String password) {
		enterEmailAddress(email);
		enterPassword(password);
		clickSigninButton();
	}
	
	public String caseOne(String email, String password) throws InterruptedException {
		logyourselfin.click();
		emailAddressField.sendKeys(email);
		passwordField.sendKeys(password);
		signinButton.click();
		String responseTitle = titleResponse.getText();
		System.out.println("Getting response title: " + responseTitle);
		return responseTitle;
	}

}


