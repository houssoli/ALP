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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Test method type.
 */
@Entity
@Table(name = "METHOD_TYPE")
public class MethodType {

	/** The primary key. */
	@Id
	private MethodTypePK primaryKey = new MethodTypePK();

	/**
	 * The Class MethodTypePK.
	 */
	@Embeddable
	private class MethodTypePK implements Serializable {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The test method. */
		@ManyToOne
		@JoinColumn(name = "TEST_METHOD_ID", referencedColumnName = "ID")
		private TestMethod testMethod;

		/** The method type. */
		@Column(name = "TYPE")
		@Enumerated(EnumType.STRING)
		private EMethodType methodType;

		/**
		 * Gets the test method.
		 *
		 * @return the test method
		 */
		public TestMethod getTestMethod() {
			return testMethod;
		}

		/**
		 * Sets the test method.
		 *
		 * @param testMethod the new test method
		 */
		public void setTestMethod(TestMethod testMethod) {
			this.testMethod = testMethod;
		}

		/**
		 * Gets the method type.
		 *
		 * @return the method type
		 */
		public com.lohika.alp.reporter.db.model.EMethodType getMethodType() {
			return methodType;
		}

		/**
		 * Sets the method type.
		 *
		 * @param methodType the new method type
		 */
		public void setMethodType(
				com.lohika.alp.reporter.db.model.EMethodType methodType) {
			this.methodType = methodType;
		}
	}

	/**
	 * Gets the test method.
	 *
	 * @return the test method
	 */
	public TestMethod getTestMethod() {
		return primaryKey.getTestMethod();
	}

	/**
	 * Sets the test method.
	 *
	 * @param testMethod the new test method
	 */
	public void setTestMethod(TestMethod testMethod) {
		this.primaryKey.setTestMethod(testMethod);
	}

	/**
	 * Gets the method type.
	 *
	 * @return the method type
	 */
	public com.lohika.alp.reporter.db.model.EMethodType getMethodType() {
		return primaryKey.getMethodType();
	}

	/**
	 * Sets the method type.
	 *
	 * @param methodType the new method type
	 */
	public void setMethodType(
			com.lohika.alp.reporter.db.model.EMethodType methodType) {
		this.primaryKey.setMethodType(methodType);
	}
	
}
