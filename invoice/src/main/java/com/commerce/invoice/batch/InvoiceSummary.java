package com.commerce.invoice.batch;

public class InvoiceSummary {

	// ==================================
	// = 			Attributes 			=
	// ==================================
	
	private Integer month;
	private Integer numberOfInvoices;
	private Float total;
	
	// ==================================
	// = 		Constructors 			=
	// ==================================
	
	public InvoiceSummary() {
		super();
	}

	public InvoiceSummary(Integer month) {
		super();
		this.month = month;
	}
	
	// ==================================
	// = 		Getters and Setters		=
	// ==================================
	
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getNumberOfInvoices() {
		return numberOfInvoices;
	}

	public void setNumberOfInvoices(Integer numberOfInvoices) {
		this.numberOfInvoices = numberOfInvoices;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
}
