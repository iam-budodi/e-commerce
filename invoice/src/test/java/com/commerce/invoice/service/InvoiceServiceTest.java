package com.commerce.invoice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
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
	@InSequence(1)
	public void should_be_deployed() {
		Assert.assertNotNull(invoiceService);
	}
	
	@Test
	@InSequence(2)
	public void should_crud() {
		// Gets all the objects
		int initialSize = invoiceService.listAll().size();
		assertEquals(initialSize, 
				invoiceService.listAll(0, 10).size());
		
		// Creates an object
		Invoice invoice = new Invoice(
				"First name", "Last name", "email@email", "street1", "city", "zipcode", "country");
		InvoiceLine line1 = new InvoiceLine("item1", 2.25F, 1);
		InvoiceLine line2 = new InvoiceLine("item1", 12.5F, 3);
		
		invoice.addInvoiceLine(line1);
		invoice.addInvoiceLine(line2);
		
		// Insert the object into the database 
		invoice = invoiceService.persist(invoice);
		assertNotNull(invoice.getId());
		
		// Find the object in the database and check it the right one
		invoice = invoiceService.findById(invoice.getId());
		assertEquals(2, invoice.getInvoiceLines().size());
		assertEquals("First name", invoice.getFirstName());
		
		// Updates the object
		invoice.setFirstName("A new first name");
		invoice = invoiceService.merge(invoice);
		
		// Finds the object from the database and checks it is updated
		invoice = invoiceService.findById(invoice.getId());
		assertEquals("A new first name", invoice.getFirstName());
		
		// Delete the object from database and checks it is not there anymore
		invoiceService.remove(invoice);
		assertEquals(initialSize, invoiceService.listAll().size());
	}
	
	@Test
	@InSequence(3)
	public void should_create_an_invoice() {
		// Creates an object
		Invoice invoice = new Invoice("First name", "Last name", "email@email", "street1", "city", "zipcode",
				"country");
		InvoiceLine line1 = new InvoiceLine("item1", 25.25F, 1);
		InvoiceLine line2 = new InvoiceLine("item1", 33.75F, 3);

		invoice.addInvoiceLine(line1);
		invoice.addInvoiceLine(line2);

		// Insert the object into the database
		invoice = invoiceService.persist(invoice);
		assertNotNull(invoice.getId());

		// Find the object in the database and check it the right one
		invoice = invoiceService.findById(invoice.getId());
		assertEquals(2, invoice.getInvoiceLines().size());
		assertEquals("First name", invoice.getFirstName());
		
        assertEquals(Float.valueOf(5.5F), invoice.getVatRate());
        assertEquals(Float.valueOf(12.5F), invoice.getDiscountRate());

        assertEquals(Float.valueOf(126.5F), invoice.getTotalBeforeDiscount());
        assertEquals(Float.valueOf(15.81F), invoice.getDiscount());
        assertEquals(Float.valueOf(110.69F), invoice.getTotalAfterDiscount());
        assertEquals(Float.valueOf(6.09F), invoice.getVat());
        assertEquals(Float.valueOf(104.60F), invoice.getTotalAfterVat());
	}
}
