package com.commerce.view.shopping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
import com.commerce.model.Item;
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
	public String addItemToCart() {
		Item item = em.find(Item.class, getParamId("itemId"));
		
		boolean itemFound = false;
		for (ShoppingCartItem cartItem : cartItems) {
			// if item already exists in the shopping cart we just change quantity
			if (cartItem.getItem().equals(item)) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				itemFound = true;
			}
		}
		if (!itemFound) {
			// otherwise, it is added to the shopping cart
			cartItems.add(new ShoppingCartItem(item, 1));
		}
		
		facesContext.addMessage(null, 
				new FacesMessage(
						FacesMessage.SEVERITY_INFO, 
						item.getTitle() + " added to the shopping cart", 
						"You can add more Items"));
		
		return "/shopping/viewItem.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public String removeItemFromCart() {
		Item item = em.find(Item.class, getParamId("itemId"));
		
		for (ShoppingCartItem cartItem : cartItems) {
			if (cartItem.getItem().equals(item)) {
				cartItems.remove(cartItem);
				return null;
			}
		}
		
		return null;
	}
	
	public String updateQuantity() {
		return null;
	}
	
	public String confirmation() {
		// creating invoice
		return null;
	}

	protected Long getParamId(String param) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> map = context
				.getExternalContext()
				.getRequestParameterMap();
		return Long.valueOf(map.get(param));
	}
	
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
