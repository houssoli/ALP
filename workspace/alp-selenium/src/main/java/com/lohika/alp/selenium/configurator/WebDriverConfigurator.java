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
package com.lohika.alp.selenium.configurator;

import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * The Class WebDriverConfigurator. 
 */
public class WebDriverConfigurator implements IWebDriverConfigurator {
	
	/** The instance configurator. */
	private IWebDriverConfigurator configurator;

	/**
	 * Instantiates a new web driver configurator (currently only "firefox" is supported).
	 *
	 * @param browser - represent for which browser we should create configuration 
	 */
	public WebDriverConfigurator(String browser) {
		if (browser.contains("firefox"))
			configurator = new FirefoxDriverConfigurator();
	}
	
	/* (non-Javadoc)
	 * @see com.lohika.alp.selenium.configurator.IWebDriverConfigurator#configure(org.openqa.selenium.remote.DesiredCapabilities)
	 */
	public DesiredCapabilities configure(DesiredCapabilities capabilities) {
		if (configurator!=null)
			return configurator.configure(capabilities);
		else
			return capabilities;
	}
}
