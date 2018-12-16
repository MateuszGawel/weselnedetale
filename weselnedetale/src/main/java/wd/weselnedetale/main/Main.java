package wd.weselnedetale.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import wd.weselnedetale.database.utils.DbManager;
import wd.weselnedetale.database.utils.FillDatabase;
import wd.weselnedetale.utils.FxmlUtils;

@SpringBootApplication
public class Main extends Application {

	private static final String BORDER_PANE_MAIN_FXML = "/fxml/BorderPaneMain.fxml";
	private static ConfigurableApplicationContext springContext;
	
	public static void main(String[] args) {
		launch(args);
	}
	 
	@Override
    public void init() throws Exception {
        springContext = SpringApplication.run(this.getClass());
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane borderPane = FxmlUtils.fxmlLoader(BORDER_PANE_MAIN_FXML);
		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("tittle.application"));
		primaryStage.show();

		DbManager.initDatabase();
		FillDatabase.fillDatabase();
	}

	@Override
    public void stop() throws Exception {
        springContext.stop();
    }

	public static ConfigurableApplicationContext getSpringContext() {
		return springContext;
	}
}
