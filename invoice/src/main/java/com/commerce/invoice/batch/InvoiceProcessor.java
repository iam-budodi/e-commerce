package com.commerce.invoice.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

import com.commerce.invoice.model.Invoice;

@Named
public class InvoiceProcessor implements ItemProcessor {

	// ==================================
	// = 		Injection point 		=
	// ==================================
	
	@Inject
	private Logger logger;
	
	// ======================================
	// = 		Business Methods 			=
	// ======================================

	@Override
	public Object processItem(Object item) throws Exception {
		@SuppressWarnings("unchecked")
		List<Invoice> invoices = (List<Invoice>) item;
		List<InvoiceSummary> summaries = new ArrayList<>();

		for (int i = 0; i < invoices.size(); i++) {
			int currentMonth = invoices.get(i).getMonth();
			logger.info("Current Month " + currentMonth);
			InvoiceSummary summary = new InvoiceSummary(currentMonth + 1);

			int nbOfInvoices = 0;
			float total = 0F;
			while ((currentMonth == invoices.get(i).getMonth()) && (i < invoices.size() - 1)) {
				nbOfInvoices++;
				total += invoices.get(i).getTotalAfterVat();
				logger.info("Current invoices id " + invoices.get(i).getId());
				i++;
			}

			summary.setNumberOfInvoices(nbOfInvoices);
			summary.setTotal(total);
			summaries.add(summary);
		}

		logger.info("Processed " + summaries.size() + " summaries");
		return summaries;
	}
}
