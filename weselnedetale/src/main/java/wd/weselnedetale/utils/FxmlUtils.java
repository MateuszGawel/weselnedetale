package wd.weselnedetale.utils;

import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import wd.weselnedetale.main.Main;

public class FxmlUtils {

	public static Pane fxmlLoader(String fxmlPath) {
		FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
		loader.setControllerFactory(Main.getSpringContext()::getBean);
		loader.setResources(getResourceBundle());
		try {
			return loader.load();
		} catch (Exception e) {
			DialogsUtils.errorDialog(e);
		}
		return null;
	}
	
	public static ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("bundles.messages");
	}

}
