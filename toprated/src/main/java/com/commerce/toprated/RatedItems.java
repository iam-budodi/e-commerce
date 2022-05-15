package com.commerce.toprated;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(RatedItem.class)
public class RatedItems extends ArrayList<RatedItem> {

    // ======================================
    // =            Constructors            =
    // ======================================

	private static final long serialVersionUID = 1L;

	public RatedItems() {
        super();
    }

    public RatedItems(Collection<? extends RatedItem> c) {
        super(c);
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    @XmlElement(name = "topItem")
    public List<RatedItem> getTopItems() {
        return this;
    }
}