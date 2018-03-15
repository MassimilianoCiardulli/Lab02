package it.polito.tdp.alien;

/**
 * Sample Skeleton for 'Alien.fxml' Controller Class
 */

import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AlienController {
	
	private AlienDictionary dictionary;
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField txtWord;
    @FXML
    private TextArea txtResult;
    @FXML
    private Button btnTranslate;
    @FXML
    private Button btnReset;
        
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert btnTranslate != null : "fx:id=\"bntTranslate\" was not injected: check your FXML file 'Alien.fxml'.";
    	assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Alien.fxml'.";
    	
    	dictionary = new AlienDictionary();
    }
  
    
    @FXML
    void doTranslate(ActionEvent event) {
    	txtResult.clear();    
    	String inserito = txtWord.getText();
    	StringTokenizer st = new StringTokenizer(inserito,">");
    	
    	String a = st.nextToken().trim();
    	
    	if(this.controlla(a)) {
    		String alieno = a.substring(1, a.length()).toLowerCase();
        	System.out.println(alieno);
        	String b = "";
        	try {
        		b = st.nextToken().trim();
        	} catch(NoSuchElementException e) {
        		List<String> t = (List<String>) dictionary.translateWord(alieno);
        		if(t == null) {
        			txtResult.setText("Non ho trovato la parola...");
        			txtWord.setText("");
        			return;
        		}
        		StringBuilder sb = new StringBuilder();
        		sb.append("");
        		for(String s:t)
        			sb.append(s + "\n");
        		txtResult.setText(sb.toString());
        		txtWord.setText("");
        		return;
        	}
        	if(this.controlla(b)) {
        		String trad = b.substring(1, b.length()).toLowerCase();
        		System.out.println(trad);
        		dictionary.addWord(alieno, trad);
        		txtWord.setText("");
        	}
        	
        	
    	}
    	
    	
    }
    
    
    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    }
    
    public boolean controlla(String toCheck) {
    	if(toCheck.charAt(0) == '<')
    		return true;
    	return false;
    }
}
