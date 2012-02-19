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
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keyboard;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.lohika.alp.selenium.jscatcher.JsErrorCatcherConfiguration;
import com.lohika.alp.selenium.jscatcher.FirefoxJsErrorCathcer;
import com.lohika.alp.selenium.jscatcher.JSErrorCatcher;
import com.lohika.alp.selenium.jscatcher.JsErrorCatcherException;



/**
 * ALP wrapper over WebDriver for implementing autologging ability.
 */
public class LoggingWebDriver implements WebDriver, JavascriptExecutor,
		HasInputDevices, HasCapabilities, TakesScreenshot, WrapsDriver,
		DescribedElement {

	/** The log4j logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	/** The Webdriver instance. */
	protected final WebDriver driver;

	/** The ALP factory for log elements . */
	protected final LogElementsSeleniumFactory factory;

	/** The description. */
	protected LogDescriptionBean description = new LogDescriptionBean();

	/**
	 * Instantiates a new logging web driver.
	 *
	 * @param driver the driver
	 * @param name the name
	 * @param factory the factory
	 */
	public LoggingWebDriver(WebDriver driver, String name,
			LogElementsSeleniumFactory factory) {

		if (driver == null || name == null || factory == null) {
			throw new IllegalArgumentException("Parameters can't be null");
		}

		this.driver = driver;
		this.factory = factory;

		description.setName(name);
		description.setType("driver");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.internal.WrapsDriver#getWrappedDriver()
	 */
	@Override
	public WebDriver getWrappedDriver() {
		return driver;
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
	 * @see org.openqa.selenium.WebDriver#get(java.lang.String)
	 */
	@Override
	public void get(String url) {
		logger.info(factory.get(this, url));

		driver.get(url);
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#getCurrentUrl()
	 */
	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#getTitle()
	 */
	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * wraps WebElement with logging.
	 *
	 * @param from the WebElement for wrap
	 * @return wrapped instance of WebElement
	 */
	protected WebElement createWebElement(WebElement from) {
		return new LoggingWebElement(from, factory);
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#findElements(org.openqa.selenium.By)
	 */
	@Override
	public List<WebElement> findElements(By by) {
		List<WebElement> elemets = driver.findElements(by);

		List<WebElement> loggedElements = new ArrayList<WebElement>();

		for (WebElement element : elemets) {
			loggedElements.add(createWebElement(element));
		}

		return loggedElements;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#findElement(org.openqa.selenium.By)
	 */
	@Override
	public WebElement findElement(By by) {
		WebElement element = driver.findElement(by);
		WebElement loggedElement = createWebElement(element);

		return loggedElement;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#getPageSource()
	 */
	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#close()
	 */
	@Override
	public void close() {
		logJsErrors();
		logger.info(factory.close(this));

		driver.close();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#quit()
	 */
	@Override
	public void quit() {
		logJsErrors();
		logger.info(factory.quit(this));

		driver.quit();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#getWindowHandles()
	 */
	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#getWindowHandle()
	 */
	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#switchTo()
	 */
	@Override
	public TargetLocator switchTo() {
		// TODO wrap TargetLocator
		return driver.switchTo();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#navigate()
	 */
	@Override
	public Navigation navigate() {
		// TODO wrap Navigation
		return driver.navigate();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.WebDriver#manage()
	 */
	@Override
	public Options manage() {
		// TODO wrap Options
		return driver.manage();
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.TakesScreenshot#getScreenshotAs(org.openqa.selenium.OutputType)
	 */
	@Override
	public <X> X getScreenshotAs(OutputType<X> target)
			throws WebDriverException {
		if (driver instanceof TakesScreenshot) {
			return ((TakesScreenshot) driver).getScreenshotAs(target);
		}
		throw new UnsupportedOperationException(
				"Underlying driver instance does not support taking screenshots");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.JavascriptExecutor#executeScript(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Object executeScript(String script, Object... args) {
		if (driver instanceof JavascriptExecutor) {

			Object result = ((JavascriptExecutor) driver).executeScript(script,
					args);

			return result;
		}
		throw new UnsupportedOperationException(
				"Underlying driver instance does not support executing javascript");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.JavascriptExecutor#executeAsyncScript(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Object executeAsyncScript(String arg0, Object... arg1) {
		if (driver instanceof JavascriptExecutor) {
			return ((JavascriptExecutor) driver).executeAsyncScript(arg0, arg1);
		}

		throw new UnsupportedOperationException(
				"Underlying driver instance does not support executing javascript");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.HasCapabilities#getCapabilities()
	 */
	@Override
	public Capabilities getCapabilities() {
		// This 'if' handle case when LoggingWebDriver.driver is instanceof EventFiringWebDriver . 
		//In this case RemoteWebDriver is field within EventFiringWebDriver while EventFiringWebDriver don't support getting Capabilities
		if (driver instanceof EventFiringWebDriver
				&& ((EventFiringWebDriver) driver).getWrappedDriver() instanceof HasCapabilities) {
			return ((HasCapabilities) ((EventFiringWebDriver) driver)
					.getWrappedDriver()).getCapabilities();
		} else if (driver instanceof HasCapabilities) {
			return ((HasCapabilities) driver).getCapabilities();
		}

		throw new UnsupportedOperationException(
				"Underlying driver instance does not support capabilities");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.HasInputDevices#getKeyboard()
	 */
	@Override
	public Keyboard getKeyboard() {
		if (driver instanceof HasInputDevices) {
			return ((HasInputDevices) driver).getKeyboard();
		}

		throw new UnsupportedOperationException(
				"Underlying driver instance does not support input devices");
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.HasInputDevices#getMouse()
	 */
	@Override
	public Mouse getMouse() {
		if (driver instanceof HasInputDevices) {
			return ((HasInputDevices) driver).getMouse();
		}

		throw new UnsupportedOperationException(
				"Underlying driver instance does not support input devices");
	}

	/**
	 * Log js errors.
	 */
	private void logJsErrors() {
		if (!JsErrorCatcherConfiguration.getInstance().getJsErrorAutolog())
			return;
		JSErrorCatcher catcher = new FirefoxJsErrorCathcer(driver);
		ArrayList<String> errors;
		try {
			errors = catcher.getJsErrors();
			if (errors.size()>0)
				logger.error(errors.toString());
		} catch (JsErrorCatcherException e) {
			logger.warn(e.getMessage(), e.getCause());
		}
	}
}
