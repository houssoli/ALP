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

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;



/**
 * The Interface FlexElement.
 */
public interface FlexElement extends SearchContext {

	/**
	 * Click on FlexElement.
	 */
	void click();

	/**
	 * Send keys.
	 *
	 * @param keysToSend the keys to send
	 */
	void sendKeys(CharSequence... keysToSend);

	/**
	 * Getter for text String.
	 *
	 * @return text String
	 */
	String getText();

	/**
	 * Checks if is displayed.
	 *
	 * @return true, if is displayed
	 */
	boolean isDisplayed();

	/**
	 * Drag and drop FlexElement.
	 *
	 * @param element to dragAndDrop
	 */
	void dragAndDrop(FlexElement element);

	/**
	 * Drag and drop FlexElement.
	 *
	 * @param point - destination Point
	 */
	void dragAndDrop(Point point);

	/**
	 * Drag and drop FlexElement.
	 *
	 * @param element to dragAndDrop
	 * @param point - destination Point
	 */
	void dragAndDrop(FlexElement element, Point point);

	/**
	 * Select FlexElement.
	 *
	 * @param the index
	 */
	void select(int index);

	/**
	 * Select FlexElement.
	 *
	 * @param text representing FlexElement
	 */
	void select(String text);

	/**
	 * Double click on FlexElement .
	 */
	void doubleClick();

	/**
	 * Gets the property value.
	 *
	 * @param propertyName the property name
	 * @return the property value
	 */
	String getPropertyValue(String propertyName);
	
	/**
	 * Sets the property value.
	 *
	 * @param propertyName the property name
	 * @param value to set 
	 */
	void setPropertyValue(String propertyName, String value);

	/**
	 * Where on the page is the top left-hand corner of the rendered element?.
	 *
	 * @return A point, containing the location of the top left-hand corner of the element
	 * (global coords)
	 */
	Point getLocation();
	
	/**
	 * What is the width and height of the rendered element?.
	 *
	 * @return The size of the element on the page.
	 */
	Dimension getSize();
	
	/**
	 * Move mouse over FlexElement .
	 */
	void mouseOver();
	
	/**
	 * Move mouse out of FlexElement .
	 */
	void mouseOut();
	
	/**
	 * Move mouse up.
	 */
	void mouseUp();
	
	/**
	 * Move mouse down.
	 */
	void mouseDown();
	
	/**
	 * Move focus out.
	 */
	void focusOut();
	
	/**
	 * Date.
	 *
	 * @param date the date
	 */
	void date(String date);
	
	/**
	 * Context click.
	 *
	 * @param contextMenuIndex the context menu index
	 */
	void contextClick(int contextMenuIndex);
	
	/**
	 * Sets the selection.
	 *
	 * @param begin index 
	 * @param end index
	 */
	void setSelection(int begin, int end);

	/**
	 * Dump.
	 *
	 * @return dump
	 */
	String dump();
}
