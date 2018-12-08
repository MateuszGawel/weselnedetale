package wd.weselnedetale.model;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wd.weselnedetale.converter.PaperConverter;
import wd.weselnedetale.converter.ProductConverter;
import wd.weselnedetale.converter.WeddingSetConverter;
import wd.weselnedetale.database.dao.CommonDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.model.fx.WeddingSetFx;
import wd.weselnedetale.utils.exception.ApplicationException;

public class AddWeddingSetModel {
	private ObservableList<PaperFx> paperFxObservableList = FXCollections.observableArrayList();
	private ObservableList<ProductFx> productFxObservableList = FXCollections.observableArrayList();
	private ObservableList<WeddingSetFx> weddingSetFxObservableList = FXCollections.observableArrayList();
	private ObservableList<PaperFx> checkedPapers;
	private ObservableList<ProductFx> checkedProducts;
	private SimpleObjectProperty<WeddingSetFx> selectedWeddingSetProperty = new SimpleObjectProperty<>(new WeddingSetFx());
	
	public void init() throws ApplicationException {
		initPaperList();
		initProductList();
		initWeddingSetList();
	}

	private void initPaperList() throws ApplicationException {
		CommonDao commonDao = new CommonDao();
		List<Paper> papers = commonDao.queryForAll(Paper.class);
		paperFxObservableList.clear();
		papers.forEach(p -> {
			PaperFx paperFx = PaperConverter.convertToPaperFx(p);
			paperFxObservableList.add(paperFx);
		});
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
	
	public void initWeddingSetList() {
		try {
			CommonDao commonDao = new CommonDao();
			List<WeddingSet> weddingSets = commonDao.queryForAll(WeddingSet.class);
			weddingSetFxObservableList.clear();
			weddingSets.forEach(p -> {
				WeddingSetFx weddingSetFx = WeddingSetConverter.convertToWeddingSetFx(p);
				weddingSetFxObservableList.add(weddingSetFx);
			});
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
