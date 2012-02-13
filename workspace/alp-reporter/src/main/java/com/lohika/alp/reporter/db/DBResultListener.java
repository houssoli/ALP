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
package com.lohika.alp.reporter.db;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import com.lohika.alp.reporter.db.model.EMethodStatus;


/**
 * Implementation of TestNG listener to store test results into database.
 *
 * @see DBResultEvent
 */
public class DBResultListener implements IResultListener {

	/** The db results. */
	private DBResultHelper dbResults = new DBResultHelper();

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	public void onStart(ITestContext tc) {
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	public void onFinish(ITestContext arg0) {
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	public void onTestStart(ITestResult arg0) {
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	public void onTestSuccess(ITestResult itr) {
		dbResults.saveMethodResult(itr, EMethodStatus.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult itr) {
		dbResults.saveMethodResult(itr, EMethodStatus.FAILURE);
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	public void onTestSkipped(ITestResult itr) {
		dbResults.saveMethodResult(itr, EMethodStatus.SKIP);
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO handle test failure with success percentage
	}

	/* (non-Javadoc)
	 * @see org.testng.IConfigurationListener#onConfigurationSuccess(org.testng.ITestResult)
	 */
	public void onConfigurationSuccess(ITestResult itr) {
		dbResults.saveMethodResult(itr, EMethodStatus.SUCCESS);
	}

	/* (non-Javadoc)
	 * @see org.testng.IConfigurationListener#onConfigurationFailure(org.testng.ITestResult)
	 */
	public void onConfigurationFailure(ITestResult itr) {
		dbResults.saveMethodResult(itr, EMethodStatus.FAILURE);
	}

	/* (non-Javadoc)
	 * @see org.testng.IConfigurationListener#onConfigurationSkip(org.testng.ITestResult)
	 */
	public void onConfigurationSkip(ITestResult itr) {
		dbResults.saveMethodResult(itr, EMethodStatus.SKIP);
	}

}
