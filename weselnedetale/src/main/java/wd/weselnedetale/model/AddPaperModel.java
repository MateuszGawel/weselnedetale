package wd.weselnedetale.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wd.weselnedetale.converter.PaperConverter;
import wd.weselnedetale.database.dao.PaperDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.utils.exception.ApplicationException;

@Component
@Scope("prototype")
public class AddPaperModel {
	private PaperDao paperDao;
	
	private ObservableList<PaperFx> paperFxObservableList = FXCollections.observableArrayList();
	private SimpleObjectProperty<PaperFx> selectedPaperProperty = new SimpleObjectProperty<>(new PaperFx());
	
	@Autowired
	public AddPaperModel(PaperDao paperDao) {
		this.paperDao = paperDao;
		try {
			initPaperList();
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
