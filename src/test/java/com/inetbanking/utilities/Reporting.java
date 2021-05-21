package com.inetbanking.utilities;

//Listener class used to generate Extent reports

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	public void onStart(ITestContext testContext){
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter("./Report/"+repName);//specify locations	
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Deepak");
		
		htmlReporter.config().setDocumentTitle("Inetbanking Test Project"); //Title report
		htmlReporter.config().setReportName("Automation Report"); //Name of the report
		htmlReporter.config().setTheme(Theme.DARK);
	}	
		public void onTestSuccess(ITestResult result){
			logger=extent.createTest(result.getName());
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));// Send the pass info
		}
		
		
		public void onTestFailure(ITestResult result){
			
			logger=extent.createTest(result.getName());
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));// Send the fail info
			
			String screenshotpath=System.getProperty("user.dir") +"/Screenshots/" +result.getName()+".png";
			
			File f=new File(screenshotpath);
			
			if(f.exists()){
				try{
					logger.fail("Screenshot is below:" +logger.addScreenCaptureFromPath(screenshotpath));
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		public void onTestSkipped(ITestResult result){
			logger=extent.createTest(result.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));//create new entry in the report
		}
		
		public void onFinish(ITestContext testContext){
			
			extent.flush();
		}	

}
