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
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

/**
 * The Class LogDescriptionElementLocator.
 */
public class LogDescriptionElementLocator implements ElementLocator {

	/** The element locator. */
	private final ElementLocator locator;

	/** LogDescriptionBean */
	private final LogDescriptionBean descr;

	/**
	 * Instantiates a new log description element locator.
	 *
	 * @param elementLocator the element locator
	 * @param field the field
	 */
	public LogDescriptionElementLocator(ElementLocator elementLocator,
			Field field) {
		this.locator = elementLocator;

		LogDescriptionAnnotations annotations = new LogDescriptionAnnotations(
				field);

		descr = annotations.buildLogDescriptionBean();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.support.pagefactory.ElementLocator#findElement()
	 */
	@Override
	public WebElement findElement() {
		WebElement element;
		try {
			element = locator.findElement();
		} catch (NoSuchElementException e) {

			throw new com.lohika.alp.selenium.NoSuchElementException(descr, e);
		}

		if (element instanceof DescribedElement) {

			((DescribedElement) element).setDescription(descr);
		}

		return element;
	}



    /* (non-Javadoc)
     * @see org.openqa.selenium.support.pagefactory.ElementLocator#findElements()
     */
    @Override
    public List<WebElement> findElements() {
        List<WebElement> elements;
        try {
            elements = locator.findElements();
        } catch (NoSuchElementException e) {

            throw new com.lohika.alp.selenium.NoSuchElementException(descr, e);
        }

        for (WebElement element: elements)
        if (element instanceof DescribedElement) {

            ((DescribedElement) element).setDescription(descr);
        }

        return elements;
    }

}
