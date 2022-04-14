package com.commerce.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.commerce.model.Book;

@XmlRootElement
@XmlSeeAlso(Book.class)
public class Books extends ArrayList<Book> {

	private static final long serialVersionUID = 1L;

	// =================================
	// =         Constructors          =
	// =================================
	
	public Books() {
		super();
	}

	public Books(Collection<? extends Book> c) {
		super(c);
	}
	
	// =================================
	// = 		Getters and Setters    =
	// =================================
	
	@XmlElement(name = "book")
	public List<Book> getBooks() {
		return this;
	}

}
