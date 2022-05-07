package com.commerce.invoice.model;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: InvoiceLine
 *
 */
@Entity
@Table(name = "invoice_line")
public class InvoiceLine implements Serializable {

	// ==================================
	// = 		Attributes/fields 		=
	// ==================================

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	@Column(name = "version")
	private Integer version;

	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200)
	protected String title;

	@NotNull
	@Min(1)
	@Column(name = "unit_cost")
	protected Float unitCost;

	@NotNull
	@Min(1)
	@Column(nullable = false)
	private Integer quantity;

	private static final long serialVersionUID = 1L;

	// ==================================
	// = 			Constructors		=
	// ==================================

	public InvoiceLine() {
		super();
	}

	public InvoiceLine(String title, Float unitCost, Integer quantity) {
		this.title = title;
		this.unitCost = unitCost;
		this.quantity = quantity;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================
	
	@Override
	public String toString() {
		return "InvoiceLine [id=" + id + ", version=" + version + ", title=" + title + ", unitCost=" + unitCost
				+ ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, quantity, title, unitCost, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceLine invoiceLine = (InvoiceLine) obj;
		return Objects.equals(id, invoiceLine.id) && Objects.equals(quantity, invoiceLine.quantity)
				&& Objects.equals(title, invoiceLine.title) && Objects.equals(unitCost, invoiceLine.unitCost)
				&& Objects.equals(version, invoiceLine.version);
	}

}
