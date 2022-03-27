package com.commerce.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Author
 *
 */
@Entity

public class Author extends Artist implements Serializable {

	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@Column(name = "preferred_language")
	@Convert(converter = LanguageConverter.class)
	private Language preferredLanguage;
	
	private static final long serialVersionUID = 1L;

	// =================================
	// =         Constructors          =
	// =================================

	public Author() {
		super();
	}   
	
	public Author(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// =================================
	// = 		Getters and Setters    =
	// =================================
	
	public Language getPreferredLanguage() {
		return this.preferredLanguage;
	}

	public void setPreferredLanguage(Language preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}	

	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================

	@Override
	public String toString() {
		return "Author [preferredLanguage=" + preferredLanguage + ", id=" + id + ", version=" + version + ", firstName="
				+ firstName + ", lastName=" + lastName + ", bio=" + bio + ", dateOfBirth=" + dateOfBirth + ", age="
				+ age + "]";
	}
   
}
