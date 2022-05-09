package com.commerce.invoice.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.commerce.invoice.model.Invoice;
import com.commerce.invoice.service.InvoiceService;

/**
 * Servlet implementation class InvoiceServlet
 */

@WebServlet(name = "InvoiceServlet", urlPatterns = { "/invoice" })
public class InvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ======================================
    // =          Injection Point           =
    // ======================================
	
	@Inject
	private InvoiceService invoiceService;
	 
	// =======================================
    // =          Business Methods           =
    // =======================================
 
	protected void doGet(HttpServletRequest request,
					HttpServletResponse response)
					throws ServletException, IOException {
		
		// Handle request
		Long id = Long.valueOf(request.getParameter("id"));

		// Invoke business logic
		Invoice invoice = invoiceService.findById(id);

		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (invoice == null)
			out.print("No invoice with such an id");
		else
			out.print(invoice);
	}

}
