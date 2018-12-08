package wd.weselnedetale.database.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PAPER")
public class Paper implements BaseModel{
    public Paper() {
    }

    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = "PRICE", canBeNull = false)
    private double price;
    
    @DatabaseField(columnName = "NAME", unique = true, canBeNull = false)
    private String name;
    
    @DatabaseField(columnName = "WIDTH", canBeNull = false)
    private int width;
    
    @DatabaseField(columnName = "HEIGHT", canBeNull = false)
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

	public double getPrice() {
		return price;
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

	public void setPrice(double d) {
		this.price = d;
	}
}
