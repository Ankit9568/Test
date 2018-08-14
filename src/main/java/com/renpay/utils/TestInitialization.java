package com.renpay.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.renpay.config.ObjectRepository;
import com.renpay.dbConnection.DataBaseConnection;
import com.renpay.pages.HomePage;
import com.renpay.pages.LoginPage;

public class TestInitialization extends ObjectRepository {

	public static WebDriver driver;
	public static Logger log;
	public static final ExtentReports reports = ExtentReports.get(TestInitialization.class);
	public static Calendar cald = Calendar.getInstance();
	public static String captureFilePath;
	public static String currentMethodName;
	public static Xls_Reader excel = new Xls_Reader(ObjectRepository.testDataFilePath);
	public static WebDriverWait wait = null;
	static SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
	public static String currentExecutionFoldername;
	public static String currentExecutionReportPath;
	public static ArrayList<ReportsData> testResult = new ArrayList<ReportsData>();
	public static Date executionStartTime = cald.getTime();
	public static int STBRebootAfterTestCase = 30;
	public static int executedMethodCount = 0;
	public static int implicitTimeOutInSec = 30;

	protected static String configFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "java" + File.separator + "com" + File.separator + "renpay" + File.separator
			+ "config" + File.separator + "config.properties";

	@BeforeSuite
	public void Setup() throws InterruptedException, IOException {

		System.setOut(createLoggingProxy(System.out));
		System.setErr(createLoggingProxy(System.err));

		currentExecutionFoldername = "BuildVer_" + getBuildParam("ReleaseVersion") + "_ExecutionReport_"
				+ formatter.format(executionStartTime).toString();

		currentExecutionReportPath = System.getProperty("user.dir") + File.separator + "ExecutionReports"
				+ File.separator + currentExecutionFoldername;

		String extentReportFileName = "index.html";
		new File(currentExecutionReportPath).mkdirs();

		String extentReportPath = new File(currentExecutionReportPath + "/" + extentReportFileName).getAbsolutePath();
		String seleniumLogs = new File(
				currentExecutionReportPath + File.separator + "Logs" + File.separator + "Selenium.log")
						.getAbsolutePath();
		String applicationLogs = new File(
				currentExecutionReportPath + File.separator + "Logs" + File.separator + "Application.log")
						.getAbsolutePath();

		System.setProperty("seleniumLogs", seleniumLogs);
		System.setProperty("ApplicationLogs", applicationLogs);

		Properties props = new Properties();
		props.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
				+ File.separator + "java" + File.separator + "log4j.properties"));
		PropertyConfigurator.configure(props);

		reports.init(extentReportPath, true);
		reports.config().reportHeadline("TM GUI Automation Testing");
		reports.config().reportTitle("Regression Test Execution");
		reports.config().useExtentFooter(false);

		log = Logger.getLogger("ProximusTMGUILogger");

		log.info("Logger Info:: Inside Setup Method");

		launchWebdriver();
		launchApplication();

	}

	@AfterClass
	public void afterClass(ITestContext result) throws IOException {
		/** Add test result for each module */
		ReportsData tcResult = new ReportsData(result);
		testResult.add(tcResult);
	}

	@BeforeClass
	public void beforeClass() throws InterruptedException {

		// Log-in application if user execute the test case other than Login
		// Module
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			String currectClassNameToBeExecuted = this.getClass().getSimpleName();

			log.info("Inside before class method class Name is : " + currectClassNameToBeExecuted);
			if (!currectClassNameToBeExecuted.contentEquals("LogInPageTestCase")) {

				// if application is already log in then no need to login
				log.info("Validate the application is login or not");
				try {
					if (new LoginPage(driver).signinLink.isDisplayed())
						driver.navigate().refresh();
						applicationLogin();
				} catch (NoSuchElementException e) {
					// Application is not in login page
					setApplicationHomePage();
				}
			}
		} finally {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		}
	}

	@BeforeMethod
	public void beforeMethodCalled(Method method) throws InterruptedException {

		reports.startTest("Starting the Test: " + method.getName());
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			log.info("Testcase name is :::::: " + method.getName());
			currentMethodName = method.getName();
			if (!(method.getDeclaringClass().getSimpleName().contentEquals("LogInPageTestCase"))) {
				currentMethodName = method.getName();
				reports.log(LogStatus.PASS, "Start Step : Start with the focus on Home page");
				try {
					setApplicationHomePage();
					reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			else {
				// Before start each test case of login module we need blank
				// user
				// name and blank password
				try {
					setApplicationLoginPage();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} finally {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		}
	}

	public static void applicationLogin() throws InterruptedException {

		// force fully log in application
		String url = getBuildParam("ApplicationURL");
		log.info("URL Param " + url);
		LoginPage loginPage = new LoginPage(driver);

		
		if (loginPage.signinLink.isDisplayed()) {
			HomePage homePage = new HomePage(driver);
			TestUtil.click(loginPage.signinLink, "Sign in link");
			TestUtil.waitForObjectVisible(loginPage.emailName, 60, "Sign in email");
			
			String userName = TestUtil.getExcelKeyValue("LogInPage", "UserName", "ValidValues");
			String password = TestUtil.getExcelKeyValue("LogInPage", "Password", "ValidValues");
			
			TestUtil.sendKeys(userName, loginPage.emailName, "Email/Mobile editbox");
			TestUtil.sendKeys(password, loginPage.password, "Password editbox");
			
			TestUtil.click(loginPage.loginBtn, "login button");
			TestUtil.waitForObjectInvisble(By.id(ObjectRepository.pleaseWaitModal_ID),180, "Please wait modal alertbox");
			
			TestUtil.waitForObjectVisible(homePage.imageProfile, 60, "User Profile");
		
		} else {
			log.info("Application is not on login page :" + driver.getCurrentUrl());
		}
	}

	@AfterMethod()
	public void afterMethodCalled(Method method) throws InterruptedException, IOException {

		try {

			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			if (!(method.getDeclaringClass().getSimpleName().contentEquals("LogInPageTestCase"))) {
				// Executed below code in whose test case which is not a part of
				// Login module
				reports.log(LogStatus.PASS, "TestCase Completed : Leave the test case with focus on Home page");
				try {
					setApplicationHomePage();
					reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// close DB connection if connected
					DataBaseConnection.closeDbConnectionIfExist();
				}
			}

			executedMethodCount = executedMethodCount + 1;
		} finally {
			driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		}
	}

	public static void applicationLogout() throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		TestUtil.click(homePage.logoutOpenerLink, "Logout button Opener Link");
		
		reports.log(LogStatus.PASS, "Click on logout button and validate application logout successfully");
		TestUtil.click(homePage.logoutLink, "Logout button");
		TestUtil.waitForObjectVisible(loginPage.signinLink, 180, "Login link");
		reports.log(LogStatus.PASS, "Application logout successfully");
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
	}

	@AfterSuite
	public void suiteEndReached() throws IOException, InterruptedException {

		log.info("Logger Info:: Inside suiteEndReached Method");
		System.out.println("Trying to quit webdriver");

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

		try {
			if (new LoginPage(driver).signinLink.isDisplayed()) {
				log.info("Application is already logout no need to logout.");

			}
			else{
				reports.log(LogStatus.PASS, "Trying to Logout appliaction.");
				applicationLogout();
			}
		} catch (NoSuchElementException e) {
			reports.log(LogStatus.PASS, "Trying to Logout appliaction.");
			applicationLogout();
		}
		new ReportGenerationForGraph().createRunWiseReport(testResult);
		new ReportGeneration().dashBoardGenerator();
		driver.quit();
		reports.endTest();

	}

	public static void sendKeys(String text, WebElement we, String objectName) throws InterruptedException {

		we.sendKeys(Keys.CONTROL, "a");
		we.sendKeys(Keys.DELETE);

		// we.clear();
		reports.log(LogStatus.PASS, "Sending the " + "\"" + text + "\"" + " on " + objectName);
		we.sendKeys(text);

		log.debug("Get value attribute from Text box " + we.getAttribute("value") + " Get Text Attribute "
				+ we.getText());

		if (!we.getAttribute("value").trim().contentEquals(text)) {
			log.info("Actual Text  " + we.getText().trim() + " expected Text " + text);
			failTestCase("Unable to validate sended Text on " + objectName);

		}
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
	}

	public void setApplicationHomePage() throws InterruptedException {

		HomePage homePage =new HomePage(driver);
		
		try {
			if (driver.getCurrentUrl().contains("Dashboard.aspx")) {
				log.info("Application is already on home page");
			} else {
				// Currently application is not on home page
				TestUtil.click(homePage.renpayLogoImg, "renpay Logo");
			}
		}

		catch (NoSuchElementException e) {
			log.info("Trying to move on Home page");
			applicationLogin();
		}
	}

	public static void setApplicationLoginPage() throws InterruptedException {

		String url = getBuildParam("ApplicationURL");
		try {
			if (new LoginPage(driver).signinLink.isDisplayed()) {
				driver.navigate().to(url);
				reports.log(LogStatus.PASS, "Login page successfully displayed");
				reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
			}
		} catch (NoSuchElementException e) {
			log.info("Unable to move login page. trying to logout application");
			// logout application if login
			applicationLogout();
		}
	}

	private void launchWebdriver() throws IOException, InterruptedException {

		String url = null;
		url = getBuildParam("ApplicationURL");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + File.separator + "chromedriver.exe");

		// DesiredCapabilities cap = new DesiredCapabilities();
		// cap.setCapability("applicationCacheEnabled", "false");

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-cache");

		boolean runInHeadLessMode = Boolean.parseBoolean(getBuildParam("RunInHeadlessMode"));

		if (runInHeadLessMode) {
			log.info("Execute browser in headless mode");
			options.addArguments("--window-size=1920,1080", "--headless");
		}
		driver = new ChromeDriver(options);

		log.info("Window dimestion :" + driver.manage().window().getSize());
		driver.manage().window().maximize();
		log.info("Suite is started on Application URL is ## " + url);
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(implicitTimeOutInSec, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

	}

	public static void failTestCase(String reason) throws InterruptedException {
		Assert.fail(reason);
	}

	public void passTestCase(String reason) throws InterruptedException {
		reports.log(LogStatus.PASS, reason);
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
	}

	/**
	 * 
	 * Overload function
	 * 
	 * @throws InterruptedException
	 */
	public void passTestCase() throws InterruptedException {
		reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
	}

	public void isDisplayed(WebElement we, String webElementName) throws InterruptedException {

		try {
			if (we.isDisplayed()) {

				reports.log(LogStatus.PASS, webElementName + " is visible on webpage");
				reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
			} else {

				failTestCase(webElementName + " exist on webpage but it is not visible on webpage");

			}
		} catch (NoSuchElementException e) {
			failTestCase(webElementName + " is not found on webpage");

		}

	}

	public void isNotDisplayed(WebElement we, String webElementName) throws InterruptedException {

		try {
			if (!we.isDisplayed()) {

				reports.log(LogStatus.PASS, webElementName + " is not visible on webpage");
				reports.attachScreenshot(TestUtil.captureCurrentScreenshot());
			} else {
				failTestCase(webElementName + "is visible on webpage");

			}
		} catch (NoSuchElementException e) {
			failTestCase(webElementName + " is not found on webpage");

		}

	}

	public static Properties getUpdatedProptiesFile() {
		Properties PR = new Properties();
		FileInputStream FI;
		try {
			FI = new FileInputStream(configFilePath);
			PR.load(FI);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return PR;
	}

	public static String getBuildParam(String paramName) {
		String param = null;
		param = System.getProperty(paramName);
		if (param == null || param.contentEquals("")) {
			param = TestInitialization.getUpdatedProptiesFile().getProperty(paramName);
		}
		return param;
	}

	/**
	 * @param realPrintStream
	 * @return override the console logger with proximus logger
	 */
	public static PrintStream createLoggingProxy(final PrintStream realPrintStream) {
		return new PrintStream(realPrintStream) {
			public void print(final String string) {
				log.info(string);
			}
		};
	}

	private void launchApplication() throws InterruptedException {

		System.out.println("Waiting for the page to load");
		wait = new WebDriverWait(driver, 120L);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ObjectRepository.LoginScreen.signinLink_xpath)));
		System.out.println("Application loaded");
	}
}
