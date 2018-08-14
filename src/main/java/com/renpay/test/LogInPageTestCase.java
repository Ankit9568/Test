package com.renpay.test;

import org.testng.annotations.Test;

import com.renpay.pages.LoginPage;
import com.renpay.utils.TestInitialization;

public class LogInPageTestCase extends TestInitialization {

	
	@Test
	public void tc_renpay01_LoginWithValidCredentials() throws InterruptedException {

		LoginPage LoginPage = new LoginPage(driver);
		LoginPage.appliactionLoginwithValidCredentails();
		TestInitialization.applicationLogout();
	}

	@Test
	public void tc_renpay02_LoginWithInValidCredentials() throws InterruptedException {

		LoginPage LoginPage = new LoginPage(driver);
		LoginPage.appliactionLoginwithInValidCredentails();
	}

	
}
