package br.ufmg.dcc.iot;
	
import java.io.IOException;

import br.ufmg.dcc.iot.controllers.BaseController;
import br.ufmg.dcc.iot.views.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Stage primaryStage;
	private RootController rootController;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("DCC/UFMG - IoT 2017/01");
		
		initRootLayout();
		loadHomeView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void loadHomeView() {
		rootController.clearHistoryViews();
		rootController.pushView(loadView("views/HomeView.fxml", false));
	}

	private Node loadView(String resourceName, boolean isRoot) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(resourceName));
			Node view = loader.load();
			BaseController controller = loader.getController();
			
			if (controller != null) {
				controller.setMainApp(this);
				controller.setRootController(rootController);
				controller.postShow();
			}
			
			if (isRoot)
				rootController = (RootController) controller;
			
			return view;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return new Pane();
	}
	
	private void initRootLayout() {
		Scene scene = new Scene((BorderPane) loadView("views/RootView.fxml", true));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
