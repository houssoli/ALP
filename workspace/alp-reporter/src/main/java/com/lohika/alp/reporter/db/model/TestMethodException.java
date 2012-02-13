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
package com.lohika.alp.reporter.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Exception representation.
 */
@Entity
@Table(name = "EXCEPTIONS")
public class TestMethodException {

	/** The Exception ID. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;

	/** The Exception class name. */
	@Column(name = "CLASS_NAME")
	String className;
	
	/** The exception message  . */
	@Column(name = "MESSAGE", columnDefinition="LONGTEXT")
	String message;
	
	/** The full stacktrace. */
	@Column(name = "FULL_STACKTRACE", columnDefinition="LONGTEXT")
	String fullStacktrace;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the class name.
	 *
	 * @return the class name
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Sets the class name.
	 *
	 * @param className the new class name
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the full stacktrace.
	 *
	 * @return the full stacktrace
	 */
	public String getFullStacktrace() {
		return fullStacktrace;
	}

	/**
	 * Sets the full stacktrace.
	 *
	 * @param fullStacktrace the new full stacktrace
	 */
	public void setFullStacktrace(String fullStacktrace) {
		this.fullStacktrace = fullStacktrace;
	}
}
