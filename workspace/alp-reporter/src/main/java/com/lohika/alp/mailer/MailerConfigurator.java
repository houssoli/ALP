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

package com.lohika.alp.mailer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.lohika.alp.configuration.ReporterPropertiesReader;
import com.lohika.alp.utils.validator.EmailAddressException;
import com.lohika.alp.utils.validator.EmailValidator;



/**
 * Java class for configuring mailer. Recipients can be described in the 
 * .properties file or in the testng xml file. Default configuration file name is 
 * 'reporter.properties'
 * @author Dmitry Irzhov
 *
 */
public class MailerConfigurator extends ReporterPropertiesReader {
	
	/** The log4j logger. */
	private Logger logger = Logger.getLogger(MailerConfigurator.class);
	
	/** The instance. */
	private static MailerConfigurator instance = null;
	
	/** The boolean for auto mail. */
	private Boolean autoMail = false;
	
	/** If auth needed. */
	private Boolean authNeed = false;
	
	/** The smtp host. */
	private String smtpHost;
	
	/** The smtp port. */
	private Integer smtpPort = 25;
	
	/** The smtp ssl. */
	private Boolean smtpSsl = false;
	
	/** The sender. */
	private String sender;
	
	/** The smtp user. */
	private String smtpUser;
	
	/** The smtp password. */
	private String smtpPassword;
	
	/** The list of recipients. */
	private List<String> recipients;
	
	/** The suite recipients. */
	private List<String> suiteRecipients;
		

	/**
	 * Gets the auto mail.
	 *
	 * @return if auto mail turned on
	 */
	public Boolean getAutoMail() {
		if(autoMail == null) autoMail = false;
		return autoMail;
	}

	/**
	 * Sets the auto mail.
	 *
	 * @param autoMail the new auto mail
	 */
	public void setAutoMail(Boolean autoMail) {
		this.autoMail = autoMail;
	}

	/**
	 * Gets the smtp host.
	 *
	 * @return the smtp host
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * Sets the smtp host.
	 *
	 * @param smtpHost the new smtp host
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * Gets the smtp port.
	 *
	 * @return the smtp port
	 */
	public String getSmtpPort() {
		return Integer.toString(smtpPort);
	}

	/**
	 * Gets the smtp ssl.
	 *
	 * @return the smtp ssl
	 */
	public Boolean getSmtpSsl() {
		return smtpSsl;
	}

	/**
	 * Sets the smtp ssl.
	 *
	 * @param smtpSsl the new smtp ssl
	 */
	public void setSmtpSsl(Boolean smtpSsl) {
		this.smtpSsl = smtpSsl;
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender the new sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Sets the smtp port.
	 *
	 * @param smtpPort the new smtp port
	 */
	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * Gets the smtp user.
	 *
	 * @return the smtp user
	 */
	public String getSmtpUser() {
		return smtpUser;
	}

	/**
	 * Sets the smtp user.
	 *
	 * @param smtpUser the new smtp user
	 */
	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	/**
	 * Gets the smtp password.
	 *
	 * @return the smtp password
	 */
	public String getSmtpPassword() {
		return smtpPassword;
	}

	/**
	 * Sets the smtp password.
	 *
	 * @param smtpPassword the new smtp password
	 */
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	/**
	 * Gets the recipients.
	 *
	 * @return the list of recipients
	 */
	public List<String> getRecipients() {
		return recipients;
	}

	/**
	 * Sets the recipients.
	 *
	 * @param list the new recipients
	 */
	public void setRecipients(List<String> list) {
		this.recipients = list;
	}

	/**
	 * Gets the suite recipients.
	 *
	 * @return the list suite recipients
	 */
	public List<String> getSuiteRecipients() {
		return suiteRecipients;
	}

	/**
	 * Sets the suite recipients.
	 *
	 * @param suiteRecipients the new suite recipients
	 */
	public void setSuiteRecipients(List<String> suiteRecipients) {
		this.suiteRecipients = suiteRecipients;
	}

	/**
	 * Instantiates a new mailer configurator.
	 */
	public MailerConfigurator () {			
			
			autoMail = getBooleanProperty("mail.autoMail",false);
			
			if(!autoMail) return;
				
			smtpHost = getProperty("mail.smtp.host");
			
			System.out.println(smtpHost);
			
			if (smtpHost==null) {
	    		setAutoMail(false);
	    		logger.warn(new MailerException("'mail.smtp.host' is not defined"));
	    		return;
	    		
			}
			
			sender = getProperty("mail.smtp.submitter");
			
			if (sender!=null) {
				if (!validateSender(sender)) {
		    		setAutoMail(false);
		    		logger.warn(new EmailAddressException(Arrays.asList(sender)));
		    		return;
				}
			} else {
	    		setAutoMail(false);
	    		logger.warn(new MailerException("'mail.smtp.submitter' should be defined. Automail is turn off."));
	    		return;
			}
			
			smtpUser = getProperty("mail.smtp.username");
			
			if(smtpUser != null) authNeed = true;
			
			
			smtpPassword = getProperty("mail.smtp.password");			
			
			String str = getProperty("mail.recipients");
			
			if(str == null)
			{
				setAutoMail(false);
	    		logger.warn(new MailerException("'mail.recipients' should be defined. Automail is turn off."));
	    		return;
			}
			
			setRecipients(readRecipients(str));
			
			smtpPort = getIntProperty("mail.smtp.port",25);
			
			smtpSsl = getBooleanProperty("mail.smtp.ssl",false);
			
	}	
	
	/**
	 * Builds the properties.
	 *
	 * @return the properties
	 */
	public Properties buildProperties() {
		Properties to_ret = new Properties();
		
		if(authNeed)
		{
			to_ret.setProperty("mail.smtp.submitter",getSmtpUser());
			to_ret.setProperty("mail.smtp.auth","true");
		}
		
		if(smtpSsl)
		{
			to_ret.setProperty("mail.smtp.ssl","true");
			// TODO : Make SSL Factory customizable by user
			to_ret.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		
		to_ret.setProperty("mail.smtp.host", smtpHost);
		to_ret.setProperty("mail.smtp.port", getSmtpPort());		
		
		return to_ret;
	}
	
		
	/**
	 * Gets the single instance of MailerConfigurator.
	 *
	 * @return single instance of MailerConfigurator
	 */
	public static MailerConfigurator getInstance() {
		if (instance==null)
			instance = new MailerConfigurator();
		return instance;
	}
	
	/**
	 * Read recipients.
	 *
	 * @param recipientsStr the recipients str
	 * @return the array list
	 */
	public ArrayList<String> readRecipients(String recipientsStr) {
        if (recipientsStr==null)
        	return null;
		try {
        	String[] r = recipientsStr.split(",");
        	EmailValidator emailValidator = new EmailValidator();
	        ArrayList<String> failedRecipients = new ArrayList<String>();
	        ArrayList<String> recipients = new ArrayList<String>();
	        for (String recipient : r) {
	        	if (!emailValidator.validate(recipient))
	        		failedRecipients.add(recipient);
	        	else
	        		recipients.add(recipient);
	        }
	        if (failedRecipients.size()>0)
        		throw new EmailAddressException(failedRecipients);
	        if (recipients.size()>0)
	        	return recipients;
	        else
	        	return null;
	    } catch (EmailAddressException e) {
	    	logger.warn(e.getMessage());
	    	return null;
		}
	}
	
	/**
	 * Validate sender.
	 *
	 * @param sender the sender
	 * @return true, if successful
	 */
	private boolean validateSender(String sender) {
    	EmailValidator emailValidator = new EmailValidator(); 
    	return emailValidator.validate(sender);
	}
}
