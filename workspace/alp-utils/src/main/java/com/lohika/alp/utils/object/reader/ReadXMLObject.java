package com.lohika.alp.utils.object.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
		
		File file;
		
		if(url==null) file = new File(XMLfile); 
		else file = new File(url.getPath());
		if(file.exists())
		{
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
		else throw new IOException("Can not find file - "+ XMLfile);
	}
	
	
	/**
	 * Method that take folder with xmls and unmarshal them with getObject method
	 * 
	 * @param FolderWithXMLs
	 * @return array of Objects
	 * @throws IOException
	 */
	public Object [] getObjectsFromFolder(String FolderWithXMLs) throws IOException
	{
		File dataFolder = new File(FolderWithXMLs);
		
		ArrayList<Object> buffer = new  ArrayList<Object>();
		
		if(dataFolder.isDirectory()) 
		{
			// xmlFilter filters only files that ends with "*.xml"
			FilenameFilter xmlFilter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					String lowercaseName = name.toLowerCase();
					if (lowercaseName.endsWith(".xml")) {
						return true;
					} else {
						return false;
					}
				}
			}; 
			File [] listOfFiles = dataFolder.listFiles(xmlFilter);
			
			for (File xmlFile:listOfFiles)			
				buffer.add(getObject(xmlFile.getCanonicalPath()));
			
			return buffer.toArray();
		}
		else throw new IOException("'"+FolderWithXMLs+"' - does not exists or it is not a directory");		
	}

}
