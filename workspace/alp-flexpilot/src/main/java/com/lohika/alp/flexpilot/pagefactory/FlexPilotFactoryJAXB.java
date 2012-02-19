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
package com.lohika.alp.flexpilot.pagefactory;

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
import com.lohika.alp.selenium.log.DescribedElement;


/**
 * The Class FlexPilotFactoryJAXB.
 */
public class FlexPilotFactoryJAXB implements FlexPilotFactory {

	/** The  factory. */
	protected ObjectFactory factory = new ObjectFactory();

	/**
	 * Create action with specified name for self DescribedElement  
	 *
	 * @param self - com.lohika.alp.selenium.log.DescribedElement
	 * @param name - name for action
	 * @return created Action instance 
	 */
	protected Action getAction(DescribedElement self, String name) {
		Action action = factory.createAction();

		action.setName(name);
		action.setWebelement((Webelement) element(self));

		return action;
	}
	
	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#element(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object element(DescribedElement element) {
		Webelement webelement = factory.createWebelement();

		if (element != null && element.getDescription() != null) {
			webelement.setName(element.getDescription().getName());
			webelement.setType(element.getDescription().getType());
		}

		return webelement;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#click(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object click(DescribedElement self) {
		return getAction(self, "click");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#sendKeys(com.lohika.alp.selenium.log.DescribedElement, java.lang.CharSequence[])
	 */
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
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#screenshot(org.openqa.selenium.TakesScreenshot, java.lang.String)
	 */
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
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#screenshot(org.openqa.selenium.WebDriver, java.lang.String)
	 */
	public Object screenshot(WebDriver driver, String description) {

		if (driver instanceof TakesScreenshot) {
			return screenshot((TakesScreenshot) driver, description);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#drugAndDrop(com.lohika.alp.selenium.log.DescribedElement, com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object drugAndDrop(DescribedElement self, DescribedElement toElement) {
		Action action = getAction(self, "drag and drop");
		action.getArg().add(toElement.getDescription().getName());
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#drugAndDrop(com.lohika.alp.selenium.log.DescribedElement, com.lohika.alp.selenium.log.DescribedElement, int, int)
	 */
	public Object drugAndDrop(DescribedElement self, DescribedElement toElement, int x, int y) {
		Action action = getAction(self, "drag and drop");
		action.getArg().add(toElement.getDescription().getName());
		action.getArg().add(x);
		action.getArg().add(y);
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#drugAndDrop(com.lohika.alp.selenium.log.DescribedElement, int, int)
	 */
	public Object drugAndDrop(DescribedElement self, int x, int y) {
		Action action = getAction(self, "drag and drop");
		action.getArg().add(x);
		action.getArg().add(y);
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#doubleClick(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object doubleClick(DescribedElement self) {
		return getAction(self, "double click");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#select(com.lohika.alp.selenium.log.DescribedElement, java.lang.String)
	 */
	public Object select(DescribedElement self, String text) {
		Action action = getAction(self, "select");
		action.getArg().add(text);
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#mouseOver(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object mouseOver(DescribedElement self) {
		return getAction(self, "mouse over");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#mouseOut(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object mouseOut(DescribedElement self) {
		return getAction(self, "mouse out");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#mouseUp(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object mouseUp(DescribedElement self) {
		return getAction(self, "mouse up");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#mouseDown(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object mouseDown(DescribedElement self) {
		return getAction(self, "mouse down");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#focusOut(com.lohika.alp.selenium.log.DescribedElement)
	 */
	public Object focusOut(DescribedElement self) {
		return getAction(self, "focus out");
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#date(com.lohika.alp.selenium.log.DescribedElement, java.lang.String)
	 */
	public Object date(DescribedElement self, String date) {
		Action action = getAction(self, "date");
		action.getArg().add(date);
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#contextClick(com.lohika.alp.selenium.log.DescribedElement, int)
	 */
	public Object contextClick(DescribedElement self, int contextMenuIndex) {
		Action action = getAction(self, "context click");
		action.getArg().add(contextMenuIndex);
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#setSelection(com.lohika.alp.selenium.log.DescribedElement, int, int)
	 */
	public Object setSelection(DescribedElement self, int begin, int end) {
		Action action = getAction(self, "select text");
		action.getArg().add(begin);
		action.getArg().add(end);
		return action;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory#dump(com.lohika.alp.selenium.log.DescribedElement, java.lang.String)
	 */
	public Object dump(DescribedElement self, String content) {
		Action action = getAction(self, "dump");
		action.getArg().add(content);
		return action;
	}

}
