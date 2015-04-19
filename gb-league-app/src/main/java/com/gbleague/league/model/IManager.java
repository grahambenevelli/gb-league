package com.gbleague.league.model;

/**
 * Manager interface
 */
public interface IManager {

	/**
	 * Get the manager id
	 */
	public long getId();

	/**
	 * Set the manager id
	 */
	public void setId(long id);

	/**
	 * Get the first name of the manager
	 */
	public String getFirstName();

	/**
	 * Set the first name of the manager
	 */
	public void setFirstName(String firstName);

	/**
	 * Get the last name of the manager
	 */
	public String getLastName();

	/**
	 * Set the last name of the manager
	 */
	public void setLastName(String lastName);

}
