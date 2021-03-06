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

import com.commerce.cdbookstore.model.Label;

@RunWith(Arquillian.class)
public class LabelBeanTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private LabelBean labelBean;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment
    public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
                .addClass(LabelBean.class)
                .addClass(Label.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

//    @Test
//    public void should_be_deployed() {
//        assertNotNull(labelBean);
//    }

    @Test
    public void should_crud() {
        // Creates an object
        Label majorLabel = new Label();
        majorLabel.setName("Dummy value");

        // Inserts the object into the database
        labelBean.setLabel(majorLabel);
        labelBean.create();
        labelBean.update();
        majorLabel = labelBean.getLabel();
        assertNotNull(majorLabel.getId());

        // Finds the object from the database and checks it's the right one
        majorLabel = labelBean.findById(majorLabel.getId());
        assertEquals("Dummy value", majorLabel.getName());

        // Deletes the object from the database and checks it's not there anymore
        labelBean.setId(majorLabel.getId());
        labelBean.create();
        labelBean.delete();
        majorLabel = labelBean.findById(majorLabel.getId());
        assertNull(majorLabel);
    }

    @Test
    public void should_paginate() {
        // Creates an empty example
        Label example = new Label();

        // Paginates through the example
        labelBean.setExample(example);
        labelBean.paginate();
        assertTrue((labelBean.getPageItems().size() == labelBean.getPageSize())
                || (labelBean.getPageItems().size() == labelBean.getCount()));
    }
}
