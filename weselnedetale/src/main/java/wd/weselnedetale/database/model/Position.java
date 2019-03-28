package wd.weselnedetale.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "POSITION")
public class Position implements BaseModel {
	public Position() {
	}

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = "NAME", canBeNull = false)
	private String name;

	@DatabaseField(columnName = "AMOUNT", canBeNull = false)
	private int amount;

	@DatabaseField(foreign = true)
	private WeddingSet weddingSet;

	@DatabaseField(foreign = true)
	private Product product;
	
	@DatabaseField(foreign = true)
	private Paper paper;
	
	@DatabaseField(foreign = true)
	private Order order;
	

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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public WeddingSet getWeddingSet() {
		return weddingSet;
	}

	public void setWeddingSet(WeddingSet weddingSet) {
		this.weddingSet = weddingSet;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
