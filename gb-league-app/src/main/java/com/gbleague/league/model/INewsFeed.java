package com.gbleague.league.model;

/**
 * New feed interface
 */
public interface INewsFeed {

	/**
	 * Get the id of the news feed
	 */
	public int getId();

	/**
	 * Set the id of the news feed
	 */
	public void setId(int id);

	/**
	 * Get the image to display with the article
	 */
	public String getImage();

	/**
	 * Set the image to display with the article
	 */
	public void setImage(String image);

	/**
	 * Get the title of the news feed
	 */
	public String getTitle();

	/**
	 * Set the title of the news feed
	 */
	public void setTitle(String title);

	/**
	 * Get the description of the article
	 */
	public String getDescription();

	/**
	 * Set the description of the article
	 */
	public void setDescription(String description);

}
