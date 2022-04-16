package com.commerce.model;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Invoice
 *
 */
@Entity

public class Invoice implements Serializable {

	// ==================================
	// = 		Attributes/fields 		=
	// ==================================
	
	@Id
	private Long id;
	private Integer version;
	private Date invoiceDate;
	private Float totalBeforeDiscount;
	private Float discountRate;
	private Float discount;
	private Float totalAfterDiscount;
	private String firstName;
	private String lastName;
	private String email;
	private String telephone;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zipcode;
	private String country;
	private static final long serialVersionUID = 1L;

	// ==================================
	// = 			Constructors		=
	// ==================================
	
	public Invoice() {
		super();
	}

	public Invoice(String firstName, String lastName, String street1, String city, String zipcode, String country,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.street1 = street1;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
	}

	public Invoice(String firstName, String lastName, String email, String street1, String city, String zipcode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.street1 = street1;
		this.city = city;
		this.zipcode = zipcode;
	}

	public Invoice(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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

	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Float getTotalBeforeDiscount() {
		return this.totalBeforeDiscount;
	}

	public void setTotalBeforeDiscount(Float totalBeforeDiscount) {
		this.totalBeforeDiscount = totalBeforeDiscount;
	}

	public Float getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(Float discountRate) {
		this.discountRate = discountRate;
	}

	public Float getDiscount() {
		return this.discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getTotalAfterDiscount() {
		return this.totalAfterDiscount;
	}

	public void setTotalAfterDiscount(Float totalAfterDiscount) {
		this.totalAfterDiscount = totalAfterDiscount;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

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

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================
	
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", version=" + version + ", invoiceDate=" + invoiceDate + ", totalBeforeDiscount="
				+ totalBeforeDiscount + ", discountRate=" + discountRate + ", discount=" + discount
				+ ", totalAfterDiscount=" + totalAfterDiscount + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", telephone=" + telephone + ", street1=" + street1 + ", street2=" + street2
				+ ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, discount, discountRate, email, firstName, id, invoiceDate, lastName, state,
				street1, street2, telephone, totalAfterDiscount, totalBeforeDiscount, version, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(discount, other.discount) && Objects.equals(discountRate, other.discountRate)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(invoiceDate, other.invoiceDate)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(state, other.state)
				&& Objects.equals(street1, other.street1) && Objects.equals(street2, other.street2)
				&& Objects.equals(telephone, other.telephone)
				&& Objects.equals(totalAfterDiscount, other.totalAfterDiscount)
				&& Objects.equals(totalBeforeDiscount, other.totalBeforeDiscount)
				&& Objects.equals(version, other.version) && Objects.equals(zipcode, other.zipcode);
	}
	
	

}
