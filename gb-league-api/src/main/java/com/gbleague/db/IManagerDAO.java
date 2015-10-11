package com.gbleague.db;

import com.gbleague.models.manager.Manager;
import com.google.common.base.Optional;

public interface IManagerDAO {

	/**
	 * Gets the manager by username
	 * @param username The username of the manager
	 * @return The Manager is existent, absent if not
	 */
	public Optional<Manager> getManagerByUsername(String username);

	/**
	 * Get the manager by id 
	 * @param id The is of the manager
	 * @return The Manager is existent, absent if not
	 */
	public Optional<Manager> getById(long id);

	/**
	 * Create a manager record in the database
	 * @param manager The manager to save
	 */
	public Manager createManager(Manager manager);

	/**
	 * Update a manager record in the database
	 * @param manager The manager record to update
	 */
	public void updateManager(Manager manager);
}
