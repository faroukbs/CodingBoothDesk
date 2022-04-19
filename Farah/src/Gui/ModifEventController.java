/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Eventl;
import Services.ServiceEventl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;
/**
 * FXML Controller class
 *
 * @author Home
 */
public class ModifEventController implements Initializable {
 int idevent= 5;
    @FXML
    private TextField titretx;
    @FXML
    private DatePicker tfDateDebut;
    @FXML
    private TextField villetx;
    @FXML
    private DatePicker tfDateFin;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button btnModifier;
        ServiceEventl ec = new ServiceEventl();
    Eventl e;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        e = ec.afficherEvenement(idevent);
   
              titretx.setText(e.getTitre());
        tfDateDebut.setValue(e.getDatedebut().toLocalDate());
        tfDateFin.setValue(e.getDatefin().toLocalDate());
        
        villetx.setText(e.getVille());
        tfDescription.setText(e.getDescription());
     
    }    

    @FXML
    private void modifierEvenement(ActionEvent event) {
             if (titretx.getText().isEmpty() || villetx.getText().isEmpty()|| tfDescription.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else {
                  e.setTitre(titretx.getText());
               e.setVille(villetx.getText());
        e.setDescription(tfDescription.getText());
        
        e.setDatedebut(java.sql.Date.valueOf(tfDateDebut.getValue()));
        e.setDatefin(java.sql.Date.valueOf(tfDateFin.getValue()));
  
        ec.modifier(e);
         Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succesful");
            alert.setHeaderText(null);
            alert.setContentText(" Evenement modifié avec succéez!");
            alert.show();
        ((Stage) btnModifier.getScene().getWindow()).close();
    }}

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }
    }
    

