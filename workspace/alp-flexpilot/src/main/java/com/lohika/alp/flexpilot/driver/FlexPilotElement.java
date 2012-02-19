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
package com.lohika.alp.flexpilot.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.collect.ImmutableMap;
import com.lohika.alp.flexpilot.By;
import com.lohika.alp.flexpilot.ElementNotVisibleException;
import com.lohika.alp.flexpilot.FlexElement;
import com.lohika.alp.flexpilot.WrapsElement;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory;
import com.lohika.alp.selenium.log.DescribedElement;
import com.lohika.alp.selenium.log.LogDescriptionBean;

/**
 * The Class FlexPilotElement.
 */
public class FlexPilotElement implements FlexElement, DescribedElement, WrapsElement {
	
	/** The log4j logger. */
	protected final Logger logger = Logger.getLogger(getClass());

	/** The FlexPilotDriver instance. */
	protected FlexPilotDriver driver;
	
	/** The id. */
	protected String id;
	
	/** The LogDescriptionBean. */
	protected LogDescriptionBean description;
	
	/** The factory. */
	protected FlexPilotFactory factory;
	
	/** The element. */
	protected final FlexElement element;
	
	/**
	 * Instantiates a new flex pilot element.
	 *
	 * @param driver - instance of FlexPilotDriver used for init 
	 */
	public FlexPilotElement(FlexPilotDriver driver) {
		this.driver = driver;
		element = this;
	}
	
	/**
	 * Instantiates a new flex pilot element.
	 *
	 * @param driver - instance of FlexPilotDriver used for init
	 * @param factory the factory
	 */
	public FlexPilotElement(FlexPilotDriver driver, FlexPilotFactory factory) {
		this(driver);
		if (factory == null) {
			throw new IllegalArgumentException("Parameters can't be null");
		}
		this.factory = factory;
	}
	
	// TODO
	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.WrapsElement#getWrappedElement()
	 */
	public FlexElement getWrappedElement() {
		//return this;
		return element;
	}

