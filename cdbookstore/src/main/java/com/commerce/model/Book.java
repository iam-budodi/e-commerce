package com.commerce.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Book
 *
 */
@Entity
@DiscriminatorValue("B")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book extends Item implements Serializable {

	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@NotNull
	@Size
	@Column(length = 15)
	private String isbn;
	
	@Min(1)
	@XmlElement(name = "number-of-page")
	@Column(name = "nb_of_pages")
	private Integer nbOfPage;
	
	@Temporal(TemporalType.DATE)
	@XmlElement(name = "publication-date")
	@Column(name = "publication_date")
	private Date publicationDate;
	
	@Enumerated
	private Language language;
	
	@ManyToOne
	private Publisher publisher;
	
	@ManyToOne
	private Category category;
	
	@OneToMany
	private Set<Author> authors = new HashSet<>();
	
	private static final long serialVersionUID = 1L;

	// =================================
	// =         Constructors          =
	// =================================
	
	public Book() {
		super();
	}  

	public Book(String isbn, Integer nbOfPage, Date publicationDate, Language language, Publisher publisher,
			Category category, Set<Author> authors ) {
		super();
		this.isbn = isbn;
		this.nbOfPage = nbOfPage;
		this.publicationDate = publicationDate;
		this.language = language;
		this.publisher = publisher;
		this.category = category;
		this.authors = authors;
	} 
	
	// =================================
	// = 		Getters and Setters    =
	// =================================
	
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}   
	
	public Integer getNbOfPage() {
		return this.nbOfPage;
	}

	public void setNbOfPage(Integer nbOfPage) {
		this.nbOfPage = nbOfPage;
	}   
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}   
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}   
	public Publisher getPublisher() {
		return this.publisher;
	}

	public void setPublisher(final Publisher publisher) {
		this.publisher = publisher;
	}   
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}   
	public Set<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(final Set<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthors(Author author) {
		if (authors == null) {
			authors = new HashSet<>();
		}
		authors.add(author);
	}
	
	// ========================================
	// = 	Method hash, equals, toString     =
	// ========================================

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", nbOfPage=" + nbOfPage + ", publicationDate=" + publicationDate + ", language="
				+ language + ", publisher=" + publisher + ", category=" + category + ", authors=" + authors + ", id="
				+ id + ", version=" + version + ", title=" + title + ", description=" + description + ", unitCost="
				+ unitCost + ", rank=" + rank + ", smallImageURL=" + smallImageURL + ", mediumImageURL="
				+ mediumImageURL + "]";
	}
   
}
