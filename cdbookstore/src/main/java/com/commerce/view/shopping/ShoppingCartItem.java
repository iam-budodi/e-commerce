package com.commerce.view.shopping;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.commerce.model.Item;

public class ShoppingCartItem {

	// ========================================
	// = 			Attributes		 		  =
	// ========================================
	
	@NotNull
	private Item item;
	
	@Min(1)
	@NotNull
	private Integer quantity;
	
	// ========================================
	// = 			Constructors		 	  =
	// ========================================
	
	public ShoppingCartItem(Item item, Integer quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}	
	
	// ========================================
	// = 			Business Methods	 	  =
	// ========================================
	
	public Float getSubTotal() {
		return item.getUnitCost() * quantity;
	}
	
	// ========================================
	// = 			Constructors		 	  =
	// ========================================
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	// ========================================
	// = 	Methods Hash, equals, toString	  =
	// ========================================

	@Override
	public int hashCode() {
		int result = item.hashCode();
		result = 31 * result + quantity.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartItem cartItem = (ShoppingCartItem) obj;
		return Objects.equals(item, cartItem.item) && Objects.equals(quantity, cartItem.quantity);
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [item=" + item + ", quantity=" + quantity + "]";
	}
	
}
