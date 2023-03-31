/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;
	List<Corso> listaCorsi=new LinkedList<Corso>();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonCercaCorsi"
    private Button buttonCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="buttonCercaMatricola"
    private Button buttonCercaMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="buttonIscrivi"
    private Button buttonIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="buttonReset"
    private Button buttonReset; // Value injected by FXMLLoader

    @FXML // fx:id="cercaIscrittiButton"
    private Button cercaIscrittiButton; // Value injected by FXMLLoader

    @FXML // fx:id="comboBox"
    private ComboBox<Corso> comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtInserimentoMatricola"
    private TextField txtInserimentoMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void ResetAction(ActionEvent event) {
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtInserimentoMatricola.clear();
    	this.txtRisultato.clear();
    	this.comboBox.setValue(null);
    }

    @FXML
    void cercaCorsi(ActionEvent event) {
    	String matricola=this.txtInserimentoMatricola.getText();
    	int matricolaIntero=0;
    	try {
	    	matricolaIntero=Integer.parseInt(matricola);
	    	List<Corso> listaCorsi=this.model.getCorsiStudente(matricolaIntero);
	    	//this.txtRisultato.setText("CORSI DELLO STUDENTE: "+matricola);
	    	if (this.comboBox.getValue()==null){
	    		this.txtRisultato.setText("CORSI DELLO STUDENTE: "+matricola);
	    		if (listaCorsi.size()==0)
	    			this.txtRisultato.setText("Studente non iscritto a nessun corso");
		    	for (Corso c: listaCorsi) {
		    		this.txtRisultato.appendText("\n"+c);
		    		}
	    	}
	    	else {
	    		for (Corso c: listaCorsi) {
		    		if (comboBox.getValue().getCodins().equals(c.getCodins()))
		    			this.txtRisultato.setText("Lo studente "+matricola+" è regolamente iscritto al corso "+ c);
		    			
		    		}
	    		
	    	}
    			
    	
    	
    	}catch (NumberFormatException e) {
    		//e.printStackTrace();
    		this.txtRisultato.setText("Inserire un intero");
    		this.txtNome.clear();
    		this.txtCognome.clear();
    	}

    	
    	

    }

    @FXML
    void cercaIscrittiCorso(ActionEvent event) {
    	Corso corso=this.comboBox.getValue();
    	if (corso==null) {
    		this.txtRisultato.setText("inserire un corso");
    		return;}
    	List<Studente> listaIscritti=this.model.getStudentiCorso(corso);
    	this.txtRisultato.setText("STUDENTI ISCITTI AL CORSO: "+corso);
    	if (listaIscritti==null) {
    		this.txtRisultato.setText("Non è presente nessuno studente iscritto al corso");
    		return;}
    	for (Studente s: listaIscritti) {
    		this.txtRisultato.appendText("\n"+s.toString());
    		}

    }

    @FXML
    void cercaMatricola(ActionEvent event) {
    	String matricola=this.txtInserimentoMatricola.getText();
    	int matricolaIntero=0;
    	try {
	    	matricolaIntero=Integer.parseInt(matricola);
	    	Studente studente=this.model.getStudente(matricolaIntero);
	    	if (studente==null){
	    		this.txtRisultato.setText("Studente non esistente");
	    		return;
	    	}
	    	
	    	this.txtNome.setText(studente.getNome());
	    	this.txtCognome.setText(studente.getCognome());
    		}
    	catch (NumberFormatException e) {
    		//e.printStackTrace();
    		this.txtRisultato.setText("Inserire un intero");
    	}

    }

    @FXML
    void iscriviStudente(ActionEvent event) {
    	String matricola=this.txtInserimentoMatricola.getText();
	    int matricolaIntero=0;
	    Corso corso=this.comboBox.getValue();
    	if (corso==null) {
    		this.txtRisultato.setText("inserire un corso");
    		return;}
	    try {
	    	matricolaIntero=Integer.parseInt(matricola);
	    	try {
	    		boolean risultato=this.model.iscriviStudenteACorso(matricolaIntero, corso);
	    	if (risultato){
	    		this.txtRisultato.setText("studente iscritto in maniera positiva");
	    		return;
	    	}
	    	else
	    		this.txtRisultato.setText("matricola inesistente");
	    	}catch(SQLException e) {
				this.txtRisultato.setText("impossibile inserire iscrizione (iscrizione già presente)");
				}
				
			}
		catch (NumberFormatException e) {
			
			this.txtRisultato.setText("Inserire un intero");
		}
	
	    }

    @FXML
    void selectCorso(ActionEvent event) {
    	//this.listaCorsi=this.model.getTuttiICorsi();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert buttonCercaCorsi != null : "fx:id=\"buttonCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonCercaMatricola != null : "fx:id=\"buttonCercaMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonIscrivi != null : "fx:id=\"buttonIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonReset != null : "fx:id=\"buttonReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cercaIscrittiButton != null : "fx:id=\"cercaIscrittiButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInserimentoMatricola != null : "fx:id=\"txtInserimentoMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
       
       
        
    }
    
    
    
    
    public void setModel(Model model) {
    	this.model=model;
    	this.listaCorsi=this.model.getTuttiICorsi(); // questi metodi devo metterli qua perchè altrimenti il modello non esiste ancora nell'initialize
    	this.comboBox.getItems().add(null);
    	this.comboBox.getItems().addAll(listaCorsi);
    }

}
