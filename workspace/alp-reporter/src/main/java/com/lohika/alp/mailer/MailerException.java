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



/**
 * The Class MailerException.
 *
 * @author Dmitry Irzhov
 */
public class MailerException extends Exception{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7687550514677295162L;

	/**
	 * Instantiates a new mailer exception.
	 */
	public MailerException() {
	}
	
	/**
	 * Instantiates a new mailer exception.
	 *
	 * @param msg the exception message
	 */
	public MailerException(String msg) {
		super(msg);
	}
}
