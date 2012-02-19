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
package com.lohika.alp.selenium.log;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/**
 * A factory for creating LogElementsSelenium objects.
 */
public interface LogElementsSeleniumFactory {

	/**
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object element(DescribedElement self);

	/**
	 * Gets the.
	 *
	 * @param self - instance of DescribedElement
	 * @param url - 
	 * @return instance of Object
	 */
	public Object get(DescribedElement self, String url);

	/**
	 * Close.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object close(DescribedElement self);

	/**
	 * Quit.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object quit(DescribedElement self);

	/**
	 * Click.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object click(DescribedElement self);

	/**
	 * Submit.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object submit(DescribedElement self);

	/**
	 * Send keys.
	 *
	 *
	 * @param self - instance of DescribedElement
	 * @param keysToSend the keys to send
	 * @return instance of Object
	 */
	public Object sendKeys(DescribedElement self, CharSequence... keysToSend);

	/**
	 * Clear.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object clear(DescribedElement self);

	/**
	 * Toggle.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object toggle(DescribedElement self);

	/**
	 * Sets the selected.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object setSelected(DescribedElement self);

	/**
	 * Hover.
	 *
	 * @param self - instance of DescribedElement
	 * @return instance of Object
	 */
	public Object hover(DescribedElement self);

	/**
	 * Drag and drop by.
	 *
	 * @param self - instance of DescribedElement
	 * @param moveRightBy - quantity of pixels need to drag element to right before drop 
	 * @param moveDownBy - quantity of pixels need to drag element to down before drop
	 * @return instance of Object
	 */
	public Object dragAndDropBy(DescribedElement self, int moveRightBy,
			int moveDownBy);

	/**
	 * Drag and drop on.
	 *
	 * @param self - instance of DescribedElement
	 * @param element on which we need to drop on 
	 * @return instance of Object
	 */
	public Object dragAndDropOn(DescribedElement self, DescribedElement element);

	/**
	 * made screenshot.
	 *
	 * @param takesScreenshot - instance of driver that supports TakeScreenshot
	 * @param description 
	 * @return instance of Object
	 */
	public Object screenshot(TakesScreenshot takesScreenshot, String description);

	/**
	 * Creates screenshot log element or null if WebDriver instance doesn't
	 * support TakesScreenshot interface.
	 *
	 * @param driver instance of WebDriver
	 * @param description 
	 * @return instance of Object
	 */
	public Object screenshot(WebDriver driver, String description);

}
