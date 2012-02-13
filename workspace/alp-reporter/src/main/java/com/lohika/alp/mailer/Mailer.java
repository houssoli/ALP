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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.TestNG;

import com.lohika.alp.reporter.HTMLLogTransformer;
import com.lohika.alp.utils.zip.Zip;


// TODO: Auto-generated Javadoc
/**
 * Listener which send email when tests suite is completed. Recipients cand be
 * adjusted in the "reporter.properties" file or in your custom .properties
 * file. You can add recipients for specific suite in the testng xml as
 * 'recipients' parameter
 * @author Dmitry Irzhov
 *
 */

public class Mailer implements ISuiteListener {

	/** The log4j logger. */
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** The mailer configurator. */
	private MailerConfigurator mailerConfigurator;
	
	/** The session instance. */
	private Session session = null;

	/** The results filename. */
	private String resultsName = "results.xml";
	
	
	// TODO : value hardcoded in several different places !!!!
	/** The logs data dir. */
	private String logsDataDir = "logs-data";
	
	/** The instance of html file. */
	private File htmlFile;
	
	// Suite listener implementation

	/* (non-Javadoc)
	 * @see org.testng.ISuiteListener#onStart(org.testng.ISuite)
	 */
	public void onStart(ISuite suite) {
		mailerConfigurator = MailerConfigurator.getInstance();

		// getting recipients from testng suite xml as a parameter
		ArrayList<String> suiteRecipients =
				mailerConfigurator.readRecipients(suite.getParameter("recipients"));
		if (suiteRecipients != null && suiteRecipients.size()>0)
			mailerConfigurator.setSuiteRecipients(suiteRecipients); 

		if (mailerConfigurator.getAutoMail()) session = getSession();		
	}

