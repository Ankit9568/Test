package com.renpay.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class SignUpPage extends TestInitialization {

	static WebDriver driver;

	public SignUpPage(WebDriver driver) {
		SignUpPage.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = SignUpScreen.registerAsBuyerHeading_xpath)
	public WebElement registerAsBuyerHeading;

	@FindBy(how = How.ID, using = SignUpScreen.firstNameField_ID)
	public WebElement firstNameField;

	@FindBy(how = How.ID, using = SignUpScreen.lastNameField_ID)
	public WebElement lastNameField;

	@FindBy(how = How.ID, using = SignUpScreen.companyNameField_ID)
	public WebElement companyNameField;

	@FindBy(how = How.ID, using = SignUpScreen.addressCompanyField_ID)
	public WebElement addressCompanyField;

	
	
	@FindBy(how = How.ID, using = SignUpScreen.stateField_ID)
	public WebElement stateField;

	@FindBy(how = How.ID, using = SignUpScreen.cityField_ID)
	public WebElement cityField;

	@FindBy(how = How.ID, using = SignUpScreen.pinField_ID)
	public WebElement pinField;

	@FindBy(how = How.ID, using = SignUpScreen.stateCode_ID)
	public WebElement stateCode;

	@FindBy(how = How.ID, using = SignUpScreen.gstField_ID)
	public WebElement gstField;

	@FindBy(how = How.ID, using = SignUpScreen.emailField_ID)
	public WebElement emailField;

	@FindBy(how = How.ID, using = SignUpScreen.mobileField_ID)
	public WebElement mobileField;

	@FindBy(how = How.ID, using = SignUpScreen.passwordField_ID)
	public WebElement passwordField;

	@FindBy(how = How.ID, using = SignUpScreen.confirmPasswordField_ID)
	public WebElement confirmPasswordField;

	@FindBy(how = How.ID, using = SignUpScreen.termsAndConditionCheckbox_ID)
	public WebElement termsAndConditionCheckbox;

	@FindBy(how = How.ID, using = SignUpScreen.registerBtn_ID)
	public WebElement registerBtn;
	
	@FindBy(how = How.XPATH, using = SignUpScreen.termsAndCnditionLink_xpath)
	public WebElement termsAndCnditionLink;
	
	@FindBy(how = How.XPATH, using = SignUpScreen.termsOfUseHeadingInsideT_And_Cpage)
	public WebElement termsOfUseHeadingInsideT_And_Cpage;
	
	public void navigateToSignUpPageAsBuyerAndValidate() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		TestUtil.click(loginPage.signinLink, "Sign Link");
		reports.log(LogStatus.PASS, "Validate Application is move to Sign up page");
		TestUtil.click(loginPage.buyerImgForSignup, "Buyer image");
		TestUtil.waitForObjectVisible(registerAsBuyerHeading, 60, "Register as a Buyer heading");
	}

	public void validateSignUpPageFields() throws InterruptedException {

		TestUtil.isElementExist(firstNameField, "First Name Field");
		TestUtil.isElementExist(lastNameField, "Last Name Field");
		TestUtil.isElementExist(companyNameField, "Company Name Field");
		TestUtil.isElementExist(addressCompanyField, "Company Address Field");
		TestUtil.isElementExist(stateField, "State Field");
		TestUtil.isElementExist(cityField, "City Field");
		TestUtil.isElementExist(pinField, "Pincode Field");
		TestUtil.isElementExist(stateCode, "State Field");
		TestUtil.isElementExist(gstField, "GST Field");
		TestUtil.isElementExist(emailField, "Email Field");
		TestUtil.isElementExist(mobileField, "Mobile Field");
		TestUtil.isElementExist(passwordField, "Password Field");
		TestUtil.isElementExist(confirmPasswordField, "Confirm Password Field");
		TestUtil.isElementExist(termsAndConditionCheckbox, "Terms and Condition Field");

	}

	public void fillOptionalField(String userType) throws InterruptedException {

		String gstNumber = null;
		
		if (userType.equalsIgnoreCase("AlreadyRegisteredUser")) {
			 gstNumber = TestUtil.getExcelKeyValue("SignUpPage", "GstField", "RegisteredUserValue");
		}
		else if (userType.equalsIgnoreCase("UnRegisteredUser")) {
			 gstNumber = TestUtil.getExcelKeyValue("SignUpPage", "GstField", "UnRegisteredUserValue");
		}
		sendKeys(gstNumber, gstField, "Gst Field");

	}

	public void fillMandatoryData(String userType) throws InterruptedException {

		String firstName = null;
		String lastName = null;
		String companyName = null;
		String companyAddress = null;
		String userState = null;
		String city = null;
		String pincodeField = null;
		String userEmail = null;
		String userMobile = null;
		String userPassword = null;

		if (userType.equalsIgnoreCase("AlreadyRegisteredUser")) {
			firstName = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
			lastName = TestUtil.getExcelKeyValue("SignUpPage", "LastName", "RegisteredUserValue");
			companyName = TestUtil.getExcelKeyValue("SignUpPage", "CompanyName", "RegisteredUserValue");
			companyAddress = TestUtil.getExcelKeyValue("SignUpPage", "CompanyAddress", "RegisteredUserValue");
			userState = TestUtil.getExcelKeyValue("SignUpPage", "StateField", "RegisteredUserValue");
			city = TestUtil.getExcelKeyValue("SignUpPage", "CityField", "RegisteredUserValue");
			pincodeField = TestUtil.getExcelKeyValue("SignUpPage", "PinField", "RegisteredUserValue");
			userEmail = TestUtil.getExcelKeyValue("SignUpPage", "EmailField", "RegisteredUserValue");
			userMobile = TestUtil.getExcelKeyValue("SignUpPage", "MobileField", "RegisteredUserValue");
			userPassword = TestUtil.getExcelKeyValue("SignUpPage", "PasswordField", "RegisteredUserValue");
		}

		else if (userType.equalsIgnoreCase("UnRegisteredUser")) {

			firstName = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "UnRegisteredUserValue");
			lastName = TestUtil.getExcelKeyValue("SignUpPage", "LastName", "UnRegisteredUserValue");
			companyName = TestUtil.getExcelKeyValue("SignUpPage", "CompanyName", "UnRegisteredUserValue");
			companyAddress = TestUtil.getExcelKeyValue("SignUpPage", "CompanyAddress", "UnRegisteredUserValue");
			userState = TestUtil.getExcelKeyValue("SignUpPage", "StateField", "UnRegisteredUserValue");
			city = TestUtil.getExcelKeyValue("SignUpPage", "CityField", "UnRegisteredUserValue");
			pincodeField = TestUtil.getExcelKeyValue("SignUpPage", "PinField", "UnRegisteredUserValue");
			userEmail = TestUtil.getExcelKeyValue("SignUpPage", "EmailField", "UnRegisteredUserValue");
			userMobile = TestUtil.getExcelKeyValue("SignUpPage", "MobileField", "UnRegisteredUserValue");
			userPassword = TestUtil.getExcelKeyValue("SignUpPage", "PasswordField", "UnRegisteredUserValue");

		}

		sendKeys(firstName, firstNameField, "First Name Field");
		sendKeys(lastName, lastNameField, "Last Name Field");
		sendKeys(companyName, companyNameField, "Company Name Field");
		sendKeys(companyAddress, addressCompanyField, "Company Address Field");
		
		reports.log(LogStatus.PASS, "Select state " + userState);
		new Select(stateField).selectByVisibleText(userState);
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		
		reports.log(LogStatus.PASS, "Select City  " + userState);
		new Select(cityField).selectByVisibleText(city);
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		
		sendKeys(pincodeField, pinField, "Pincode Field");
		sendKeys(userEmail, emailField, "Email Field");
		sendKeys(userMobile, mobileField, "Mobile Number Field");
		sendKeys(userPassword, passwordField, "User Password Field");
		sendKeys(userPassword, confirmPasswordField, "User Confirm Password Field");
		
		
	}

	public void clickRegisterBtnAndValidateErrorMsg(WebElement we , String objectName , String expectedTooltip) throws InterruptedException {

		reports.log(LogStatus.PASS, "Click on Register Button");
		registerBtn.click();
		Thread.sleep(5000);
		TestUtil.movePointerToObject(we, objectName);
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		new LoginPage(driver).valitateTooltip(expectedTooltip);

	}
	
	
	
	public void ClickTermsAndConditionAndValidate() throws InterruptedException{
		
		TestUtil.click(termsAndCnditionLink, "Terms and condition link");
		Thread.sleep(2000);
		ArrayList<String> windowHandleAfterOpenNewTab = new ArrayList<String>(driver.getWindowHandles());
		log.info("Window size :" + windowHandleAfterOpenNewTab.size());
		reports.log(LogStatus.PASS, "Terms and condition page successfully displayed");
		driver.switchTo().window(windowHandleAfterOpenNewTab.get(windowHandleAfterOpenNewTab.size() - 1));
		TestUtil.isElementExist(termsOfUseHeadingInsideT_And_Cpage, "Heading of Terms and Condition page");
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		driver.close();
		driver.switchTo().window(windowHandleAfterOpenNewTab.get(0));
	
	}
}