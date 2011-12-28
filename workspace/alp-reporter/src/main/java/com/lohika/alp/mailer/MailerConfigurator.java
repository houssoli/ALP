//Copyright 2011 Lohika .  This file is part of ALP.
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
	
	private Logger logger = Logger.getLogger(MailerConfigurator.class);
	
	private static MailerConfigurator instance = null;
	
	private Boolean autoMail = false;
	private Boolean authNeed = false;
	private String smtpHost;
	private Integer smtpPort = 25;
	private Boolean smtpSsl = false;
	private String sender;
	private String smtpUser;
	private String smtpPassword;
	private List<String> recipients;
	private List<String> suiteRecipients;
		

	public Boolean getAutoMail() {
		if(autoMail == null) autoMail = false;
		return autoMail;
	}

	public void setAutoMail(Boolean autoMail) {
		this.autoMail = autoMail;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return Integer.toString(smtpPort);
	}

	public Boolean getSmtpSsl() {
		return smtpSsl;
	}

	public void setSmtpSsl(Boolean smtpSsl) {
		this.smtpSsl = smtpSsl;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> list) {
		this.recipients = list;
	}

	public List<String> getSuiteRecipients() {
		return suiteRecipients;
	}

	public void setSuiteRecipients(List<String> suiteRecipients) {
		this.suiteRecipients = suiteRecipients;
	}

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
	
		
	public static MailerConfigurator getInstance() {
		if (instance==null)
			instance = new MailerConfigurator();
		return instance;
	}
	
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
	
	private boolean validateSender(String sender) {
    	EmailValidator emailValidator = new EmailValidator(); 
    	return emailValidator.validate(sender);
	}
}
