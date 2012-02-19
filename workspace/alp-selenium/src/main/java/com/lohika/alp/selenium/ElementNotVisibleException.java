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
package com.lohika.alp.selenium;

import com.lohika.alp.selenium.log.LogDescription;
import com.lohika.alp.selenium.log.LogDescriptionBean;
import com.lohika.alp.selenium.log.LoggingWebElement;

/**
 * Extends Selenium {@link org.openqa.selenium.ElementNotVisibleException}
 * message with {@link LogDescription} info
 */
public class ElementNotVisibleException extends
		org.openqa.selenium.ElementNotVisibleException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2689379101772386737L;

	/** The LogDescriptionBean. */
	private final LogDescriptionBean description;

	/**
	 * Instantiates a new element not visible exception.
	 *
	 * @param element the element
	 * @param cause the cause
	 */
	public ElementNotVisibleException(LoggingWebElement element,
			org.openqa.selenium.ElementNotVisibleException cause) {
		this(element.getDescription(), cause);
	}

	/**
	 * Instantiates a new element not visible exception.
	 *
	 * @param description the description
	 * @param cause the cause
	 */
	public ElementNotVisibleException(LogDescriptionBean description,
			org.openqa.selenium.ElementNotVisibleException cause) {
		super(null, cause);

		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriverException#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO add locator info
		String reason = "Element is not currently visible and so may not be interacted: {"
				+ "\"type\":\""
				+ description.getType()
				+ "\","
				+ "\"name\":\""
				+ description.getName() + "}";

		return reason;
	}

}
