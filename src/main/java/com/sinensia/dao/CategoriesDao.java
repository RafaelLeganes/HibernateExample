package com.sinensia.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.sinensia.model.Categories;
import com.sinensia.model.Products;




public class CategoriesDao {
	
	private static final java.util.logging.Logger logger = Logger.getLogger(CategoriesDao.class.getName());
	
	private final SessionFactory sessionFactory = new HibernateSessionFactory().getSessionFactory("hibernate.cfg.xml");
	
	public boolean persist(Categories transientInstance) {
		logger.log(Level.INFO, "persisting Categories instance");
		Transaction tx = null;
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			tx = sessionObj.beginTransaction();
			sessionObj.persist(transientInstance);
			if(transientInstance.getProducts() != null) {
				for(Products product: transientInstance.getProducts()) {
					product.setCategories(transientInstance);
					sessionObj.persist(product);
				}
			}
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
		return true;
	}
	
	public boolean saveOrUpdate(Categories instance) {
		logger.log(Level.INFO, "attaching dirty Alumno instance");
		Transaction tx = null;
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			tx = sessionObj.beginTransaction();
			sessionObj.saveOrUpdate(instance);
			if(instance.getProducts() != null) {
				for(Products product: instance.getProducts()) {
					product.setCategories(instance);
					sessionObj.persist(product);
				}
			}
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
		return true;
	}
	
	public Integer save(Categories instance) {
		logger.log(Level.INFO, "attaching dirty Categories instance");
		Integer categoryId = 0;
		Transaction tx = null;
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			tx = sessionObj.beginTransaction();
			categoryId = (Integer) sessionObj.save(instance);
			if(instance.getProducts() != null) {
				for(Products product: instance.getProducts()) {
					product.setCategories(instance);
					sessionObj.persist(product);
				}
			}
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
		return categoryId;
	}
	
	public List<Categories> findAll(){
		logger.log(Level.INFO, "finding Categories instance by example");
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			sessionObj.beginTransaction();
			
			Query categoriesQuery = sessionObj.createQuery("From Categories");
			
			@SuppressWarnings("unchecked")
			List<Categories> categoriesList = categoriesQuery.getResultList();
			
			sessionObj.close();
			
			return categoriesList;		
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "findAll failed", re);
			throw re;
		}
	}
	
	public boolean delete(Integer id) {
		logger.log(Level.INFO, "deleting Categories instance");
		Transaction tx = null;
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			tx = sessionObj.beginTransaction();
			Categories instance = (Categories) sessionObj.get("com.sinensia.model.Categories", id);
			sessionObj.delete(instance);
			tx.commit();
			sessionObj.close();
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			tx.rollback();
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
		return true;
	}
	
	public Categories merge(Integer id) {
		logger.log(Level.INFO, "merging Categories instance");
		Transaction tx = null;
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			tx = sessionObj.beginTransaction();
			Categories instance = (Categories) sessionObj.get("com.sinensia.model.Categories", id);
			instance.setCategoryName("Merged");
			Categories result = (Categories) sessionObj.merge(instance);			
			tx.commit();
			logger.log(Level.INFO, "merge successful");
			sessionObj.close();
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			tx.rollback();
			throw re;
		}
	}
	
	public Categories findById(Integer id){
		logger.log(Level.INFO, "finding Categories instance by example");
		Transaction tx = null;
		try {
			Session sessionObj = sessionFactory.getCurrentSession();
			tx  = sessionObj.beginTransaction();
			
			Categories instance = (Categories) sessionObj.get("com.sinensia.model.Categories", id);
			
			if(instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			tx.commit();
			sessionObj.close();
			return instance;	
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
	}
	
}
