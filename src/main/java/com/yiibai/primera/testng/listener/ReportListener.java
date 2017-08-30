package com.yiibai.primera.testng.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class ReportListener implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		List<ITestResult> list = new ArrayList<ITestResult>();
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult suiteResult : suiteResults.values()) {
				ITestContext testContext = suiteResult.getTestContext();
				IResultMap passedTests = testContext.getPassedTests();
				IResultMap failedTests = testContext.getFailedTests();
				IResultMap skippedTests = testContext.getSkippedTests();
				IResultMap failedConfig = testContext.getFailedConfigurations();
				list.addAll(this.listTestResult(passedTests));
				list.addAll(this.listTestResult(failedTests));
				list.addAll(this.listTestResult(skippedTests));
				list.addAll(this.listTestResult(failedConfig));
			}
		}
		this.sort(list);
		this.outputResult(list, outputDirectory + "/report.txt");
	}

	private ArrayList<ITestResult> listTestResult(IResultMap resultMap) {
		Set<ITestResult> results = resultMap.getAllResults();
		return new ArrayList<ITestResult>(results);
	}

	private void sort(List<ITestResult> list) {
		Collections.sort(list, new Comparator<ITestResult>() {
			@Override
			public int compare(ITestResult r1, ITestResult r2) {
				if (r1.getStartMillis() > r2.getStartMillis()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
	}

	private void outputResult(List<ITestResult> list, String path) {
		try {
			BufferedWriter output = new BufferedWriter(
					new FileWriter(new File(path)));
			StringBuffer sb = new StringBuffer();
			for (ITestResult result : list) {
				if (sb.length() != 0) {
					sb.append("\r\n");
				}
				sb.append(result.getTestClass().getRealClass().getName())
						.append(" ").append(result.getMethod().getMethodName())
						.append(" ")
						.append(this.formatDate(result.getStartMillis()))
						.append(" ")
						.append(result.getEndMillis() - result.getStartMillis())
						.append("毫秒 ")
						.append(this.getStatus(result.getStatus()));
			}
			output.write(sb.toString());
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String getStatus(int status) {
		String statusString = null;
		switch (status) {
			case 1 :
				statusString = "SUCCESS";
				break;
			case 2 :
				statusString = "FAILURE";
				break;
			case 3 :
				statusString = "SKIP";
				break;
			default :
				break;
		}
		return statusString;
	}

	private String formatDate(long date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

}