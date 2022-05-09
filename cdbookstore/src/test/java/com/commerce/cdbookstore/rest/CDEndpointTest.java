package com.commerce.cdbookstore.rest;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.commerce.cdbookstore.model.Artist;
import com.commerce.cdbookstore.model.CD;
import com.commerce.cdbookstore.model.Genre;
import com.commerce.cdbookstore.model.Item;
import com.commerce.cdbookstore.model.Label;
import com.commerce.cdbookstore.model.Musician;
import com.commerce.cdbookstore.util.ResourceProducer;

@RunWith(Arquillian.class)
@RunAsClient
public class CDEndpointTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @ArquillianResource
    private URI baseURL;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment(testable = false)
    public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
                .addClass(RestApplication.class)
                .addClass(CDEndpoint.class)
                .addClass(CD.class)
                .addClass(Item.class)
                .addClass(Genre.class)
                .addClass(Label.class)
                .addClass(Artist.class)
                .addClass(Musician.class)
                .addClass(ResourceProducer.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

    @Test
    public void should_be_deployed() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURL).path("rest").path("cds");
        assertEquals(Response.Status.OK.getStatusCode(), target.request(MediaType.APPLICATION_XML).get().getStatus());
    }
}
