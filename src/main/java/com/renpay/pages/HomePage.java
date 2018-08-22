package com.renpay.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.renpay.utils.TestInitialization;

public class HomePage extends TestInitialization {

	static WebDriver driver;

	public HomePage(WebDriver driver) {
		HomePage.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = HomeScreen.imageProfile_ID)
	public WebElement imageProfile;
	
	@FindBy(how = How.XPATH, using = HomeScreen.logoutOpenerLink_xpath)
	public WebElement logoutOpenerLink;
	
	@FindBy(how = How.XPATH, using = HomeScreen.logoutLink_xpath)
	public WebElement logoutLink;
	
	@FindBy(how = How.XPATH, using = HomeScreen.userProfile_xpath)
	public WebElement userProfile;
	
	@FindBy(how = How.ID, using = HomeScreen.renpayLogoImg_ID)
	public WebElement renpayLogoImg;
	
	@FindBy(how = How.XPATH, using = HomeScreen.manageMyaccount_xpath)
	public WebElement manageMyaccount;
	
	@FindBy(how = How.ID, using = HomeScreen.profileEdit_ID)
	public WebElement profileEditLink;
	
	@FindBy(how = How.XPATH, using = HomeScreen.rpfTabInLeftPannel_xpath)
	public WebElement rpfTabInLeftPannel;
	
	@FindBy(how = How.ID, using = HomeScreen.createRFP_Btn_ID)
	public WebElement createRFP_Btn;
	

}
