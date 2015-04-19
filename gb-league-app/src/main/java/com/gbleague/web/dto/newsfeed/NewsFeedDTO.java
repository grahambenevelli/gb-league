package com.gbleague.web.dto.newsfeed;

import com.gbleague.league.model.INewsFeed;
import com.gbleague.league.model.newsfeed.PojoNewsFeed;
import com.gbleague.web.dto.AbstractResponseDTO;


public class NewsFeedDTO extends AbstractResponseDTO<INewsFeed> {

	private int id;
	private String image;
	private String title;
	private String description;

	public NewsFeedDTO(PojoNewsFeed newsFeed) {
		copyFromModel(newsFeed);
	}

	@Override
	protected void copyFromModel(INewsFeed newsFeed) {
		this.setId(newsFeed.getId());
		this.setImage(newsFeed.getImage());
		this.setTitle(newsFeed.getTitle());
		this.setDescription(newsFeed.getDescription());
	}

	@Override
	protected INewsFeed copyToModel(INewsFeed newsFeed) {
		newsFeed.setId(this.getId());
		newsFeed.setImage(this.getImage());
		newsFeed.setTitle(this.getTitle());
		newsFeed.setDescription(this.getDescription());
		return newsFeed;
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
