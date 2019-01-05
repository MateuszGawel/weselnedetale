package wd.weselnedetale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import wd.weselnedetale.converter.PaperConverter;
import wd.weselnedetale.database.dao.PaperDao;
import wd.weselnedetale.database.model.Paper;
import wd.weselnedetale.model.AddPaperModel;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.utils.exception.ApplicationException;

@Component
@Scope("prototype")
public class AddPaperController {

	@FXML
	private TextField paperNameField;
	@FXML
	private TextField paperPriceField;
	@FXML
	private TextField paperWidthField;
	@FXML
	private TextField paperHeightField;
	@FXML
	private Button paperAddButton;
	@FXML
	private TableView<PaperFx> addPaperTableView;
	@FXML
	private TableColumn<PaperFx, String> nameColumn;
	@FXML
	private TableColumn<PaperFx, Number> priceColumn;
	@FXML
	private TableColumn<PaperFx, Number> widthColumn;
	@FXML
	private TableColumn<PaperFx, Number> heightColumn;
	@FXML
	private MenuItem deleteMenuItem;

	private AddPaperModel addPaperModel;
	private PaperDao paperDao;
	
	@Autowired
	public AddPaperController(AddPaperModel addPaperModel, PaperDao paperDao) {
		this.addPaperModel = addPaperModel;
		this.paperDao = paperDao;
	}

	@FXML
	public void initialize() {
		//TODO add paperSize table
		bindings();
		initTable();
	}

	private void bindings() {
		paperPriceField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
		paperWidthField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		paperHeightField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		paperAddButton.disableProperty().bind(paperNameField.textProperty().isEmpty().or(paperPriceField.textProperty().isEmpty()).or(paperWidthField.textProperty().isEmpty()).or(paperHeightField.textProperty().isEmpty()));
	}
	
	private void initTable() {
		addPaperTableView.setItems(addPaperModel.getPaperFxObservableList());
		addPaperTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			addPaperModel.setSelectedPaper(newValue);
		});
		
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		widthColumn.setCellValueFactory(cellData -> cellData.getValue().getWidthProperty());
		widthColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		heightColumn.setCellValueFactory(cellData -> cellData.getValue().getHeightProperty());
		heightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
	}
	
	@FXML
	public void addPaper() {
		PaperFx paperFx = new PaperFx();
		paperFx.setName(paperNameField.getText());
		paperFx.setPrice(Double.valueOf(paperPriceField.getText()));
		paperFx.setWidth(Integer.valueOf(paperWidthField.getText()));
		paperFx.setHeight(Integer.valueOf(paperHeightField.getText()));
		Paper paper = PaperConverter.convertToPaper(paperFx);
		try {
			paperDao.createOrUpdate(paper);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		addPaperModel.addpaper(paperFx);
		clearForm();
	}

	private void clearForm() {
		paperPriceField.setText("");
		paperWidthField.setText("");
		paperHeightField.setText("");
		paperNameField.setText("");
	}

	@FXML
	public void onPaperDelete() {
		PaperFx selectedPaper = addPaperModel.getSelectedPaper();
		addPaperModel.getPaperFxObservableList().remove(selectedPaper);
		deleteFromDatabase(selectedPaper);
	}
	
	@FXML
	public void onNameCommit(TableColumn.CellEditEvent<PaperFx, String> e) {
		PaperFx paperFx = e.getRowValue();
		paperFx.setName(e.getNewValue());
		updateInDatabase(paperFx);
	}

	@FXML
	public void onPriceCommit(TableColumn.CellEditEvent<PaperFx, Number> e) {
		PaperFx paperFx = e.getRowValue();
		paperFx.setPrice(e.getNewValue().doubleValue());
		updateInDatabase(paperFx);
	}

	@FXML
	public void onWidthCommit(TableColumn.CellEditEvent<PaperFx, Number> e) {
		PaperFx paperFx = e.getRowValue();
		paperFx.setWidth(e.getNewValue().intValue());
		updateInDatabase(paperFx);
	}

	@FXML
	public void onHeightCommit(TableColumn.CellEditEvent<PaperFx, Number> e) {
		PaperFx paperFx = e.getRowValue();
		paperFx.setHeight(e.getNewValue().intValue());
		updateInDatabase(paperFx);
	}
	

	private void updateInDatabase(PaperFx paperFx) {
		try {
			paperDao.createOrUpdate(PaperConverter.convertToPaper(paperFx));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteFromDatabase(PaperFx paperFx) {
		try {
			paperDao.delete(PaperConverter.convertToPaper(paperFx));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}


}
