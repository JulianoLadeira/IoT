package br.ufmg.dcc.iot.views;

import br.ufmg.dcc.iot.controllers.BaseController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class RootController extends BaseController {
	
	@FXML
	private StackPane stackViews;

	@Override
	public void postShow() {
		
	}
	
	public void pushView(Node element) {
		stackViews.getChildren().add(element);
	}
	
	public void popView() {
		stackViews.getChildren().remove(stackViews.getChildren().size() - 1);
	}
	
	public void clearHistoryViews() {
		stackViews.getChildren().clear();
	}

}
