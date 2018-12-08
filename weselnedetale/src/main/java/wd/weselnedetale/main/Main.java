package wd.weselnedetale.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import wd.weselnedetale.database.utils.DbManager;
import wd.weselnedetale.database.utils.FillDatabase;
import wd.weselnedetale.utils.FxmlUtils;

public class Main extends Application {

	private static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
		Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);
		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("tittle.application"));
		primaryStage.show();

		DbManager.initDatabase();
		FillDatabase.fillDatabase(); // w tym miejscu uruchamiam dodatkowy kod, który wypełnia bazę danych
	}

}
