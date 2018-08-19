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

	@FindBy(how = How.ID, using = LoginScreen.buyerRadioBtn_ID)
	public WebElement buyerRadioBtn;

	@FindBy(how = How.ID, using = LoginScreen.supplierRadioBtn_ID)
	public WebElement supplierRadioBtn;

	@FindBy(how = How.XPATH, using = LoginScreen.labelForBuyerRadioBtn_xpath)
	public WebElement labelForBuyerRadioBtn;

	@FindBy(how = How.XPATH, using = LoginScreen.labelForSuppilerRadioBtn_xpath)
	public WebElement labelForSuppilerRadioBtn;

	@FindBy(how = How.ID, using = LoginScreen.rememberMeCheckBox_ID)
	public WebElement rememberMeCheckBox;

	@FindBy(how = How.XPATH, using = LoginScreen.forgotPasswordLink_xpath)
	public WebElement forgotPasswordLink;

	@FindBy(how = How.XPATH, using = LoginScreen.buyerImgForSignup_xpath)
	public WebElement buyerImgForSignup;

	@FindBy(how = How.XPATH, using = LoginScreen.suppilerImgForForSignup_xpath)
	public WebElement suppilerImgForForSignup;

	@FindBy(how = How.XPATH, using = LoginScreen.emailboxTooltip_xpath)
	public WebElement emailboxTooltip;

	@FindBy(how = How.ID, using = LoginScreen.loginErrorPopupCloseBtn_ID)
	public WebElement loginErrorPopupCloseBtn;
	
	@FindBy(how = How.XPATH, using = LoginScreen.ForgotPassword.forgotPasswordModal_xpath)
	public WebElement forgotPasswordModal;
	
	
	@FindBy(how = How.ID, using = LoginScreen.ForgotPassword.submitBtn_ID)
	public WebElement submitBtnForForgotPassword;

	@FindBy(how = How.ID, using = LoginScreen.ForgotPassword.userName_ID)
	public WebElement userNameForForgotPassword;
	
	@FindBy(how = How.ID, using = LoginScreen.ForgotPassword.loginError_ID)
	public WebElement loginError_ForgotPassward;
	
	@FindBy(how = How.ID, using = LoginScreen.ForgotPassword.closeBtnForgotPasswordModal_ID)
	public WebElement closeBtnForgotPasswordModal;
	
	@FindBy(how = How.XPATH, using = LoginScreen.ForgotPassword.popupCloseButtonAtModalHeader_xpath)
	public WebElement popupCloseButtonAtModalHeader;
	
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
		TestUtil.waitForObjectInvisble(By.id(ObjectRepository.pleaseWaitModal_ID), 180, "Please wait modal alertbox");

		TestUtil.waitForObjectVisible(homePage.imageProfile, 60, "User Profile");
	}

	public void appliactionLoginWithRememberMe(String userName , String pwdVal) throws InterruptedException {

		TestUtil.click(signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(emailName, 60, "Sign in email");

		TestUtil.sendKeys(userName, emailName, "Email/Mobile editbox");
		TestUtil.sendKeys(pwdVal, password, "Password editbox");
		TestUtil.selectCheckBox(rememberMeCheckBox, "Remember me checkbox");
		TestUtil.click(loginBtn, "login button");
		TestUtil.waitForObjectInvisble(By.id(ObjectRepository.pleaseWaitModal_ID), 180, "Please wait modal alertbox");
		TestUtil.waitForObjectVisible(new HomePage(driver).imageProfile, 60, "User Profile");
		
		
	}

	
	public void appliactionLoginWithoutRememberMe(String userName , String pwdVal) throws InterruptedException {

		TestUtil.click(signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(emailName, 60, "Sign in email");

		TestUtil.sendKeys(userName, emailName, "Email/Mobile editbox");
		TestUtil.sendKeys(pwdVal, password, "Password editbox");
		
		if(rememberMeCheckBox.isSelected()){
			reports.log(LogStatus.PASS, "Unselect Remember me checkbox");
			rememberMeCheckBox.click();
			reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		}
		
		TestUtil.click(loginBtn, "login button");
		TestUtil.waitForObjectVisible(loginError, 60, "Login error");
		
		
	}

	
	
	public void validateLoginErrorMsg(String expectedErrorMsg , WebElement we) throws InterruptedException{
		

		if (we.getText().trim()
				.contentEquals(expectedErrorMsg.trim())) {

			passTestCase("Log in error has been displayed Actual error message :" + we.getText().trim()
					+ " \n and expected error message : "
					+expectedErrorMsg.trim());
		}

		else {
			failTestCase("Unable to validate login error.Actual error message :" + we.getText().trim()
					+ " \n and expected error message : "
					+ expectedErrorMsg.trim());
		}
	}
	
	public void verifyLoginScreenPopUp() {
		TestUtil.isElementExist(buyerRadioBtn, "Buyer radio button");
		TestUtil.isElementExist(supplierRadioBtn, "Supplier radio button");
		TestUtil.isElementExist(buyerRadioBtn, "Buyer radio button");
		TestUtil.isElementExist(supplierRadioBtn, "Supplier radio button");
		TestUtil.isElementExist(labelForBuyerRadioBtn, "label for buyer checkbox");
		TestUtil.isElementExist(labelForSuppilerRadioBtn, "label for Supplier checkbox");
		TestUtil.isElementExist(emailName, "User name Email box");
		TestUtil.isElementExist(password, "User name Password box");
		TestUtil.isElementExist(rememberMeCheckBox, "Remember me checkbox");
		TestUtil.isElementExist(forgotPasswordLink, "Forger password link");
	}

	public void valitateTooltip(String expectedText) throws InterruptedException {

		String expectedVal = emailboxTooltip.getCssValue("display");
		String actualText = emailboxTooltip.getText();
		
		System.out.println("expectedVal" + expectedVal);
		System.out.println("actualText " + actualText);
		
		if (expectedVal.trim().equalsIgnoreCase("block") && actualText.trim().equalsIgnoreCase(expectedText)) {
			passTestCase("Tooltip has been shows. Actual Tooltip Text : " + actualText
					+ " and expected tooltip text is : " + expectedText);
		}

		else {
			failTestCase("Tooltip verification failed. Actual Tooltip Text : " + actualText
					+ " and expected tooltip text is : " + expectedText);
		}
	}
	
	public void navigateToForgotPasswordScreen() throws InterruptedException{
		
		TestUtil.click(signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(emailName, 60, "Sign in email");
		
		TestUtil.click(forgotPasswordLink, "Forgot password link");
		TestUtil.waitForObjectVisible(forgotPasswordModal, 60, "Forgot password modal box");
		
		
	}
}
