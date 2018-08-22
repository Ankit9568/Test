package com.renpay.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.renpay.config.ObjectRepository;
import com.renpay.pages.HomePage;
import com.renpay.pages.LoginPage;
import com.renpay.utils.CheckEmail;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class LogInPageTestCase extends TestInitialization {

	@Test
	public void tc_Login_001_VerifyLoginPopup() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		TestUtil.click(loginPage.signinLink, "Sign in link");
		reports.log(LogStatus.PASS, "Validate Login pop is displayed");
		TestUtil.waitForObjectVisible(loginPage.emailName, 60, "Sign in email");
		TestUtil.waitForObjectVisible(loginPage.password, 60, "Sign in password");
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
	}
	
	@Test
	public void tc_Login_002_VerifyLoginScreenField() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		TestUtil.click(loginPage.signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(loginPage.emailName, 60, "Sign in email");
		loginPage.verifyLoginScreenPopUp();
	}

	@Test
	public void tc_Login_004_Login_With_BlankUser_Pwd() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		TestUtil.click(loginPage.signinLink, "Sign in link");
		reports.log(LogStatus.PASS, "Click on login button");
		loginPage.loginBtn.click();
		Thread.sleep(5000);
		TestUtil.movePointerToObject(loginPage.emailName, "login popup email field");
		loginPage.valitateTooltip(TestUtil.getExcelKeyValue("ErrorMessages", "BlankUserName", "Message").trim());
	}

	@Test
	public void tc_Login_005_login_ValidUserAndInvalidPwd() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.appliactionLoginAndValidateError(TestUtil.getExcelKeyValue("LogInPage", "UserName", "ValidValues"),
				TestUtil.getExcelKeyValue("LogInPage", "Password", "InvalidValues"));
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "IncorrectUserNameAndPassword", "Message").trim(), loginPage.loginError);
		TestUtil.click(loginPage.loginErrorPopupCloseBtn, "Login error close button");
		TestUtil.waitForObjectVisible(loginPage.signinLink, 60, "Sign in link");

	}

	@Test
	public void tc_Login_006_login_InValidUserAndValidPwd() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.appliactionLoginAndValidateError(TestUtil.getExcelKeyValue("LogInPage", "UserName", "InvalidValues"),
				TestUtil.getExcelKeyValue("LogInPage", "Password", "ValidValues"));
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "IncorrectUserNameAndPassword", "Message").trim(),loginPage.loginError);
		TestUtil.click(loginPage.loginErrorPopupCloseBtn, "Login error close button");
		TestUtil.waitForObjectVisible(loginPage.signinLink, 60, "Sign in link");
	}

	@Test
	public void tc_Login_007_login_validUserAndvalidPwd() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.appliactionLoginwithValidCredentails();
		TestInitialization.applicationLogout();
	}

	@Test
	public void tc_Login_008_login_InValidUserAndInValidPwd() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.appliactionLoginAndValidateError(TestUtil.getExcelKeyValue("LogInPage", "UserName", "InvalidValues"),
				TestUtil.getExcelKeyValue("LogInPage", "Password", "InvalidValues"));
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "IncorrectUserNameAndPassword", "Message").trim(), loginPage.loginError);
		TestUtil.click(loginPage.loginErrorPopupCloseBtn, "Login error close button");
		TestUtil.waitForObjectVisible(loginPage.signinLink, 60, "Sign in link");
	}

	@Test
	public void tc_Login_009_VerifyRememberMeSelectFunctionality() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.appliactionLoginWithRememberMe(TestUtil.getExcelKeyValue("LogInPage", "UserName", "ValidValues"),
				TestUtil.getExcelKeyValue("LogInPage", "Password", "ValidValues"));
		TestInitialization.applicationLogout();

		TestUtil.click(loginPage.signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(loginPage.emailName, 60, "Sign in email");
		TestUtil.click(loginPage.loginBtn, "Login button");

		TestUtil.waitForObjectInvisble(By.id(ObjectRepository.pleaseWaitModal_ID), 10, "Please wait modal alertbox");
		TestUtil.waitForObjectVisible(new HomePage(driver).imageProfile, 10, "User Profile");

	}

	@Test
	public void tc_Login_010_VerifyRememberMeUnselectFunctionality() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.appliactionLoginwithValidCredentails();
		TestInitialization.applicationLogout();
		TestUtil.click(loginPage.signinLink, "Sign in link");
		TestUtil.waitForObjectVisible(loginPage.emailName, 60, "Sign in email");
		if (loginPage.emailName.getAttribute("value").trim().contentEquals("")
				&& loginPage.password.getAttribute("value").trim().contentEquals(""))
		{
			passTestCase("user name and password blank found");
		}
		else {
			failTestCase("User name and password is not blank");
		}
	}
	
	
	@Test
	public void tc_F_Password001_ForgatPwdWithInvalidEmail() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.navigateToForgotPasswordScreen();
		sendKeys("WrongEmail@gmail.com", loginPage.userNameForForgotPassword, "User email for forgot password screen");
		TestUtil.click(loginPage.submitBtnForForgotPassword, "Submit password for forgot password screen");
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "ForgotPasswordInvalidEmail", "Message").trim(), loginPage.loginError_ForgotPassward);
		TestUtil.click(loginPage.closeBtnForgotPasswordModal, "Login error close button");
	
	}
	
	
	@Test
	public void tc_F_Password004_ForgatPwdWithValidEmail() throws InterruptedException, IOException {

		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.navigateToForgotPasswordScreen();
		sendKeys(TestUtil.getExcelKeyValue("LogInPage", "UserName", "ValidValues"), loginPage.userNameForForgotPassword, "User email for forgot password screen");
		TestUtil.click(loginPage.submitBtnForForgotPassword, "Submit password for forgot password screen");
		
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "ForgotPasswordValidEmail", "Message").trim(), loginPage.loginError_ForgotPassward);
		TestUtil.click(loginPage.closeBtnForgotPasswordModal, "Login error close button");
		
		
		reports.log(LogStatus.PASS, "Wait for 30 second and validate email is sended over user registered email..");
		Thread.sleep(30000);
		
		
		String userName = "hprankit@gmail.com";
		String password = "Ankit@9568989975";
		CheckEmail searcher = new CheckEmail();
		String subjectKeyword = "Reset your Password";
		String fromEmail = "notifications@renepay.in";
		String bodySearchText = "to reset your password. If you didn’t request this change, please contact the Renepay helpdesk";
		boolean emailFound = searcher.searchEmail(userName, password, subjectKeyword, fromEmail, bodySearchText);
		
		if(emailFound){
			passTestCase("Email is found on registered user");
		}
		else
		{
			failTestCase("Email is not found on registered user");
		}
		
	}

}
