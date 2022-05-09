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
import com.commerce.cdbookstore.model.Author;
import com.commerce.cdbookstore.model.Book;
import com.commerce.cdbookstore.model.Category;
import com.commerce.cdbookstore.model.Item;
import com.commerce.cdbookstore.model.Language;
import com.commerce.cdbookstore.model.LanguageConverter;
import com.commerce.cdbookstore.model.Publisher;

@RunWith(Arquillian.class)
public class BookBeanTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private BookBean bookBean;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment
    public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookBean.class)
                .addClass(Book.class)
                .addClass(Item.class)
                .addClass(Language.class)
                .addClass(LanguageConverter.class)
                .addClass(Category.class)
                .addClass(Publisher.class)
                .addClass(Artist.class)
                .addClass(Author.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

//    @Test
//    public void should_be_deployed() {
//        assertNotNull(bookBean);
//    }

    @Test
    public void should_crud() {
        // Creates an object
        Book book = new Book();
        book.setTitle("Dummy value");
        book.setIsbn("Dummy value");

        // Inserts the object into the database
        bookBean.setBook(book);
        bookBean.create();
        bookBean.update();
        book = bookBean.getBook();
        assertNotNull(book.getId());

        // Finds the object from the database and checks it's the right one
        book = bookBean.findById(book.getId());
        assertEquals("Dummy value", book.getTitle());

        // Deletes the object from the database and checks it's not there anymore
        bookBean.setId(book.getId());
        bookBean.create();
        bookBean.delete();
        book = bookBean.findById(book.getId());
        assertNull(book);
    }

    @Test
    public void should_paginate() {
        // Creates an empty example
        Book example = new Book();

        // Paginates through the example
        bookBean.setExample(example);
        bookBean.paginate();
        assertTrue((bookBean.getPageItems().size() == bookBean.getPageSize())
                || (bookBean.getPageItems().size() == bookBean.getCount()));
    }
}
