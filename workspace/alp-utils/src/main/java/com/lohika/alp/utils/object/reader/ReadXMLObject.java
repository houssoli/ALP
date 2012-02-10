package com.lohika.alp.utils.object.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import com.thoughtworks.xstream.XStream;

/**
 * The Class ReadXMLObject.
 */
public class ReadXMLObject {

	/** The xstream used to Unmarshal objects. */
	private XStream	xstream;

	/**
	 * Instantiates a new read xml object.
	 */
	public ReadXMLObject() {
		xstream = new XStream();
	}

	/**
	 * Unmarshal the object.
	 *
	 * @param XML file 
	 * @return the Object
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Object getObject(String XMLfile) throws IOException {

		URL url = this.getClass().getClassLoader().getResource(XMLfile);
		File file = new File(url.getPath());
		char[] buffer = null;
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		buffer = new char[(int) file.length()];

		int i = 0;
		int c = bufferedReader.read();

		while (c != -1) {
			buffer[i++] = (char) c;
			c = bufferedReader.read();
		}

		bufferedReader.close();

		return xstream.fromXML(new String(buffer));
	}

}
