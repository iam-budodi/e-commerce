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
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.commerce.cdbookstore.model.User;
import com.commerce.cdbookstore.model.UserRole;
import com.commerce.cdbookstore.util.PasswordUtils;

@RunWith(Arquillian.class)
public class UserBeanTest {

    // ======================================
    // =          Injection Points          =
    // ======================================

    @Inject
    private UserBean userBean;

    // ======================================
    // =         Deployment methods         =
    // ======================================

    @Deployment
    public static Archive<?> createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
                .addClass(UserBean.class)
                .addClass(User.class)
                .addClass(UserRole.class)
                .addClass(PasswordUtils.class)
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    // ======================================
    // =            Test methods            =
    // ======================================

    @Test
    public void should_be_deployed() {
        Assert.assertNotNull(userBean);
    }

    @Test
    @Ignore
    public void should_crud() {
        // Creates an object
        User user = new User();
        user.setFirstName("Dummy value");
        user.setLastName("Dummy value");
        user.setLogin("Dummy");
        user.setPassword("Dummy value");
        user.setEmail("Dummy value");

        // Inserts the object into the database
        userBean.setUser(user);
        userBean.create();
        userBean.update();
        user = userBean.getUser();
        assertNotNull(user.getId());

        // Finds the object from the database and checks it's the right one
        user = userBean.findById(user.getId());
        assertEquals("Dummy value", user.getFirstName());

        // Deletes the object from the database and checks it's not there anymore
        userBean.setId(user.getId());
        userBean.create();
        userBean.delete();
        user = userBean.findById(user.getId());
        assertNull(user);
    }

    @Test
    public void should_paginate() {
        // Creates an empty example
        User example = new User();

        // Paginates through the example
        userBean.setExample(example);
        userBean.paginate();
        assertTrue((userBean.getPageItems().size() == userBean.getPageSize())
                || (userBean.getPageItems().size() == userBean.getCount()));
    }
}
