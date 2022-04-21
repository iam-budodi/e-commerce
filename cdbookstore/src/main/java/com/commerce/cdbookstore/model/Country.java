package com.commerce.cdbookstore.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Country
 *
 */

@Entity
@Cacheable
@NamedQuery(name = Country.FIND_ALL, query = "SELECT c FROM Country c ORDER BY c.name")
public class Country implements Serializable {
	
	// =================================
	// =        Constants              =
	// =================================

	public static final String FIND_ALL = "Country.findAll";
	
	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	@Column(name = "version")
	private Integer version;
	
	@NotNull
	@Size(min = 2, max = 2)
	@Column(name = "iso_code", nullable = false, length = 2)
	private String isoCode;
	
	@NotNull
	@Size(min = 2, max = 80)
	@Column(length = 80, nullable = false)
	private String name;
	
	@NotNull
	@Size(min = 2, max = 80)
	@Column(name = "printable_name", length = 80, nullable = false)
	private String printableName;
	
	@NotNull
	@Size(min = 3, max = 3)
	@Column(length = 3)
	private String iso3;
	
	@NotNull
	@Size(min = 3, max = 3)
	@Column(length = 3)
	private String numCode;
	
	private static final long serialVersionUID = 1L;

	// =================================
	// = 		Getters and Setters    =
	// =================================
	
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getIsoCode() {
		return this.isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getPrintableName() {
		return this.printableName;
	}

	public void setPrintableName(String printableName) {
		this.printableName = printableName;
	}   
	public String getIso3() {
		return this.iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public String getNumCode() {
		return numCode;
	}
	public void setNumCode(String numCode) {
		this.numCode = numCode;
	}

	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", version=" + version + ", isoCode=" + isoCode + ", name=" + name
				+ ", printableName=" + printableName + ", iso3=" + iso3 + ", numCode=" + numCode + "]";
	}
   
}
