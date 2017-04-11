package br.ufmg.dcc.iot.views;

import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.controllers.BaseController;
import br.ufmg.dcc.iot.services.ReaderService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController extends BaseController {
	
	//--- Views
	@FXML
	private ComboBox<ReaderConn> connectionsList;
	
	@FXML
	private CheckBox autonomousCheckbox;
	
	@FXML
	private Button startReaderButton;
	
	@FXML
	private Button saveResultsButton;
	
	@FXML
	private TextField readTriesText;
	
	@FXML
	private Label readLabel;
	
	@FXML
	private Label successRateLabel;
	
	@FXML
	private Label readRateLabel;
	
	//--- Attrs
	private ReaderConn selectedConnection;
	private Boolean inExecution;
	private ReaderService readerService;
	
	@Override
	public void postShow() {		
		inExecution = false;
		
		connectionsList.getItems().addAll(
				ReaderConn.createALR9650Conn(), 
				ReaderConn.createALR9900Conn());
		
		readTriesText.textProperty().addListener((observable, oldValue, newValue) -> {
			startReaderButton.setDisable(!isStartEnabled());
		});
	}
	
	@FXML
	private void onSelectConnection() {
		selectedConnection = connectionsList.getSelectionModel().getSelectedItem();
		startReaderButton.setDisable(!isStartEnabled());
	}
	
	@FXML
	private void onAutonomousChecked() {
		readTriesText.setVisible(!autonomousCheckbox.isSelected());
		readTriesText.setText("");
		startReaderButton.setDisable(!isStartEnabled());
	}
	
	@FXML
	private void onStartRead() {
		if (inExecution) {
			readerService.stop();
			handleExecutionView(false);
		} else {
			executeReader();
		}
	}
	
	@FXML
	private void onSaveResults() {
		
	}
	
	private boolean isStartEnabled() {
		return selectedConnection != null && ((!autonomousCheckbox.isSelected() 
				&& !readTriesText.getText().isEmpty()) || autonomousCheckbox.isSelected());
	}
	
	private void handleExecutionView(boolean inExecution) {
		this.inExecution = inExecution;
		
		connectionsList.setDisable(inExecution);
		autonomousCheckbox.setDisable(inExecution);
		//saveResultsButton.setDisable(inExecution);
		readTriesText.setDisable(inExecution);
		startReaderButton.setText(inExecution ? "PARAR LEITURA" : "INICIAR LEITURA");
	}
	
	private void executeReader() {
		Long attempts = 0L;
		
		try {
			attempts = Long.parseLong(readTriesText.getText());
		} catch (NumberFormatException ex) {
			//pass
		}
		
		try {
			inExecution = true;
			
			handleExecutionView(true);
			
			readerService = new ReaderService(selectedConnection, attempts);
			readerService.read(metrics -> {				
				readLabel.setText(metrics.getReadAmount().toString());
				readRateLabel.setText(String.format("%4.3f" , metrics.getReadRate()) + " tags/ns");
				successRateLabel.setText(String.format("%4.3f" , metrics.getSuccessRatePercentage()) + "%");
			});			
		} catch (Exception e) {
			handleExecutionView(false);
			e.printStackTrace();
		}
	}
	
}
