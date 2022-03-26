package com.commerce.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity implementation class for Entity: Artist
 *
 */

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public class Artist implements Serializable {

	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlTransient
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;
	
	@Version
	@XmlTransient
	@Column(name = "version")
	protected Integer version;
	
	@NotNull
	@Size(min = 2, max = 50)
	@XmlElement(name = "first-name")
	@Column(name = "first_name", nullable = false, length = 50)
	protected String firstName;

	@NotNull
	@Size(min = 2, max = 50)
	@XmlElement(name = "last-name")
	@Column(name = "last_name", nullable = false, length = 50)
	protected String lastName;
	
	@Size(max = 5000)
	@Column(length = 5000)
	protected String bio;
	
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_birth")
	protected Date dateOfBirth;
	
	@Transient
	protected Integer age;
	
	protected static final long serialVersionUID = 1L;

	// =======================================
	// =    Life cycle Callback methods      =
	// =======================================
	
	@PostLoad
	@PostPersist
	@PostUpdate
	private void calculateAge() {
		if (dateOfBirth == null) {
			age = null;
			return;
		}
		
		Calendar birth = new GregorianCalendar();
		birth.setTime(dateOfBirth);
		
		Calendar now = new GregorianCalendar();
		now.setTime(new Date());
		
		int adjust = 0;
		if ((now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR)) < 0) {
			adjust = -1;
		}
		
		age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust;
	}
	

	// =================================
	// =         Constructors          =
	// =================================
	
	public Artist() {
		super();
	}   
	
	// =================================
	// = 		Getters and Setters    =
	// =================================
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}   
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}   
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================

	@Override
	public String toString() {
		return "Artist [id=" + id + ", version=" + version + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", bio=" + bio + ", dateOfBirth=" + dateOfBirth + ", age=" + age + "]";
	}
   
}
