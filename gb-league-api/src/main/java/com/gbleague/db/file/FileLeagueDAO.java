package com.gbleague.db.file;

import org.apache.commons.csv.CSVRecord;

import com.gbleague.db.ILeagueDAO;
import com.gbleague.models.league.League;
import com.google.common.base.Function;

public class FileLeagueDAO extends AbstractFileWithIdDAO<League> implements ILeagueDAO {

	private static final String DEFAULT_FILENAME = "db/league/league.csv";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String COMMISSIONER_ID = "comissioner_id";
	
	public FileLeagueDAO() {
		this(DEFAULT_FILENAME);
	}
	
	public FileLeagueDAO(String fileName) {
		super(fileName);
	}

	@Override 
	protected Function<League, String> getToCsvFunction() {
		return new Function<League, String>() {

			@Override
			public String apply(League input) {
				return new StringBuilder()
						.append("" + input.getId())
						.append(',')
						.append(input.getName())
						.append(',')
						.append(input.getCommissionerId())
						.append('\n')
						.toString();
			}
		};
	}

	@Override 
	protected Function<CSVRecord, League> getToObjectFunction() {
		return new Function<CSVRecord, League>() {

			@Override
			public League apply(CSVRecord input) {
				return new League()
						.setId(Long.parseLong(input.get(ID)))
						.setName(input.get(NAME))
						.setCommissionerId(Long.parseLong(input.get(COMMISSIONER_ID)));
			}
		};
	}
}
