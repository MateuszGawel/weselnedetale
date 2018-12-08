package wd.weselnedetale.model;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wd.weselnedetale.converter.ProductConverter;
import wd.weselnedetale.converter.WeddingSetConverter;
import wd.weselnedetale.database.dao.CommonDao;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.model.fx.PositionFx;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.model.fx.WeddingSetFx;
import wd.weselnedetale.utils.exception.ApplicationException;

public class AddOrderModel {
	private ObservableList<WeddingSetFx> weddingSetFxObservableList = FXCollections.observableArrayList();
	private ObservableList<ProductFx> productFxObservableList = FXCollections.observableArrayList();
	private ObservableList<PositionFx> positionFxObservableList = FXCollections.observableArrayList();
	private SimpleObjectProperty<PositionFx> selectedPositionProperty = new SimpleObjectProperty<>(new PositionFx());
	private ObservableList<PaperFx> paperFxObservableList = FXCollections.observableArrayList();
	
	public void init() throws ApplicationException {
		initWeddingSetList();
	}

	private void initWeddingSetList() throws ApplicationException {
		CommonDao commonDao = new CommonDao();
		List<WeddingSet> weddingSets = commonDao.queryForAll(WeddingSet.class);
		weddingSetFxObservableList.clear();
		weddingSets.forEach(w -> {
			WeddingSetFx weddingSetFx = WeddingSetConverter.convertToWeddingSetFx(w);
			weddingSetFxObservableList.add(weddingSetFx);
		});
	}

	public void fillProductList(WeddingSetFx weddingSetFx) throws ApplicationException {
		productFxObservableList.clear();
		if(weddingSetFx == null) {
			CommonDao commonDao = new CommonDao();
			List<Product> products = commonDao.queryForAll(Product.class);
			products.forEach(p -> productFxObservableList.add(ProductConverter.convertToProductFx(p)));
		}
		else {
			productFxObservableList.addAll(weddingSetFx.getProductFxObservableList());
		}
	}
	
	public void fillPaperList(WeddingSetFx weddingSetFx) throws ApplicationException {
		paperFxObservableList.clear();
		paperFxObservableList.addAll(weddingSetFx.getPaperFxObservableList());

	}

	public ObservableList<WeddingSetFx> getWeddingSetFxObservableList() {
		return weddingSetFxObservableList;
	}

	public ObservableList<ProductFx> getProductFxObservableList() {
		return productFxObservableList;
	}

	public ObservableList<PositionFx> getPositionFxObservableList() {
		return positionFxObservableList;
	}

	public void addPosition(PositionFx positionFx) {
		positionFxObservableList.add(positionFx);
	}

	public SimpleObjectProperty<PositionFx> getSelectedPositionProperty() {
		return selectedPositionProperty;
	}

	public PositionFx getSelectedPosition() {
		return selectedPositionProperty.get();
	}
	
	public void setSelectedPosition(PositionFx selectedPosition) {
		selectedPositionProperty.set(selectedPosition);
	}

	public ObservableList<PaperFx> getPaperFxObservableList() {
		return paperFxObservableList;
	}

}
