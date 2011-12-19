package com.lohika.alp.utils.object.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import com.thoughtworks.xstream.XStream;

public class ReadXMLObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private XStream	xstream;

	public ReadXMLObject() {
		xstream = new XStream();
	}

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
