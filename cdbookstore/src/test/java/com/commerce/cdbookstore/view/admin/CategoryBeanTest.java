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

import com.commerce.cdbookstore.model.Category;

@RunWith(Arquillian.class)
public class CategoryBeanTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private CategoryBean categoryBean;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment
    public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
                .addClass(CategoryBean.class)
                .addClass(Category.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

//    @Test
//    public void should_be_deployed() {
//        assertNotNull(categoryBean);
//    }

    @Test
    public void should_crud() {
        // Creates an object
        Category category = new Category();
        category.setName("Dummy value");

        // Inserts the object into the database
        categoryBean.setCategory(category);
        categoryBean.create();
        categoryBean.update();
        category = categoryBean.getCategory();
        assertNotNull(category.getId());

        // Finds the object from the database and checks it's the right one
        category = categoryBean.findById(category.getId());
        assertEquals("Dummy value", category.getName());

        // Deletes the object from the database and checks it's not there anymore
        categoryBean.setId(category.getId());
        categoryBean.create();
        categoryBean.delete();
        category = categoryBean.findById(category.getId());
        assertNull(category);
    }

    @Test
    public void should_paginate() {
        // Creates an empty example
        Category example = new Category();

        // Paginates through the example
        categoryBean.setExample(example);
        categoryBean.paginate();
        assertTrue((categoryBean.getPageItems().size() == categoryBean.getPageSize())
                || (categoryBean.getPageItems().size() == categoryBean.getCount()));
    }
}
