package com.lohika.alp.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.lohika.alp.mailer.MailerConfigurator;

/**
 * 
 * @author "Anton Smorodsky"
 * Main class for all configurations read from reporter.properties
 */

public class ReporterPropertiesReader {
	
	private Logger logger = Logger.getLogger(MailerConfigurator.class);	
	
	private final String configFilePath = "reporter.properties";
	
	private Properties properties;
	
	private boolean isLoaded = false;
	
	public String getConfigFilePath() {
		return configFilePath;
	}	 
		
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

	public boolean isLoaded() { return isLoaded; }
	
	public String getProperty(String prop_name)
	{
		if(isLoaded) return properties.getProperty(prop_name);
		else return null;
	}
	
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
