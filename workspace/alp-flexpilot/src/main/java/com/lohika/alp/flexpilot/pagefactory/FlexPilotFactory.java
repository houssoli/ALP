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
package com.lohika.alp.flexpilot.pagefactory;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.lohika.alp.selenium.log.DescribedElement;


/**
 * A factory for creating FlexPilot objects.
 */
public interface FlexPilotFactory {

	/**
	 * create object representing element.
	 *
	 * @param self - DescribedElement
	 * @return created Object
	 */
	public Object element(DescribedElement self);
	
	/**
	 * Click.
	 *
	 * @param self - DescribedElement
	 * @return created Object
	 */
	public Object click(DescribedElement self);
	
	/**
	 * Send keys.
	 *
	 * @param self - DescribedElement
	 * @param keysToSend the keys to send
	 * @return created Object	  
	 */
	public Object sendKeys(DescribedElement self, CharSequence... keysToSend);
	
	/**
	 * Screenshot.
	 *
	 * @param takesScreenshot the takes screenshot
	 * @param description 
	 * @return created Object
	 */
	public Object screenshot(TakesScreenshot takesScreenshot, String description);

	/**
	 * Creates screenshot  
	 *
	 * @param driver - WebDriver instance
	 * @param description 
	 * @return screenshot log element or null if WebDriver instance doesn't support TakesScreenshot interface. 
	 */
	public Object screenshot(WebDriver driver, String description);
	
	/**
	 * Drug and drop.
	 *
	 * @param self - DescribedElement
	 * @param toElement  - DescribedElement on which we will drop
	 * @return created Object
	 */
	public Object drugAndDrop(DescribedElement self, DescribedElement toElement);
	
	/**
	 * Drug and drop.
	 *
	 * @param self - DescribedElement
	 * @param toElement the to element
	 * @param x the x
	 * @param y the y
	 * @return created Object
	 */
	public Object drugAndDrop(DescribedElement self, DescribedElement toElement, int x, int y);
	
	/**
	 * Drug and drop.
	 *
	 * @param self DescribedElement
	 * @param x the x
	 * @param y the y
	 * @return created Object
	 */
	public Object drugAndDrop(DescribedElement self, int x, int y);
	
	/**
	 * Double click.
	 *
	 * @param self DescribedElement
	 * @return created Object
	 */
	public Object doubleClick(DescribedElement self);
	
	/**
	 * Mouse over.
	 *
	 * @param self DescribedElement
	 * @return created Object
	 */
	public Object mouseOver(DescribedElement self);
	
	/**
	 * Mouse out.
	 *
	 * @param self DescribedElement
	 * @return created Object
	 */
	public Object mouseOut(DescribedElement self);
	
	/**
	 * Mouse up.
	 *
	 * @param self DescribedElement
	 * @return created Object
	 * 	 */
	public Object mouseUp(DescribedElement self);
	
	/**
	 * Mouse down.
	 *
	 * @param self DescribedElement
	 * @return created Object
	 */
	public Object mouseDown(DescribedElement self);
	
	/**
	 * Focus out.
	 *
	 * @param self DescribedElement
	 * @return created Object
	 */
	public Object focusOut(DescribedElement self);
	
	/**
	 * Select.
	 *
	 * @param self DescribedElement
	 * @param text
	 * @return created Object
	 */
	public Object select(DescribedElement self, String text);
	
	/**
	 * Date.
	 *
	 * @param self DescribedElement
	 * @param date 
	 * @return created Object
	 */
	public Object date(DescribedElement self, String date);
	
	/**
	 * Context click.
	 *
	 * @param self DescribedElement
	 * @param contextMenuIndex the index of context menu item 
	 * @return created Object
	 */
	public Object contextClick(DescribedElement self, int contextMenuIndex);
	
	/**
	 * Sets the selection.
	 *
	 * @param self DescribedElement
	 * @param begin 
	 * @param end 
	 * @return created Object
	 */
	public Object setSelection(DescribedElement self, int begin, int end);
	
	/**
	 * Dump.
	 *
	 * @param self DescribedElement
	 * @param content 
	 * @return created Object
	 */
	public Object dump(DescribedElement self, String content);
}
