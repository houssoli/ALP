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
package com.lohika.alp.utils.object.reader;

import java.util.List;

/**
 * The Interface ObjectReader.
 */
public interface ObjectReader {
	
	// open a file with data
	/**
	 * Open file from which object will be read .
	 *
	 * @param fileName the file name
	 * @throws Exception the exception
	 */
	public void open(String fileName) throws Exception;
	
	// read object of specific type with the index
	/**
	 * Read object.
	 *
	 * @param type the type
	 * @param index the index
	 * @return the object
	 * @throws ObjectReaderException the object reader exception
	 */
	public Object readObject(Class<?> type, int index) throws ObjectReaderException;
	
	// read object of specific type with the index
	/**
	 * Read object.
	 *
	 * @param type the type
	 * @param index the index
	 * @return the object
	 * @throws ObjectReaderException the object reader exception
	 */
	public Object readObject(Class<?> type, String index) throws ObjectReaderException;
	
	// read all objects of specific type
	/**
	 * Read all objects.
	 *
	 * @param type the type
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<?> readAllObjects(Class<?> type) throws Exception;
	
	// close file
	/**
	 * Close file from which object was read.
	 */
	public void close();

}
