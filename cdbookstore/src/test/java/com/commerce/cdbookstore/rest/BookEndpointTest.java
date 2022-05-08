package com.commerce.cdbookstore.rest;

import static org.junit.Assert.assertEquals;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.commerce.cdbookstore.model.Artist;
import com.commerce.cdbookstore.model.Author;
import com.commerce.cdbookstore.model.Book;
import com.commerce.cdbookstore.model.Category;
import com.commerce.cdbookstore.model.Item;
import com.commerce.cdbookstore.model.Language;
import com.commerce.cdbookstore.model.LanguageConverter;
import com.commerce.cdbookstore.model.Publisher;
import com.commerce.cdbookstore.util.ResourceProducer;

@RunWith(Arquillian.class)
@RunAsClient
public class BookEndpointTest {

	// ======================================
	// = Injection Points =
	// ======================================

	@ArquillianResource
	private URI baseURL;

	private Client client;
	private WebTarget webTarget;
	private Response response;

	// ======================================
	// = Deployment methods =
	// ======================================

	@Deployment(testable = false)
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
						.addClass(RestApplication.class)
						.addClass(BookEndpoint.class).addClass(Book.class)
						.addClass(Item.class).addClass(Language.class)
						.addClass(LanguageConverter.class)
						.addClass(Category.class).addClass(Publisher.class)
						.addClass(Artist.class).addClass(Author.class)
						.addClass(ResourceProducer.class)
						.addAsResource("META-INF/test-persistence.xml",
										"META-INF/persistence.xml")
						.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	// ======================================
	// = Test methods =
	// ======================================
	
	@Before
	public void initWebTarget() {
		client = ClientBuilder.newClient();
		webTarget = client.target(baseURL).path("rest/books");
	}

	@Test
	@InSequence(1)
	public void should_be_deployed() {
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), webTarget
						.request(MediaType.APPLICATION_XML).get().getStatus());
	}

	@Test
	@InSequence(2)
	public void shouldCRUDBooks() throws IOException {
		response = webTarget.request(MediaType.APPLICATION_JSON).get();
		System.out.println("test BOOK : " + response.getStatus());
		assertEquals(NO_CONTENT.getStatusCode(), response.getStatus());
//		assertEquals(0, response.readEntity(List.class).size());
//		int initialSize = target.request(MediaType.APPLICATION_XML).get(Books.class).size();;
//
//		// creates a book
//		Book book = new Book("1234-5678", "H2G2", "The best Scifi book", 45.5f,
//						345, Language.ENGLISH);
//		response = target.request()
//						.post(Entity.entity(book, MediaType.APPLICATION_XML));
//		assertEquals(201, response.getStatus());
//		URI locationNewBook = response.getLocation();
//
//		// checks there is one more book
//		assertEquals(initialSize + 1, target.request(MediaType.APPLICATION_XML)
//						.get(Books.class).size());
//
//		// deletes the created book
//		response = client.target(locationNewBook).request().delete();
//		assertEquals(204, response.getStatus());
//
//		// checks there is one less book
//		assertEquals(initialSize, target.request(MediaType.APPLICATION_XML)
//						.get(Books.class).size());
	}
}
