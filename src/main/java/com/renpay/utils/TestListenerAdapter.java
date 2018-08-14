package com.renpay.utils;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.renpay.exceptionHandling.ExceptionTrap;

public class TestListenerAdapter extends TestInitialization implements ITestListener, IInvokedMethodListener {

	public void onTestFailure(ITestResult result) {
		ExceptionTrap exceptionTrap = new ExceptionTrap(result);
		try {
			exceptionTrap.setExtendReportStatus();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		ExceptionTrap exceptionTrap = new ExceptionTrap(result);
		try {
			exceptionTrap.setExtendReportStatus();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
		
		
	}

	public void onFinish(ITestContext context) {
		
	}

	
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
	}

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {

	}
}
