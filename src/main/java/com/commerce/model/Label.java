package com.commerce.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity implementation class for Entity: Label
 *
 */
@Entity

public class Label implements Serializable {

	// =================================
	// = 	   Attributes/fields       =
	// =================================

	@Id
	@XmlTransient
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	@XmlTransient
	@Column(name = "version")
	private Integer version;
	

	@NotNull
	@Size(max = 30)
	@XmlAttribute
	@Column(length = 30)
	private String name;
	
	private static final long serialVersionUID = 1L;

	public Label() {
		super();
	}   
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// ========================================
	// = Method hash, equals, toString        =
	// ========================================
	
	@Override
	public String toString() {
		return "Label [id=" + id + ", version=" + version + ", name=" + name + "]";
	}
   
}
