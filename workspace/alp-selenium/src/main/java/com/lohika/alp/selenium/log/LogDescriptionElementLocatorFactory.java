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

import java.lang.reflect.Field;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

/**
 * A factory for creating LogDescriptionElementLocator objects.
 */
public class LogDescriptionElementLocatorFactory implements
		ElementLocatorFactory {

	/** The element locator factory. */
	private final ElementLocatorFactory factory;

	/**
	 * Instantiates a new log description element locator factory.
	 *
	 * @param factory the factory
	 */
	public LogDescriptionElementLocatorFactory(ElementLocatorFactory factory) {
		this.factory = factory;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.pagefactory.ElementLocatorFactory#createLocator(java.lang.reflect.Field)
	 */
	@Override
	public ElementLocator createLocator(Field field) {
		ElementLocator elementLocator = factory.createLocator(field);
		return new LogDescriptionElementLocator(elementLocator, field);
	}

}
