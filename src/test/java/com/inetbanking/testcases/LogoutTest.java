package com.inetbanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pages.LoginPage;
import com.inetbanking.pages.LogoutPage;

public class LogoutTest extends BaseClass {

	@Test
		public void logoutTest() throws InterruptedException, IOException{
			LoginPage loginPage=new LoginPage(driver);
			LogoutPage logoutPage=new LogoutPage(driver);
			
			loginPage.setUserId(userName);
			logger.info("Entered user name");
			loginPage.setPassword(password);
			logger.info("Entered password");
			loginPage.clickOnLogin();
			logger.info("Click on Login Button");
			logoutPage.clickOnLogout();
			logger.info("Click on Logout Button");
			Thread.sleep(3000);
				
			if(isAlertPresent()==true){
				
				String alertMessage=driver.switchTo().alert().getText();
				
				if(alertMessage.equals("You Have Succesfully Logged Out!!")) {
					Assert.assertTrue(true);
					logger.info("Logout test pass");
				}
				else {
					captureScreenshot(driver, "LogoutTest");
					Assert.assertTrue(false);
					logger.warn("Logout Failed");
				} 
				
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}			
			
		}
}
