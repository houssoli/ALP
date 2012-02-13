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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Test method representation.
 */
@Entity
@Table(name = "TEST_METHODS")
public class TestMethod {

	/** The test method id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	Long id;

	/** The test method name. */
	@Column(name = "NAME")
	String name;
	
	/** The test method description. */
	@Column(name = "DESCRIPTION")
	String description;

	/** The test instance. */
	@ManyToOne
	@JoinColumn(name = "TEST_INSTANCE_ID")
	TestInstance testInstance;

	/** The start date. */
	@Column(name = "START_DATE")
	Long startDate;
	
	/** The formatted start date. */
	@Transient
	String formattedStartDate;

	/** The finish date. */
	@Column(name = "FINISH_DATE")
	Long finishDate;

	/** The test method status. */
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	EMethodStatus status;
	
	/** The id of test method exception (if any). */
	@OneToOne
	@JoinColumn(name = "EXCEPTION_ID")
	TestMethodException exception;
	
	/** The groups to which current test method assigned . */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "METHOD_GROUP", joinColumns = { @JoinColumn(name = "TEST_METHOD_ID") }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
	Set<Group> groups = new HashSet<Group>();
	
	/** If test method has log. */
	@Column(name = "HAS_LOG")
	Boolean hasLog = false;
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Long getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the finish date.
	 *
	 * @return the finish date
	 */
	public Long getFinishDate() {
		return finishDate;
	}

	/**
	 * Sets the finish date.
	 *
	 * @param finishDate the new finish date
	 */
	public void setFinishDate(Long finishDate) {
		this.finishDate = finishDate;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public EMethodStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(EMethodStatus status) {
		this.status = status;
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
	 * Gets the test method name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the test instance.
	 *
	 * @return the test instance
	 */
	public TestInstance getTestInstance() {
		return testInstance;
	}

	/**
	 * Sets the test instance.
	 *
	 * @param testInstance the new test instance
	 */
	public void setTestInstance(TestInstance testInstance) {
		this.testInstance = testInstance;
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public TestMethodException getException() {
		return exception;
	}

	/**
	 * Sets the exception.
	 *
	 * @param exception the new exception
	 */
	public void setException(TestMethodException exception) {
		this.exception = exception;
	}

	/**
	 * Gets the groups.
	 *
	 * @return the groups
	 */
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * Sets the groups.
	 *
	 * @param groups the new groups
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/**
	 * Gets the checks for log.
	 *
	 * @return the checks for log
	 */
	public Boolean getHasLog() {
		return hasLog;
	}

	/**
	 * Sets the checks for log.
	 *
	 * @param hasLog the new checks for log
	 */
	public void setHasLog(Boolean hasLog) {
		this.hasLog = hasLog;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @return duration in seconds
	 */
	public Long getDuration() {
		return (finishDate - startDate) / 1000;
	}

	// TODO Convert to Date object. Formated string ignores user's time zone settings
	/**
	 * Gets the formatted start date.
	 *
	 * @return the formatted start date
	 */
	public String getFormattedStartDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(startDate));
	}

	/**
	 * Sets the formatted start date.
	 *
	 * @param formattedStartDate the new formatted start date
	 */
	public void setFormattedStartDate(String formattedStartDate) {
		this.formattedStartDate = formattedStartDate;
	}
}
