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
package com.lohika.alp.flexpilot.driver;

import com.lohika.alp.flexpilot.SearchContext;



/**
 * The Interface FlexDriver.
 */
public interface FlexDriver extends SearchContext {

	/**
	 * get version of flex pilot driver.
	 *
	 * @return String
	 */
	public String getVersion();
	
	/**
	 * check if a flex/flash object is loaded and rendered in a browser.
	 *
	 * @return boolean
	 */
	public boolean isFlexLoaded();
	
}
