package com.inetbanking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.inetbanking.testcases.BaseClass;



public class LogoutPage {

	WebDriver driver;
	BaseClass baseClassObj=new BaseClass();
	public LogoutPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(), 'Log out')]")
	WebElement btnLogout;
	
	
	public void clickOnLogout() {
		
		baseClassObj.validateButtonEnable(btnLogout);
		btnLogout.click();
	}
	
}
