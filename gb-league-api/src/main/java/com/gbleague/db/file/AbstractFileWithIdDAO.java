package com.gbleague.db.file;

import com.gbleague.models.AbstractModelWithId;
import com.google.common.base.Optional;

public abstract class AbstractFileWithIdDAO<T extends AbstractModelWithId> extends AbstractFileDAO<T>{

	public AbstractFileWithIdDAO(String fileName) {
		super(fileName);
	}

	public Optional<T> getById(long id) {
		FileOutput<T> output = getFileOutput();
		for (T manager : output.records) {
			if (manager.getId() == id) {
				return Optional.of(manager);
			}
		}
		return Optional.absent();
	}
}
