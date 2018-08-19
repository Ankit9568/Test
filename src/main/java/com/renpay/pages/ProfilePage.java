package com.renpay.pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class ProfilePage extends TestInitialization {

	static WebDriver driver;

	public ProfilePage(WebDriver driver) {
		ProfilePage.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = ProfileScreen.companyName_ID)
	public WebElement companyName;

	@FindBy(how = How.XPATH, using = ProfileScreen.updateConfirmBox_xpath)
	public WebElement updateConfirmBox;

	@FindBy(how = How.ID, using = ProfileScreen.closerBtn_ID)
	public WebElement closerBtn;

	@FindBy(how = How.ID, using = ProfileScreen.submitBtn_ID)
	public WebElement submitBtn;
	
	@FindBy(how = How.ID, using = ProfileScreen.uploadButton_ID)
	public WebElement uploadButton;

	public void updateProfileButtonAndValidate() throws InterruptedException {

		TestUtil.click(new HomePage(driver).manageMyaccount, "Manage My account");
		TestUtil.click(new HomePage(driver).profileEditLink, "Profile Link");

		TestUtil.waitForObjectVisible(companyName, 180, "Company Name Editbox");
		String updateCompanyName = companyName.getAttribute("value") + new Random().nextInt(50) + 1;

		TestUtil.sendKeys(updateCompanyName, companyName, "CompanyName Editbox");
		TestUtil.click(submitBtn, "Submit Button");
		TestUtil.waitForObjectVisible(updateConfirmBox, 180, "Update Confirm box");

		if (updateConfirmBox.getText().trim().contentEquals("Your profile updated successfully.")) {
			passTestCase("Update successfully message has been displayed");
			closerBtn.click();
		} else {
			failTestCase("Your profile updated successfully has not been displayed");
		}

		TestUtil.click(new HomePage(driver).manageMyaccount, "Manage My account");
		TestUtil.click(new HomePage(driver).profileEditLink, "Profile Link");
		TestUtil.waitForObjectVisible(companyName, 180, "Company Name Editbox");

		if (companyName.getAttribute("value").trim().contentEquals(updateCompanyName)) {
			passTestCase("Update profile successfully. Actual company name : " + companyName.getAttribute("value")
					+ " and expected company name : " + updateCompanyName);

		} else {
			failTestCase("Update profile failed.Actual company name : " + companyName.getAttribute("value")
					+ " and expected company name : " + updateCompanyName);
		}

	}

	public void validateAllFields() throws InterruptedException {

		SignUpPage signUpPage = new SignUpPage(driver);
		String actualFirstNameVal = signUpPage.firstNameField.getAttribute("value");
		String actualLastName = signUpPage.lastNameField.getAttribute("value");
		String actualCompanyName = companyName.getAttribute("value");
		String actualCompanyAddress = signUpPage.addressCompanyField.getAttribute("value");
		String actualState = new Select(signUpPage.stateField).getFirstSelectedOption().getText();
		String actualCity = new Select(signUpPage.cityField).getFirstSelectedOption().getText();
		String actualPincode = signUpPage.cityField.getAttribute("value");
		String actualGstNumber = signUpPage.gstField.getAttribute("value");

		String expectedFirstName = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
		String expectedLastName = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
		String expectedCompanyName = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
		String expectedCompanyaddress = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
		String expectedState = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
		String expectedCity = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
		String expectedPinCode = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");
		String expectedGstNumber = TestUtil.getExcelKeyValue("SignUpPage", "FirstName", "RegisteredUserValue");

		if (actualFirstNameVal.contentEquals(expectedFirstName) && actualLastName.contentEquals(expectedLastName)
				&& actualCompanyName.contentEquals(expectedCompanyName)
				&& actualCompanyAddress.contentEquals(expectedCompanyaddress)
				&& actualState.contentEquals(expectedState) && actualCity.contentEquals(expectedCity)
				&& actualPincode.contentEquals(expectedPinCode) && actualGstNumber.contentEquals(expectedGstNumber)) {
			passTestCase("Verification of user profile field passed " + "Actual value of first name is : "
					+ actualFirstNameVal + " Expected First name value : " + expectedFirstName + "\n"
					+ "Actual value of last name is : " + actualLastName + " and expected last name is : "
					+ expectedLastName + "\n " + " Actual company Name :" + actualCompanyName
					+ " and expected company Name : " + expectedCompanyName + "\n" + "Actual State : " + actualState
					+ " and expected state is : " + expectedState + "\n" + "Actual City " + actualCity
					+ " and expand ected city : " + expectedCity + " \n " + " Actual Pin code " + actualPincode
					+ " and expected pin code : " + expectedPinCode + "\n" + " Actual  GST no : " + actualGstNumber
					+ " Expected gst no : " + expectedGstNumber);
		} else {
			failTestCase("Verification of user profile field failed " + "Actual value of first name is : "
					+ actualFirstNameVal + " Expected First name value : " + expectedFirstName + "\n"
					+ "Actual value of last name is : " + actualLastName + " and expected last name is : "
					+ expectedLastName + "\n " + " Actual company Name :" + actualCompanyName
					+ " and expected company Name : " + expectedCompanyName + "\n" + "Actual State : " + actualState
					+ " and expected state is : " + expectedState + "\n" + "Actual City " + actualCity
					+ " and expand ected city : " + expectedCity + " \n " + " Actual Pin code " + actualPincode
					+ " and expected pin code : " + expectedPinCode + "\n" + " Actual  GST no : " + actualGstNumber
					+ " and Expected gst no : " + expectedGstNumber);
		}

	}
}
