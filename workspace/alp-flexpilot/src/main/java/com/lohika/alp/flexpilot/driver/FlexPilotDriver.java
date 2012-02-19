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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.lohika.alp.flexpilot.By;
import com.lohika.alp.flexpilot.FindsById;
import com.lohika.alp.flexpilot.FindsByChain;
import com.lohika.alp.flexpilot.FindsByLinkText;
import com.lohika.alp.flexpilot.FindsByName;
import com.lohika.alp.flexpilot.FlexElement;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactory;
import com.lohika.alp.flexpilot.pagefactory.FlexPilotFactoryJAXB;


/**
 * The Class FlexPilotDriver.
 */
public class FlexPilotDriver implements FlexDriver,FindsById, FindsByName, FindsByLinkText, FindsByChain, TakesScreenshot {
	
	/** The log4j logger . */
	private Logger log = Logger.getLogger(getClass());

	/** The WebDriver instance. */
	private WebDriver driver;
	
	/** The root flash object name. */
	private String flashObjectName;
	
	/** The factory. */
	protected final FlexPilotFactory factory;
	
	/**
	 * Instantiates a new flex pilot driver.
	 *
	 * @param driver the driver
	 * @param flashObjectName the flash object name
	 */
	public FlexPilotDriver(WebDriver driver, String flashObjectName) {
		this.driver = driver;
		this.flashObjectName = flashObjectName;
		this.factory = new FlexPilotFactoryJAXB();
	}
	
	/**
	 * Execute command for flex using JavaScriptExecutor .
	 *
	 * @param command for execution
	 * @param args - list of arguments for command execution 
	 * @return the instance of Object
	 */
	protected Object doCommand(String command, Object... args) {
		String params = arrayToString(args,",");
		log.debug("COMMAND:"+command+" {"+params+"}");
		Object res;
		try {
			res = ((JavascriptExecutor) driver).executeScript("return document.getElementsByName('" +
				flashObjectName + "')[0]." +
				command +
				"(" +
				params +
				")");
		} catch (WebDriverException e) {
			return "Unable to locate element "+params;
		}
		return res;
	}

	/**
	 * Execute command .
	 *
	 * @param command for execution
	 * @param parameters - list of parameters 
	 * @return the instance Object
	 */
	protected Object execute(String command, Map<String, ?> parameters) {
		ArrayList<Object> args = new ArrayList<Object>();
		
		for(Map.Entry<String, ?> e : parameters.entrySet()) {
			StringBuilder item;
            item = new StringBuilder();

            if ("id".equals(e.getKey()) || "opt".equals(e.getKey()))
				item.append(e.getValue().toString());
			else {
				item.append("'");
				item.append(e.getKey().toString());
				item.append("':'");
				item.append(e.getValue());
				item.append("'");
			}
			args.add(item.toString());
		}
		
		return doCommand(command, args.toArray());
	}

	/**
	 * Converts Array of Objects to a string.
	 *
	 * @param a the array of Objects
	 * @param separator - char used to separate Objects within result String
	 * @return the resulting String
	 */
	public static String arrayToString(Object[] a, String separator) {
	    StringBuffer result = new StringBuffer();
	    if (a.length > 0) {
		    result.append('{');
	        result.append(a[0]);
	        for (int i=1; i<a.length; i++) {
	            result.append(separator);
	            result.append(a[i]);
	        }
	        result.append('}');
	    }
	    return result.toString();
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.SearchContext#findElement(com.lohika.alp.flexpilot.By)
	 */
	public FlexElement findElement(By by) {
		return by.findElement(this);
	}

    /**
     * This method is not currently implemented.
     *
     * @param by The locating mechanism to use
     * @return null
     */
	public List<FlexElement> findElements(By by) {
		return by.findElements(this);
	}
	
	/**
	 * Gets the id.
	 *
	 * @param by the by
	 * @param using the using
	 * @return the id
	 */
	// TODO : misunderstanding function name . getId is name for id getter !
	private String getId(String by, String using) {
		return "'"+by+"':'"+using+"'";
	}

	/**
	 * Find element.
	 *
	 * @param by the by
	 * @param using the using
	 * @return the flex element
	 */
	protected FlexElement findElement(String by, String using) {
		if (using == null) {
			throw new IllegalArgumentException("Cannot find elements when the selector is null.");
		}
		FlexPilotElement el = new FlexPilotElement(this, factory);
		el.setId(getId(by, using));
		return el;
	}
	
	/**
	 * TODO: implement searching elements *.
	 *
	 * @param by the by
	 * @param using the using
	 * @return the list
	 */
	protected List<FlexElement> findElements(String by, String using) {
		return null;
	}
	  
	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsById#findElementById(java.lang.String)
	 */
	public FlexElement findElementById(String using) {
		return findElement("id", using);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsById#findElementsById(java.lang.String)
	 */
	public List<FlexElement> findElementsById(String using) {
		return findElements("id", using);
	}
	  
	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsByChain#findElementByChain(java.lang.String)
	 */
	public FlexElement findElementByChain(String using) {
		return findElement("chain", using);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsByChain#findElementsByChain(java.lang.String)
	 */
	public List<FlexElement> findElementsByChain(String using) {
		return findElements("chain", using);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsByName#findElementByName(java.lang.String)
	 */
	public FlexElement findElementByName(String using) {
		return findElement("name", using);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsByName#findElementsByName(java.lang.String)
	 */
	public List<FlexElement> findElementsByName(String using) {
		return findElements("name", using);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsByLinkText#findElementByLinkText(java.lang.String)
	 */
	public FlexElement findElementByLinkText(String using) {
		return findElement("link", using);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.FindsByLinkText#findElementsByLinkText(java.lang.String)
	 */
	public List<FlexElement> findElementsByLinkText(String using) {
		return findElements("link", using);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.driver.FlexDriver#getVersion()
	 */
	public String getVersion() {
		return (String)doCommand(DriverCommand.GET_VERSION);
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.flexpilot.driver.FlexDriver#isFlexLoaded()
	 */
	public boolean isFlexLoaded() {
		log.debug("COMMAND: isFlexLoaded()");
		Object result = ((JavascriptExecutor) driver).executeScript("return document.getElementsByName('" +
				flashObjectName + "')[0]." + DriverCommand.CLICK);
		if (result==null)
			return false;
		else if (result instanceof String)
			return true;
		else return false;
	}

	/* (non-Javadoc)
	 * @see org.openqa.selenium.TakesScreenshot#getScreenshotAs(org.openqa.selenium.OutputType)
	 */
	public <X> X getScreenshotAs(OutputType<X> target)
			throws WebDriverException {
		return ((TakesScreenshot) driver).getScreenshotAs(target);
	}

}
