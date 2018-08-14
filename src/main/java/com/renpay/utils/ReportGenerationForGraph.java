package com.renpay.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReportGenerationForGraph {

	String chartPassed = "[";
	String chartFailed = "[";
	int totalPassedCount  = 0;
	int totalFailedCount = 0;
	SimpleDateFormat DateFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public void createRunWiseReport(ArrayList<ReportsData> executedModuleList) throws IOException {

		for (ReportsData testResults : executedModuleList) {
			
			// calculate the total pass and total fail count of test cases 
			totalFailedCount = totalFailedCount + testResults.getFailCount();
			totalPassedCount = totalPassedCount + testResults.getPassCount();
			
			chartPassed = chartPassed
					.concat("{y:" + testResults.getPassCount() + ",label:'" + testResults.getModuleName() + "'"
							+ ",toolTipContent:'" + testResults.getReportTooltipForPassedTC() + "'},");

			chartFailed = chartFailed
					.concat("{y:" + testResults.getFailCount() + ",label:'" + testResults.getModuleName() + "'"
							+ ",toolTipContent:'" + testResults.getReportTooltipForFailedTC() + "'},");

		}

		chartPassed = chartPassed.substring(0, chartPassed.length() - 1);
		chartFailed = chartFailed.substring(0, chartFailed.length() - 1);

		chartPassed = chartPassed.concat("]");
		chartFailed = chartFailed.concat("]");

		System.out.println("chartPassed :" + chartPassed);
		System.out.println("chartFailed :" + chartFailed);

		readAndWriteFromfile();
	}

	public void readAndWriteFromfile() throws IOException {

		BufferedReader inputStream = new BufferedReader(new FileReader(
				System.getProperty("user.dir") + File.separator + "ExecutionReports" + File.separator + "MyHTML.html"));
		File UIFile = new File(TestInitialization.currentExecutionReportPath+File.separator
				+ "RunWiseReport.html");
		// if File doesnt exists, then create it
		if (!UIFile.exists()) {
			UIFile.createNewFile();
		}
		FileWriter filewriter = new FileWriter(UIFile.getAbsoluteFile());
		BufferedWriter outputStream = new BufferedWriter(filewriter);
		String count;
		while ((count = inputStream.readLine()) != null) {
			
			count = count.replaceAll("@TotalTestCase", (totalFailedCount+totalPassedCount)+"");
			count = count.replaceAll("@PassTestCase", totalPassedCount+"");
			count = count.replaceAll("@FailTestCase", totalFailedCount+"");
			count = count.replaceAll("@chartpassed", chartPassed);
			count = count.replaceAll("@chartFailed", chartFailed);
			count  = count.replaceAll("@Date",  "'"+DateFormatter.format(TestInitialization.executionStartTime) + "'");
			count = count.concat("\n");
			outputStream.write(count);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}
}
