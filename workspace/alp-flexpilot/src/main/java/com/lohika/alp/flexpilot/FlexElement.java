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

public interface FlexElement extends SearchContext {

	void click();

	void sendKeys(CharSequence... keysToSend);

	String getText();

	boolean isDisplayed();

	void dragAndDrop(FlexElement element);

	void dragAndDrop(Point point);

	void dragAndDrop(FlexElement element, Point point);

	void select(int index);

	void select(String text);

	void doubleClick();

	String getPropertyValue(String propertyName);
	
	void setPropertyValue(String propertyName, String value);

	/**
	 * Where on the page is the top left-hand corner of the rendered element?
	 * 
	 * @return A point, containing the location of the top left-hand corner of the element
	 * (global coords)
	 */
	Point getLocation();
	
	/**
	 * What is the width and height of the rendered element?
	 * 
	 * @return The size of the element on the page.
	 */
	Dimension getSize();
	
	void mouseOver();
	void mouseOut();
	void mouseUp();
	void mouseDown();
	
	void focusOut();
	
	void date(String date);
	
	void contextClick(int contextMenuIndex);
	
	void setSelection(int begin, int end);

	String dump();
}
