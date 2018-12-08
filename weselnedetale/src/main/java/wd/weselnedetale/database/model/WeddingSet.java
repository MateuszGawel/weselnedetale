package wd.weselnedetale.database.model;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WEDDING_SET")
public class WeddingSet implements BaseModel {
	public WeddingSet() {
	}

	@DatabaseField(generatedId = true, unique = true)
	private int id;

	@DatabaseField(columnName = "NAME", canBeNull = false)
	private String name;

	@ForeignCollectionField(columnName = "WEDDINGSET_PRODUCT_ID", eager = true)
	private ForeignCollection<WeddingSet_Product> weddingSet_product;
	
	@ForeignCollectionField(columnName = "WEDDINGSET_PAPER_ID", eager = true)
	private ForeignCollection<WeddingSet_Paper> weddingSet_paper;
	
	private List<Paper> papers = new ArrayList<Paper>();
	private List<Product> products = new ArrayList<Product>();

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

	public ForeignCollection<WeddingSet_Product> getWeddingSet_product() {
		return weddingSet_product;
	}

	public void setWeddingSet_product(ForeignCollection<WeddingSet_Product> weddingSet_product) {
		this.weddingSet_product = weddingSet_product;
	}

	public ForeignCollection<WeddingSet_Paper> getWeddingSet_paper() {
		return weddingSet_paper;
	}

	public void setWeddingSet_paper(ForeignCollection<WeddingSet_Paper> weddingSet_paper) {
		this.weddingSet_paper = weddingSet_paper;
	}
	
	public void addPaper(Paper paper) {
		this.papers.add(paper);
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}

	public List<Paper> getPapers() {
		return papers;
	}

	public List<Product> getProducts() {
		return products;
	}
}
