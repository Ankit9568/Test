package com.renpay.test;

import java.io.File;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.renpay.pages.ProfilePage;
import com.renpay.pages.SignUpPage;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class Buyer_ProfileTestCases extends TestInitialization {

	@Test
	public void tc_001_UpdateUserProfile() throws InterruptedException {

		ProfilePage profilePage = new ProfilePage(driver);
		profilePage.navigateToUserProfile();
		profilePage.validateAllFields();
		String imageFilePath = System.getProperty("user.dir") + File.separator + "Renepay.png";
		reports.log(LogStatus.PASS, "Trying to upload an company logo");
		profilePage.uploadButton.sendKeys(imageFilePath);
		TestUtil.waitForObjectVisible(new ProfilePage(driver).deleteUploadImg, 60, "Delete Upload Image icon");
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		profilePage.pressDelBtnAndValidate();
		TestUtil.click(profilePage.popupNoBtn, "Popup NO button");
		sendKeys("http://www.google.co.in", profilePage.companyWebsite, "Company Website");
		profilePage.clickSubmitBtnAndValidate();

	}
	
	@Test
	public void tc_ValidateUserProfileBtn() throws InterruptedException {

		ProfilePage profilePage = new ProfilePage(driver);
		SignUpPage signupPage = new SignUpPage(driver);
		profilePage.navigateToUserProfile();
		TestUtil.validateAttribute(signupPage.gstField, "maxlength",
				TestUtil.getExcelKeyValue("ProfilePage", "GstField", "MaxLengthValue"));
		reports.log(LogStatus.PASS, "Clear the First name field");
		signupPage.firstNameField.sendKeys(Keys.CONTROL, "a");
		signupPage.firstNameField.sendKeys(Keys.DELETE);
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		profilePage.clickSubmitrBtnAndValidateErrorMsg(signupPage.firstNameField, "First Name",
				TestUtil.getExcelKeyValue("ErrorMessages", "SignUpError", "Message"));
		
	}

}
