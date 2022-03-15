package com.sinensia.dao;

import static org.junit.Assert.*;


import org.junit.Test;

public class HibernateSessionFactoryTest {

	@Test
	public void testGetSessionProductionFactory() {
		HibernateSessionFactory hibernateSessionFactory = new HibernateSessionFactory();
		assertTrue(hibernateSessionFactory.getSessionFactory("hibernate.cfg.xml") != null);
	}
	
	@Test
	public void testGetSessionDevelotmentFactory() {
		HibernateSessionFactory hibernateSessionFactory = new HibernateSessionFactory();
		assertTrue(hibernateSessionFactory.getSessionFactory("hibernate-heroku.cfg.xml") != null);
	}
}
