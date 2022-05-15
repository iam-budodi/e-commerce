package com.commerce.toprated;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/toprateditems")
@Transactional
public class TopRatedItemEndpoint {

	// ======================================
	// = Injection Points =
	// ======================================

	@Inject
	private EntityManager em;

	@Inject
	private Logger logger;

	// ======================================
	// = Business methods =
	// ======================================

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RatedItems TopRatedItems() {

		RatedItems results = new RatedItems();

		TypedQuery<RatedItem> query = em.createNamedQuery(RatedItem.FIND_TOP_ITEMS, RatedItem.class);
		results.addAll(query.getResultList());

		logger.info("Top Items are " + results);

		return results;
	}
}
