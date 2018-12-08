package wd.weselnedetale.database.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ORDER_PRODUCT")
public class Order implements BaseModel {
	public Order() {
	}

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = "NAME", canBeNull = false)
	private String name;

	@ForeignCollectionField(columnName = "POSITION_ID", eager = true)
	private ForeignCollection<Position> position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