	/**
	 * Getter for id.
	 *
	 * @return String id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for id.
	 *
	 * @param id - the new id for assign to this.id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.DescribedElement#setDescription(com.lohika.alp.selenium.log.LogDescriptionBean)
	 */
	public void setDescription(LogDescriptionBean description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.log.DescribedElement#getDescription()
	 */
	public LogDescriptionBean getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.SearchContext#findElement(com.lohika.alp.flexpilot.By)
	 */
	public FlexElement findElement(By by) {
		return by.findElement(this);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.SearchContext#findElements(com.lohika.alp.flexpilot.By)
	 */
	public List<FlexElement> findElements(By by) {
		return by.findElements(this);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#click()
	 */
	public void click() {
		logger.info(factory.click(this));
		execute(DriverCommand.CLICK, ImmutableMap.of("id", id));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#sendKeys(java.lang.CharSequence[])
	 */
	public void sendKeys(CharSequence... keysToSend) {
		logger.info(factory.sendKeys(this, keysToSend));
		StringBuilder builder = new StringBuilder();
		for (CharSequence key : keysToSend) {
			builder.append(key);
		}
		execute(DriverCommand.TYPE, ImmutableMap.of("id", id, "text", builder.toString()));
	}
	
	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#getText()
	 */
	public String getText() {
		return (String)execute(DriverCommand.GET_TEXT_VALUE, ImmutableMap.of("id", id));
	}

	/**
	 * Execute flex command throught WebDriver JavaExecutor interface .
	 *
	 * @param command to execute
	 * @param parameters - command execution parameters
	 * @return Object instance
	 * @throws ElementNotVisibleException the element not visible exception
	 */
	protected Object execute(String command, Map<String, ?> parameters) throws ElementNotVisibleException {
		int timeout = 14;
		try {
			while (timeout-->0) {
				Object rId = driver.execute(
						DriverCommand.ASSERT_DISPLAY_OBJECT,
						ImmutableMap.of("id", id)); 
				if ((rId instanceof Boolean) && ((Boolean)rId))
					return driver.execute(command, parameters);
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		throw new ElementNotVisibleException(this, (TakesScreenshot)driver);
	}
	
	/**
	 * Wait while FlexPilotElement will appear.
	 *
	 * @param element to wait for
	 */
	protected void waitForObject(FlexPilotElement element) {
		int timeout = 14;
		try {
			while (timeout-->0) {
				Object rId = driver.execute(
						DriverCommand.ASSERT_DISPLAY_OBJECT,
						ImmutableMap.of("id", element.getId())); 
				if ((rId instanceof Boolean) && ((Boolean)rId))
					return;
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		throw new ElementNotVisibleException(this, (TakesScreenshot)driver);
	}

	/**
	 * Waits for list of FlexPilotElement.
	 *
	 * @param elements - array of elements to wait for 
	 */
	protected void waitForObjects(ArrayList<FlexPilotElement> elements) {
		int timeout = 14;
		Object[] r = new Object[elements.size()];
		try {
			while (timeout-->0) {
				int i=0;
				for (FlexPilotElement element: elements) {
					r[i++] = driver.execute(
							DriverCommand.ASSERT_DISPLAY_OBJECT,
							ImmutableMap.of("id", element.getId())); 
					if ((r[i] instanceof Boolean) && ((Boolean)r[i]))
						return;
				}
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		throw new ElementNotVisibleException(this, (TakesScreenshot)driver);
	}
	
	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#isDisplayed()
	 */
	public boolean isDisplayed() {
		Object r = driver.execute(
				DriverCommand.ASSERT_DISPLAY_OBJECT,
				ImmutableMap.of("id", id));
		if (r!=null && (r instanceof Boolean))
			return (Boolean)r;
		else return false;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#dragAndDrop(com.lohika.alp.flexpilot.FlexElement)
	 */
	public void dragAndDrop(FlexElement element) {
		dragAndDrop(element, new Point(8,8));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#dragAndDrop(com.lohika.alp.flexpilot.FlexElement, org.openqa.selenium.Point)
	 */
	public void dragAndDrop(FlexElement element, Point point) {
		logger.info(factory.drugAndDrop(this, 
				((DescribedElement)((WrapsElement)element).getWrappedElement()),
				point.x, point.y)
		);
		WrapsElement we = (WrapsElement)element;
		FlexElement wfe = we.getWrappedElement();
		FlexPilotElement toElement = (FlexPilotElement)wfe;
		
		// wait for object display, if not throw ElementNotVisibleException exception
		waitForObject(toElement);
		
		String optBy = "'opt"+toElement.getId().substring(1);
		
		execute(DriverCommand.DRAG_DROP_ELEM_TO_ELEM,
			ImmutableMap.of("id", id, "opt", optBy, "offsetx", point.x, "offsety", point.y));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#dragAndDrop(org.openqa.selenium.Point)
	 */
	public void dragAndDrop(Point point) {
		logger.info(factory.drugAndDrop(this, point.x, point.y)); 
		execute(DriverCommand.DRAG_DROP_TO_COORDS,
				ImmutableMap.of("id", id, "offsetx", point.getX(), "offsety", point.getY()));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#select(int)
	 */
	public void select(int index) {
		logger.info(factory.select(this, Integer.valueOf(index).toString()));
		execute(DriverCommand.SELECT,
				ImmutableMap.of("id", id, "index", index));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#select(java.lang.String)
	 */
	public void select(String text) {
		logger.info(factory.select(this, text));
		execute(DriverCommand.SELECT,
			ImmutableMap.of("id", id, "data", text));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#doubleClick()
	 */
	public void doubleClick() {
		logger.info(factory.doubleClick(this));
		execute(DriverCommand.DOUBLE_CLICK,
			ImmutableMap.of("id", id));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#getPropertyValue(java.lang.String)
	 */
	public String getPropertyValue(String propertyName) {
		return (String) execute(DriverCommand.GET_PROPERTY_VALUE,
			ImmutableMap.of("id", id, "attrName", propertyName));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#setPropertyValue(java.lang.String, java.lang.String)
	 */
	public void setPropertyValue(String propertyName, String value) {
		execute(DriverCommand.SET_PROPERTY_VALUE,
			ImmutableMap.of("id", id, "attrName", propertyName, "value", value));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#getLocation()
	 */
	public Point getLocation() {
		String strPoint = (String)execute(DriverCommand.GET_OBJECT_COORDS,
				ImmutableMap.of("id", id));
		strPoint = strPoint.substring(1, strPoint.length()-1);
		String[] coords = strPoint.split(",");
		return new Point(Integer.valueOf(coords[0]), Integer.valueOf(coords[1]));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#getSize()
	 */
	public Dimension getSize() {
		String strSize = (String)execute(DriverCommand.GET_OBJECT_SIZE,
			ImmutableMap.of("id", id));
		strSize = strSize.substring(1, strSize.length()-1);
		String[] size = strSize.split(",");
		return new Dimension(Integer.valueOf(size[0]), Integer.valueOf(size[1]));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#mouseOver()
	 */
	public void mouseOver() {
		logger.info(factory.mouseOver(this));
		execute(DriverCommand.MOUSE_OVER,
			ImmutableMap.of("id", id));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#mouseOut()
	 */
	public void mouseOut() {
		logger.info(factory.mouseOut(this));
		execute(DriverCommand.MOUSE_OUT,
			ImmutableMap.of("id", id));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#mouseUp()
	 */
	public void mouseUp() {
		logger.info(factory.mouseUp(this));
		execute(DriverCommand.MOUSE_UP,
			ImmutableMap.of("id", id));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#mouseDown()
	 */
	public void mouseDown() {
		logger.info(factory.mouseDown(this));
		execute(DriverCommand.MOUSE_DOWN,
			ImmutableMap.of("id", id));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#focusOut()
	 */
	public void focusOut() {
		logger.info(factory.focusOut(this));
		execute(DriverCommand.FOCUS_OUT,
			ImmutableMap.of("id", id));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#date(java.lang.String)
	 */
	public void date(String date) {
		logger.info(factory.date(this, date));
		execute(DriverCommand.DATE,
			ImmutableMap.of("id", id, "date", date));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#contextClick(int)
	 */
	public void contextClick(int contextMenuIndex) {
		logger.info(factory.contextClick(this, contextMenuIndex));
		execute(DriverCommand.CONTEXT_MENU_CLICK,
			ImmutableMap.of("id", id, "contextMenuIndex", contextMenuIndex));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#setSelection(int, int)
	 */
	public void setSelection(int begin, int end) {
		logger.info(factory.setSelection(this, begin, end));
		execute(DriverCommand.SET_TEXT_SELECTION,
			ImmutableMap.of("id", id, "begin", begin, "end", end));
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FlexElement#dump()
	 */
	public String dump() {
		String result = (String)execute(DriverCommand.DUMP,
				ImmutableMap.of("id", id));
		logger.info(factory.dump(this, result));
		return result;
	}

}
