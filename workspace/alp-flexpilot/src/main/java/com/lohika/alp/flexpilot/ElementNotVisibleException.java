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
package com.lohika.alp.flexpilot;

import org.openqa.selenium.TakesScreenshot;
import org.testng.log4testng.Logger;

import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactoryJAXB;
import com.lohika.alp.selenium.log.DescribedElement;
import com.lohika.alp.selenium.log.LogDescription;
import com.lohika.alp.selenium.log.LogDescriptionBean;

/**
 * Extends {@link java.lang.RuntimeException}
 * message with {@link LogDescription} info
 */
public class ElementNotVisibleException extends RuntimeException {
	
	/** The Constant serialVersionUID. */
	private static final long			serialVersionUID	= 9202333194773543853L;

	/** LogDescriptionBean. */
	private final LogDescriptionBean	description;
	
	/** The log4j logger . */
	private Logger						lg					= Logger.getLogger(ElementNotVisibleException.class);
	
	/** The log factory. */
	private FlexPilotFactory			logFactory = new FlexPilotFactoryJAXB();

	/**
	 * Instantiates a new element not visible exception.
	 *
	 * @param element - DescribedElement
	 * @param takesScreenshot - instance of Object that supports TakesScreenshot 
	 */
	public ElementNotVisibleException(DescribedElement element, TakesScreenshot takesScreenshot) {
		this(element.getDescription());
		if (takesScreenshot != null)
			lg.error(logFactory.screenshot(
					takesScreenshot, getMessage()));
	}

	/**
	 * Instantiates a new element not visible exception.
	 *
	 * @param element DescribedElement
	 */
	public ElementNotVisibleException(DescribedElement element) {
		this(element.getDescription());
	}

	/**
	 * Instantiates a new element not visible exception.
	 *
	 * @param LogDescriptionBean
	 */
	public ElementNotVisibleException(LogDescriptionBean description) {
		super();
		this.description = description;
		lg.error(getMessage(), this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		// TODO add locator info
		String reason = "Element is not currently visible and so may not be interacted: {"
				+ "\"type\":\"" + description.getType() + "\"," + "\"name\":\""
				+ description.getName() + "\"}";

		return reason;
	}
}
