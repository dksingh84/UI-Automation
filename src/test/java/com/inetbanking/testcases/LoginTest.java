package com.inetbanking.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pages.LoginPage;
import com.inetbanking.utilities.ReadConfig;

public class LoginTest extends BaseClass {

	@Test
	public void loginTest() throws IOException{
		
		ReadConfig readConfig=new ReadConfig();
		logger.info("URL is opened");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserId(userName);
		logger.info("Entered username");
		loginPage.setPassword(password);	
		logger.info("Entered password");
		loginPage.clickOnLogin();
		
		if(driver.getTitle().equals(readConfig.getTitle())) {
			Assert.assertTrue(true);
			logger.info("Login test passes");
		}
		else {
			
			//take screenshot for failure test
			captureScreenshot(driver, "LoginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
			logger.warn("Login Failed");
		} 
	}
	
	
}
