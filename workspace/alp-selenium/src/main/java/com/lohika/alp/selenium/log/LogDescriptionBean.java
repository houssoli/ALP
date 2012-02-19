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


/**
 * The Class LogDescriptionBean annotation .
 */
public class LogDescriptionBean {

	/** The name that appear in log as element name . */
	private String name;

	/** The type rule which picture will appear in log for this element .	 * 
	 *	Available values :
	 *	- button
	 *	- checkbox
	 *	- comment 
	 *	- customstep
	 *	- driver
	 *	- edit
	 *	- frame
	 *	- image
	 *	- link
	 *	- password
	 *	- radiobutton
	 *	- screenshot
	 *	- select 
	 *	- text
	 *	- textarea
	 *	- textfield
	 **/
	private String type = "element";

	/**
	 * Instantiates a new log description bean.
	 */
	public LogDescriptionBean() {}
	
	/**
	 * Instantiates a new log description bean.
	 *
	 * @param name - value for private field name
	 * @param type - value for private field type
	 */
	public LogDescriptionBean(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Instantiates a new log description bean.
	 *
	 * @param name - value for private field name
	 */
	public LogDescriptionBean(String name) {
		this.name = name;
	}

	/**
	 * Getter for name.
	 *
	 * @return name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter the type.
	 *
	 * @return the type String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getType()+" "+getName();
	}

}
