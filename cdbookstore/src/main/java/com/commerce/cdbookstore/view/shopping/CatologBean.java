package com.commerce.cdbookstore.view.shopping;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.commerce.cdbookstore.model.Item;
import com.commerce.cdbookstore.util.Auditable;

@Named
@RequestScoped
@Transactional
public class CatologBean {
	
	// =================================
	// =        Injection Points       =
	// =================================
	
	/*
	 * @Inject private FacesContext facesContext;
	 */
	
	@Inject
	private EntityManager em;
		
	// =================================
	// =        Constants              =
	// =================================
	
	private String keyword;
	private List<Item> items;
	private Item item;
	private Long itemId;
	
	// =================================
	// =        Business Methods	   =
	// =================================
	
	@Auditable
	public String doSearch() {
		TypedQuery<Item> typedQuery = em.createNamedQuery(Item.SEARCH, Item.class);
		typedQuery.setParameter("keyword", "%" + keyword.toUpperCase() + "%");
		items = typedQuery.getResultList();
		return null;
	}
	
	public String doViewItemById() {
		item = em.find(Item.class, itemId);
		return null;
	}
	
	// =================================
	// =        Getters and Setters    =
	// =================================
		
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
}
