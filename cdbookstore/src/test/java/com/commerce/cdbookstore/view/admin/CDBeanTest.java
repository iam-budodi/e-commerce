package com.commerce.cdbookstore.view.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.commerce.cdbookstore.model.Artist;
import com.commerce.cdbookstore.model.CD;
import com.commerce.cdbookstore.model.Genre;
import com.commerce.cdbookstore.model.Item;
import com.commerce.cdbookstore.model.Label;
import com.commerce.cdbookstore.model.Musician;

@RunWith(Arquillian.class)
public class CDBeanTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private CDBean cdBean;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment
    public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
                .addClass(CDBean.class)
                .addClass(CD.class)
                .addClass(Item.class)
                .addClass(Genre.class)
                .addClass(Label.class)
                .addClass(Artist.class)
                .addClass(Musician.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

//    @Test
//    public void should_be_deployed() {
//        assertNotNull(cdBean);
//    }

    @Test
    public void should_crud() {
        // Creates an object
        CD cd = new CD();
        cd.setTitle("Dummy value");

        // Inserts the object into the database
        cdBean.setCD(cd);
        cdBean.create();
        cdBean.update();
        cd = cdBean.getCD();
        assertNotNull(cd.getId());

        // Finds the object from the database and checks it's the right one
        cd = cdBean.findById(cd.getId());
        assertEquals("Dummy value", cd.getTitle());

        // Deletes the object from the database and checks it's not there anymore
        cdBean.setId(cd.getId());
        cdBean.create();
        cdBean.delete();
        cd = cdBean.findById(cd.getId());
        assertNull(cd);
    }

    @Test
    public void should_paginate() {
        // Creates an empty example
        CD example = new CD();

        // Paginates through the example
        cdBean.setExample(example);
        cdBean.paginate();
        assertTrue((cdBean.getPageItems().size() == cdBean.getPageSize())
                || (cdBean.getPageItems().size() == cdBean.getCount()));
    }
}
