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
package com.lohika.alp.log4j.xml;

import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlType;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import com.lohika.alp.log4j.CloneableLayout;



/**
 * The Class XMLLayout.
 */
public class XMLLayout extends Layout implements CloneableLayout {

	/** The buffer. */
	private Writer buffer;

	/** The xml writer. */
	private XMLStreamWriter xmlWriter;

	/** The marshaller. */
	private Marshaller marshaller;

	// TODO make concatenation of default values and values provided by user
	/** The context path. */
	private String contextPath = "com.lohika.alp.log.schema:com.lohika.alp.log.elements.schema";

	/* (non-Javadoc)
	 * @see org.apache.log4j.Layout#activateOptions()
	 */
	@Override
	public void activateOptions() {
		buffer = new StringWriter();

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		try {
			xmlWriter = factory.createXMLStreamWriter(buffer);
			JAXBContext jc = JAXBContext.newInstance(contextPath);

			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		} catch (Exception e) {
			LogLog.error("XMLLayout error", e);
		}

	}

	/**
	 * Gets the context path.
	 *
	 * @return the context path
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * Sets the context path.
	 *
	 * @param contextPath the new context path
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.Layout#getContentType()
	 */
	@Override
	public String getContentType() {
		return "text/xml";
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.Layout#getHeader()
	 */
	@Override
	public String getHeader() {
		try {
			xmlWriter.writeStartDocument();
			xmlWriter.writeCharacters("\n");
			xmlWriter.writeStartElement("log");

			// TODO allow namespaces to be configured
			xmlWriter.setDefaultNamespace("http://alp.lohika.com/log/schema");
			xmlWriter.writeNamespace("", "http://alp.lohika.com/log/schema");
			xmlWriter.writeNamespace("elm",
					"http://alp.lohika.com/log/elements/schema");

			xmlWriter.writeCharacters("\n");
			xmlWriter.flush();
		} catch (XMLStreamException e) {
			LogLog.error("XMLLayout error", e);
		}

		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.Layout#getFooter()
	 */
	@Override
	public String getFooter() {
		try {
			xmlWriter.writeEndDocument();
			xmlWriter.flush();
		} catch (XMLStreamException e) {
			LogLog.error("XMLLayout error", e);
		}

		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.Layout#format(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public String format(LoggingEvent event) {
		try {
			xmlWriter.writeStartElement("event");
			xmlWriter.writeAttribute("level", event.getLevel() + "");
			xmlWriter.writeAttribute("thread", event.getThreadName());
			xmlWriter.writeAttribute("logger", event.getLoggerName());
			xmlWriter.writeAttribute("timestamp", event.getTimeStamp() + "");

			Object message = event.getMessage();

			if (isJAXBElement(message)) {
				try {
					marshaller.marshal(message, xmlWriter);
				} catch (JAXBException e) {
					LogLog.error("XMLLayout error", e);
				}

			} else {
				xmlWriter.writeCharacters(message.toString() + "\r\n");
			}
			
			// Handle throwable
			String[] s = event.getThrowableStrRep();
			StringBuffer sb = new StringBuffer();
			if (s != null) {
				for (int i = 0; i < s.length; i++) {
					sb.append(s[i]);
					sb.append("\r\n");
				}
				
				xmlWriter.writeCharacters(sb.toString());
			}

			xmlWriter.writeEndElement();
			xmlWriter.writeCharacters("\r\n");
		} catch (XMLStreamException e) {
			LogLog.error("XMLLayout error", e);
		}

		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.Layout#ignoresThrowable()
	 */
	@Override
	public boolean ignoresThrowable() {
		return false;
	}

	/**
	 * Checks if is jAXB element.
	 *
	 * @param object the object to check 
	 * @return true, if is jAXB element
	 */
	private boolean isJAXBElement(Object object) {
		if (object == null)
			return false;
			
		XmlType xmlType = object.getClass().getAnnotation(XmlType.class);

		if (xmlType == null)
			return false;

		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		XMLLayout clone = (XMLLayout) super.clone();

		clone.activateOptions();
		return clone;
	}

	/* (non-Javadoc)
	 * @see com.lohika.alp.log4j.CloneableLayout#cloneLayout()
	 */
	@Override
	public Layout cloneLayout() throws CloneNotSupportedException {
		return (Layout) clone();
	}

}
