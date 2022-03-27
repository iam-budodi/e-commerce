package com.commerce.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: CD
 *
 */
@Entity
@XmlRootElement
@DiscriminatorValue("C")
public class CD extends Item implements Serializable {

	// =================================
	// = 	   Attributes/fields       =
	// =================================
	
	@Column(name = "nb_of_discs")
	private Integer nbOfDiscs;
	
	@ManyToOne
	private Label label;
	
	@ManyToMany
	private Set<Musician> musicians = new HashSet<>();
	
	@ManyToOne
	private Genre genre;
	
	private static final long serialVersionUID = 1L;

	public CD() {
		super();
	}  	

	// =================================
	// =        Getters and Setters    =
	// =================================
	
	public Integer getNbOfDiscs() {
		return this.nbOfDiscs;
	}

	public void setNbOfDiscs(Integer nbOfDiscs) {
		this.nbOfDiscs = nbOfDiscs;
	}   
	public Label getLabel() {
		return this.label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}   
	public Set<Musician> getMusicians() {
		return this.musicians;
	}

	public void setMusicians(Set<Musician> musicians) {
		this.musicians = musicians;
	}   
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	// ========================================
	// = Method hash, equals, toString        =
	// ========================================
	
	@Override
	public String toString() {
		return "CD [nbOfDiscs=" + nbOfDiscs + ", label=" + label + ", musicians=" + musicians + ", genre=" + genre
				+ ", id=" + id + ", version=" + version + ", title=" + title + ", description=" + description
				+ ", unitCost=" + unitCost + ", rank=" + rank + ", smallImageURL=" + smallImageURL + ", mediumImageURL="
				+ mediumImageURL + "]";
	}

}
