package com.gbleague.db.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Iterables;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.gbleague.db.IManagerDAO;
import com.gbleague.models.manager.Manager;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

public class FileManagerDAO implements IManagerDAO {

	private static final String DEFAULT_FILENAME = "db/manager/manager.csv";
	private static final String ID = "id";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	private String fileName;

	public FileManagerDAO() {
		this(DEFAULT_FILENAME);
	}

	public FileManagerDAO(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Optional<Manager> getManagerByUsername(String username) {
		FileOutput output = getFileOutput();
		for (Manager manager : output.managers) {
			if (username.equals(manager.getUsername())) {
				return Optional.of(manager);
			}
		}
		return Optional.absent();
	}

	@Override
	public Optional<Manager> getManagerById(long id) {
		FileOutput output = getFileOutput();
		for (Manager manager : output.managers) {
			if (manager.getId() == id) {
				return Optional.of(manager);
			}
		}
		return Optional.absent();
	}

	@Override
	public Manager createManager(Manager manager) {
		try {
			FileOutput output = getFileOutput();
			for (Manager record : output.managers) {
				if (record.getUsername().equals(manager.getUsername())) {
					return record;
				}
			}
			long newId = output.managers.isEmpty() ? 1 : Iterables.getLast(output.managers).getId() + 1;
			manager.setId(newId);
			output.managers.add(manager);

			File file = getFile();
			FileWriter fileWriter = new FileWriter(file, false);
			
			output.writeHeader(fileWriter);
			output.writeManagers(fileWriter);
			
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			Throwables.propagate(e);
		}
		return manager;
	}

	private File getFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(fileName).getFile());
	}

	@Override
	public void updateManager(Manager manager) {
		// TODO implement
	}

	private FileOutput getFileOutput() {
		try {
			FileOutput output = new FileOutput();
			File file = getFile();
			CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.RFC4180.withHeader());

			output.headers = parser.getHeaderMap();
			for (CSVRecord csvRecord : parser) {
				output.addManager(csvRecord);
			}

			parser.close();
			return output;
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
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

	private class FileOutput {

		public Map<String, Integer> headers;
		public List<Manager> managers;

		public FileOutput() {
			managers = Lists.newArrayList();
		}

		public void addManager(CSVRecord record) {
			managers.add(convertCsvRecordToManager(record));
		}

		public Manager convertCsvRecordToManager(CSVRecord csvRecord) {
			return new Manager(Long.parseLong(csvRecord.get(ID)), csvRecord.get(USERNAME), csvRecord.get(PASSWORD));
		}

		public void writeHeader(FileWriter writer) {
			try {
				List<String> headersList = this.convertHeaders();
				boolean first = true;
				for (String column : headersList) {
					if (!first) {
						writer.append(',');
					}
					first = false;
					writer.append(column);
				}
				writer.append('\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private List<String> convertHeaders() {
			List<String> result = Lists.newArrayList();
			for (String header : headers.keySet()) {
				result.add(header);
			}
			return result;
		}

		public void writeManagers(FileWriter writer) {
			try {
				for (Manager manager : managers) {
					this.writeManger(writer, manager);
				}
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}

		private void writeManger(FileWriter writer, Manager manager) throws IOException {
			writer.append("" + manager.getId())
					.append(',')
					.append(manager.getUsername())
					.append(',')
					.append(manager.getPassword())
					.append('\n');
		}
	}
}
