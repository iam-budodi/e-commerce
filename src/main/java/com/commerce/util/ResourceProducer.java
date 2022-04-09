package com.commerce.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ResourceProducer {

	// =================================
	// = 	   Producers		       =
	// =================================
	
	@Produces
	@PersistenceContext(unitName = "applicationCDBookStorePU")
	private EntityManager em;

}
