package com.commerce.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * Entity implementation class for Entity: Musician
 *
 */
@Entity

public class Musician extends Artist implements Serializable {

	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@Column(name = "preferred_instrument")
	private String preferredInstrument;
	
	@ManyToMany
	private Set<CD> cds = new HashSet<>();
	
	private static final long serialVersionUID = 1L;

	public Musician() {
		super();
	}   

	// =================================
	// =        Getters and Setters    =
	// =================================
	
	public String getPreferredInstrument() {
		return this.preferredInstrument;
	}

	public void setPreferredInstrument(String preferredInstrument) {
		this.preferredInstrument = preferredInstrument;
	}   
	public Set<CD> getCds() {
		return this.cds;
	}

	public void setCds(Set<CD> cds) {
		this.cds = cds;
	}

	// ========================================
	// = Method hash, equals, toString        =
	// ========================================
	
	@Override
	public String toString() {
		return "Musician [preferredInstrument=" + preferredInstrument + ", cds=" + cds + ", id=" + id + ", version="
				+ version + ", firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + ", dateOfBirth="
				+ dateOfBirth + ", age=" + age + "]";
	}
   
}
