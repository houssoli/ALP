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

package com.lohika.alp.utils.validator;

import java.util.Arrays;
import java.util.List;

/**
 * The Class EmailAddressException.
 */
public class EmailAddressException extends Exception {

	/** The list of mail addresses. */
	private List<String> emails;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new email address exception.
	 *
	 * @param emails the emails
	 */
	public EmailAddressException(List<String> emails) {
		this.emails = emails;
	}
	
	/**
	 * Instantiates a new email address exception.
	 *
	 * @param email the email
	 */
	public EmailAddressException(String email) {
		this.emails = Arrays.asList(email);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		String message = "Invalid email adresses: ";
		if (emails!=null)
		for (String email: emails) {
			message.concat(email);
			message.concat(", ");
		}
		return message;
	}

}
