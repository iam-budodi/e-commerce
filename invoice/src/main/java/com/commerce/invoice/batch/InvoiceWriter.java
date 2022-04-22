package com.commerce.invoice.batch;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

@Named
public class InvoiceWriter extends AbstractItemWriter {

	public InvoiceWriter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeItems(List<Object> arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
