package wd.weselnedetale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

@Component
public class TopMenuButtonsController {
	
	@Autowired
	private MainController mainController;
	
	@FXML
	public void onCreateOrder(ActionEvent event) {
		mainController.setCenter(MainController.CREATE_ORDER_FXML);
	}
	
	@FXML
	public void onCreatePaper() {
		mainController.setCenter(MainController.ADD_PAPER_FXML);
	}

	@FXML
	public void onCreateProduct() {
		mainController.setCenter(MainController.ADD_PRODUCT_FXML);
	}

	@FXML
	public void onAddWeddingSet() {
		mainController.setCenter(MainController.ADD_WEDDING_SET_FXML);
	}
}
