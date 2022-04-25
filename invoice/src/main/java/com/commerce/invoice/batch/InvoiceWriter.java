package com.commerce.invoice.batch;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

@Named
public class InvoiceWriter extends AbstractItemWriter {

	// ==================================
	// = 		Injection Points		=
	// ==================================
	
//	@Inject
//	private JobContext jobContext;
//	
//	@Inject
//	private EntityManager em;
	
	@Inject
	private Logger logger;

	// ==================================
	// = 		Business Methods		=
	// ==================================
	
	@Override
	public void writeItems(List<Object> items) throws Exception {
		InvoiceSummaries summaries = new InvoiceSummaries();
		summaries.setYear(2016);
		
		@SuppressWarnings("unchecked")
		List<InvoiceSummary> invoiceSummaries = (List<InvoiceSummary>) items.get(0);
		for (InvoiceSummary invoiceSummary : invoiceSummaries) {
			summaries.add(invoiceSummary);
		}
		
		JAXBContext context = JAXBContext.newInstance(InvoiceSummaries.class);
		Marshaller m = context.createMarshaller();
		File file = new File("invoice.xml");
		m.marshal(summaries, file);
		logger.info("Wrote " + file.getAbsolutePath());
		System.out.println("#######################");
		System.out.println(file.getAbsolutePath());
	}

}
