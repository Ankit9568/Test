package com.renpay.test;

import org.testng.annotations.Test;

import com.renpay.pages.LoginPage;
import com.renpay.pages.SignUpPage;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class SignupTestCase extends TestInitialization {

	@Test
	public void tc_SignUp_001_RedirectToSignUpPage() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
	}
	
	@Test
	public void tc_SignUp_002_ValidateSignUpFields() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.validateSignUpPageFields();
	}
	
	@Test
	public void tc_SignUP_005_SignUpwithOutMadaoryField() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.fillOptionalField("AlreadyRegisteredUser");
		signUppage.clickRegisterBtnAndValidateErrorMsg(signUppage.firstNameField, "First Name",
				TestUtil.getExcelKeyValue("ErrorMessages", "SignUpError", "Message"));
	}
	
	@Test
	public void tc_SignUP_006_SignUPWithAlreadyRegisterUser() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		LoginPage loginPage = new LoginPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.fillMandatoryData("AlreadyRegisteredUser");
		TestUtil.click(signUppage.termsAndConditionCheckbox, "Terms and condition checkbox");
		signUppage.fillOptionalField("AlreadyRegisteredUser");
		TestUtil.click(signUppage.registerBtn, "Register Button");
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "AlreadyRegisteredUserErrorMsg", "Message").trim(),
				loginPage.loginError_ForgotPassward);
		TestUtil.click(loginPage.popupCloseButtonAtModalHeader, "Pop up closer button");

	}

	@Test
	public void tc_SignUP_007_SignUPWithoutSelectTermsAndCondition() throws InterruptedException {

		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.fillMandatoryData("AlreadyRegisteredUser");
		signUppage.fillOptionalField("AlreadyRegisteredUser");

		signUppage.clickRegisterBtnAndValidateErrorMsg(signUppage.termsAndConditionCheckbox, "Terms And Condition Checkbox",
				TestUtil.getExcelKeyValue("ErrorMessages", "AcceptTermsAndConditionErrorMsg", "Message"));

	}
	
	@Test
	public void tc_SignUP_008_VerifyTermsAndConditionLink() throws InterruptedException{
		
		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.ClickTermsAndConditionAndValidate();
	
	}
	
	
	
	public void tc_SignUp_012_AfterSignUPValidateActivationLink() throws InterruptedException{
		
		// To Do pending
		SignUpPage signUppage = new SignUpPage(driver);
		signUppage.navigateToSignUpPageAsBuyerAndValidate();
		signUppage.fillMandatoryData("UnRegisteredUserValue");
		signUppage.fillOptionalField("UnRegisteredUserValue");
		TestUtil.click(signUppage.termsAndConditionCheckbox, "Terms And Condition Checkbox");
		
		
	}
	
}
