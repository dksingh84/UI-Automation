package com.inetbanking.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.inetbanking.testcases.BaseClass;

public class LoginPage {

	BaseClass baseclass=new BaseClass();
	WebDriver driver;
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (name="uid")
	WebElement txtUserId;
	
	@FindBy (name="password")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@name='btnLogin']")
	WebElement btnLogin;
	
	
	public void setUserId(String uid){
		
		//this command will clear the text filed
		//txtUserId.clear();
		baseclass.validateElementDisplay(txtUserId);
		
		txtUserId.sendKeys(uid);
	}
	
	public void setPassword(String password){
		
		//txtPassword.clear();
		baseclass.validateElementDisplay(txtPassword);
		txtPassword.sendKeys(password);
	}
	
	public void clickOnLogin() {
		
		baseclass.validateButtonEnable(btnLogin);
		btnLogin.click();
	}
	
	
}
