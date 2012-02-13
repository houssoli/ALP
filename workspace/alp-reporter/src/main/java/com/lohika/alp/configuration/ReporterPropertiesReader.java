package com.lohika.alp.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.lohika.alp.mailer.MailerConfigurator;



/**
 * The Class ReporterPropertiesReader.
 *
 * @author "Anton Smorodsky"
 * Main class for all configurations read from reporter.properties
 */

public class ReporterPropertiesReader {
	
	/** The log4j logger . */
	private Logger logger = Logger.getLogger(MailerConfigurator.class);	
	
	/** The path to configuration file . */
	private final String configFilePath = "reporter.properties";
	
	/** The Java properties. */
	private Properties properties;
	
	/** If properties load already happens . */
	private boolean isLoaded = false;
	
	/**
	 * Gets the config file path.
	 *
	 * @return the config file path
	 */
	public String getConfigFilePath() {
		return configFilePath;
	}	 
		
	/**
	 * Instantiates a new reporter properties reader.
	 */
	public ReporterPropertiesReader(){
		properties = new Properties();
		try {
			InputStream in = getClass().getClassLoader().getResourceAsStream(configFilePath);
			if(in!=null)
			{
				properties.load(in);
				isLoaded = true;
			}
			else throw new FileNotFoundException("Can't find reporter.properties file");
		} catch (IOException e) {
			logger.error(e.getMessage());			
		}
	}

	/**
	 * Checks if is loaded.
	 *
	 * @return true, if is loaded
	 */
	public boolean isLoaded() { return isLoaded; }
	
	/**
	 * Gets the property.
	 *
	 * @param prop_name the name of Java property
	 * @return the property's value in String
	 */
	public String getProperty(String prop_name)
	{
		if(isLoaded) return properties.getProperty(prop_name);
		else return null;
	}
	
	/**
	 * Gets the string property . If there is no such property return deaulValue.
	 *
	 * @param prop_name the name of Java property
	 * @param defaultValue the value to return when there is no such property
	 * @return the property's value in String
	 */
	public String getStringProperty(String prop_name,String defaultValue)
	{
		if(isLoaded) 
		{
			String tmp = properties.getProperty(prop_name);
			if(tmp!=null) return tmp;
			else return defaultValue;
		}
		else return defaultValue;
	}
	
	/**
	* Gets the boolean property . If there is no such property return deaulValue.
	 *
	 * @param prop_name the name of Java property
	 * @param defaultValue the value to return when there is no such property
	 * @return the property's value in boolean
	 */
	public boolean getBooleanProperty(String prop_name,boolean defaultValue)
	{
		if(isLoaded) 
		{
			String tmp = properties.getProperty(prop_name);
			if(tmp!=null) return Boolean.parseBoolean(tmp);
			else return defaultValue;
		}
		else return defaultValue;
	}
	
	/**
	* Gets theint property . If there is no such property return deaulValue.
	 *
	 * @param prop_name the name of Java property
	 * @param defaultValue the value to return when there is no such property
	 * @return the property's value in int
	 */
	public int getIntProperty(String prop_name,int defaultValue)
	{
		if(isLoaded) 
		{
			String tmp = properties.getProperty(prop_name);
			if(tmp!=null) return Integer.parseInt(tmp);
			else return defaultValue;
		}
		else return defaultValue;
	}
}
