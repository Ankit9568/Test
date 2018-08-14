package com.renpay.config;

import java.io.File;

public class ObjectRepository {

	public static final String testDataFilePath = System.getProperty("user.dir") + File.separator + "src"
			+ File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "renpay"
			+ File.separator + "config" + File.separator + "testdata.xlsx";

	
	
	public static final String pleaseWaitModal_ID = "H1";
	
	public class LoginScreen{
		
		public static final String signinLink_xpath = "//span[text()='Sign in']";
		public static final String emailName_ID = "xtxtUserName";
		public static final String password_ID = "xtxtPassword";
		public static final String loginBtn_ID = "lnkLogin";
		public static final String loginError_xpath = "//div[@id='divLoginErrorMsg']//p[@id='p2']"; 
		
	}
	
	public class HomeScreen{
		
		public static final String imageProfile_ID = "imgProfile";
		
		public static final String logoutOpenerLink_xpath = "//li[@class='dropdown'][2]/a";
		public static final String logoutLink_xpath = "//ul[@class='dropdown-menu dropdown-user']//a[text()='Logout']";
		
		public static final String renpayLogoImg_ID = "imgLogo";
		
		public static final String manageMyaccount_xpath = "//a[text()='Manage Your Account']";
		
		public static final String profileEdit_ID = "ProfileEdit";
		
		
	}
	
	
	public class ProfileScreen{
		public static final String companyName_ID = "ContentPlaceHolder1_xtxtOrgName";
		public static final String updateConfirmBox_xpath = "//div[@id='divConfirm']//p[@id='ContentPlaceHolder1_pRegMsg']";
		public static final String closerBtn_ID = "ContentPlaceHolder1_btnClose";
		public static final String submitBtn_ID = "ContentPlaceHolder1_xlnkBtnSubmit";
		
		
	}
}
