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
package com.lohika.alp.selenium.jscatcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.lohika.alp.configuration.ReporterPropertiesReader;
import com.lohika.alp.mailer.MailerConfigurator;

/**
 * <p>Java class of ALP configuration
 * <p>Read all parameter from reporter.properties file. These parameters are uses
 * for ALP at all.
 * <p>jsErrorAutolog allow to logs js errors automatically
 * hosts is an array which contains each host separated by comma 
 * which is uses in our projects
 * @author Dmitry Irzhov
 */
public class JsErrorCatcherConfiguration extends ReporterPropertiesReader {	
	
	/** The log4j logger. */
	private Logger logger = Logger.getLogger(MailerConfigurator.class);

	/** The instance. */
	private static JsErrorCatcherConfiguration instance = null;
	
	/** If JS Error logging enabled  */
	private Boolean jsErrorAutolog = false;
	
	/** List of domains which will have special security restricts within Mozilla browser. */
	private String[] allowDomains;	
		
	/**
	 * Getter for allowDomains.
	 *
	 * @return array of Strings each one is allowed domain
	 */
	public String[] getAllowDomains() {
		return allowDomains;
	}

	/**
	 * Setter for allowDomains.
	 *
	 * @param allowDomains new array that will replace this.allowDomains
	 */
	public void setAllowDomains(String[] allowDomains) {
		this.allowDomains = allowDomains;
	}

	/**
	 * Getter for jsErrorAutolog 
	 *
	 * @return the js error autolog
	 */
	public Boolean getJsErrorAutolog() {
		return jsErrorAutolog;
	}

	/**
	 * Setter for jsErrorAutolog.
	 *
	 * @param jsErrorAutolog the new value for this.jsErrorAutolog
	 */
	public void setJsErrorAutolog(Boolean jsErrorAutolog) {
		this.jsErrorAutolog = jsErrorAutolog;
	}	
	
	/**
	 * Gets the single instance of JsErrorCatcherConfiguration.
	 *
	 * @return single instance of JsErrorCatcherConfiguration
	 */
	public static JsErrorCatcherConfiguration getInstance() {
		if (instance==null)
			instance = new JsErrorCatcherConfiguration();
		return instance;
	}	
	
	
	/**
	 * Instantiates a new js error catcher configuration.
	 */
	public JsErrorCatcherConfiguration() {		
		
		Boolean b = Boolean.parseBoolean(getProperty("jserrcatcher.autolog"));
        if (b!=null) jsErrorAutolog=b;
        
        String hostStr = getProperty("jserrcatcher.allowDomains");
        
        if (hostStr!=null) {
	        allowDomains = hostStr.split(",");
	        for (String host : allowDomains) {	        	
				try {					
					URL url;
					url = new URL(host);
					url.openConnection();
				} catch (MalformedURLException e) {
					jsErrorAutolog = false;
		        	logger.warn(new JsErrorCatcherException("Wrong URL in 'jserrcatcher.allowDomains' parameter. Auto logging of js errors was diactivated."));					
				} catch (IOException e) {
					jsErrorAutolog = false;
		            logger.warn(new JsErrorCatcherException("Unable connect to '"+host+"'"), e.getCause());
				}
	        	
	        }
        } else {
        	jsErrorAutolog = false;
        	logger.warn(new JsErrorCatcherException("unable to read 'jserrcatcher.allowDomains' parameter in the 'reporter.properties' file. Auto logging of js errors was diactivated."));
        }
	}	
}
