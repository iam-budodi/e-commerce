package com.commerce.invoice.batch;

import java.io.IOException;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InvoiceJobServlet
 */

@WebServlet(name = "InvoiceJobServlet", urlPatterns = "startJob")
public class InvoiceJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		jobOperator.start("invoiceJob", null);
	}

}
