package com.gbleague.db.file;

import com.gbleague.models.manager.Manager;
import com.google.common.base.Function;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public abstract class AbstractFileDAO<T> {

	private final String fileName;

	public AbstractFileDAO(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Get the file used by this DAO 
	 * @return The file used in the dao
	 */
	protected File getFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return new File(classLoader.getResource(fileName).getFile());
	}

	/**
	 * Get the file output object for parsing and updating file 
	 * @return The file output object
	 */
	protected FileOutput<T> getFileOutput() {
		try {
			return new FileOutput<T>(getFile(), getToObjectFunction(), getToCsvFunction());
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	/**
	 * Get the conversion function that takes an Object and creates a csv string 
	 * @return The csv string
	 */
	protected abstract Function<T, String> getToCsvFunction();

	/**
	 * Get the conversion function that takes a csv record and creates an object
	 * @return The object version
	 */
	protected abstract Function<CSVRecord, T> getToObjectFunction();

	protected class FileOutput<T> {

		public Map<String, Integer> headers;
		public List<T> records;
		public File file;
		public Function<CSVRecord, T> toObject;
		public Function<T, String> toCSV;

		public FileOutput(File file, Function<CSVRecord, T> toObject, Function<T, String> toCSV) throws IOException {
			this.toObject = toObject;
			this.toCSV = toCSV;
			this.file = file;
			records = Lists.newArrayList();
			parseFile();
		}

		public void parseFile() throws IOException {
			CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.RFC4180.withHeader());

			headers = parser.getHeaderMap();
			for (CSVRecord csvRecord : parser) {
				addRecord(csvRecord);
			}

			parser.close();
		}

		public void addRecord(CSVRecord record) {
			records.add(toObject.apply(record));
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
				for (T object : records) {
					this.writeManger(writer, object);
				}
			} catch (IOException e) {
				Throwables.propagate(e);
			}
		}

		private void writeManger(FileWriter writer, T object) throws IOException {
			writer.append(toCSV.apply(object));
		}

		public void writeFile(FileOutput<Manager> output) throws IOException {
			File file = getFile();
			FileWriter fileWriter = new FileWriter(file, false);

			output.writeHeader(fileWriter);
			output.writeManagers(fileWriter);

			fileWriter.flush();
			fileWriter.close();
		}
	}

}
