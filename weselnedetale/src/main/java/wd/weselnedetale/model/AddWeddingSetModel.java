package wd.weselnedetale.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wd.weselnedetale.converter.PaperConverter;
import wd.weselnedetale.converter.ProductConverter;
import wd.weselnedetale.converter.WeddingSetConverter;
import wd.weselnedetale.database.dao.PaperDao;
import wd.weselnedetale.database.dao.ProductDao;
import wd.weselnedetale.database.dao.WeddingSetDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.model.fx.WeddingSetFx;
import wd.weselnedetale.utils.exception.ApplicationException;

@Component
@Scope("prototype")
public class AddWeddingSetModel {
	private ProductDao productDao;
	private PaperDao paperDao;
	private WeddingSetDao weddingSetDao;

	private ObservableList<PaperFx> paperFxObservableList = FXCollections.observableArrayList();
	private ObservableList<ProductFx> productFxObservableList = FXCollections.observableArrayList();
	private ObservableList<WeddingSetFx> weddingSetFxObservableList = FXCollections.observableArrayList();
	private ObservableList<PaperFx> checkedPapers;
	private ObservableList<ProductFx> checkedProducts;
	private SimpleObjectProperty<WeddingSetFx> selectedWeddingSetProperty = new SimpleObjectProperty<>(new WeddingSetFx());

	@Autowired
	public AddWeddingSetModel(ProductDao productDao, PaperDao paperDao, WeddingSetDao weddingSetDao) {
		this.productDao = productDao;
		this.paperDao = paperDao;
		this.weddingSetDao = weddingSetDao;
		try {
			initPaperList();
			initProductList();
			initWeddingSetList();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private void initPaperList() throws ApplicationException {
		List<Paper> papers = paperDao.queryForAll(Paper.class);
		paperFxObservableList.clear();
		papers.forEach(p -> {
			PaperFx paperFx = PaperConverter.convertToPaperFx(p);
			paperFxObservableList.add(paperFx);
		});
	}

	private void initProductList() throws ApplicationException {
		List<Product> products = productDao.queryForAll(Product.class);
		productFxObservableList.clear();
		products.forEach(p -> {
			ProductFx productFx = ProductConverter.convertToProductFx(p);
			productFxObservableList.add(productFx);
		});
	}

	public void initWeddingSetList() throws ApplicationException {
		List<WeddingSet> weddingSets = weddingSetDao.queryForAll(WeddingSet.class);
		weddingSetFxObservableList.clear();
		weddingSets.forEach(p -> {
			WeddingSetFx weddingSetFx = WeddingSetConverter.convertToWeddingSetFx(p);
			weddingSetFxObservableList.add(weddingSetFx);
		});
	}

	public void addWeddingSet(WeddingSetFx weddingSetFx) {
		weddingSetFxObservableList.add(weddingSetFx);
	}

	public ObservableList<PaperFx> getPaperFxObservableList() {
		return paperFxObservableList;
	}

	public ObservableList<PaperFx> getCheckedPapers() {
		return checkedPapers;
	}

	public void setCheckedPapers(ObservableList<PaperFx> checkedPapers) {
		this.checkedPapers = checkedPapers;
	}

	public ObservableList<ProductFx> getProductFxObservableList() {
		return productFxObservableList;
	}

	public ObservableList<ProductFx> getCheckedProducts() {
		return checkedProducts;
	}

	public void setCheckedProducts(ObservableList<ProductFx> checkedProducts) {
		this.checkedProducts = checkedProducts;
	}

	public ObservableList<WeddingSetFx> getWeddingSetFxObservableList() {
		return weddingSetFxObservableList;
	}

	public SimpleObjectProperty<WeddingSetFx> getSelectedWeddingSetProperty() {
		return selectedWeddingSetProperty;
	}

	public WeddingSetFx getSelectedWeddingSet() {
		return selectedWeddingSetProperty.get();
	}

	public void setSelectedWeddingSet(WeddingSetFx selectedWeddingSet) {
		selectedWeddingSetProperty.set(selectedWeddingSet);
	}

}
