package com.platform.project.tests;

//RULE OF THUMB..ALL CLASSES AND METHODS HERE SHOULD BE STATIC..AN INSTANCE SHOULD NOT BE NEEDED
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.platform.project.commons.Commons;
import com.platform.project.commons.ReadPropertyFile;
import com.platform.project.commons.WebDriverManager;
import com.platform.project.pageObjects.HomePage;
import com.platform.project.pageObjects.LoginPage;

public class HomePageTest {
	
	//defining these as class VARIABLES, so they can be used in any method.
	WebDriver driver;
	HomePage homepage; 
	LoginPage login;
	WebDriverManager webDriverManager;
	Commons commons;
	
	
	//the annotation are possible cause of a dependency in the pom file..each annotation needs a method 
	@BeforeMethod
	public void setup() {
		
		//setting the location of the executable property..takes key and value
		//relative path for the executable is fine, cause the executable is located in our project. 
		//System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver");
		//driver = new ChromeDriver();
		webDriverManager = new WebDriverManager();
		driver = webDriverManager.getDriver(ReadPropertyFile.getConfigPropertyVal("browser")); //calling getPropertyConfigVal on the class, cause the class itself is static. 
		homepage = new HomePage(driver); //instantiating an object of the HomePage to use in this class
		homepage.openHomePage();//cause you need a manner to get to the page. 
		login = new LoginPage(driver);
		 
	}
	
	@Test
	public void checkHomePage() { 
		Assert.assertEquals(homepage.getHomePageTitle(), "Welcome to iBusiness");
		commons.screenShot(driver, "HomePage", "getHomePageTitle");
	}
	
	@Test
	public void loginFailure() {
		
		//these web-element is being used once and never again, better from a memory aspect to do the one-liner.
		
//		WebElement myAccountButton = driver.findElement(By.id("tdb3"));
//		myAccountButton.click();
//		driver.findElement(By.id("tdb3")).click();
		homepage.clickAccountButton();
		login.loginWithCredentials("abc", "abc");
		
//		WebElement emailAddressField = driver.findElement(By.name("email_address"));
//		emailAddressField.sendKeys("abc");
		//driver.findElement(By.name("email_address")).sendKeys("abc");
		//login.enterEmailAddress("abc"); DO NOT HAVE ACCESS THIS ANYMORE CAUSE IT IS PRIVATE
		
//		WebElement passwordField = driver.findElement(By.name("password"));
//		passwordField.sendKeys("abc");
		//driver.findElement(By.name("password")).sendKeys("abc");
		//login.enterPassword("abc"); DO NOT HAVE ACCESS THIS ANYMORE CAUSE IT IS PRIVATE
		
//		WebElement signInButton = driver.findElement(By.id("tdb5"));
//		signInButton.click();
		//driver.findElement(By.id("tdb5")).click();
		//login.clickSigninButton(); DO NOT HAVE ACCESS THIS ANYMORE CAUSE IT IS PRIVATE
		
		//this is a list of the WebElements that interact with on the page
		//there is .findElement() and .findElements()
//		List<WebElement> errorList = driver.findElements(By.className("messageStackError"));
		//login.getErrorText();
		
		Assert.assertTrue(login.getErrorText().equals(" Error: No match for E-Mail Address and/or Password."));
	
	}
	
	@Test
	public void hmwkCases() throws InterruptedException {
		//login.caseOne("ecalix@test.com", "test123");
		//System.out.println(test);
		Assert.assertEquals(login.caseOne("ecalix@test.com", "test123"),"Welcome to iBusiness");
	}
	
//	@Test
//	public void pagefileTest() throws FileNotFoundException {
//		pageFile.readConfigFile();
//	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit(); //or you can use driver.close()... .close() only closes the driver, which can be used again. .quit() closes 
		//and quits the driver, which can not be used again. 
	}

}
