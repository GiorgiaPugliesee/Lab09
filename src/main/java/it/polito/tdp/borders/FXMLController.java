
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
