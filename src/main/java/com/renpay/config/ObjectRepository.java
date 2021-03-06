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
			public static final String popupCloseButtonAtModalHeader_xpath = "//button[text()='×']";
			
			
		}
		
	}
	
	public class HomeScreen{
		
		public static final String imageProfile_ID = "imgProfile";
		
		public static final String logoutOpenerLink_xpath = "//li[@class='dropdown'][2]/a";
		public static final String logoutLink_xpath = "//ul[@class='dropdown-menu dropdown-user']//a[text()='Logout']";
		
		public static final String userProfile_xpath = "//ul[@class='dropdown-menu dropdown-user']//a[text()='User Profile']";
		
		public static final String renpayLogoImg_ID = "imgLogo";
		
		public static final String manageMyaccount_xpath = "//a[text()='Manage Your Account']";
		
		public static final String profileEdit_ID = "ProfileEdit";
		
		public static final String rpfTabInLeftPannel_xpath = "//a[text()='RFP']";
		
		public static final String createRFP_Btn_ID = "RFPCreate";
	}
	
	
	public class ProfileScreen{
		public static final String companyName_ID = "ContentPlaceHolder1_xtxtOrgName";
		public static final String updateConfirmBox_xpath = "//div[@id='divConfirm']//p[@id='ContentPlaceHolder1_pRegMsg']";
		public static final String closerBtn_ID = "ContentPlaceHolder1_btnClose";
		public static final String submitBtn_ID = "ContentPlaceHolder1_xlnkBtnSubmit";
		public static final String uploadButton_ID = "ContentPlaceHolder1_xctrlCompanyLogo_fileToUpload";
		public static final String companyAddress_ID = "ContentPlaceHolder1_xtxtAddress1";
		public static final String deleteUploadImg_xpath = "//a[@class='DelLink']";
		public static final String deleteUploadImgPopup_xpath = "//div[@class='modal-body']//p[@id='p2']";
		public static final String popupNoBtn_ID = "lnkDeleteFileConf";
		public static final String companyWebsite_ID = "ContentPlaceHolder1_xtxtwebsite";
		
		
	}
	
	public class CreateRFPScreen{
		
		public static final String productName_ID = "ContentPlaceHolder1_xtxtProduct";
		public static final String oneTimeRadioBtn_ID = "ContentPlaceHolder1_rdbType_0";
		public static final String recurringRadioBtn_ID = "ContentPlaceHolder1_rdbType_1";
		public static final String categoryDropdown_ID = "ContentPlaceHolder1_xddlCategory";
		public static final String subCategoryDropdown_ID = "ContentPlaceHolder1_xddlSubCategory";
		public static final String descriptionField_ID = "ContentPlaceHolder1_xtxtDescription";
		public static final String quantityField_ID = "ContentPlaceHolder1_xtxtQty";
		public static final String unitDropdown_ID = "ContentPlaceHolder1_xddlUnit";
		public static final String expectedDeliveryDaysField_ID = "ContentPlaceHolder1_xtxtNoDays";
		public static final String registeredCheckbox_ID = "ContentPlaceHolder1_xchkRegisteredAddr";
		public static final String shippingAddress_ID = "ContentPlaceHolder1_xtxtRegAdd";
		public static final String country_ID = "ContentPlaceHolder1_xtxtRegCountry";
		public static final String state_ID = "ContentPlaceHolder1_xtxtRegState";
		public static final String city_ID = "ContentPlaceHolder1_xtxtRegCity";
		public static final String pincode_ID = "ContentPlaceHolder1_xtxtRegPinCode";
		public static final String creditPeriodDropdown_ID = "ContentPlaceHolder1_xddlCreditPeriod";
		public static final String rfpDeadlineStartDate_ID = "ContentPlaceHolder1_xtxtStartDate";
		public static final String rfpDeadlineEndDate_ID = "ContentPlaceHolder1_xtxtEndDate";
		public static final String createBtn_ID = "ContentPlaceHolder1_xlnkbtnCreate";
		public static final String newAddressField_ID = "ContentPlaceHolder1_xtxtAdd1";
		public static final String newAddressCountryDropdown_ID = "ContentPlaceHolder1_xddlCountryAddr1";
		public static final String newAddressStateDropdown_ID = "ContentPlaceHolder1_xddlStateAddr1";
		public static final String newAddressCityDropdown_ID = "ContentPlaceHolder1_xddlCityAddr1";
		public static final String newAddressPincodeField_ID  = "ContentPlaceHolder1_xtxtPincodeAddr1";
		public static final String suppliersDetailsTable_ID = "tblSupplierList";
		public static final String addNewAddreddCheckbox_ID = "ContentPlaceHolder1_xchckAddr1";
		public static final String calander_xpath = "//div[@id='ui-datepicker-div']";
		public static final String RFPDetailsMsg_xpath = "//div[@class='modal-body']/h4[text()='Your RFP has been set for :']";
		
		
	}
	
	
	
	
	public class SignUpScreen{
		
		public static final String registerAsBuyerHeading_xpath = "//span[@id='spnCap' and text()='Register as a Buyer']";
		public static final String firstNameField_ID = "ContentPlaceHolder1_xtxtFirstName";
		public static final String lastNameField_ID = "ContentPlaceHolder1_xtxtLastName";
		public static final String companyNameField_ID = "ContentPlaceHolder1_xtxtName";
		public static final String addressCompanyField_ID = "ContentPlaceHolder1_xtxtAddress";
		public static final String stateField_ID = "ContentPlaceHolder1_xddlState";
		public static final String cityField_ID = "ContentPlaceHolder1_xddlCity";
		public static final String pinField_ID = "ContentPlaceHolder1_xtxtPincode";
		public static final String stateCode_ID = "ContentPlaceHolder1_xtxtStateCode";
		public static final String gstField_ID = "ContentPlaceHolder1_xtxtGST";
		public static final String emailField_ID = "ContentPlaceHolder1_xtxtEmail";
		public static final String mobileField_ID = "ContentPlaceHolder1_xtxtMobileNo";
		public static final String passwordField_ID = "ContentPlaceHolder1_xtxtPWD";
		public static final String confirmPasswordField_ID = "ContentPlaceHolder1_xtxtConfirmPWD";
		public static final String termsAndConditionCheckbox_ID = "ContentPlaceHolder1_xchkTnC";
		public static final String registerBtn_ID = "ContentPlaceHolder1_xbtnRegister";
		public static final String termsAndCnditionLink_xpath = "//a[text()='terms and conditions']";
		
		public static final String termsOfUseHeadingInsideT_And_Cpage = "//h2[text()='TERMS OF USE' and @class='txtbold1']";
		
		
	}
}
