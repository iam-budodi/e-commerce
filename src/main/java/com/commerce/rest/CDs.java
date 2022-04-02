package com.commerce.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.commerce.model.CD;

@XmlRootElement
@XmlSeeAlso(CD.class)
public class CDs extends ArrayList<CD> {

	private static final long serialVersionUID = 1L;

	// =================================
	// =         Constructors          =
	// =================================
	
	public CDs() {
		super();
	}

	public CDs(Collection<? extends CD> c) {
		super(c);
	}

	// =================================
	// = 		Getters and Setters    =
	// =================================
	
	@XmlElement(name = "cd")
	public List<CD> getCDs() {
		return this;
	}

}
