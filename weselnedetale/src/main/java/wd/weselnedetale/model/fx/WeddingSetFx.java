package wd.weselnedetale.model.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WeddingSetFx {
	private IntegerProperty idProperty = new SimpleIntegerProperty();
	private SimpleStringProperty nameProperty = new SimpleStringProperty();
	private ObservableList<ProductFx> productFxObservableList = FXCollections.observableArrayList();
	private ObservableList<PaperFx> paperFxObservableList = FXCollections.observableArrayList();
	
	public IntegerProperty getIdProperty() {
		return idProperty;
	}

	public void setId(int id) {
		this.idProperty.set(id);
	}

	public int getId() {
		return idProperty.get();
	}

	public SimpleStringProperty getNameProperty() {
		return nameProperty;
	}

	public String getName() {
		return nameProperty.get();
	}

	public void setName(String name) {
		this.nameProperty.set(name);
	}

	public ObservableList<ProductFx> getProductFxObservableList() {
		return productFxObservableList;
	}
	
	public void addProduct(ProductFx productFx) {
		productFxObservableList.add(productFx);
	}

	public ObservableList<PaperFx> getPaperFxObservableList() {
		return paperFxObservableList;
	}
	
	public void addPaper(PaperFx paperFx) {
		paperFxObservableList.add(paperFx);
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProperty == null) ? 0 : idProperty.hashCode());
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
		WeddingSetFx other = (WeddingSetFx) obj;
		if (idProperty == null) {
			if (other.idProperty != null)
				return false;
		} else if (idProperty.get() != other.idProperty.get())
			return false;
		return true;
	}
}
