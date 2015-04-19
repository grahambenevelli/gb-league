package com.gbleague.web.dto;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * The abstract response DTO
 * @param <T> The class we are wrapping in a DTO
 */
public abstract class AbstractResponseDTO<T> {

	private Map<String, String> links = Maps.newHashMap();
	private Map<String, String> nextActions = Maps.newHashMap();

	protected abstract void copyFromModel(T model);

	protected abstract T copyToModel(T model);

	public Map<String, String> getLinks() {
		return links;
	}

	public void setLinks(Map<String, String> links) {
		this.links = links;
	}

	public void putLink(String key, String value) {
		this.links.put(key, value);
	}

	public Map<String, String> getNextActions() {
		return nextActions;
	}

	public void setNextActions(Map<String, String> nextActions) {
		this.nextActions = nextActions;
	}

	public void putNextAction(String key, String value) {
		this.nextActions.put(key, value);
	}
}
