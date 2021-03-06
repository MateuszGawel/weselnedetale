package wd.weselnedetale.controller;

import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import wd.weselnedetale.converter.PositionConverter;
import wd.weselnedetale.database.dao.OrderDao;
import wd.weselnedetale.database.dao.PositionDao;
import wd.weselnedetale.database.model.Order;
import wd.weselnedetale.database.model.Position;
import wd.weselnedetale.main.ConfigurationKeys;
import wd.weselnedetale.model.AddOrderModel;
import wd.weselnedetale.model.fx.PaperFx;
import wd.weselnedetale.model.fx.PositionFx;
import wd.weselnedetale.model.fx.ProductFx;
import wd.weselnedetale.model.fx.WeddingSetFx;
import wd.weselnedetale.utils.CalculationsUtils;
import wd.weselnedetale.utils.exception.ApplicationException;

@Component
public class CreateOrderController {
	@FXML
	private TextField orderNameField;
	@FXML
	private ComboBox<WeddingSetFx> weddingSetComboBox;
	@FXML
	private ComboBox<ProductFx> productComboBox;
	@FXML
	private TextField amountField;
	@FXML
	private TextField productNameField;
	@FXML
	private CheckBox isPersonalizedCheckBox;
	@FXML
	private CheckBox isDummyCheckBox;
	@FXML
	private CheckBox isSpecialCheckBox;
	@FXML
	private ComboBox<PaperFx> paperComboBox;
	@FXML
	private Button addButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button generateFileButton;
	@FXML
	private Button clearButton;
	@FXML
	private Button loadButton;
	
	@FXML
	private TableView<PositionFx> productOrderTableView;
	@FXML
	private TableColumn<PositionFx, String> positionNameColumn;
	@FXML
	private TableColumn<PositionFx, WeddingSetFx> weddingSetColumn;
	@FXML
	private TableColumn<PositionFx, ProductFx> productNameColumn;
	@FXML
	private TableColumn<PositionFx, Number> amountColumn;
	@FXML
	private TableColumn<PositionFx, PaperFx> paperColumn;
	@FXML
	private TableColumn<PositionFx, Number> countOnPaperColumn;
	@FXML
	private TableColumn<PositionFx, Number> totalCountColumn;
	@FXML
	private TableColumn<PositionFx, Number> paperCountColumn;
	@FXML
	private TableColumn<PositionFx, Number> singleCostColumn;
	@FXML
	private TableColumn<PositionFx, Number> totalCostColumn;
	
	@FXML
	private MenuItem deleteMenuItem;
	
	private AddOrderModel addOrderModel;
	private PositionDao positionDao;
	private OrderDao orderDao;
	
	@Autowired
	public CreateOrderController(AddOrderModel addOrderModel, PositionDao positionDao, OrderDao orderDao) {
		this.addOrderModel = addOrderModel;
		this.positionDao = positionDao;
		this.orderDao = orderDao;
	}

	@FXML
	public void initialize() {
		bindings();
		initTable();
	}
	
	public void reloadWeddingSets() {
		addOrderModel.reload();
	}
	
	private void bindings() {
		reloadWeddingSets();
		weddingSetComboBox.setItems(addOrderModel.getWeddingSetFxObservableList());
		productComboBox.setItems(addOrderModel.getProductFxObservableList());
		paperComboBox.setItems(addOrderModel.getPaperFxObservableList());
		productComboBox.disableProperty().bind(weddingSetComboBox.valueProperty().isNull());
		addButton.disableProperty().bind(productNameField.textProperty().isEmpty().or(productComboBox.valueProperty().isNull()));
		amountField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
	}

