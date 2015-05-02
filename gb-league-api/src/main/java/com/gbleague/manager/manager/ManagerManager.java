package com.gbleague.manager.manager;

import com.gbleague.db.IManagerDAO;
import com.gbleague.models.manager.Manager;
import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;

public class ManagerManager {
	
	private final IManagerDAO managerDAO;

	public ManagerManager(IManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}

	/**
	 * Get the manager by id
	 * @param id manager id
	 * @return the manager
	 */
	public Manager getManagerById(long id) {
		Optional<Manager> manager = managerDAO.getManagerById(id);
		if (manager.isPresent()) {
			return manager.get();
		} else {
			throw new NotFoundException();
		}
	}

	/**
	 * Persist the given manager
	 * @param managerToCreate The manager to persist
	 * @return The manager created
	 */
	public Manager createManager(Manager managerToCreate) {
		return managerDAO.createManager(managerToCreate);
	}
}
