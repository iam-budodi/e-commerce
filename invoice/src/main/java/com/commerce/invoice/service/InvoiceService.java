package com.commerce.invoice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import com.commerce.invoice.model.Invoice;
import com.commerce.invoice.model.InvoiceLine;
import com.commerce.invoice.util.Discount;
import com.commerce.invoice.util.Vat;

@Transactional
public class InvoiceService {

	// ==================================
	// = 		Injection point 		=
	// ==================================

	@Inject
	private EntityManager em;

	@Inject
	@Vat
	private Float vatRate;

	@Inject
	@Discount
	private Float discountRate;

	// ======================================
	// = 		Business Methods 			=
	// ======================================

	public Invoice persist(Invoice invoice) {
		invoice.setInvoiceDate(new Date());
		invoice.setVatRate(vatRate);
		invoice.setDiscountRate(discountRate);

		// compute total amount
		Float total = 0F;
		for (InvoiceLine invoiceLine : invoice.getInvoiceLines()) {
			total += invoiceLine.getQuantity() * invoiceLine.getUnitCost();
		}

		invoice.setTotalBeforeDiscount(total);
		invoice.setDiscount(round(total * (discountRate / 100)));
		invoice.setTotalAfterDiscount(round(total - invoice.getDiscount()));
		invoice.setVat(round(
						invoice.getTotalAfterDiscount() * (vatRate / 100)));
		invoice.setTotalAfterVat(round(
						invoice.getTotalAfterDiscount() - invoice.getVat()));

		em.persist(invoice);
		return invoice;
	}

	public Invoice findById(Long id) {
		return em.find(Invoice.class, id);
	}

	public void remove(Invoice invoice) {
		em.remove(em.merge(invoice));
	}

	public Invoice merge(Invoice invoice) {
		return em.merge(invoice);
	}

	public List<Invoice> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Invoice> findAllQuery = getListAllQuery();

		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}

		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}

		final List<Invoice> results = findAllQuery.getResultList();
		return results;
	}

	public List<Invoice> listAll() {
		return getListAllQuery().getResultList();
	}

	private TypedQuery<Invoice> getListAllQuery() {
		CriteriaQuery<Invoice> criteria = em.getCriteriaBuilder()
						.createQuery(Invoice.class);
		return em.createQuery(criteria.select(criteria.from(Invoice.class)));
	}

	private static Float round(Float d) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(2, RoundingMode.HALF_EVEN);
		return bd.floatValue();
	}
}
