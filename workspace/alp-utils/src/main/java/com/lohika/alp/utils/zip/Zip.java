package com.lohika.alp.utils.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;


/**
 * This class is uses for archive files and folder to zip.
 *
 * @author Dmitry Irzhov
 */

public class Zip {
	
	/** The log4j logger. */
	private Logger logger = Logger.getLogger(this.getClass());
	
	/** The Constant BUFFER.  */
	static final int BUFFER = 2048;	
	
	/** The destination file. */
	private FileOutputStream dest;
	
	/** The checksum. */
	private CheckedOutputStream checksum;
	
	/** The ZipOutputStream. */
	private ZipOutputStream out;
	
	/** The zip file location. */
	private String zipFileLocation;
	
	/**
	 * Gets the zip file location.
	 *
	 * @return the zip file location
	 */
	public String getZipFileLocation() {
		return zipFileLocation;
	}

	/**
	 * Instantiates a new zip.
	 *
	 * @param in_zipFileLocation the in_zip file location
	 */
	public Zip(String in_zipFileLocation) {
		
			zipFileLocation = in_zipFileLocation;
			
			try {
				dest = new FileOutputStream(zipFileLocation);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			checksum = new CheckedOutputStream(dest, new Adler32());
			out = new ZipOutputStream(new 
					BufferedOutputStream(checksum));
		
	}
	
	/**
	 * Adds file to Zip.
	 *
	 * @param filePath the file path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void add(String filePath) throws IOException {
		
		byte data[] = new byte[BUFFER];

		// get a list of files from current directory
		File f = new File(filePath);
		if (f.isDirectory()) {
			File files[] = f.listFiles();
			for (int i=0; i<files.length; i++) {
				add(files[i].getAbsolutePath());
			}
		} else {
			logger.debug("Adding: "+filePath);
			FileInputStream fi = new 
					FileInputStream(filePath);
			BufferedInputStream origin = new 
					BufferedInputStream(fi, BUFFER);
			ZipEntry entry = new ZipEntry(filePath);
			out.putNextEntry(entry);
			int count;
			while((count = origin.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			origin.close();
		}
		
		logger.debug("checksum: "+checksum.getChecksum().getValue());
	}

	/**
	 * Close output stream.
	 */
	public void close() {
		if (out != null)
			try {
				out.close();
			} catch (IOException e){}
	}

	/**
	 * Read file as bytes.
	 *
	 * @param filePath the file path
	 * @return the byte[]
	 */
	public static byte[] readFileAsBytes(String filePath) {
	    byte[] buffer = new byte[(int) new File(filePath).length()];
	    BufferedInputStream f = null;	    
        try {
			f = new BufferedInputStream(new FileInputStream(filePath));
			f.read(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	    
	    return buffer;
	}
}
