package com.commerce.view.shopping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.commerce.model.Address;
import com.commerce.model.Country;
import com.commerce.model.CreditCard;
import com.commerce.model.CreditCardType;
import com.commerce.view.account.AccountBean;

@Named
@SessionScoped
public class ShoppingCartBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// ========================================
	// = 			Injection Point 		  =
	// ========================================
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private AccountBean accountBean;
	
	@Inject
	private transient JMSContext jmsContext;
	
	@Resource(lookup = "jms/queue/invoiceQueue")
	private Queue queue;
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Logger logger;
	
	// ========================================
	// = 			Attributes		 		  =
	// ========================================

	private List<ShoppingCartItem> cartItems = new ArrayList<>();
	private Address address = new Address();
	private String country = new String();
	private CreditCard creditCard = new CreditCard();
	
	// ========================================
	// = 			Business Methods		  =
	// ========================================
	
//	TODO: add and remove item on shopping cart and produce invoice.
	
	// ========================================
	// = 			Getters and Setters		  =
	// ========================================
	
	public List<ShoppingCartItem> getCartItems() {
		return cartItems;
	}

	/*
	 * public void setCartItems(List<ShoppingCartItem> cartItems) { this.cartItems =
	 * cartItems; }
	 */

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	public CreditCardType[] getCreditCardTypes() {
		return CreditCardType.values();
	}
	
	public String[] getCountries() {
		TypedQuery<Country> query = em.createNamedQuery(Country.FIND_ALL, Country.class);
		List<Country> countries = query.getResultList();
		String[] result = new String[countries.size()];
		for (int i = 0; i < countries.size(); i++) {
			result[i] = countries.get(i).getName();
		}
		return result;
	}
}
