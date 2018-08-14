package com.renpay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.renpay.config.ObjectRepository;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class LoginPage extends TestInitialization {

	static WebDriver driver;

	public LoginPage(WebDriver driver) {
		LoginPage.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = LoginScreen.signinLink_xpath)
	public WebElement signinLink;

	@FindBy(how = How.ID, using = LoginScreen.emailName_ID)
	public WebElement emailName;

	@FindBy(how = How.ID, using = LoginScreen.password_ID)
	public WebElement password;

	@FindBy(how = How.ID, using = LoginScreen.loginBtn_ID)
	public WebElement loginBtn;

	@FindBy(how = How.XPATH, using = LoginScreen.loginError_xpath)
	public WebElement loginError;

	public void appliactionLoginwithValidCredentails() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		TestUtil.click(signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(emailName, 60, "Sign in email");
		
		String userName = TestUtil.getExcelKeyValue("LogInPage", "UserName", "ValidValues");
		String passwordVal = TestUtil.getExcelKeyValue("LogInPage", "Password", "ValidValues");
		
		TestUtil.sendKeys(userName, emailName, "Email/Mobile editbox");
		TestUtil.sendKeys(passwordVal, password, "Password editbox");
		
		reports.log(LogStatus.PASS, "Click on log in button and validate application login successfully.");
		TestUtil.click(loginBtn, "login button");
		TestUtil.waitForObjectInvisble(By.id(ObjectRepository.pleaseWaitModal_ID),180, "Please wait modal alertbox");
		
		TestUtil.waitForObjectVisible(homePage.imageProfile, 60, "User Profile");
	}

	public void appliactionLoginwithInValidCredentails() throws InterruptedException {

		TestUtil.click(signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(emailName, 60, "Sign in email");

		String passwordVal = TestUtil.getExcelKeyValue("LogInPage", "Password", "ValidValues");

		TestUtil.sendKeys("WrongEmail@gmail.com", emailName, "Email/Mobile editbox");
		TestUtil.sendKeys(passwordVal, password, "Password editbox");

		
		TestUtil.click(loginBtn, "login button");
		TestUtil.waitForObjectVisible(loginError, 60, "Login error");

		if (loginError.getText().trim()
				.contentEquals(TestUtil.getExcelKeyValue("ErrorMessages", "LoginPage", "InvalidCredentials").trim())) {

			passTestCase("Log in error has been displayed Actual error message :" + loginError.getText().trim()
					+ " \n and expected error message : "
					+ TestUtil.getExcelKeyValue("ErrorMessages", "LoginPage", "InvalidCredentials").trim());
		}

		else {
			failTestCase("Unable to validate login error.Actual error message :" + loginError.getText().trim()
					+ " \n and expected error message : "
					+ TestUtil.getExcelKeyValue("ErrorMessages", "LoginPage", "InvalidCredentials").trim());
		}
	}

}
