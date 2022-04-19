package com.commerce.invoice.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ResourceProducer {

	// ==================================
	// = 			Produces	 		=
	// ==================================
	
	@Produces
	@PersistenceContext(unitName = "")
	private EntityManager em;
	
	@Produces
	public Logger produceLogger(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
