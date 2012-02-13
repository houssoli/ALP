//Copyright 2011-2012 Lohika .  This file is part of ALP.
//
//    ALP is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    ALP is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with ALP.  If not, see <http://www.gnu.org/licenses/>.
package com.lohika.alp.reporter;

import java.io.File;
import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;

import com.lohika.alp.log4j.AppendersCloser;



/**
 * The Class Reporter.
 */
public class Reporter implements ISuiteListener, ITestListener,
		IReporter, IResultListener {

	/** The log4j logger. */
	private Logger logger = Logger.getLogger(getClass());

	/** The log4j configurer. */
	private Log4jConfigurer log4jc = new Log4jConfigurer();
	
	/** The appenders closer. */
	private AppendersCloser appendersCloser = new AppendersCloser();

	// Suite listener implementation

	/* (non-Javadoc)
	 * @see org.testng.ISuiteListener#onStart(org.testng.ISuite)
	 */
	@Override
	public void onStart(ISuite suite) {
	}

	/* (non-Javadoc)
	 * @see org.testng.ISuiteListener#onFinish(org.testng.ISuite)
	 */
	@Override
	public void onFinish(ISuite suite) {
	}

	// Test listener implementation

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext context) {
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
	}

	// Method listener implementation

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult itr) {
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult itr) {
		appendersCloser.close(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult itr) {
		appendersCloser.close(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult itr) {
		appendersCloser.close(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult itr) {
		appendersCloser.close(itr);
	}

	// Configuration listener implementation

	/* (non-Javadoc)
	 * @see org.testng.IConfigurationListener#onConfigurationSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		appendersCloser.close(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.IConfigurationListener#onConfigurationFailure(org.testng.ITestResult)
	 */
	@Override
	public void onConfigurationFailure(ITestResult itr) {
		appendersCloser.close(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.IConfigurationListener#onConfigurationSkip(org.testng.ITestResult)
	 */
	@Override
	public void onConfigurationSkip(ITestResult itr) {
		appendersCloser.close(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.IReporter#generateReport(java.util.List, java.util.List, java.lang.String)
	 */
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		String log4jAbsolute = new File(log4jc.getOutputDirectory())
				.getAbsolutePath();
		String testNgAbsolute = new File(outputDirectory).getAbsolutePath();

		if (!testNgAbsolute.equals(log4jAbsolute)) {
			logger.warn("TestNG output directory parameter is ignored. "
					+ "It differs from Log4j file appender parameter");
		}

		// Generate general XML report
		ResultsXML xmlReporter = new ResultsXML();
		xmlReporter.generateReport(xmlSuites, suites, log4jAbsolute);

		// Transform XML to HTML
		ResultsXML2HTML htmlReporter = new ResultsXML2HTML();
		htmlReporter.generateReport(xmlSuites, suites, log4jAbsolute);
	}

}
