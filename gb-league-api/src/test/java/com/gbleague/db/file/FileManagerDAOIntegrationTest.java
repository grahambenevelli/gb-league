package com.gbleague.db.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gbleague.models.manager.Manager;
import com.google.common.base.Optional;

public class FileManagerDAOIntegrationTest {
	
	private FileManagerDAO dao;
	
	@Before
	public void setup() {
		dao = new FileManagerDAO("db/manager/manager-test.csv");
	}
	
	@After
	public void teardown() {
		dao.truncateTable();
	}

	@Test
	public void testCreateUserAndRetrieveByUsername() throws Exception {
		Manager expected = new Manager()
				.setPassword("secret")
				.setUsername("userA");

		dao.createManager(expected);
		Optional<Manager> optionalResult = dao.getManagerByUsername("userA");

		assertTrue(optionalResult.isPresent());
		assertEquals(expected, optionalResult.get());
	}

	@Test
	public void testCreateUserAndRetrieveById() throws Exception {
		Manager expected = new Manager()
				.setPassword("secret")
				.setUsername("userA");

		dao.createManager(expected);
		Optional<Manager> optionalResult = dao.getById(1l);

		assertTrue(optionalResult.isPresent());
		assertEquals(expected, optionalResult.get());
	}
}