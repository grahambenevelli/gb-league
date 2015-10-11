package com.gbleague.server.resources.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.gbleague.db.IManagerDAO;
import com.gbleague.db.file.FileManagerDAO;
import com.gbleague.manager.manager.ManagerManager;
import com.gbleague.models.manager.CreateManagerRequest;
import com.gbleague.models.manager.Manager;
import com.yammer.dropwizard.jersey.params.LongParam;

public class ManagerResourceIntegrationTest {
	
	private CreateManagerResource createManagerResource;
	private ManagerByIdResource managerByIdResource;
	private CurrentManagerResource currentManagerResource;
	
	@Before
	public void setup() {
		IManagerDAO managerDao = new FileManagerDAO("db/manager/manager-test.csv");
		ManagerManager managerManager = new ManagerManager(managerDao);
		createManagerResource = new CreateManagerResource(managerManager);
		managerByIdResource = new ManagerByIdResource(managerManager);
		currentManagerResource = new CurrentManagerResource();
	}

	@Test
	public void testCreateAndRetrieveById() {
		String password = "niy98h1ou2hy4";
		String username = "Richard Sloan";

		Manager manager = new Manager()
				.setUsername(username);

		CreateManagerRequest createRequest = new CreateManagerRequest()
				.setPassword(password)
				.setManager(manager);

		Response createResponse = createManagerResource.createManager(manager, createRequest);

		Object createEntity = createResponse.getEntity();
		assertTrue(createEntity instanceof Manager);

		Manager createdManager = (Manager) createEntity;
		assertEquals(password, createdManager.getPassword());
		assertEquals(username, createdManager.getUsername());

		long id = createdManager.getId();

		Response getResponse = managerByIdResource.getManager(manager, new LongParam("" + id));

		Object getEntity = getResponse.getEntity();

		assertTrue(getEntity instanceof Manager);

		Manager gottenManager = (Manager) getEntity;
		assertEquals(password, gottenManager.getPassword());
		assertEquals(username, gottenManager.getUsername());
	}

	@Test
	public void testCreateAndRetrieveCurrent() {
		String password = "niy98h1ou2hy4";
		String username = "Richard Sloan";

		Manager manager = new Manager()
				.setUsername(username);

		CreateManagerRequest createRequest = new CreateManagerRequest()
				.setPassword(password)
				.setManager(manager);

		Response createResponse = createManagerResource.createManager(manager, createRequest);

		Object createEntity = createResponse.getEntity();
		assertTrue(createEntity instanceof Manager);

		Manager createdManager = (Manager) createEntity;
		assertEquals(password, createdManager.getPassword());
		assertEquals(username, createdManager.getUsername());

		Response getResponse = currentManagerResource.getManager(null, createdManager);

		Object getEntity = getResponse.getEntity();

		assertTrue(getEntity instanceof Manager);

		Manager gottenManager = (Manager) getEntity;
		assertEquals(password, gottenManager.getPassword());
		assertEquals(username, gottenManager.getUsername());
	}
}