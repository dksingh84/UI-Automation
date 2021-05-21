package com.inetbanking.testcases;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig=new ReadConfig();
	String dateName=new SimpleDateFormat("yyyyMMmmss").format(new Date()); 
	public String baseUrl=readconfig.getApplicationURL();
	public String userName=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public String testdata=readconfig.getTestDataPath();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {
		
		logger=Logger.getLogger("inetBanking");
		PropertyConfigurator.configure("log4j2.properties");
			
		if(br.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver=new ChromeDriver();		
		}
		else if(br.equals("firefox")){
			System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
			driver=new FirefoxDriver();		
		}
		
		else if(br.equals("edge")){
			System.setProperty("webdriver.edge.driver",readconfig.getEdgePath());
			driver=new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(1,TimeUnit.MINUTES);	
				
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	/*
	 * Capture Screenshot for failed test case
	 */
	public void captureScreenshot(WebDriver driver, String screenshotname){
		try{
			
			TakesScreenshot takescreen=(TakesScreenshot)driver;
			File src=takescreen.getScreenshotAs(OutputType.FILE);
			//File dest=new File(System.getProperty("user.dir") +"/Screenshots/" +tname +".png");
			File dest=new File("./Screenshots/" +screenshotname +dateName +".png");
			FileUtils.copyFile(src, dest);
			//System.out.println("Screenshot taken");
		}
		catch(Exception e){
			System.out.println("Exception while taking screenshot "+e.getMessage());
		}	
		 
	}
	
	/*
	 * Handle Alert window 
	 */
	public boolean isAlertPresent(){
		
		try{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e){
			return false;
		}
	}
	
	public void validateElementDisplay(WebElement element){
		
		boolean b=element.isEnabled();
		if(b==true){
			logger.info("Element is present");
			//element.clear();
		}
		else
			logger.warn("Element is not present");
		
	}
	
	public void sendText(WebElement element, String txt){
		element.clear();
		element.sendKeys(txt);
	}
	
	public void validateButtonEnable(WebElement element){
		
		boolean b=element.isDisplayed();
		if(b==true){
			logger.info("Button is enable");
		
		}
		else
			logger.warn("Element is not enable");
		
	}
		
}