	/* (non-Javadoc)
	 * @see org.testng.ISuiteListener#onFinish(org.testng.ISuite)
	 */
	public void onFinish(ISuite suite) {

		if (session!=null&& mailerConfigurator.getAutoMail()) {
			String html = null;
			try {
				transformXml(new File(TestNG.DEFAULT_OUTPUTDIR).getAbsolutePath());
				html = new String(Zip.readFileAsBytes(htmlFile.getAbsolutePath()), "UTF-8");
			} catch (IOException e1) {
				logger.error("Log transformation for email failure", e1);
				e1.printStackTrace();
			} catch (TransformerException e1) {
				logger.error("Log transformation for email failure", e1);
				e1.printStackTrace();
			}
			
			List<String> recipients = mailerConfigurator.getRecipients();
			List<String> suiteRecipients = mailerConfigurator.getSuiteRecipients();
			if (recipients!=null || suiteRecipients!=null) {
				Message msg = new MimeMessage(session);		        
					try {
						msg.setFrom(new InternetAddress(mailerConfigurator.getSender()));
						if (recipients != null)
							for (String recipient: recipients)
								msg.addRecipient(Message.RecipientType.TO, 
				                    new InternetAddress(recipient));
							if (suiteRecipients != null)
							for (String recipient: suiteRecipients)
								msg.addRecipient(Message.RecipientType.TO, 
				                    new InternetAddress(recipient));
							msg.setSubject("ALP Suite Mailer: "+suite.getName());
							msg.setHeader("Content-Type", "text/html; charset=utf-8");

				            // HTMLDataSource is an inner class
					        MimeBodyPart messageBodyPart = new MimeBodyPart();
					        HTMLDataSource ds = new HTMLDataSource(html);
					        messageBodyPart.setDataHandler(new DataHandler(ds));
							messageBodyPart.setHeader("Content-Type", "text/html; charset=utf-8");

					        Multipart multipart = new MimeMultipart();
					        multipart.addBodyPart(messageBodyPart);
					        
					        // Generate zip file with all logs and data
							String dir = new File(TestNG.DEFAULT_OUTPUTDIR).getAbsolutePath();
							
							Zip zip = new Zip(dir+File.separatorChar+"mail-logs.zip");
							zip.add(dir+File.separatorChar+"results.html");
							zip.add(dir+File.separatorChar+"logs-data");
							zip.add(dir+File.separatorChar+"logs-html");
							zip.close();
					        
					        File fileAttachment = new File(zip.getZipFileLocation());
					        attachFile(fileAttachment, suite.getName()+".zip", multipart);

					        msg.setContent(multipart);

					        Transport.send(msg);							
							
					} catch (AddressException e) {
						logger.warn(new MailerException("Unable to sent email to "+mailerConfigurator.getRecipients()+": "+e.getMessage()), e.getCause());
						e.printStackTrace();						
					} catch (MessagingException e) {
						logger.warn(new MailerException("Unable to sent email to "+mailerConfigurator.getRecipients()+": "+e.getMessage()), e.getCause());
						e.printStackTrace();
					} catch (IOException e) {
						logger.warn(new MailerException("Unable to sent email to "+mailerConfigurator.getRecipients()+": "+e.getMessage()), e.getCause());
						e.printStackTrace();
					}	
			}
		}
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	private Session getSession() {
		Authenticator authenticator = new Authenticator(
			mailerConfigurator.getSmtpUser(),
			mailerConfigurator.getSmtpPassword());		
		
		return Session.getInstance(mailerConfigurator.buildProperties(), authenticator);
	}
    
	/**
	 * The Class Authenticator.
	 */
	private class Authenticator extends javax.mail.Authenticator {
		
		/** The authentication. */
		private PasswordAuthentication authentication = null;

		/**
		 * Instantiates a new authenticator.
		 *
		 * @param username the username
		 * @param password the password
		 */
		public Authenticator(String username, String password) {
			authentication = new PasswordAuthentication(username, password);
		}

		/* (non-Javadoc)
		 * @see javax.mail.Authenticator#getPasswordAuthentication()
		 */
		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
	
    /*
     * Inner class to act as a JAF datasource to send HTML e-mail content
     */
    /**
     * The Class HTMLDataSource.
     */
    static class HTMLDataSource implements DataSource {
    	
	    /** The logger. */
	    private Logger logger = Logger.getLogger(this.getClass());
        
        /** The html. */
        private String html;
 
        /**
         * Instantiates a new hTML data source.
         *
         * @param htmlString the html string
         */
        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }
 
        // Return html string in an InputStream.
        // A new stream must be returned each time.
        /* (non-Javadoc)
         * @see javax.activation.DataSource#getInputStream()
         */
        public InputStream getInputStream() throws IOException {
            //if (html == null) throw new IOException("Null HTML");
        	if (html == null) {
        		logger.error(new IOException("Null HTML"));
        		return new ByteArrayInputStream("".getBytes());
        	}
            return new ByteArrayInputStream(html.getBytes("UTF-8"));
        }
 
        /* (non-Javadoc)
         * @see javax.activation.DataSource#getOutputStream()
         */
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }
 
        /* (non-Javadoc)
         * @see javax.activation.DataSource#getContentType()
         */
        public String getContentType() {
            return "text/html";
        }
 
        /* (non-Javadoc)
         * @see javax.activation.DataSource#getName()
         */
        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }
    
    /**
     * Attach file.
     *
     * @param file the file
     * @param newFileName the new file name
     * @param multipart the multipart
     * @throws MessagingException the messaging exception
     */
    private void attachFile(File file, String newFileName, Multipart multipart) throws MessagingException {
    	MimeBodyPart messageBodyPart = new MimeBodyPart();
        DataSource fds = new FileDataSource
          (file);
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setFileName(newFileName);
        multipart.addBodyPart(messageBodyPart);
    }
    
    /**
     * Transform xml.
     *
     * @param outputDirectory the output directory
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws TransformerException the transformer exception
     */
    private void transformXml(String outputDirectory) throws IOException, TransformerException {

		// HTML data output directory
		File logsData = new File(outputDirectory, logsDataDir);
		// XSL file (extracted to logs-data)
		File xsl = new File(logsData, "email.xsl");
		// XML file (created xml log file)
		File xml = new File(outputDirectory, resultsName);
		// XML file (created html log file)
		htmlFile = new File(outputDirectory, "email.html");

		HTMLLogTransformer transformer = new HTMLLogTransformer(xsl, null);
		transformer.transform(xml, htmlFile);

		logger.info("Suite email: " + htmlFile.toURI().toURL());	
    }

}
