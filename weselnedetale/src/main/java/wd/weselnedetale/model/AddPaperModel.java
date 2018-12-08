package wd.weselnedetale.model;

import java.util.List;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wd.weselnedetale.converter.PaperConverter;
import wd.weselnedetale.database.dao.CommonDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.utils.exception.ApplicationException;

public class AddPaperModel {
	private ObservableList<PaperFx> paperFxObservableList = FXCollections.observableArrayList();
	private SimpleObjectProperty<PaperFx> selectedPaperProperty = new SimpleObjectProperty<>(new PaperFx());
	
	public void init() throws ApplicationException {
		initPaperList();
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
	
	public ObservableList<PaperFx> getPaperFxObservableList() {
		return paperFxObservableList;
	}

	public void addpaper(PaperFx paperFx) {
		paperFxObservableList.add(paperFx);
	}
	
	public SimpleObjectProperty<PaperFx> getSelectedPaperProperty() {
		return selectedPaperProperty;
	}

	public PaperFx getSelectedPaper() {
		return selectedPaperProperty.get();
	}
	
	public void setSelectedPaper(PaperFx selectedPaper) {
		selectedPaperProperty.set(selectedPaper);
	}
}
