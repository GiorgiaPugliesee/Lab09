
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    
    @FXML
    private ComboBox<Country> cmbStati;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	
    	String input = this.txtAnno.getText();
    	int inputNum = 0;
    	
    	try {
    		inputNum = Integer.parseInt(input);
    	} catch(NumberFormatException e) {
    		this.txtResult.setText("Input non valido.");
    		return;
    	}
    	
    	if(inputNum >= 1816 && inputNum <= 2016) {
    		this.model.creaGrafo(inputNum);
    		
    		this.txtResult.appendText("Il grafo creato ha " + this.model.getGrafo().vertexSet().size() + " vertici.\n");
        	this.txtResult.appendText("Il grafo creato ha " + this.model.getGrafo().edgeSet().size() + " archi.\n");
        	
        	for(Country c : model.getGrafo().vertexSet()) {
				this.txtResult.appendText("Lo stato " + c.getStateNme() + " confina con " + model.getGrafo().degreeOf(c) + " stati.\n");
			}
        	
        	this.txtResult.appendText("Il grafo ha " + this.model.calcolaConnessa() + " componenti connesse.\n");
        	
    		
    	} else {
    		this.txtResult.setText("Anno inserito non valido. Inserire un anno compreso tra il 1816 e il 2016.");
    	}

    }
    
    @FXML
    void doCalcolaStatiRaggiungibili(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Country c = this.cmbStati.getValue();
    	
    	if(this.model.trovaStatiRaggiungibili(c).isEmpty()) {
    		this.txtResult.setText("Lo stato selezionato non è nel grafo. Riprovare.");
    		return;
    	}
    	
    	this.txtResult.appendText("Dallo stato " + c.getStateNme() + " è possibile raggiungere:\n");
    	for(Country co : this.model.trovaStatiRaggiungibili(c)) {
    		this.txtResult.appendText(co.getStateNme() + "\n");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert cmbStati != null : "fx:id=\"cmbStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	for(Country c : this.model.loadAllCountries()) {
    		this.cmbStati.getItems().add(c);
    	}
    	
    }
}
