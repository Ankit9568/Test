package com.renpay.utils;

import org.testng.ITestContext;

public class ReportsData {

	private String moduleName = null;
	private int passCount = 0;
	private int failCount = 0;
	private int totalTestCaseCount = 0;

	public ReportsData(ITestContext result) {
		this.moduleName = result.getCurrentXmlTest().getName();
		this.passCount = result.getPassedTests().size();
		this.failCount = result.getFailedTests().size();
		// count skip test case as fail
		this.failCount = this.failCount + result.getSkippedTests().size();
		this.totalTestCaseCount = result.getAllTestMethods().length;
	}

	/**
	 * 
	 * @return test case module name
	 */
	public String getModuleName() {
		return this.moduleName;
	}

	/**
	 * @return pass count for specific module
	 */
	public int getPassCount() {
		return this.passCount;
	}

	/**
	 * @return fail count for specific module
	 */
	public int getFailCount() {
		return this.failCount;
	}

	/**
	 * 
	 * @return total test case count for specific module
	 */
	public int getTotalTestCaseCount() {
		return this.totalTestCaseCount;
	}

	/**
	 * return report tooltip for passed Test cases
	 */
	public String getReportTooltipForPassedTC() {
		return this.moduleName + ":" + this.passCount + "/" + this.totalTestCaseCount;
	}

	public String getReportTooltipForFailedTC() {

		return this.moduleName + ":" + this.failCount + "/" + this.totalTestCaseCount;
	}

}
