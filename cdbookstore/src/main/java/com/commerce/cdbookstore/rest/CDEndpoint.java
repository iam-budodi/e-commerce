package com.commerce.cdbookstore.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.commerce.cdbookstore.model.CD;

@Path("/cds")
@Transactional
public class CDEndpoint {

	// ========================================
	// = 			Injection Point 		  =
	// ========================================

	@Inject
	private EntityManager em;

	// ========================================
	// = 			Business Methods 		  =
	// ========================================

	@POST
	@Consumes({ "application/xml", "application/json" })
	public Response create(CD entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(CDEndpoint.class)
				.path(String.valueOf(entity.getId())).build())
				.build();
	}
	
	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		CD entity = em.find(CD.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		em.remove(entity);
		return Response.noContent().build();
	}
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces({ "application/xml", "application/json" })
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<CD> findByIdQuery = em.createQuery(
				"SELECT DISTINCT c FROM CD c "
				+ "LEFT JOIN FETCH c.label "
				+ "LEFT JOIN FETCH c.musicians "
				+ "LEFT JOIN FETCH c.genre "
				+ "WHERE c.id = :entityId "
				+ "ORDER BY c.id", 
				CD.class);
		
		findByIdQuery.setParameter("entityId", id);
		
		CD entity;
		
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok(entity).build();
	}
	
	@GET
	@Produces({ "application/xml", "application/json" })
	public List<CD> listAll(@QueryParam("start") Integer startPosition, 
			@QueryParam("max") Integer maxResult) {
		
		TypedQuery<CD> findAllQuery = em.createQuery(
				"SELECT DISTINCT c FROM CD c "
				+ "LEFT JOIN FETCH c.label "
				+ "LEFT JOIN FETCH c.musicians "
				+ "LEFT JOIN FETCH c.genre "
				+ "ORDER BY c.id", 
				CD.class);
		
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<CD> results = findAllQuery.getResultList();
		System.out.println("CDs : " + results);
		return results;
	}
	
	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes({ "application/xml", "application/json" })
	public Response update(@PathParam("id") Long id, CD entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(CD.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}
		
		return Response.noContent().build();
	}
}
