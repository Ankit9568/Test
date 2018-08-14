package com.renpay.test;

import org.testng.annotations.Test;

import com.renpay.pages.ProfilePage;
import com.renpay.utils.TestInitialization;

public class HomePageTestCase extends TestInitialization {

	
	@Test
	public void tc_renpay01_UpdateUserProfile() throws InterruptedException {

		ProfilePage profilePage = new ProfilePage(driver);
		profilePage.updateProfileButtonAndValidate();
		
	}

	

	
}
