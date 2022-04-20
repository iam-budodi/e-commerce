package com.commerce.invoice.service;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.commerce.invoice.model.Invoice;
import com.commerce.invoice.model.InvoiceLine;
import com.commerce.invoice.util.Discount;
import com.commerce.invoice.util.RateProducer;
import com.commerce.invoice.util.ResourceProducer;
import com.commerce.invoice.util.Vat;

@RunWith(Arquillian.class)
public class InvoiceServiceTest {

	// ==================================
	// = 		Injection Point	 		=
	// ==================================
	
	@Inject
	private InvoiceService invoiceService;

	// ==================================
	// = 		Deployment Methods 		=
	// ==================================
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(Invoice.class)
				.addClass(InvoiceLine.class)
				.addClass(InvoiceService.class)
				.addClass(ResourceProducer.class)
				.addClass(RateProducer.class)
				.addClass(Vat.class)
				.addClass(Discount.class)
				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	// ==================================
	// = 			Test Methods 		=
	// ==================================
	
	@Test
	public void should_be_deployed() {
		Assert.assertNotNull(invoiceService);
	}

}
