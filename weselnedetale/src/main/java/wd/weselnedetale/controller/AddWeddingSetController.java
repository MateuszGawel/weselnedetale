package wd.weselnedetale.controller;

import org.controlsfx.control.CheckComboBox;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import wd.weselnedetale.converter.WeddingSetConverter;
import wd.weselnedetale.database.dao.WeddingSetDao;
import wd.weselnedetale.database.model.WeddingSet;
import wd.weselnedetale.model.AddWeddingSetModel;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.model.fx.WeddingSetFx;
import wd.weselnedetale.utils.DialogsUtils;
import wd.weselnedetale.utils.exception.ApplicationException;

public class AddWeddingSetController {

	@FXML
	private TextField weddingSetNameField;
	@FXML
	private CheckComboBox<PaperFx> paperCheckComboBox;
	@FXML
	private CheckComboBox<ProductFx> productCheckComboBox;
	@FXML
	private Button weddingSetAddButton;

	@FXML
	private TableView<WeddingSetFx> weddingSetTableView;
	@FXML
	private TableColumn<WeddingSetFx, String> nameColumn;
	@FXML
	private TableColumn<WeddingSetFx, String> papersColumn;
	@FXML
	private TableColumn<WeddingSetFx, String> productsColumn;
	@FXML
	private MenuItem deleteMenuItem;
	@FXML
	private MenuItem editMenuItem;

	private AddWeddingSetModel addWeddingSetModel;

	@FXML
	public void initialize() {
		//TODO add model+some view to be able to display all papers on the same screen
		addWeddingSetModel = new AddWeddingSetModel();
		try {
			addWeddingSetModel.init();
		} catch (ApplicationException e) {
			DialogsUtils.errorDialog(e);
		}
		bindings();
		initTable();
	}

	private void bindings() {
		paperCheckComboBox.getItems().addAll(addWeddingSetModel.getPaperFxObservableList());
		paperCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<PaperFx>() {
			@Override
			public void onChanged(Change<? extends PaperFx> c) {
				addWeddingSetModel.setCheckedPapers(paperCheckComboBox.getCheckModel().getCheckedItems());
			}
		});

		productCheckComboBox.getItems().addAll(addWeddingSetModel.getProductFxObservableList());
		productCheckComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<ProductFx>() {
			@Override
			public void onChanged(Change<? extends ProductFx> c) {
				addWeddingSetModel.setCheckedProducts(productCheckComboBox.getCheckModel().getCheckedItems());
			}
		});

		weddingSetAddButton.disableProperty().bind(weddingSetNameField.textProperty().isEmpty());
	}

	private void initTable() {
		weddingSetTableView.setItems(addWeddingSetModel.getWeddingSetFxObservableList());
		weddingSetTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			addWeddingSetModel.setSelectedWeddingSet(newValue);
		});

		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		papersColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaperFxObservableList().toString()));
		papersColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		papersColumn.setEditable(false);

		productsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductFxObservableList().toString()));
		productsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		productsColumn.setEditable(false);
	}

	@FXML
	public void addWeddingSet() {
		WeddingSetFx weddingSetFx = new WeddingSetFx();
		weddingSetFx.setName(weddingSetNameField.getText());
		//TODO implement removal
		//save previous state
		//if something existed but now it is removed -> mark to delete (some new flag for paper and product)
		//in weddingSetDao read the flag and use paper/product dao to remove it
		addWeddingSetModel.getCheckedPapers().forEach(p -> weddingSetFx.addPaper(p));
		addWeddingSetModel.getCheckedProducts().forEach(p -> weddingSetFx.addProduct(p));
		WeddingSet weddingSet = WeddingSetConverter.convertToWeddingSet(weddingSetFx);

		weddingSet.setId(addWeddingSetModel.getSelectedWeddingSet().getId());
		new WeddingSetDao().createOrUpdate(weddingSet);
		addWeddingSetModel.initWeddingSetList();

		clearForm();
	}

	private void clearForm() {
		weddingSetNameField.setText("");
		paperCheckComboBox.getCheckModel().clearChecks();
		productCheckComboBox.getCheckModel().clearChecks();
	}

	@FXML
	public void onDeleteWeddingSet() {
		WeddingSetFx selectedWeddingSet = addWeddingSetModel.getSelectedWeddingSet();
		addWeddingSetModel.getWeddingSetFxObservableList().remove(selectedWeddingSet);
		deleteFromDatabase(selectedWeddingSet);
	}

	@FXML
	public void onEditWeddingSet() {
		WeddingSetFx selectedWeddingSet = addWeddingSetModel.getSelectedWeddingSet();
		weddingSetNameField.setText(selectedWeddingSet.getName());
		selectedWeddingSet.getPaperFxObservableList().forEach(p -> {
			paperCheckComboBox.getCheckModel().check(p);
		});
		selectedWeddingSet.getProductFxObservableList().forEach(p -> {
			productCheckComboBox.getCheckModel().check(p);
		});
	}

	@FXML
	public void onEditName(TableColumn.CellEditEvent<WeddingSetFx, String> e) {
		WeddingSetFx weddingSetFx = e.getRowValue();
		weddingSetFx.setName(e.getNewValue());
		updateInDatabase(weddingSetFx);
	}

	private void updateInDatabase(WeddingSetFx weddingSetFx) {
		new WeddingSetDao().createOrUpdate(WeddingSetConverter.convertToWeddingSet(weddingSetFx));
	}

	private void deleteFromDatabase(WeddingSetFx weddingSetFx) {
		new WeddingSetDao().delete(WeddingSetConverter.convertToWeddingSet(weddingSetFx));
	}

}
