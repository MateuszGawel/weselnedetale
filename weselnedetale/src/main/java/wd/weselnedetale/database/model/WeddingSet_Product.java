package wd.weselnedetale.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WEDDINGSET_PRODUCT")
public class WeddingSet_Product implements BaseModel {
	public static final String PRODUCT_ID = "PRODUCT_ID";
	public static final String WEDDINGSET_ID = "WEDDINGSET_ID";

	public WeddingSet_Product() {
	}

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = PRODUCT_ID, foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
	private Product product;

	@DatabaseField(columnName = WEDDINGSET_ID, foreign = true, foreignAutoRefresh = true, uniqueCombo = true)
	private WeddingSet weddingSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public WeddingSet getWeddingSet() {
		return weddingSet;
	}

	public void setWeddingSet(WeddingSet weddingSet) {
		this.weddingSet = weddingSet;
	}

}
