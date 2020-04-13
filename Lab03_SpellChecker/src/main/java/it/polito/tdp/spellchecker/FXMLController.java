
package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Modello;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Modello model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private Button btnTranslate;

    @FXML
    private TextArea textToTranslate;

    @FXML
    private TextArea textTranslation;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblErrors;

    @FXML
    private Label lblTime;

    @FXML
    void clear(ActionEvent event) {
    	textToTranslate.clear();
    	textTranslation.clear();
    	lblErrors.setText("");
    	lblTime.setText("");
    }

    @FXML
    void translate(ActionEvent event) {
    	String s = textToTranslate.getText();
    	String result = model.traduci(s, cmbLanguage.getValue());
    	textTranslation.setText(result);
    	lblErrors.setText("The text contains "+ model.getCntErr() +" errors!");
    }

    @FXML
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert textToTranslate != null : "fx:id=\"textToTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert textTranslation != null : "fx:id=\"textTranslation\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    void setModel (Modello m) {
    	model = m;
    	cmbLanguage.getItems().addAll("English", "Italian");
    	cmbLanguage.setValue("English");
    }
}
