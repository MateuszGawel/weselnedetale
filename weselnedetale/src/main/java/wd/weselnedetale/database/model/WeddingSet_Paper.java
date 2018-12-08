package wd.weselnedetale.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WEDDINGSET_PAPER")
public class WeddingSet_Paper implements BaseModel {
	public static final String WEDDINGSET_ID = "WEDDINGSET_ID";
	public static final String PAPER_ID = "PAPER_ID";

	public WeddingSet_Paper() {
	}

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = PAPER_ID, foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
	private Paper paper;

	@DatabaseField(columnName = WEDDINGSET_ID, foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
	private WeddingSet weddingSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public WeddingSet getWeddingSet() {
		return weddingSet;
	}

	public void setWeddingSet(WeddingSet weddingSet) {
		this.weddingSet = weddingSet;
	}




}
