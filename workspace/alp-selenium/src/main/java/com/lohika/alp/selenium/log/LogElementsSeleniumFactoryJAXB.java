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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.lohika.alp.log.elements.schema.Action;
import com.lohika.alp.log.elements.schema.ObjectFactory;
import com.lohika.alp.log.elements.schema.Screenshot;
import com.lohika.alp.log.elements.schema.Webelement;
import com.lohika.alp.log4j.LogFileAttachment;

/**
 * The Class that implements LogElementsSeleniumFactory with JAXB.
 */
public class LogElementsSeleniumFactoryJAXB implements
		LogElementsSeleniumFactory {

	/** The ALP Object factory. */
	protected ObjectFactory factory = new ObjectFactory();

	/**
	 * Create action with specified name for webelement self.
	 *
	 * @param self - instance of class that support DescribedElement 
	 * @param name - name of returned Action
	 * @return created Action instance
	 */
	protected Action getAction(DescribedElement self, String name) {
		Action action = factory.createAction();

		action.setName(name);
		action.setWebelement((Webelement) element(self));

		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#element(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object element(DescribedElement element) {
		Webelement webelement = factory.createWebelement();

		if (element != null && element.getDescription() != null) {
			webelement.setName(element.getDescription().getName());
			webelement.setType(element.getDescription().getType());
		}

		return webelement;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#get(com.lohika.alp.selenium.log.DescribedElement, java.lang.String)
	 */
	@Override
	public Object get(DescribedElement self, String url) {
		Action action = getAction(self, "get");

		action.getArg().add(url);
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#close(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object close(DescribedElement self) {
		return getAction(self, "close");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#quit(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object quit(DescribedElement self) {
		return getAction(self, "quit");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#click(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object click(DescribedElement self) {
		return getAction(self, "click");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#submit(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object submit(DescribedElement self) {
		return getAction(self, "click");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#sendKeys(com.lohika.alp.selenium.log.DescribedElement, java.lang.CharSequence[])
	 */
	@Override
	public Object sendKeys(DescribedElement self, CharSequence... keysToSend) {
		Action action = getAction(self, "send keys");

		StringBuilder builder = new StringBuilder();
		for (CharSequence key : keysToSend) {
			builder.append(key);
		}

		action.getArg().add(builder.toString());

		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#clear(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object clear(DescribedElement self) {
		return getAction(self, "clear");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#toggle(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object toggle(DescribedElement self) {
		return getAction(self, "toggle");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#setSelected(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object setSelected(DescribedElement self) {
		return getAction(self, "set selected");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#hover(com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object hover(DescribedElement self) {
		return getAction(self, "hover");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#dragAndDropBy(com.lohika.alp.selenium.log.DescribedElement, int, int)
	 */
	@Override
	public Object dragAndDropBy(DescribedElement self, int moveRightBy,
			int moveDownBy) {
		Action action = getAction(self, "drag and drop by");

		action.getArg().add(moveRightBy);
		action.getArg().add(moveDownBy);

		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#dragAndDropOn(com.lohika.alp.selenium.log.DescribedElement, com.lohika.alp.selenium.log.DescribedElement)
	 */
	@Override
	public Object dragAndDropOn(DescribedElement self, DescribedElement element) {
		Action action = getAction(self, "drag and drop by");

		action.getArg().add(element);

		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#screenshot(org.openqa.selenium.TakesScreenshot, java.lang.String)
	 */
	@Override
	public Object screenshot(TakesScreenshot takesScreenshot, String description) {

		Screenshot screenshot = factory.createScreenshot();
		screenshot.setDescription(description);

		File tempFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		File attachmentFile = null;
		try {
			attachmentFile = LogFileAttachment.getAttachmentFile("", "png");
			FileUtils.copyFile(tempFile, attachmentFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (attachmentFile != null)
			screenshot.setUrl(attachmentFile.getName());

		return screenshot;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.LogElementsSeleniumFactory#screenshot(org.openqa.selenium.WebDriver, java.lang.String)
	 */
	@Override
	public Object screenshot(WebDriver driver, String description) {

		if (driver instanceof TakesScreenshot) {
			return screenshot((TakesScreenshot) driver, description);
		} else {
			return null;
		}
	}
	
}
