package com.renpay.test;

import java.sql.Timestamp;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.renpay.pages.CreateRFP_Page;
import com.renpay.pages.ProfilePage;
import com.renpay.utils.TestInitialization;
import com.renpay.utils.TestUtil;

public class RFPTestCases extends TestInitialization {

	@Test
	public void tc_CreateRFP_001() throws InterruptedException {

		CreateRFP_Page createRFP_Page = new CreateRFP_Page(driver);
		createRFP_Page.navigateToCreateRFPPage();
		createRFP_Page.validateAllField();
		createRFP_Page.clickCreaterBtnAndValidateErrorMsg(createRFP_Page.productName, "product Name",
				TestUtil.getExcelKeyValue("ErrorMessages", "CreateRFPError", "Message"));
		
		sendKeys("Auto_"+ new Timestamp(System.currentTimeMillis()).getTime(), createRFP_Page.productName, "Product Name");
		createRFP_Page.selectCategoryAndValidateAndValidateSubcategoryItems(
				TestUtil.getExcelKeyValue("CreateRFA", "Mineral Water", "Category"));
		createRFP_Page.selectCategoryAndValidateAndValidateSubcategoryItems(
				TestUtil.getExcelKeyValue("CreateRFA", "Travel & Hotels", "Category"));
		
		createRFP_Page.selectDropdown(createRFP_Page.subCategoryDropdown, "Travel & Lodging", "Sub category");
		
		sendKeys("Automated RFA Description", createRFP_Page.descriptionField, "Description field");
		
		createRFP_Page.validateFieldIsNumaric(createRFP_Page.quantityField, "Quantity Field");
		
		createRFP_Page.selectDropdown(createRFP_Page.unitDropdown, "Litre", "unit");
		
		createRFP_Page.validateFieldIsNumaric(createRFP_Page.expectedDeliveryDaysField, "Expected delivery days");
		TestUtil.slectCheckboxIfNotSelected(createRFP_Page.registeredCheckbox , "Registered Address checkbox");
		
		
		createRFP_Page.validateShippingAddress();
		
		TestUtil.slectCheckboxIfNotSelected(createRFP_Page.addNewAddreddCheckbox , "Add new Address checkbox");
		createRFP_Page.validateNewelyAddedAddressAndFill();
		
		createRFP_Page.selectDropdown(createRFP_Page.creditPeriodDropdown, "15 Days", "credit Period");
		
		createRFP_Page.validateSuppliersDetails("Travel & Hotels");
		createRFP_Page.validateSupplierCheckboxAndSelect();	
		
		TestUtil.click(	createRFP_Page.rfpDeadlineStartDate, "RFA Deadline Start date");
		createRFP_Page.selectDate(createRFP_Page.calander, "25/11/2018");
		TestUtil.click(	createRFP_Page.rfpDeadlineEndDate, "RFA Deadline End date");
		createRFP_Page.selectDate(createRFP_Page.calander, "28/11/2018");
		
		
		TestUtil.click(createRFP_Page.createBtn, "Create button");
		
		reports.log(LogStatus.PASS, "Validation for RFA creation");
		TestUtil.waitForObjectVisible(createRFP_Page.RFPDetailsMsg, 180, "Your RFP has been set for heading");;
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
		TestUtil.click(new ProfilePage(driver).closerBtn, "Popup close button");
		
		
	}	
		

	
	
}
