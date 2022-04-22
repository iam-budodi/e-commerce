package com.commerce.invoice.batch;

import java.util.List;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.commerce.invoice.model.Invoice;

@Named
public class InvoiceReader extends AbstractItemReader {

	// ==================================
	// = 		Injection point 		=
	// ==================================
	
	@Inject
	private EntityManager em;
	
	@Inject
	private Logger logger;

	// ======================================
	// = 		Business Methods 			=
	// ======================================

	@Override
	public Object readItem() throws Exception {
		TypedQuery<Invoice> query = em.createNamedQuery(Invoice.FIND_MONTHLY, Invoice.class);
		// query.setParameter("month", 2);
		List<Invoice> invoices = query.getResultList();
		logger.info("Read " + invoices.size() + " invoices");
		return invoices;
	}

}
