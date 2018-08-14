package com.renpay.pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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
}
