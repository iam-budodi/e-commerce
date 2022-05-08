package com.commerce.cdbookstore.view.admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.commerce.cdbookstore.model.Country;

@RunWith(Arquillian.class)
public class CountryBeanTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private CountryBean countryBean;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap
                .create(JavaArchive.class)
                .addClass(CountryBean.class)
                .addClass(Country.class)
                .addAsManifestResource("META-INF/persistence-test.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

    @Test
    public void should_be_deployed() {
        Assert.assertNotNull(countryBean);
    }

    @Test
    public void should_crud() {
        // Creates an object
        Country country = new Country();
        country.setName("Dummy value");
        country.setIsoCode("XY");
        country.setPrintableName("Dummy value");
        country.setIso3("XYZ");
        country.setNumCode("XYZ");

        // Inserts the object into the database
        countryBean.setCountry(country);
        countryBean.create();
        countryBean.update();
        country = countryBean.getCountry();
        assertNotNull(country.getId());

        // Finds the object from the database and checks it's the right one
        country = countryBean.findById(country.getId());
        assertEquals("Dummy value", country.getName());

        // Deletes the object from the database and checks it's not there anymore
        countryBean.setId(country.getId());
        countryBean.create();
        countryBean.delete();
        country = countryBean.findById(country.getId());
        assertNull(country);
    }

    @Test
    public void should_paginate() {
        // Creates an empty example
        Country example = new Country();

        // Paginates through the example
        countryBean.setExample(example);
        countryBean.paginate();
        assertTrue((countryBean.getPageItems().size() == countryBean.getPageSize())
                || (countryBean.getPageItems().size() == countryBean.getCount()));
    }
}
