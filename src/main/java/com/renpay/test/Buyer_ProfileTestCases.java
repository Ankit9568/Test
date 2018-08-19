package com.renpay.test;

import java.io.File;

import org.testng.annotations.Test;

import com.renpay.pages.HomePage;
import com.renpay.pages.ProfilePage;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class Buyer_ProfileTestCases extends TestInitialization {

	@Test
	public void tc_001_ClickMyProfileLink() throws InterruptedException{
		
		
		ProfilePage profilePage = new ProfilePage(driver);
		
		TestUtil.click(new HomePage(driver).manageMyaccount, "Manage My account");
		TestUtil.click(new HomePage(driver).profileEditLink, "Profile Link");
		TestUtil.waitForObjectVisible(new ProfilePage(driver).companyName, 180, "Company Name Editbox");
		profilePage.validateAllFields();
		
		String imageFilePath = System.getProperty("user.dir")+ File.separator + "Renepay.png";
		System.out.println("Renepay image : " + imageFilePath);
		TestUtil.click(profilePage.uploadButton, "Upload Button");
		profilePage.uploadButton.sendKeys(imageFilePath);
		
	}
	
	
	
}
