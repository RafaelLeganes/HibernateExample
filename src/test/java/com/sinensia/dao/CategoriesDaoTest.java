package com.sinensia.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sinensia.model.Categories;
import com.sinensia.model.Products;

public class CategoriesDaoTest {
	
	CategoriesDao categoriesDao = null;
	static Integer idUpdated = 0;
	static Integer idDeleted = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Categories category1 = new Categories();
		category1.setCategoryName("category1");
		
		List<Products> listaProductosMerge = new ArrayList<Products>();
		Products product = new Products();
		product.setProductName("Productmerge");
		product.setCategories(category1);
		listaProductosMerge.add(product);		
		category1.setProducts(listaProductosMerge);
		
		Categories category2 = new Categories();
		category2.setCategoryName("category2");

		List<Products> listaProductosdelete = new ArrayList<Products>();
		Products product2 = new Products();
		product2.setProductName("ProductDeleted");
		product2.setCategories(category2);
		listaProductosdelete.add(product2);		
		category2.setProducts(listaProductosdelete);
		
		
		CategoriesDao categoryDaoBeforeClass = new CategoriesDao();
		
		
		
		idUpdated = categoryDaoBeforeClass.save(category1);
		idDeleted = categoryDaoBeforeClass.save(category2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		categoriesDao = new CategoriesDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPersist() {
		List<Products> listaProductos = new ArrayList<Products>();
		Categories category = new Categories();
		category.setCategoryName("Persisted");
		
		Products product = new Products();
		product.setProductName("ProductPersisted1");
		product.setCategories(category);

		Products product2 = new Products();
		product2.setProductName("ProductPersisted2");
		product2.setCategories(category);
		
		listaProductos.add(product);
		listaProductos.add(product2);
		category.setProducts(listaProductos);
		assertTrue(categoriesDao.persist(category)==true);
	}

	@Test
	public void testSaveOrUpdate() {
		Categories category = new Categories();
		category.setCategoryName("SaveOrUpdate");
		List<Products> listaProductos = new ArrayList<Products>();
		
		Products product = new Products();
		product.setProductName("ProductSaveOrUpdate1");
		product.setCategories(category);
		
		Products product2 = new Products();
		product2.setProductName("ProductSaveOrUpdate2");
		product2.setCategories(category);
		
		listaProductos.add(product);
		listaProductos.add(product2);
		category.setProducts(listaProductos);
		assertTrue(categoriesDao.saveOrUpdate(category)==true);
	}

	@Test
	public void testSave() {
		List<Products> listaProductos = new ArrayList<Products>();
		Categories category = new Categories();
		category.setCategoryName("Save");
		
		Products product = new Products();
		product.setProductName("ProductSave1");
		product.setCategories(category);
		
		Products product2 = new Products();
		product2.setProductName("ProductSave2");
		product2.setCategories(category);
		
		listaProductos.add(product);
		listaProductos.add(product2);
		category.setProducts(listaProductos);
		assertTrue(categoriesDao.save(category)>0);
	}

	@Test
	public void testFindAll() {
		assertTrue(categoriesDao.findAll().size()>1);
	}

	@Test
	public void testDelete() {
		assertTrue(categoriesDao.delete(idDeleted) == true);
	}

	@Test
	public void testMerge() {
		assertTrue(categoriesDao.merge(idUpdated) != null);
	}

	@Test
	public void testFindById() {
		assertTrue(categoriesDao.findById(idUpdated)!= null);
	}

}
