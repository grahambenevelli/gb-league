package com.gbleague.db.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Nullable;

import org.apache.commons.csv.CSVRecord;

import com.gbleague.db.IManagerDAO;
import com.gbleague.models.manager.Manager;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;

public class FileManagerDAO extends AbstractFileWithIdDAO<Manager> implements IManagerDAO {

	private static final String DEFAULT_FILENAME = "db/manager/manager.csv";
	private static final String ID = "id";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	public FileManagerDAO() {
		this(DEFAULT_FILENAME);
	}
	
	public FileManagerDAO(String fileName) {
		super(fileName);
	}

	@Override
	public Optional<Manager> getManagerByUsername(String username) {
		FileOutput<Manager> output = getFileOutput();
		for (Manager manager : output.records) {
			if (username.equals(manager.getUsername())) {
				return Optional.of(manager);
			}
		}
		return Optional.absent();
	}

	@Override
	public Manager createManager(Manager manager) {
		try {
			FileOutput<Manager> output = getFileOutput();
			for (Manager record : output.records) {
				if (record.getUsername().equals(manager.getUsername())) {
					return record;
				}
			}
			
			long newId = output.records.isEmpty() ? 1 : Iterables.getLast(output.records).getId() + 1;
			manager.setId(newId);
			output.records.add(manager);

			output.writeFile(output);
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		return manager;
	}

	@Override
	public void updateManager(Manager manager) {
		// TODO implement
	}

	@Override
	protected Function<Manager, String> getToCsvFunction() {
		return new Function<Manager, String>() {

			@Nullable 
			@Override 
			public String apply(Manager manager) {
				return new StringBuilder()
						.append("" + manager.getId())
						.append(',')
						.append(manager.getUsername())
						.append(',')
						.append(manager.getPassword())
						.append('\n')
						.toString();
			}
		};
	}

	@Override
	protected Function<CSVRecord, Manager> getToObjectFunction() {
		return new Function<CSVRecord, Manager>() {

			@Override 
			public Manager apply(CSVRecord csvRecord) {
				return new Manager(Long.parseLong(csvRecord.get(ID)), csvRecord.get(USERNAME), csvRecord.get(PASSWORD));
			}
		};
	}

	@VisibleForTesting
	void truncateTable() {
		try {
			FileOutput output = getFileOutput();

			File file = getFile();
			FileWriter fileWriter = new FileWriter(file, false);

			output.writeHeader(fileWriter);

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			Throwables.propagate(e);
		}
	}
}
