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
package com.lohika.alp.log.elements;

import java.util.List;


/**
 * A factory for creating LogElements objects.
 */
public interface LogElementsFactory {

	/**
	 * Create text area object.
	 *
	 * @param name the name of the text Area 
	 * @param content the content of the text Area
	 * @return the text area object
	 */
	public Object textArea(String name, String content);

	/**
	 * Create link object.
	 *
	 * @param url the url text 
	 * @return the Link object
	 */
	public Object link(String url);

	/**
	 * Create link object.
	 *
	 * @param url the url text
	 * @param description the url description
	 * @return the Link object
	 */
	public Object link(String url, String description);

	/**
	 * Create screenshot object.
	 *
	 * @param url the link to screenshot
	 * @param description the screanshot description
	 * @return the screenshot object
	 */
	public Object screenshot(String url, String description);

	/**
	 * Create comment object.
	 *
	 * @param comment the comment text
	 * @return the comment object
	 */
	public Object comment(String comment);

	/**
	 * Create comment object .
	 *
	 * @param comment the comment text
	 * @param style the comment style
	 * @return the comment object
	 */
	public Object comment(String comment, LogStyle style);

	/**
	 * Create comment object .
	 *
	 * @param comment the list of comment texts
	 * @return the comment object
	 */
	public Object comment(List<Object> comment);

	/**
	 * Combine several comment objects tp one .
	 *
	 * @param comment the comment objects (can get from 1 to many objects) 
	 * @return the comment object
	 */
	public Object comment(Object... comment);

	/**
	 * Combine comment objects to one with specified style.
	 *
	 * @param comment the list comment objects 
	 * @param style the style of result comment object
	 * @return the comment object
	 */
	public Object comment(List<Object> comment, LogStyle style);

}