	private void initTable() {
		productOrderTableView.setItems(addOrderModel.getPositionFxObservableList());
		productOrderTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			addOrderModel.setSelectedPosition(newValue);
		});
		
		positionNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		positionNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		weddingSetColumn.setCellValueFactory(cellData -> cellData.getValue().getWeddingSetProperty());
		
		productNameColumn.setCellValueFactory(cellData -> cellData.getValue().getProductProperty());
		
		amountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
		amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		paperColumn.setCellValueFactory(cellData -> cellData.getValue().getPaperProperty());
		
		countOnPaperColumn.setCellValueFactory(cellData -> cellData.getValue().getCountOnPaperProperty());
		
		totalCountColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalCountProperty());
		paperCountColumn.setCellValueFactory(cellData -> cellData.getValue().getPaperCountProperty());
		singleCostColumn.setCellValueFactory(cellData -> cellData.getValue().getSingleCostProperty());
		totalCostColumn.setCellValueFactory(cellData -> cellData.getValue().getTotalCostProperty());
		
		singleCostColumn.setCellFactory(tc -> getCurrencyFormattedCell());
		totalCostColumn.setCellFactory(tc -> getCurrencyFormattedCell());
		
		paperComboBox.setDisable(true);
	}
	
	private TableCell<PositionFx, Number> getCurrencyFormattedCell() {
		TableCell<PositionFx, Number> currencyFormattedCell = new TableCell<PositionFx, Number>() {
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
		    @Override
		    protected void updateItem(Number price, boolean empty) {
		        super.updateItem(price, empty);
		        if (empty) {
		            setText(null);
		        } else {
		            setText(currencyFormat.format(price));
		        }
		    }
		};
		return currencyFormattedCell;
	}

	@FXML
	public void onWeddingSetChange() throws ApplicationException {
		if(!isSpecialCheckBox.isSelected()) {
			addOrderModel.fillProductList(weddingSetComboBox.getValue());
			addOrderModel.fillPaperList(weddingSetComboBox.getValue());
		}
		else{
			addOrderModel.fillProductList(null);
		}
	}

	@FXML
	public void onAddProduct() {
		PositionFx positionFx = new PositionFx();
		positionFx.setName(productNameField.getText());
		positionFx.setWeddingSet(weddingSetComboBox.getValue());
		positionFx.setProduct(productComboBox.getValue());
		positionFx.setAmount(amountField.getText().isEmpty() ? 1 : Integer.valueOf(amountField.getText()));
		positionFx.setPaper(paperComboBox.getValue());
		if(productComboBox.getValue().isPaperProduct()) {
			calculatepositionValues(positionFx);
		}
		addOrderModel.addPosition(positionFx);
		clearSinglePositionFields();
	}

	private void clearSinglePositionFields() {
		productComboBox.setValue(null);
		paperComboBox.setValue(null);
		isDummyCheckBox.setSelected(false);
		isPersonalizedCheckBox.setSelected(false);
		isSpecialCheckBox.setSelected(false);
		productNameField.clear();
	}

	private void calculatepositionValues(PositionFx positionFx) {
		positionFx.setCountOnPaper(CalculationsUtils.calculateCountOnPaper(positionFx.getProduct(), positionFx.getPaper()));
		positionFx.setTotalCount(CalculationsUtils.calculateTotalCount(positionFx.getAmount(), positionFx.getCountOnPaper()));
		positionFx.setPaperCount(CalculationsUtils.calculatePaperCount(positionFx.getTotalCount(), positionFx.getCountOnPaper()));
		boolean printed = positionFx.getProduct().isPrinted();
		positionFx.setSingleCost(CalculationsUtils.calculateSingleCost(positionFx.getPaper().getPrice(), printed ? ConfigurationKeys.COLOR_PRINT_COST : 0, positionFx.getCountOnPaper()));
		positionFx.setTotalCost(CalculationsUtils.calculateTotal(positionFx.getSingleCost(), positionFx.getTotalCount()));
	}

	@FXML
	public void onEditName(TableColumn.CellEditEvent<PositionFx, String> e) {
		PositionFx positionFx = e.getRowValue();
		positionFx.setName(e.getNewValue());
	}

	@FXML
	public void onEditAmount(TableColumn.CellEditEvent<PositionFx, Number> e) {
		PositionFx positionFx = e.getRowValue();
		e.getRowValue().setAmount(e.getNewValue().intValue());
		calculatepositionValues(positionFx);
	}

	@FXML
	public void onDeletePosition() {
		addOrderModel.getPositionFxObservableList().remove(addOrderModel.getSelectedPosition());
	}

	@FXML
	public void onSpecialSelected() throws ApplicationException {
		ProductFx value = productComboBox.getValue();
		if(isSpecialCheckBox.isSelected()) {
			addOrderModel.fillProductList(null);
		}
		else {
			addOrderModel.fillProductList(weddingSetComboBox.getValue());
		}
		productComboBox.setValue(value);
	}

	@FXML
	public void onProductChange() {
		if(productComboBox.getValue() != null) {
			boolean paperProduct = productComboBox.getValue().isPaperProduct();
			paperComboBox.setDisable(!paperProduct);
		}
		else {
			paperComboBox.setDisable(true);
		}
	}

	@FXML
	public void onLoad() {
		//TODO show modal view with list of orders ordered by date + buttons load/generate/delete
	}

	@FXML
	public void onSave() {
		Order order = new Order();
		order.setName(orderNameField.getText());
		
		try {
			orderDao.createOrUpdate(order);
		} catch (ApplicationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		addOrderModel.getPositionFxObservableList().forEach(p -> {
			try {
				Position position = PositionConverter.convertToPosition(p);
				position.setOrder(order);
				positionDao.createOrUpdate(position);
			} catch (ApplicationException e) {
				e.printStackTrace();
				return;
			}
		});
		clearSinglePositionFields();
		clearOrderFields();
	}

	@FXML
	public void onClear() {
		clearSinglePositionFields();
		clearOrderFields();
	}

	private void clearOrderFields() {
		addOrderModel.clearPositions();
		orderNameField.clear();
		weddingSetComboBox.setValue(null);
		amountField.clear();
	}

	@FXML
	public void onGenerateFile() {
		//TODO export file to hergon
	}

}
