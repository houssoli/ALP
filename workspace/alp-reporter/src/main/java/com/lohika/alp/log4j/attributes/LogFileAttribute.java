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
package com.lohika.alp.log4j.attributes;

import java.io.File;

import org.testng.ITestResult;


/**
 * The Class LogFileAttribute.
 */
public class LogFileAttribute {

	/** The Constant NAME. */
	public static final String NAME = "log4j.file";

	/**
	 * Gets the log file.
	 *
	 * @param tr the tr
	 * @return the log file
	 */
	public static File getLogFile(ITestResult tr) {
		return (File) tr.getAttribute(NAME);
	}

	/**
	 * Sets the log file.
	 *
	 * @param tr the tr
	 * @param file the file
	 */
	public static void setLogFile(ITestResult tr, File file) {
		tr.setAttribute(NAME, file);
	}

}
