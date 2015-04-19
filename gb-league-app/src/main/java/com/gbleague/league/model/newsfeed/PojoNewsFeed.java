package com.gbleague.league.model.newsfeed;

import com.gbleague.league.model.INewsFeed;

/**
 * The simple pojo class for the new feed model
 */
public class PojoNewsFeed implements INewsFeed {

	private int id;
	private String image;
	private String title;
	private String description;

	public PojoNewsFeed(int id, String image, String title, String description) {
		this.setId(id);
		this.setImage(image);
		this.setTitle(title);
		this.setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
