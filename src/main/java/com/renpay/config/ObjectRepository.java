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
		public static final String buyerRadioBtn_ID = "rdbLoginType_0";
		public static final String supplierRadioBtn_ID = "rdbLoginType_1";
		public static final String labelForBuyerRadioBtn_xpath = "//label[@for='rdbLoginType_0']";
		public static final String labelForSuppilerRadioBtn_xpath = "//label[@for='rdbLoginType_1']";
		public static final String buyerImgForSignup_xpath = "//img[@alt='Buyer Icon']";
		public static final String suppilerImgForForSignup_xpath = "//img[@alt='Seller Icon']";
		public static final String rememberMeCheckBox_ID = "chkremember";
		public static final String forgotPasswordLink_xpath = "//a[text()='Forgot Password?']";
		public static final String emailboxTooltip_xpath = "//div[@role='tooltip']";
		public static final String loginErrorPopupCloseBtn_ID = "lnkbtnAddUserClose";
		
		
		public class ForgotPassword{
			
			public static final String forgotPasswordModal_xpath = "//div[@class='modal-body']/p[text()='Enter your username to reset your password']";
			public static final String userName_ID = "txtFPUserName";
			public static final String submitBtn_ID = "lnkFPSubmit";
			public static final String loginError_ID = "msgBody"; 
			public static final String closeBtnForgotPasswordModal_ID = "xbtnClose";
		}
		
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
