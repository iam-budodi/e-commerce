package com.commerce.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Address implements Serializable {

	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@NotNull
	@Size(min = 5, max = 50)
	private String street1;
	private String street2;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String city;
	private String state;
	
	@NotNull
	@Size(min = 1, max = 10)
	private String zipCode;
	
	private static final long serialVersionUID = 1L;

	// =================================
	// = 		Getters and Setters    =
	// =================================
	
	public String getStreet1() {
		return this.street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}   
	public String getStreet2() {
		return this.street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}   
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}   
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================
	
	@Override
	public String toString() {
		return "Address [street1=" + street1 + ", street2=" + street2 + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + "]";
	}
   
}
