package com.inetbanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.inetbanking.pages.LoginPage;
import com.inetbanking.pages.LogoutPage;
import com.inetbanking.utilities.XLUtils;

public class LoginWithDataSetTest extends BaseClass{

	/*
	 * Login with different set of data
	 */
	
	@Test(dataProvider="LoginData")
	public void loginDataSet(String user, String password) throws InterruptedException, IOException{
		
		LoginPage loginPage=new LoginPage(driver);
		LogoutPage logoutPage=new LogoutPage(driver);
		loginPage.setUserId(user);
		logger.info("Entered username");
		loginPage.setPassword(password);
		logger.info("Entered username");
		loginPage.clickOnLogin();
		Thread.sleep(3000);
		
		if(isAlertPresent()==true){
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			//take screenshot for failure test
			captureScreenshot(driver, "LoginWithDataSetTest");
			Assert.assertTrue(false);
			logger.warn("Login Failed");
		}
		else{
			Assert.assertTrue(true);
			logger.info("Login Passed");
			logoutPage.clickOnLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
		}
	}

	
	/*
	 * Method to get set of data from excel file
	 */
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
	
		//String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testdata/LoginData.xlsx";

		//String path="./src/test/java/com/inetbanking/testdata/LoginData.xlsx";
		
		String path=testdata;  //Reading file path from properties file
		int rownum=XLUtils.getRowCount(path, "Logindata");
		int colcount=XLUtils.getCellCount(path,"Logindata",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++){
			for(int j=0;j<colcount;j++){
				logindata[i-1][j]=XLUtils.getCellData(path, "Logindata", i, j);
			}
		}
		return logindata;
	}
}
