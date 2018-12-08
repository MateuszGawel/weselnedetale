package wd.weselnedetale.model.fx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class PositionFx {
	private IntegerProperty idProperty = new SimpleIntegerProperty();
	private SimpleStringProperty nameProperty = new SimpleStringProperty();
	private SimpleObjectProperty<WeddingSetFx> weddingSetProperty = new SimpleObjectProperty<WeddingSetFx>();
	private SimpleObjectProperty<ProductFx> productProperty = new SimpleObjectProperty<ProductFx>();
	private IntegerProperty amountProperty = new SimpleIntegerProperty();
	private SimpleObjectProperty<PaperFx> paperProperty = new SimpleObjectProperty<PaperFx>();
	private IntegerProperty countOnPaperProperty = new SimpleIntegerProperty();
	private IntegerProperty totalCountProperty= new SimpleIntegerProperty();
	private IntegerProperty paperCountProperty = new SimpleIntegerProperty();
	private DoubleProperty singleCostProperty = new SimpleDoubleProperty();
	private DoubleProperty totalCostProperty = new SimpleDoubleProperty();

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

	public WeddingSetFx getWeddingSet() {
		return weddingSetProperty.getValue();
	}
	
	public SimpleObjectProperty<WeddingSetFx> getWeddingSetProperty() {
		return weddingSetProperty;
	}

	public void setWeddingSet(WeddingSetFx weddingSetFx) {
		this.weddingSetProperty.set(weddingSetFx);
	}
	
	public ProductFx getProduct() {
		return productProperty.getValue();
	}
	
	public SimpleObjectProperty<ProductFx> getProductProperty() {
		return productProperty;
	}

	public void setProduct(ProductFx weddingSetFx) {
		this.productProperty.set(weddingSetFx);
	}
	
	public IntegerProperty getAmountProperty() {
		return amountProperty;
	}

	public void setAmount(int id) {
		this.amountProperty.set(id);
	}

	public int getAmount() {
		return amountProperty.get();
	}
	
	public SimpleObjectProperty<PaperFx> getPaperProperty() {
		return paperProperty;
	}
	
	public PaperFx getPaper() {
		return paperProperty.get();
	}

	public void setPaper(PaperFx paperFX) {
		this.paperProperty.set(paperFX);
	}

	public IntegerProperty getCountOnPaperProperty() {
		return countOnPaperProperty;
	}
	
	public int getCountOnPaper() {
		return countOnPaperProperty.get();
	}

	public void setCountOnPaper(Integer countOnPaper) {
		this.countOnPaperProperty.set(countOnPaper);
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public IntegerProperty getTotalCountProperty() {
		return totalCountProperty;
	}
	
	public int getTotalCount() {
		return totalCountProperty.get();
	}

	public void setTotalCount(int totalCount) {
		this.totalCountProperty.set(totalCount);
	}

	public IntegerProperty getPaperCountProperty() {
		return paperCountProperty;
	}
	
	public int getPaperCount() {
		return paperCountProperty.get();
	}

	public void setPaperCount(int paperCount) {
		this.paperCountProperty.set(paperCount);
	}

	public DoubleProperty getSingleCostProperty() {
		return singleCostProperty;
	}
	
	public double getSingleCost() {
		return singleCostProperty.get();
	}

	public void setSingleCost(double singleCost) {
		this.singleCostProperty.set(singleCost);
	}

	public DoubleProperty getTotalCostProperty() {
		return totalCostProperty;
	}
	
	public double getTotalCost() {
		return totalCostProperty.get();
	}

	public void setTotalCost(double totalCost) {
		this.totalCostProperty.set(totalCost);
	}

}
