package com.commerce.invoice.model;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Invoice
 *
 */
@Entity
@NamedQuery(name = Invoice.FIND_MONTHLY, query = "SELECT i FROM Invoice i "
		+ "ORDER BY i.invoiceDate ASC")
public class Invoice implements Serializable {

	// ==================================
	// = 			Constants	 		=
	// ==================================
	
	public static final String FIND_MONTHLY = "Invoice.findMonthly";
	
	// ==================================
	// = 		Attributes/fields 		=
	// ==================================
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false, name = "id")
	private Long id;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date", updatable = false)
	private Date invoiceDate;
	private Float totalBeforeDiscount;
	
	@Column(name = "discount_rate")
	private Float discountRate;
	private Float discount;
	private Float totalAfterDiscount;
	
	@Column(name = "vat_rate")
	private Float vatRate;
	private Float vat;
	private Float totalAfterVat;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	private String telephone;
	
	@Email
	@NotNull
	@Column
	private String email;
	
	@NotNull
	@Size(min = 5, max = 50)
	@Column(length = 50, nullable = false)
	private String street1;
	private String street2;
	
	@NotNull
	@Size(min = 5, max = 50)
	@Column(length = 50, nullable = false)
	private String city;
	private String state;
	
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "zip_code", length = 10, nullable = false)
	private String zipcode;
	private String country;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<InvoiceLine> invoiceLines = new HashSet<>();
	
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

	public Float getVatRate() {
		return vatRate;
	}

	public void setVatRate(Float vatRate) {
		this.vatRate = vatRate;
	}

	public Float getVat() {
		return vat;
	}

	public void setVat(Float vat) {
		this.vat = vat;
	}

	public Float getTotalAfterVat() {
		return totalAfterVat;
	}

	public void setTotalAfterVat(Float totalAfterVat) {
		this.totalAfterVat = totalAfterVat;
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
	
	public Set<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}
	
	public void addInvoiceLine(InvoiceLine invoiceLine) {
		invoiceLines.add(invoiceLine);
	}
	
	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", version=" + version + ", invoiceDate=" + invoiceDate + ", totalBeforeDiscount="
				+ totalBeforeDiscount + ", discountRate=" + discountRate + ", discount=" + discount
				+ ", totalAfterDiscount=" + totalAfterDiscount + ", vatRate=" + vatRate + ", vat=" + vat
				+ ", totalAfterVat=" + totalAfterVat + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", telephone=" + telephone + ", email=" + email + ", street1=" + street1 + ", street2=" + street2
				+ ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country
				+ ", invoiceLines=" + invoiceLines + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, discount, discountRate, email, firstName, id, invoiceDate, invoiceLines,
				lastName, state, street1, street2, telephone, totalAfterDiscount, totalAfterVat, totalBeforeDiscount,
				vat, vatRate, version, zipcode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice invoice = (Invoice) obj;
		return Objects.equals(city, invoice.city) && Objects.equals(country, invoice.country)
				&& Objects.equals(discount, invoice.discount) && Objects.equals(discountRate, invoice.discountRate)
				&& Objects.equals(email, invoice.email) && Objects.equals(firstName, invoice.firstName)
				&& Objects.equals(id, invoice.id) && Objects.equals(invoiceDate, invoice.invoiceDate)
				&& Objects.equals(invoiceLines, invoice.invoiceLines) && Objects.equals(lastName, invoice.lastName)
				&& Objects.equals(state, invoice.state) && Objects.equals(street1, invoice.street1)
				&& Objects.equals(street2, invoice.street2) && Objects.equals(telephone, invoice.telephone)
				&& Objects.equals(totalAfterDiscount, invoice.totalAfterDiscount)
				&& Objects.equals(totalAfterVat, invoice.totalAfterVat)
				&& Objects.equals(totalBeforeDiscount, invoice.totalBeforeDiscount) && Objects.equals(vat, invoice.vat)
				&& Objects.equals(vatRate, invoice.vatRate) && Objects.equals(version, invoice.version)
				&& Objects.equals(zipcode, invoice.zipcode);
	}
	
	

}
