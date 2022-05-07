/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
}
