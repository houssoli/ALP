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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import com.lohika.alp.selenium.log.LogDescriptionDefaultFieldDecorator;
import com.lohika.alp.selenium.log.LogDescriptionElementLocatorFactory;

/**
 * A factory for creating objects wrapped with ALP autologging according to PageObject pattern.
 */
public class AlpPageFactory {

	/**
	 * Inits WebElements within class created by PageObject pattern.
	 *
	 * @param <T> the generic type
	 * @param driver the driver
	 * @param pageClassToProxy the page class to proxy
	 * @return the t
	 */
	public static <T> T initElements(WebDriver driver, Class<T> pageClassToProxy) {
		T page = instantiatePage(driver, pageClassToProxy);
		initElements(driver, page);
		return page;
	}

	/**
	 * Inits WebElements within class created by PageObject pattern.
	 *
	 * @param driver the driver
	 * @param page the page
	 */
	public static void initElements(WebDriver driver, Object page) {
		final WebDriver driverRef = driver;

		ElementLocatorFactory factory;

		factory = new DefaultElementLocatorFactory(driverRef);

		factory = new LogDescriptionElementLocatorFactory(factory);

		initElements(factory, page);
	}
	
	/**
	 * Inits WebElements within class created by PageObject pattern.
	 *
	 * @param factory the factory
	 * @param page the page
	 */
	public static void initElements(ElementLocatorFactory factory, Object page) {
		final ElementLocatorFactory factoryRef = factory;
		
		// Temporary fix for http://code.google.com/p/selenium/issues/detail?id=1754
		PageFactory.initElements(new LogDescriptionDefaultFieldDecorator(factoryRef), page);
	}

	/**
	 * Instantiate page.
	 *
	 * @param <T> the generic type
	 * @param driver the driver
	 * @param pageClassToProxy the page class to proxy
	 * @return the t
	 */
	private static <T> T instantiatePage(WebDriver driver,
			Class<T> pageClassToProxy) {
		try {
			try {
				Constructor<T> constructor = pageClassToProxy
						.getConstructor(WebDriver.class);
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClassToProxy.newInstance();
			}
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
