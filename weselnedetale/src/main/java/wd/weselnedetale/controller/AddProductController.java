package wd.weselnedetale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import wd.weselnedetale.converter.ProductConverter;
import wd.weselnedetale.database.dao.ProductDao;
import wd.weselnedetale.database.model.Product;
import wd.weselnedetale.model.AddProductModel;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.utils.exception.ApplicationException;

@Component
@Scope("prototype")
public class AddProductController {

	@FXML
	private TextField productNameField;
	@FXML
	private TextField productWidthField;
	@FXML
	private TextField productHeightField;
	@FXML
	private CheckBox productPrintedCheckbox;
	@FXML
	private CheckBox productPaperCheckbox;
	@FXML
	private TextField productPriceField;
	@FXML
	private Button productAddButton;
	@FXML
	private TableView<ProductFx> productTableView;
	@FXML
	private TableColumn<ProductFx, String> nameColumn;
	@FXML
	private TableColumn<ProductFx, Boolean> printedColumn;
	@FXML
	private TableColumn<ProductFx, Boolean> paperProductColumn;
	@FXML
	private TableColumn<ProductFx, Number> priceColumn;
	@FXML
	private TableColumn<ProductFx, Number> widthColumn;
	@FXML
	private TableColumn<ProductFx, Number> heightColumn;
	@FXML
	private MenuItem deleteMenuItem;
	
	private AddProductModel addProductModel;
	private ProductDao productDao;
	
	@Autowired
	public AddProductController(AddProductModel addProductModel, ProductDao productDao) {
		this.addProductModel = addProductModel;
		this.productDao = productDao;
	}

	@FXML
	public void initialize() {
		bindings();
		initTable();
	}

	private void bindings() {       
		productWidthField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		productWidthField.disableProperty().bind(productPaperCheckbox.selectedProperty().not());
		
		productHeightField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
		productHeightField.disableProperty().bind(productPaperCheckbox.selectedProperty().not());
		
		productPriceField.setTextFormatter(new TextFormatter<>(new DoubleStringConverter()));
		productPriceField.disableProperty().bind(productPaperCheckbox.selectedProperty());
		
		productAddButton.disableProperty().bind(productNameField.textProperty().isEmpty().or(productWidthField.textProperty().isEmpty()).or(productHeightField.textProperty().isEmpty()));
	}

	private void initTable() {
		productTableView.setItems(addProductModel.getProductFxObservableList());
		productTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			addProductModel.setSelectedProduct(newValue);
		});
		
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		printedColumn.setCellValueFactory(f -> f.getValue().getPrintedProperty());
		printedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(p -> {
		    ProductFx productFx = addProductModel.getProductFxObservableList().get(p);
		    updateInDatabase(productFx);
		    if(productFx.isPrinted()) {
		    	productFx.setPaperProduct(true);
		    }
		    return productFx.getPrintedProperty();
		}));
		
		paperProductColumn.setCellValueFactory(cellData -> cellData.getValue().getPaperProductProperty());
		paperProductColumn.setCellFactory(CheckBoxTableCell.forTableColumn(p -> {
		    ProductFx productFx = addProductModel.getProductFxObservableList().get(p);
		    updateInDatabase(productFx);
		    if(!productFx.isPaperProduct()) {
		    	productFx.setPrinted(false);
		    }
		    return productFx.getPaperProductProperty();
		}));
		
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPriceProperty());
		priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		widthColumn.setCellValueFactory(cellData -> cellData.getValue().getWidthProperty());
		widthColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		heightColumn.setCellValueFactory(cellData -> cellData.getValue().getHeightProperty());
		heightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
	}
	
	@FXML
	public void onAddProduct() {
		ProductFx productFx = new ProductFx();
		productFx.setName(productNameField.getText());

		productFx.setPrinted(productPrintedCheckbox.isSelected());
		productFx.setPaperProduct(productPaperCheckbox.isSelected());
		if(!productPriceField.getText().isEmpty()) {
			productFx.setPrice(Double.valueOf(productPriceField.getText()));
		}
		if(!productWidthField.getText().isEmpty()) {
			productFx.setWidth(Integer.valueOf(productWidthField.getText()));
		}
		if(!productHeightField.getText().isEmpty()) {
			productFx.setHeight(Integer.valueOf(productHeightField.getText()));
		}
		
		Product product = ProductConverter.convertToProduct(productFx);
		try {
			productDao.createOrUpdate(product);
			clearForm();
			productFx.setId(product.getId());
			addProductModel.addProduct(productFx);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private void clearForm() {
		productNameField.setText("");
		productWidthField.setText("");
		productHeightField.setText("");
		productPrintedCheckbox.setSelected(false);
		productPaperCheckbox.setSelected(false);
	}

	@FXML
	public void onProductPrintedSelected() {
		if(productPrintedCheckbox.isSelected()) {
			productPaperCheckbox.setSelected(true);
		}
	}

	@FXML
	public void onDeleteProduct() {
		ProductFx selectedProduct = addProductModel.getSelectedProduct();
		addProductModel.getProductFxObservableList().remove(selectedProduct);
		deleteFromDatabase(selectedProduct);
	}

	@FXML
	public void onEditName(TableColumn.CellEditEvent<ProductFx, String> e) {
		ProductFx productFx = e.getRowValue();
		productFx.setName(e.getNewValue());
		updateInDatabase(productFx);
	}

	@FXML
	public void onEditPrice(TableColumn.CellEditEvent<ProductFx, Number> e) {
		ProductFx productFx = e.getRowValue();
		productFx.setPrice(e.getNewValue().doubleValue());
		updateInDatabase(productFx);
	}

	@FXML
	public void onEditWidth(TableColumn.CellEditEvent<ProductFx, Number> e) {
		ProductFx productFx = e.getRowValue();
		productFx.setWidth(e.getNewValue().intValue());
		updateInDatabase(productFx);
	}

	@FXML
	public void onEditHeight(TableColumn.CellEditEvent<ProductFx, Number> e) {
		ProductFx productFx = e.getRowValue();
		productFx.setHeight(e.getNewValue().intValue());
		updateInDatabase(productFx);
	}
	

	private void updateInDatabase(ProductFx productFx) {
		try {
			productDao.createOrUpdate(ProductConverter.convertToProduct(productFx));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteFromDatabase(ProductFx selectedProduct) {
		try {
			productDao.delete(ProductConverter.convertToProduct(selectedProduct));
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
