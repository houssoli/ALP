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
package com.lohika.alp.reporter.fe.logs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Repository;

/**
 * File system log storage as a simplest solution.
 * 
 */
@Repository
public class LogStorageFS implements LogStorage {


	@Override
	public InputStream getLog(long testMethodId, String fileName,String filePath)
			throws IOException {

		File logdir = new File(filePath + "/" + testMethodId);
		
		File logfile = new File(logdir, fileName);

		return new FileInputStream(logfile);
	}

	@Override
	public void saveLog(long testMethodId, String fileName, InputStream is,String filePath)
			throws IOException {

		File testMethodDir = new File(filePath + "/" + testMethodId);

		if (!testMethodDir.exists())
			testMethodDir.mkdirs();

		File logfile = new File(testMethodDir, fileName);
		FileOutputStream os = new FileOutputStream(logfile);

		int c;
		while ((c = is.read()) != -1)
			os.write(c);

		os.flush();
		os.close();
	}		

	@Override
	public File getLogFile(long testMethodId, String fileName,String filePath) throws IOException {
				File logdir = new File(filePath + "/" + testMethodId);
				return new File(logdir, fileName);		
	}

}
