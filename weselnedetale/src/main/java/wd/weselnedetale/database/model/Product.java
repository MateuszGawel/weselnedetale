package wd.weselnedetale.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRODUCT")
public class Product implements BaseModel{
    public Product() {
    }

    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String name;
    
    @DatabaseField(columnName = "PAPER_PRODUCT", canBeNull = false)
    private boolean paperProduct;
    
    @DatabaseField(columnName = "PRINTED", canBeNull = false)
    private boolean printed;
    
    @DatabaseField(columnName = "PRICE", canBeNull = true)
    private double price;
    
    @DatabaseField(columnName = "WIDTH", canBeNull = true)
    private int width;
    
    @DatabaseField(columnName = "HEIGHT", canBeNull = true)
    private int height;
    

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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isPaperProduct() {
		return paperProduct;
	}

	public void setPaperProduct(boolean paperProduct) {
		this.paperProduct = paperProduct;
	}

	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
