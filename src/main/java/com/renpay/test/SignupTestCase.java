package com.renpay.test;

import org.testng.annotations.Test;

import com.renpay.pages.LoginPage;
import com.renpay.pages.SignUpPage;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class SignupTestCase extends TestInitialization {

	public void tc_SignUp_001_RedirectToSignUpPage() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
	}

	public void tc_SignUp_002_ValidateSignUpFields() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.validateSignUpPageFields();
	}

	public void tc_SignUP_005_SignUpwithOutMadaoryField() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.fillOptionalField("AlreadyRegisteredUser");
		signUppage.clickRegisterBtnAndValidateErrorMsg();
	}

	@Test
	public void tc_SignUP_006_SignUPWithAlreadyRegisterUser() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.fillMandatoryData("AlreadyRegisteredUser");
		signUppage.fillOptionalField("AlreadyRegisteredUser");
		System.out.println("filled");
		TestUtil.click(signUppage.registerBtn, "Register Button");
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "AlreadyRegisteredUserErrorMsg", "Message").trim(),
				loginPage.loginError_ForgotPassward);
		TestUtil.click(loginPage.popupCloseButtonAtModalHeader, "Pop up closer button");

	}

}
