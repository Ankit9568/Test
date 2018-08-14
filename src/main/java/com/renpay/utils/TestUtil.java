package com.renpay.utils;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class TestUtil extends TestInitialization {

	public static boolean isDisplayed(WebElement we, int waitingTimeInSec) {

		boolean isVisible;
		driver.manage().timeouts().implicitlyWait(waitingTimeInSec, TimeUnit.SECONDS);
		try {
			if (we.isDisplayed()) {
				isVisible = true;
			} else {
				isVisible = false;
			}
		} catch (NoSuchElementException e) {
			isVisible = false;
		}
		driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		return isVisible;
	}

	public static void isElementExist(WebElement we, String elementName) {
		// Throws NoSuchElementException in case of element is not visible on
		// webpage.
		we.isDisplayed();
		reports.log(LogStatus.PASS, elementName + " with text " + we.getText() + " is visible ");
		log.info("Element is displayed " + elementName);
	}

	public static boolean validateErrorMessage(WebElement we, String msg) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TestInitialization.log.info("Actual error message " + we.getText() + " Expected error message " + msg);

		if (getTextNode(we).trim().contentEquals(msg)) {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
			return true;

		}
		failTestCase("Unable to verify Error message. Actual Error message is : " + we.getText()
				+ " Expected error message " + msg);
		return false;
	}

	public static boolean validateSuccessMessage(WebElement we, String msg) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		TestInitialization.log.info("Actual message " + we.getText() + " Expected message " + msg);

		if (getTextNode(we).trim().contentEquals(msg)) {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
			return true;

		}
		failTestCase("Unable to verify message. Actual message is : " + we.getText() + " Expected message " + msg);
		return false;
	}

	public static boolean validateBgColor(WebElement we, String color) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualBgColor = we.getCssValue("background-color");
		String actualHexBgColor = Color.fromString(actualBgColor).asHex();
		String expectedBgColor = color;
		TestInitialization.log.info("Actual bg color " + actualHexBgColor + " Expected bg color " + expectedBgColor);

		if (actualHexBgColor.trim().contentEquals(expectedBgColor)) {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
			return true;

		}
		failTestCase("Unable to verify color. Actual bg color is : " + actualHexBgColor + " Expected bg color "
				+ expectedBgColor);
		return false;
	}

	public static boolean validateAttribute(WebElement we, String attrubuteName, String expectedAttrValue) {

		String actualAttributeVal = we.getAttribute(attrubuteName);
		TestInitialization.log.info("Validate an attribute name " + attrubuteName + " expected value "
				+ expectedAttrValue + " Actual attribute value " + actualAttributeVal);
		if (actualAttributeVal.contentEquals(expectedAttrValue)) {
			return true;
		}
		return false;
	}

	public static void sendKeyFromKeyboard(Keys key, WebElement we) {

		TestInitialization.log.info("Sending Key from keyboard " + key);
		we.sendKeys(key);
	}

	public static void clear(WebElement we) {
		TestInitialization.log.info("Send CTRL+A and DELETE on element");
		we.sendKeys(Keys.CONTROL, "a");
		we.sendKeys(Keys.DELETE);
	}

	public static String getExcelKeyValue(String sheetname, String objectname, String keyname) {

		// System.out.println("Inside getExcelKeyValue Function");

		String sheetName = sheetname;
		String objectName = objectname;
		String keyName = keyname;
		String errorMessage = "Something wrong with your Key-Value Pair";

		int rows = excel.getRowCount(sheetName);

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			if (excel.getCellData(sheetName, "objectID", rowNum).equalsIgnoreCase(objectName)) {

				// System.out.println("Going out of getExcelKeyValue Function
				// with valid value");
				return excel.getCellData(sheetName, keyName, rowNum);

			}

		}

		System.out.println("NOT VALIDDDDDDDDDDDDD");
		System.out.println("Going out of getExcelKeyValue Function without any valid value");
		return errorMessage;

	}

	public static String captureCurrentScreenshot() throws InterruptedException {

		// Wait for page load
		Thread.sleep(2000);
		cald = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String method_id = currentMethodName.substring(0, Math.min(currentMethodName.length(), 10));

		String captureFileName = method_id + formatter.format(cald.getTime()).toString() + ".jpg";

		captureFilePath = currentExecutionReportPath + "/screenshots" + "/" + captureFileName;
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(captureFilePath));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot");
			log.info("Exception while taking screenshot");

			e.printStackTrace();
		}
		captureFilePath = "./screenshots" + File.separator + captureFileName;
		return captureFilePath;

	}

	public static String captureCurrentScreenshotFrmApp() throws InterruptedException {

		// Wait for page load
		Thread.sleep(2000);
		cald = Calendar.getInstance();

		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String method_id = currentMethodName.substring(0, Math.min(currentMethodName.length(), 10));

		String captureFileName = method_id + formatter.format(cald.getTime()).toString() + ".jpg";
		captureFilePath = currentExecutionReportPath + "/screenshots" + "/" + captureFileName;
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(captureFilePath));
		} catch (IOException e) {
			System.out.println("Exception while taking screenshot");
			log.info("Exception while taking screenshot");

			e.printStackTrace();
		}
		captureFilePath = "./screenshots" + File.separator + captureFileName;
		return captureFilePath;

	}

	public static String getUserDataFromLocalStorage() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String userData = (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", "user"));
		log.info("Fetch user data from localstorage variable " + userData);
		return userData;
	}

	public static ArrayList<String> openNewTab() throws InterruptedException {

		reports.log(LogStatus.PASS, "Open new tab in browser");
		ArrayList<String> windowHandleBeforeOpenNewTab = new ArrayList<String>(driver.getWindowHandles());
		((JavascriptExecutor) driver).executeScript("window.open();");
		ArrayList<String> windowHandleAfterOpenNewTab = new ArrayList<String>(driver.getWindowHandles());

		if (windowHandleBeforeOpenNewTab.size() == windowHandleAfterOpenNewTab.size()) {
			failTestCase("Unable to open new Tab");

		}

		driver.switchTo().window(windowHandleAfterOpenNewTab.get(windowHandleAfterOpenNewTab.size() - 1));
		reports.attachScreenshot(captureCurrentScreenshot());
		return windowHandleAfterOpenNewTab;
	}

	public static void refreshApplication() throws InterruptedException {

		reports.log(LogStatus.PASS, "Refresh the Application");
		driver.navigate().refresh();
		reports.attachScreenshot(captureCurrentScreenshot());
	}

	public static void navigateToApplicationURL() throws InterruptedException {

		String url = TestInitialization.getBuildParam("ApplicationURL");
		reports.log(LogStatus.PASS, "Naviaget the Application URL");
		driver.navigate().to(url);
		reports.attachScreenshot(captureCurrentScreenshot());
	}

	
	public static void send_CTRL_F5_onBrowser() throws AWTException {

		driver.navigate().refresh();
	}

	public static void movePointerToObject(WebElement we, String objectName) throws InterruptedException {

		reports.log(LogStatus.PASS, "Mouse move to " + objectName);
		Actions builder = new Actions(driver);
		builder.moveToElement(we).build().perform();
		Thread.sleep(5000);

	}

	public static void verifyMaxLengthProp(WebElement we, String expectedMaxLength, String objectName)
			throws InterruptedException {

		reports.log(LogStatus.PASS, "Validating the Maxlength Property of " + objectName);
		String actualMaxLength = we.getAttribute("maxlength");
		if (!actualMaxLength.contentEquals(expectedMaxLength)) {
			failTestCase("Verification Failed of  Maxlength  " + objectName + " Actual Maxlength : " + actualMaxLength
					+ " expected Maxlength :" + expectedMaxLength);
		} else {
			reports.log(LogStatus.PASS, "Verification passed of  Maxlength  " + objectName + " Actual Maxlength : "
					+ actualMaxLength + " expected Maxlength :" + expectedMaxLength);
		}
	}

	public static void verifyMaxValProp(WebElement we, String expectedMaxVal, String objectName)
			throws InterruptedException {

		reports.log(LogStatus.PASS, "Validating the Maximum value Property of " + objectName);
		String actualMaxLength = we.getAttribute("max");
		if (!actualMaxLength.contentEquals(expectedMaxVal)) {
			failTestCase("Verification Failed of Maximum value " + objectName + " Actual Max Value : " + actualMaxLength
					+ " expected Max Value : " + expectedMaxVal);
		} else {
			reports.log(LogStatus.PASS, "Verification Passed of Maximum value " + objectName + " Actual Max Value : "
					+ actualMaxLength + " expected Max Value : " + expectedMaxVal);
		}
	}

	public static void clickAndWait(WebElement we, long timeInMilisecond) throws InterruptedException {

		new Actions(driver).moveToElement(we).build().perform();
		we.click();
		Thread.sleep(timeInMilisecond);
	}

	/**
	 * @param navigationOrder
	 * @throws InterruptedException
	 *             Validate item navigation in breadcrumb
	 */

	

	public static void waitForObjectInvisble(By by, int waitingTimeoutInSec, String objectName)
			throws InterruptedException {

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(driver, waitingTimeoutInSec * 1000);
			Boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

			if (isInvisible) {
				log.info(objectName + " is not visible on webpage");
			}

			else {
				failTestCase(objectName + " is visible on webpage after waiting " + waitingTimeoutInSec + " second");
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		}
	}
	
	public static void waitForObjectVisible(WebElement we , int waitingTimeoutInSec, String objectName)
			throws InterruptedException {

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			WebDriverWait wait = new WebDriverWait(driver, waitingTimeoutInSec * 1000);
			wait.until(ExpectedConditions.visibilityOf(we));

		} finally {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		}
	}
	

	/**
	 * @param we
	 * @param objectName
	 * @throws InterruptedException
	 *             verify given field is enabled
	 */

	public static void verifyFieldisEnabled(WebElement we, String objectName) throws InterruptedException {

		if (we.isEnabled()) {
			reports.log(LogStatus.PASS, objectName + " is enabled");
		} else {
			failTestCase(objectName + " is not enabled");
		}
	}

	/**
	 * 
	 * return original size of windows before changing the resolution
	 */
	public static Dimension resizebrowser() {

		Dimension d = new Dimension(800, 480);
		Dimension originalSize = driver.manage().window().getSize();
		driver.manage().window().setSize(d);
		return originalSize;

	}

	public static void waitForPageToLoad(int maxWaitingTimeInSec) {

		long currentTime = System.currentTimeMillis();
		long endTime = currentTime + (maxWaitingTimeInSec * 1000);

		String pageLoadStatus;
		do {
			currentTime = System.currentTimeMillis();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			pageLoadStatus = (String) js.executeScript("return document.readyState");
			log.info("Page state " + pageLoadStatus);
			log.info(".");
		} while ((!pageLoadStatus.equals("complete")) && currentTime < endTime);

		log.info("Page Loaded.");

	}

	public static void scrollToView(WebElement we) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		Thread.sleep(500);
	}

	public static void validateObjectReadonly(WebElement we, String objectName) throws InterruptedException {

		String readOnlyAttribute = we.getAttribute("readonly");

		if (Boolean.parseBoolean(readOnlyAttribute)) {
			reports.log(LogStatus.PASS, objectName + " is read only");
		}

		else {
			failTestCase(objectName + " is not read only.");
		}
	}

	public static void validateFieldIsEditable(WebElement we, String objectName) throws InterruptedException {

		String readOnlyAttribute = we.getAttribute("readonly");

		if (readOnlyAttribute == null || readOnlyAttribute.trim().contentEquals("null")) {
			reports.log(LogStatus.PASS, objectName + " is editable");
		}

		else {
			failTestCase(objectName + " is not editable.");
		}

	}

	public static void dragAndDrop(WebElement sourceElement, WebElement destinationElement)
			throws InterruptedException {

		final String java_script = "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe"
				+ "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun"
				+ "ction(format,data){this.items[format]=data;this.types.append(for"
				+ "mat);},getData:function(format){return this.items[format];},clea"
				+ "rData:function(format){}};var emit=function(event,target){var ev"
				+ "t=document.createEvent('Event');evt.initEvent(event,true,false);"
				+ "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('"
				+ "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit("
				+ "'drop',tgt);emit('dragend',src);";

		((JavascriptExecutor) driver).executeScript(java_script, sourceElement, destinationElement);
		Thread.sleep(2000);

	}

	public static void dragAndDrop(WebElement sourceElement, WebElement destinationElement, Integer sleepTime)
			throws InterruptedException {

		final String java_script = "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe"
				+ "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun"
				+ "ction(format,data){this.items[format]=data;this.types.append(for"
				+ "mat);},getData:function(format){return this.items[format];},clea"
				+ "rData:function(format){}};var emit=function(event,target){var ev"
				+ "t=document.createEvent('Event');evt.initEvent(event,true,false);"
				+ "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('"
				+ "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit("
				+ "'drop',tgt);emit('dragend',src);";

		((JavascriptExecutor) driver).executeScript(java_script, sourceElement, destinationElement);
		Thread.sleep(sleepTime);

	}

	public static void verifyScrollBarExist(WebElement we, String objectName) throws InterruptedException {

		String JS_ELEMENT_IS_SCROLLABLE = "return arguments[0].scrollHeight > arguments[0].offsetHeight;";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Boolean isScrollable = (Boolean) jse.executeScript(JS_ELEMENT_IS_SCROLLABLE, we);

		if (isScrollable) {
			reports.log(LogStatus.PASS, "Scrollbar is visible on " + objectName);
			reports.attachScreenshot(TestUtil.captureCurrentScreenshot());

		} else {
			failTestCase("Scrollbar is not visible on " + objectName);
		}

	}

	public static String getTextNode(WebElement e) {
		String text = e.getText().trim();
		List<WebElement> children = e.findElements(By.xpath("./*"));
		for (WebElement child : children) {
			text = text.replaceFirst(child.getText(), "").trim();
		}
		return text;
	}

	public static boolean isCurserAllowed(WebElement we) {

		try {

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(we));
			String cursorProp = we.getCssValue("cursor");
			log.info("Cursor prop :" + cursorProp);
			if (cursorProp != null && cursorProp.toLowerCase().contentEquals("pointer")) {
				return true;
			} else {
				return false;
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		}
	}
	
	public static void click(WebElement we , String objectName) throws InterruptedException{
		
		TestUtil.waitForObjectVisible(we, 180, objectName);
		reports.log(LogStatus.PASS, "Click on " + objectName);
		we.click();
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
	}

}
