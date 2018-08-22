package com.renpay.pages;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;
import com.renpay.config.ObjectRepository;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class CreateRFP_Page extends TestInitialization {

	static WebDriver driver;

	public CreateRFP_Page(WebDriver driver) {
		CreateRFP_Page.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = CreateRFPScreen.productName_ID)
	public WebElement productName;

	@FindBy(how = How.ID, using = CreateRFPScreen.oneTimeRadioBtn_ID)
	public WebElement oneTimeRadioBtn;

	@FindBy(how = How.ID, using = CreateRFPScreen.recurringRadioBtn_ID)
	public WebElement recurringRadioBtn;

	@FindBy(how = How.ID, using = CreateRFPScreen.categoryDropdown_ID)
	public WebElement categoryDropdown;

	@FindBy(how = How.ID, using = CreateRFPScreen.subCategoryDropdown_ID)
	public WebElement subCategoryDropdown;

	@FindBy(how = How.ID, using = CreateRFPScreen.descriptionField_ID)
	public WebElement descriptionField;

	@FindBy(how = How.ID, using = CreateRFPScreen.quantityField_ID)
	public WebElement quantityField;

	@FindBy(how = How.ID, using = CreateRFPScreen.unitDropdown_ID)
	public WebElement unitDropdown;

	@FindBy(how = How.ID, using = CreateRFPScreen.expectedDeliveryDaysField_ID)
	public WebElement expectedDeliveryDaysField;

	@FindBy(how = How.ID, using = CreateRFPScreen.registeredCheckbox_ID)
	public WebElement registeredCheckbox;

	@FindBy(how = How.ID, using = CreateRFPScreen.shippingAddress_ID)
	public WebElement shippingAddress;

	@FindBy(how = How.ID, using = CreateRFPScreen.country_ID)
	public WebElement country;

	@FindBy(how = How.ID, using = CreateRFPScreen.state_ID)
	public WebElement state;

	@FindBy(how = How.ID, using = CreateRFPScreen.city_ID)
	public WebElement city;

	@FindBy(how = How.ID, using = CreateRFPScreen.pincode_ID)
	public WebElement pincode;

	@FindBy(how = How.ID, using = CreateRFPScreen.creditPeriodDropdown_ID)
	public WebElement creditPeriodDropdown;

	@FindBy(how = How.ID, using = CreateRFPScreen.rfpDeadlineStartDate_ID)
	public WebElement rfpDeadlineStartDate;

	@FindBy(how = How.ID, using = CreateRFPScreen.rfpDeadlineEndDate_ID)
	public WebElement rfpDeadlineEndDate;

	@FindBy(how = How.ID, using = CreateRFPScreen.newAddressField_ID)
	public WebElement newAddressField;

	@FindBy(how = How.ID, using = CreateRFPScreen.newAddressCountryDropdown_ID)
	public WebElement newAddressCountryDropdown;

	@FindBy(how = How.ID, using = CreateRFPScreen.newAddressStateDropdown_ID)
	public WebElement newAddressStateDropdown;

	@FindBy(how = How.ID, using = CreateRFPScreen.newAddressCityDropdown_ID)
	public WebElement newAddressCityDropdown;

	@FindBy(how = How.ID, using = CreateRFPScreen.newAddressPincodeField_ID)
	public WebElement newAddressPincodeField;

	@FindBy(how = How.ID, using = CreateRFPScreen.createBtn_ID)
	public WebElement createBtn;

	@FindBy(how = How.ID, using = CreateRFPScreen.suppliersDetailsTable_ID)
	public WebElement suppliersDetailsTable;

	@FindBy(how = How.ID, using = CreateRFPScreen.addNewAddreddCheckbox_ID)
	public WebElement addNewAddreddCheckbox;

	@FindBy(how = How.XPATH, using = CreateRFPScreen.calander_xpath)
	public WebElement calander;
	
	@FindBy(how = How.XPATH, using = CreateRFPScreen.RFPDetailsMsg_xpath)
	public WebElement RFPDetailsMsg;

	
	
	public void navigateToCreateRFPPage() throws InterruptedException {

		HomePage homePage = new HomePage(driver);

		TestUtil.click(homePage.rpfTabInLeftPannel, "RFP Tab in left pannel");
		TestUtil.waitForObjectVisible(homePage.createRFP_Btn, 20, "Product Name");
		TestUtil.click(homePage.createRFP_Btn, "Create RFP button");
		TestUtil.waitForObjectVisible(productName, 60, "Product Name");
	}

	public void validateAllField() throws InterruptedException {

		TestUtil.isElementExist(productName, "Product Name");
		TestUtil.isElementExist(oneTimeRadioBtn, "One time radio button");
		TestUtil.isElementExist(recurringRadioBtn, "Recurring radio button");
		TestUtil.isElementExist(categoryDropdown, "Category dropdown");
		TestUtil.isElementExist(subCategoryDropdown, "Sub category dropdown");
		TestUtil.isElementExist(descriptionField, "Description field");
		TestUtil.isElementExist(quantityField, "Quantity Field");
		TestUtil.isElementExist(unitDropdown, "Unit dropdown");
		TestUtil.isElementExist(expectedDeliveryDaysField, "Expected delivery days field");
		TestUtil.isElementExist(registeredCheckbox, "Registered checkbox");
		TestUtil.isElementExist(shippingAddress, "Shipping address");
		TestUtil.isElementExist(country, "Country field");
		TestUtil.isElementExist(state, "State Field");
		TestUtil.isElementExist(city, "City Field");
		TestUtil.isElementExist(pincode, "Pincode Field");
		TestUtil.isElementExist(creditPeriodDropdown, "Credit period dropdown");
		TestUtil.isElementExist(rfpDeadlineStartDate, "RFP deadline start date");
		TestUtil.isElementExist(rfpDeadlineEndDate, "RFP deadline End date");
	}

	public void clickCreaterBtnAndValidateErrorMsg(WebElement we, String objectName, String expectedTooltip)
			throws InterruptedException {
	
		reports.log(LogStatus.PASS, "Click create button and validate tooltip of blank product name");
		TestUtil.click(createBtn, "Create Button");
		Thread.sleep(5000);
		TestUtil.movePointerToObject(we, objectName);
		new LoginPage(driver).valitateTooltip(expectedTooltip);

	}

	public void selectCategoryAndValidateAndValidateSubcategoryItems(String categoryValue) throws InterruptedException {

		
		selectDropdown(categoryDropdown, categoryValue, "Category");

		TestUtil.waitForObjectInvisble(By.id(ObjectRepository.pleaseWaitModal_ID), 180, "Please wait modal alertbox");
		Thread.sleep(2000);
		
		String expectedSubCategoryItemsArr = TestUtil.getExcelKeyValue("CreateRFA", categoryValue.trim(),
				"SubCategory");

		ArrayList<String> ActualsubcategoryItems = new ArrayList<String>();
	
		for (WebElement option : new Select(subCategoryDropdown).getOptions()) {
			if (!option.getText().equalsIgnoreCase("Select")) {
				ActualsubcategoryItems.add(option.getText());
			}
		}

		ArrayList<String> expectedSubcategoryItems = new ArrayList<String>(
				Arrays.asList(expectedSubCategoryItemsArr.split(",")));

		if (ActualsubcategoryItems.equals(expectedSubcategoryItems)) {
			passTestCase("validation of subcategory " + categoryValue + " has been passed. Actual sub category : "
					+ ActualsubcategoryItems.toString() + " and expected subcategory items "
					+ expectedSubcategoryItems.toString());
		}

		else {
			failTestCase("validation of subcategory " + categoryValue + " has been failed. Actual sub category : "
					+ ActualsubcategoryItems.toString() + " and expected subcategory items "
					+ expectedSubcategoryItems.toString());

		}

	}

	public void validateFieldIsNumaric(WebElement we, String objectName) throws InterruptedException {

		reports.log(LogStatus.PASS, "Sending the alphabet char on " + objectName);
		we.sendKeys("Automation");
		if (we.getAttribute("value").trim().contentEquals("")) {
			passTestCase(objectName + " is not accepted alphabet. trying to send the numaric digit");
			we.sendKeys("12");
			if (we.getAttribute("value").trim().contentEquals("12")) {
				passTestCase(objectName + " is accepted the numaric keys");
			} else {
				failTestCase(objectName + " is not accepted alpganumaric and alphabet");
			}
		} else {
			failTestCase(objectName + " is accepted alphabets " + we.getAttribute("value"));
		}
	}

	public void validateShippingAddress() throws InterruptedException {

		TestUtil.movePointerToObject(pincode, "Pincode");
		String actualCompanyAddress = shippingAddress.getAttribute("value");
		String actualState = state.getAttribute("value");
		String actualCity = city.getAttribute("value");
		String actualPincode = pincode.getAttribute("value");

		String expectedCompanyaddress = TestUtil.getExcelKeyValue("SignUpPage", "CompanyAddress",
				"RegisteredUserValue");
		String expectedState = TestUtil.getExcelKeyValue("SignUpPage", "StateField", "RegisteredUserValue");
		String expectedCity = TestUtil.getExcelKeyValue("SignUpPage", "CityField", "RegisteredUserValue");
		String expectedPinCode = TestUtil.getExcelKeyValue("SignUpPage", "PinField", "RegisteredUserValue");

		if (actualCompanyAddress.contentEquals(expectedCompanyaddress) && actualState.contentEquals(expectedState)
				&& actualCity.contentEquals(expectedCity) && actualPincode.contentEquals(expectedPinCode)) {
			passTestCase("Verification of user profile field passed.<br>" + "Actual State : " + actualState
					+ " and expected state is : " + expectedState + "<br>" + "Actual City " + actualCity
					+ " and expected city : " + expectedCity + " <br> " + " Actual Pin code " + actualPincode
					+ " and expected pin code : " + expectedPinCode);
		} else {
			failTestCase("Verification of user profile field failed " + "Actual State : " + actualState
					+ " and expected state is : " + expectedState + "<br>" + "Actual City " + actualCity
					+ " and expected city : " + expectedCity + " <br> " + " Actual Pin code " + actualPincode
					+ " and expected pin code : " + expectedPinCode);
		}

	}

	public void validateNewelyAddedAddressAndFill() throws InterruptedException {

		TestUtil.waitForObjectVisible(newAddressField, 60, "Newly Added address");
		sendKeys("Automation Address", newAddressField, "Newly Added address");

		reports.log(LogStatus.PASS, "Select Country in new address India");
		new Select(newAddressCountryDropdown).selectByVisibleText("India");
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());

		Thread.sleep(2000);

		reports.log(LogStatus.PASS, "Select State in new address Uttar Pradesh ");
		new Select(newAddressStateDropdown).selectByVisibleText("Uttar Pradesh");
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());

		Thread.sleep(2000);
		reports.log(LogStatus.PASS, "Select City in new address noida");
		new Select(newAddressCityDropdown).selectByVisibleText("Noida");
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());

		sendKeys("201307", newAddressPincodeField, "New Address Pincode ");

	}

	public void validateSuppliersDetails(String categoryName) throws InterruptedException {

		String expectedSupplierName = TestUtil.getExcelKeyValue("CreateRFA", categoryName, "SuppliersName");

		String actualSupplierName = suppliersDetailsTable.findElement(By.xpath("./tbody/tr[1]//span")).getText().trim();

		if (actualSupplierName.contentEquals(expectedSupplierName.trim())) {
			passTestCase("Supplier name has been matched. Actual supplier name : " + actualSupplierName
					+ " and expected supplier name : " + expectedSupplierName);
		} else {
			failTestCase("Supplier name has not been matched. Actual supplier name : " + actualSupplierName
					+ " and expected supplier name : " + expectedSupplierName);
		}

	}

	public void validateSupplierCheckboxAndSelect() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		reports.log(LogStatus.PASS, "Validate error when No supplier is selected");
		TestUtil.click(createBtn, "Create Button");
		loginPage.validateLoginErrorMsg(
				TestUtil.getExcelKeyValue("ErrorMessages", "CreateRFPSupplierError", "Message").trim(),
				loginPage.loginError_ForgotPassward);

		TestUtil.click(loginPage.popupCloseButtonAtModalHeader, "Pop up closer button");
		TestUtil.waitForObjectVisible(createBtn, 20, "Create Button");
		TestUtil.click(suppliersDetailsTable.findElement(By.xpath("./tbody/tr[1]//input[@type='checkbox']")),
				"Supplier checkbox");

	}

	public void selectDropdown(WebElement dropdown, String visibleValue, String dropdownName)
			throws InterruptedException {
		reports.log(LogStatus.PASS, "Select " + dropdownName + " value " + visibleValue);
		new Select(dropdown).selectByVisibleText(visibleValue);
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
	}

	public void selectDate(WebElement datePicker, String dateToBeSelect) throws InterruptedException {

		String date = dateToBeSelect.split("/")[0];
		String month = dateToBeSelect.split("/")[1];
		String year = dateToBeSelect.split("/")[2];

		if(selectMonthAndYear(Integer.parseInt(month) , Integer.parseInt(year))){
			String xpath = ".//table[@class='ui-datepicker-calendar']/tbody//td/a[text()='"+ date + "']";
			log.info("Selection date xpath :: "+ xpath);
			reports.log(LogStatus.PASS, "Select data from calander :" + dateToBeSelect);
			datePicker.findElement(By.xpath(xpath)).click();
			reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		}
		

	}
	
	private boolean selectMonthAndYear(int monthNumber , int year){
		
		String monthName  = Month.of(monthNumber).name();
		int findingCount = 12;
		
		boolean isCurrentMonthFine = false;
		
		while(findingCount>0){
			
			String currentMonth = driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String currentyear =  driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			
			if(currentMonth.equalsIgnoreCase(monthName) && Integer.parseInt(currentyear)== year){
				log.info("Current year and current months are fine ");
				isCurrentMonthFine = true;
				break;
			}
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			findingCount --;
			
			
		}
		
		return isCurrentMonthFine;
		
		
	}
	
	
}
