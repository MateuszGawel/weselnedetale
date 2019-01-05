package wd.weselnedetale.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wd.weselnedetale.converter.ProductConverter;
import wd.weselnedetale.database.dao.ProductDao;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.utils.exception.ApplicationException;

@Component
@Scope("prototype")
public class AddProductModel {
	private ProductDao productDao;
	
	private ObservableList<ProductFx> productFxObservableList = FXCollections.observableArrayList();
	private SimpleObjectProperty<ProductFx> selectedProductProperty = new SimpleObjectProperty<>(new ProductFx());
	
	@Autowired
	public AddProductModel(ProductDao productDao) {
		this.productDao = productDao;
		try {
			initProductList();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private void initProductList() throws ApplicationException {
		List<Product> products = productDao.queryForAll(Product.class);
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
