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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Test instance representation.
 */
@Entity
@Table(name = "TEST_INSTANCES")
public class TestInstance {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;

	/** The test class. */
	@ManyToOne
	@JoinColumn(name = "CLASS_ID")
	TestClass testClass;
	
	/** The test. */
	@ManyToOne
	@JoinColumn(name = "TEST_ID")
	Test test;

	/**
	 * Gets the test.
	 *
	 * @return the test
	 */
	public Test getTest() {
		return test;
	}

	/**
	 * Sets the test.
	 *
	 * @param test the new test
	 */
	public void setTest(Test test) {
		this.test = test;
	}

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
	 * Gets the test class.
	 *
	 * @return the test class
	 */
	public TestClass getTestClass() {
		return testClass;
	}

	/**
	 * Sets the test class.
	 *
	 * @param testClass the new test class
	 */
	public void setTestClass(TestClass testClass) {
		this.testClass = testClass;
	}

}
