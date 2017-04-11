package br.ufmg.dcc.iot.controllers;

import br.ufmg.dcc.iot.Main;
import br.ufmg.dcc.iot.views.RootController;
import javafx.application.Application;

public abstract class BaseController {

	private RootController rootController;
	private Main mainApp;
	
	public abstract void postShow();
	
	public void setRootController(RootController rootController) {
		this.rootController = rootController;
	}
	
	public void setMainApp(Application mainApp) {
		this.mainApp = (Main) mainApp;
	}
	
	protected Main getMainApp() {
		return mainApp;
	}
	
	protected void historyBack() {
		if (rootController != null)
			rootController.popView();
	}
	
}
