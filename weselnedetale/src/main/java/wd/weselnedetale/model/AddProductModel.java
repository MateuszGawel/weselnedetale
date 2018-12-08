package wd.weselnedetale.model;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wd.weselnedetale.converter.ProductConverter;
import wd.weselnedetale.database.dao.CommonDao;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.utils.exception.ApplicationException;

public class AddProductModel {
	private ObservableList<ProductFx> productFxObservableList = FXCollections.observableArrayList();
	private SimpleObjectProperty<ProductFx> selectedProductProperty = new SimpleObjectProperty<>(new ProductFx());
	
	public void init() throws ApplicationException {
		initProductList();
	}
	
	private void initProductList() throws ApplicationException {
		CommonDao commonDao = new CommonDao();
		List<Product> products = commonDao.queryForAll(Product.class);
		productFxObservableList.clear();
		products.forEach(p -> {
			ProductFx productFx = ProductConverter.convertToProductFx(p);
			productFxObservableList.add(productFx);
		});
	}
	
	public ObservableList<ProductFx> getProductFxObservableList() {
		return productFxObservableList;
	}

	public void addProduct(ProductFx productFx) {
		productFxObservableList.add(productFx);
	}
	
	public SimpleObjectProperty<ProductFx> getSelectedProductProperty() {
		return selectedProductProperty;
	}

	public ProductFx getSelectedProduct() {
		return selectedProductProperty.get();
	}
	
	public void setSelectedProduct(ProductFx selectedProduct) {
		selectedProductProperty.set(selectedProduct);
	}
}
