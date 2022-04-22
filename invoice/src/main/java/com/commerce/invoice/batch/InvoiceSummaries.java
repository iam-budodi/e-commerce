package com.commerce.invoice.batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "invoiceSummary")
@XmlType(propOrder = { "year", "invoiceSummaries" })
@XmlSeeAlso(InvoiceSummary.class)
public class InvoiceSummaries extends ArrayList<InvoiceSummary> {

	// ==================================
	// = 			Attributes 			=
	// ==================================
	private Integer year;
	private static final long serialVersionUID = 1L;
	
	// ==================================
	// = 		Constructors 			=
	// ==================================
	
	public InvoiceSummaries() {
		super();
	}
	
	public InvoiceSummaries(Collection<? extends InvoiceSummary> c) {
		super(c);
	}

	@XmlElement(name = "invoiceSummaries")
	public List<InvoiceSummary> getInvoiceSummaries() {
		return this;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
