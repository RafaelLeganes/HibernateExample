package com.sinensia.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sinensia.model.Categories;
import com.sinensia.model.Products;

public class HibernateSessionFactory {
	
	private static final java.util.logging.Logger logger = Logger.getLogger(HibernateSessionFactory.class.getName());
	
	public SessionFactory getSessionFactory(String configurationFile) {
		try {
			Configuration config = new Configuration().configure(configurationFile).addAnnotatedClass(Categories.class).addAnnotatedClass(Products.class);
			SessionFactory sessionFactory = config.buildSessionFactory();
			return sessionFactory;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory", e);
			throw new IllegalStateException ("Could not locate SessionFactory");
		}
	}

}
