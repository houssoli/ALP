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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * ALP wrapper over WebElement to add automatic logging ability.
 */
public class LoggingWebElement implements WebElement, DescribedElement,
		Locatable, WrapsElement {

	/** The log4j logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	/** The instance of WebElement interface . */
	protected final WebElement element;

	/** ALP factory . */
	protected final LogElementsSeleniumFactory factory;

	/** The description. */
	protected LogDescriptionBean description;

	/**
	 * Instantiates a new logging web element.
	 *
	 * @param element the element
	 * @param factory the factory
	 */
	public LoggingWebElement(WebElement element,
			LogElementsSeleniumFactory factory) {

		if (element == null || factory == null) {
			throw new IllegalArgumentException("Parameters can't be null");
		}

		this.element = element;
		this.factory = factory;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.internal.WrapsElement#getWrappedElement()
	 */
	@Override
	public WebElement getWrappedElement() {
		return element;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.DescribedElement#setDescription(com.lohika.alp.selenium.log.LogDescriptionBean)
	 */
	@Override
	public void setDescription(LogDescriptionBean description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.DescribedElement#getDescription()
	 */
	@Override
	public LogDescriptionBean getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof WebElement)) {
			return false;
		}

		WebElement other = (WebElement) obj;
		if (other instanceof WrapsElement) {
			other = ((WrapsElement) other).getWrappedElement();
		}

		return element.equals(other);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return element.hashCode();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#click()
	 */
	@Override
	public void click() {
		logger.info(factory.click(this));

		element.click();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#submit()
	 */
	@Override
	public void submit() {
		logger.info(factory.submit(this));

		element.click();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#sendKeys(java.lang.CharSequence[])
	 */
	@Override
	public void sendKeys(CharSequence... keysToSend) {
		logger.info(factory.sendKeys(this, keysToSend));

		element.sendKeys(keysToSend);
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#clear()
	 */
	@Override
	public void clear() {
		logger.info(factory.clear(this));

		element.clear();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#getTagName()
	 */
	@Override
	public String getTagName() {
		return element.getTagName();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#getAttribute(java.lang.String)
	 */
	@Override
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#isSelected()
	 */
	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#getText()
	 */
	@Override
	public String getText() {
		return element.getText();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#findElements(org.openqa.selenium.By)
	 */
	@Override
	public List<WebElement> findElements(By by) {
		List<WebElement> children = element.findElements(by);
		List<WebElement> loggedChildren = new ArrayList<WebElement>();

		for (WebElement child : children) {
			loggedChildren.add(new LoggingWebElement(child, factory));
		}

		return children;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#findElement(org.openqa.selenium.By)
	 */
	@Override
	public WebElement findElement(By by) {
		WebElement child = element.findElement(by);
		WebElement loggedChild = new LoggingWebElement(child, factory);

		return loggedChild;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#getCssValue(java.lang.String)
	 */
	@Override
	public String getCssValue(String propertyName) {
		return element.getCssValue(propertyName);
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#getLocation()
	 */
	@Override
	public Point getLocation() {
		return element.getLocation();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#getSize()
	 */
	@Override
	public Dimension getSize() {
		return element.getSize();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebElement#isDisplayed()
	 */
	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.internal.Locatable#getLocationOnScreenOnceScrolledIntoView()
	 */
	@Override
	public Point getLocationOnScreenOnceScrolledIntoView() {
		if (element instanceof Locatable) {
			return ((Locatable) element)
					.getLocationOnScreenOnceScrolledIntoView();
		}

		throw new UnsupportedOperationException(
				"Underlying element instance does not support location");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.internal.Locatable#getCoordinates()
	 */
	@Override
	public Coordinates getCoordinates() {
		if (element instanceof Locatable) {
			return ((Locatable) element).getCoordinates();
		}

		throw new UnsupportedOperationException(
				"Underlying element instance does not support location");
	}

}
