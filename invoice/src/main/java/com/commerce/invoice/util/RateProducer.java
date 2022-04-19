package com.commerce.invoice.util;

import javax.enterprise.inject.Produces;

public class RateProducer {

	// ==================================
	// = 			Constants	 		=
	// ==================================
	
	@Produces
	@Vat
	private Float vatRate = 5.5F;
	
	@Produces
	@Discount
	private Float discountRate = 12.5F;
}
