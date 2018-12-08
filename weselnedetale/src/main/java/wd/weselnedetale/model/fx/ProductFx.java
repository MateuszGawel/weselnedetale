package wd.weselnedetale.model.fx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductFx {
	private IntegerProperty idProperty = new SimpleIntegerProperty();
	private StringProperty nameProperty = new SimpleStringProperty();
	private BooleanProperty paperProduct = new SimpleBooleanProperty();
	private ObservableList<PaperFx> paperFxObservableList = FXCollections.observableArrayList();
	private BooleanProperty printed = new SimpleBooleanProperty();
	private IntegerProperty widthProperty = new SimpleIntegerProperty();
	private IntegerProperty heightProperty = new SimpleIntegerProperty();
	private DoubleProperty priceProperty = new SimpleDoubleProperty();
	
	public IntegerProperty getIdProperty() {
		return idProperty;
	}

	public void setId(int id) {
		this.idProperty.set(id);
	}

	public int getId() {
		return idProperty.get();
	}

	public StringProperty getNameProperty() {
		return nameProperty;
	}

	public String getName() {
		return nameProperty.get();
	}

	public void setName(String name) {
		this.nameProperty.set(name);
	}

	public boolean isPaperProduct() {
		return paperProduct.get();
	}

	public void setPaperProduct(boolean paperProduct) {
		this.paperProduct.set(paperProduct);
	}
	
	public BooleanProperty getPaperProductProperty() {
		return paperProduct;
	}
	
	public ObservableList<PaperFx> getPaperFxObservableList() {
		return paperFxObservableList;
	}
	
	public boolean isPrinted() {
		return printed.get();
	}

	public void setPrinted(boolean printed) {
		this.printed.set(printed);
	}
	
	public BooleanProperty getPrintedProperty() {
		return printed;
	}
	
	public IntegerProperty getWidthProperty() {
		return widthProperty;
	}

	public int getWidth() {
		return widthProperty.get();
	}
	
	public void setWidth(int width) {
		widthProperty.set(width);
	}
	
	public void setHeight(int height) {
		heightProperty.set(height);
	}
	
	public int getHeight() {
		return heightProperty.get();
	}

	public IntegerProperty getHeightProperty() {
		return heightProperty;
	}

	public DoubleProperty getPriceProperty() {
		return priceProperty;
	}

	public double getPrice() {
		return priceProperty.get();
	}
	
	public void setPrice(double price) {
		this.priceProperty.set(price);
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getId();
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
		ProductFx other = (ProductFx) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
}
