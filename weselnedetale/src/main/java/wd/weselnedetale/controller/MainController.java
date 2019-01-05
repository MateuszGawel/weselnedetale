package wd.weselnedetale.controller;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

import java.util.Optional;

import org.springframework.stereotype.Component;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import wd.weselnedetale.utils.DialogsUtils;
import wd.weselnedetale.utils.FxmlUtils;

@Component
public class MainController {
	static final String CREATE_ORDER_FXML = "/fxml/CreateOrder.fxml";
	static final String ADD_PAPER_FXML = "/fxml/AddPaper.fxml";
	static final String ADD_PRODUCT_FXML = "/fxml/AddProduct.fxml";
	static final String ADD_WEDDING_SET_FXML = "/fxml/AddWeddingSet.fxml";
	
	@FXML
	private BorderPane borderPane;
    @FXML
    private TopMenuButtonsController topMenuButtonsController;
    
	private Pane createOrderPane;
	
	@FXML
    private void initialize() {
        createOrderPane = FxmlUtils.fxmlLoader(CREATE_ORDER_FXML);
    }

	public void setCenter(String fxmlPath) {
		if(CREATE_ORDER_FXML.equals(fxmlPath)) {
			borderPane.setCenter(createOrderPane);
		}
		else {
			borderPane.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
		}
	}

	public void closeApplication() {
		Optional<ButtonType> result = DialogsUtils.confirmationDialog();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
			System.exit(0);
		}
	}

	public void setCaspian() {
		Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
	}

	public void setModena() {
		Application.setUserAgentStylesheet(STYLESHEET_MODENA);
	}

	public void setAlwaysOnTop(ActionEvent actionEvent) {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
		stage.setAlwaysOnTop(value);
	}

	public void about() {
		DialogsUtils.dialogAboutApplication();
	}
}
