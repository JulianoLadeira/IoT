package br.ufmg.dcc.iot.views;

import br.ufmg.dcc.iot.business.common.ReaderConn;
import br.ufmg.dcc.iot.controllers.BaseController;
import br.ufmg.dcc.iot.services.RFIDAutonomousReaderService;
import br.ufmg.dcc.iot.services.RFIDReaderService;
import br.ufmg.dcc.iot.services.RFIDService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	
	@FXML
	private TextArea tagList;
	
	//--- Attrs
	private ReaderConn selectedConnection;
	private Boolean inExecution;
	private RFIDService readerService;
	
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
		readTriesText.setPromptText(autonomousCheckbox.isSelected() ? "Tempo de Execução (segundos)" : "Nº de Leituras");
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
		return selectedConnection != null && !readTriesText.getText().isEmpty();
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
		Long attempts = 1L;
		
		try {
			attempts = Long.parseLong(readTriesText.getText());
		} catch (NumberFormatException ex) {
			return;
		}
		
		try {
			inExecution = true;
			
			handleExecutionView(true);
			
			if (autonomousCheckbox.isSelected())
				readerService = new RFIDAutonomousReaderService(selectedConnection, attempts * 1000);
			else
				readerService = new RFIDReaderService(selectedConnection, attempts);
				
			readerService.start(metrics -> {		
				Platform.runLater(() -> {
					if (metrics.getTagReads() == null) {
						handleExecutionView(false);
					} else {
						tagList.setText(metrics.getTagString());						
						readLabel.setText(metrics.getReadAmount().toString());
						readRateLabel.setText(String.format("%4.3f" , metrics.getReadRate()) + " tags/s");
						successRateLabel.setText(String.format("%4.3f" , metrics.getSuccessRatePercentage()) + "%");
					}
				});				
			});			
		} catch (Exception e) {
			handleExecutionView(false);
			e.printStackTrace();
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro");
			alert.setHeaderText("Ocorreu um erro na leitura das tags");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	
}
