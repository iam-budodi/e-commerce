package com.commerce.cdbookstore.model;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue("I")
@NamedQueries({ 
	@NamedQuery(name = Item.FIND_TOP_RATED, 
			query = "SELECT i FROM Item i WHERE i.id IN :ids"),
	@NamedQuery(name = Item.SEARCH, 
			query = "SELECT i FROM Item i WHERE UPPER(i.title) LIKE :keyword "
					+ "OR UPPER(i.description) LIKE :keyword ORDER BY i.title") 
	})
@XmlAccessorType(XmlAccessType.FIELD)
public class Item implements Serializable {
	
	// =================================
	// =        Constants              =
	// =================================

	public static final String FIND_TOP_RATED = "Item.findTopRated";
	public static final String SEARCH = "Item.search";

	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;
	
	@Version
	@XmlTransient
	@Column(name = "version")
	protected Integer version;
	
	@NotNull
	@Size(min = 1, max = 200)
	@Column(length = 200)
	protected String title;
	
	@Size(min = 1, max = 10000)
	@Column(length = 10000)
	protected String description;
	
	@Min(1)
	@XmlElement(name = "unit-cost")
	@Column(name = "unit_cost")
	protected Float unitCost;
	
	protected Integer rank;
	
	@XmlElement(name = "small-image-url")
	@Column(name = "small_image_url")
	protected String smallImageURL;

	@XmlElement(name = "medium-image-url")
	@Column(name = "medium_image_url")
	protected String mediumImageURL;
	
	protected static final long serialVersionUID = 1L;

	// =================================
	// =         Constructors          =
	// =================================
	
	/*
	 * public Item() { super(); }
	 */ 

	// =================================
	// =        Getters and Setters    =
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
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Float getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}   
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}   
	public String getSmallImageURL() {
		return this.smallImageURL;
	}

	public void setSmallImageURL(String smallImageURL) {
		this.smallImageURL = smallImageURL;
	}   
	public String getMediumImageURL() {
		return this.mediumImageURL;
	}

	public void setMediumImageURL(String mediumImageURL) {
		this.mediumImageURL = mediumImageURL;
	}

	// ========================================
	// = Method hash, equals, toString        =
	// ========================================	

	@Override
	public String toString() {
		return "Item [id=" + id + ", version=" + version + ", title=" + title + ", description=" + description
				+ ", unitCost=" + unitCost + ", rank=" + rank + ", smallImageURL=" + smallImageURL + ", mediumImageURL="
				+ mediumImageURL + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, mediumImageURL, rank, smallImageURL, title, unitCost, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item item = (Item) obj;
		return Objects.equals(description, item.description) && Objects.equals(id, item.id)
				&& Objects.equals(mediumImageURL, item.mediumImageURL) && Objects.equals(rank, item.rank)
				&& Objects.equals(smallImageURL, item.smallImageURL) && Objects.equals(title, item.title)
				&& Objects.equals(unitCost, item.unitCost) && Objects.equals(version, item.version);
	}
   
}
