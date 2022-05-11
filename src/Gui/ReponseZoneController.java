/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Reclamation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author lylyy
 */
public class ReponseZoneController implements Initializable {
static int RepondeurConnectedId=1;
    @FXML
    private TextArea Liste;
    @FXML
    private Button home;
    @FXML
    private Button ligne;
    @FXML
    private Button commande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 Liste.setEditable(false);
        ServiceReclamation SR = new ServiceReclamation();
        
         List<Reclamation> Rlist = SR.ReclamationParRepondeur(RepondeurConnectedId);
         for(Reclamation r : Rlist){
        Liste.setText(Liste.getText() + " You are warned : " + r.getMessage());
        }
    }    

    @FXML
    private void retourLigne(ActionEvent event) {
    }

    @FXML
    private void retourCom(ActionEvent event) {
    }
    
}
