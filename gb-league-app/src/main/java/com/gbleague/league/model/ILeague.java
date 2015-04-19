package com.gbleague.league.model;

/**
 * League interface
 */
public interface ILeague {

	/**
	 * Get the id of the league
	 */
	public int getId();

	/**
	 * Set the id of the league
	 */
	public void setId(int id);

	/**
	 * Get the name of the league
	 */
	public String getName();

	/**
	 * Set the nae of the league
	 */
	public void setName(String name);

	/**
	 * Get the commissioner id of the league
	 */
	public int getCommissionerId();

	/**
	 * Set the commissioner id of the league
	 */
	public void setCommissionerId(int commissionerId);
}
