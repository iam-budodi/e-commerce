package com.commerce.invoice.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.commerce.invoice.model.Invoice;

@MessageDriven(mappedName = "invoiceQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/invoiceQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"), })
public class invoiceMDB implements MessageListener {

	// ==================================
	// = 		Injection Point	 		=
	// ==================================
	
	@Inject
	private InvoiceService invoiceService;
	
	@Inject
	private Logger logger;
	
	// ==================================
	// = 		Injection Point	 		=
	// ==================================
	
	@Override
	public void onMessage(Message message) {
		try {
			logger.info("Message received " + message);
			Invoice invoice = message.getBody(Invoice.class);
			logger.info("Invoice received " + invoice);
			invoiceService.persist(invoice);
			logger.info("Invoice persisted " + invoice);
		} catch (JMSException e) {
			logger.log(Level.SEVERE, "Cannot persist invoice", e);
		}

	}

}
