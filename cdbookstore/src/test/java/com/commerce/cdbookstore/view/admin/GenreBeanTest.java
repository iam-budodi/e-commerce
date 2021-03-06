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

import com.commerce.cdbookstore.model.Genre;

@RunWith(Arquillian.class)
public class GenreBeanTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private GenreBean genreBean;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment
    public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
                .addClass(GenreBean.class)
                .addClass(Genre.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

//    @Test
//    public void should_be_deployed() {
//        assertNotNull(genreBean);
//    }

    @Test
    public void should_crud() {
        // Creates an object
        Genre genre = new Genre();
        genre.setName("Dummy value");

        // Inserts the object into the database
        genreBean.setGenre(genre);
        genreBean.create();
        genreBean.update();
        genre = genreBean.getGenre();
        assertNotNull(genre.getId());

        // Finds the object from the database and checks it's the right one
        genre = genreBean.findById(genre.getId());
        assertEquals("Dummy value", genre.getName());

        // Deletes the object from the database and checks it's not there anymore
        genreBean.setId(genre.getId());
        genreBean.create();
        genreBean.delete();
        genre = genreBean.findById(genre.getId());
        assertNull(genre);
    }

    @Test
    public void should_paginate() {
        // Creates an empty example
        Genre example = new Genre();

        // Paginates through the example
        genreBean.setExample(example);
        genreBean.paginate();
        assertTrue((genreBean.getPageItems().size() == genreBean.getPageSize())
                || (genreBean.getPageItems().size() == genreBean.getCount()));
    }
}
