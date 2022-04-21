/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 *
 * @author GhAlone
 */
public class ChangerPasseController implements Initializable {
     @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField cofirmMdp;
    @FXML
    private Button btn_valid;
    
    private int id;
    @FXML
    private Label lab_id;

    @FXML
    void changerpasse(ActionEvent event) {
          

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_valid.setOnAction(event -> {
             
             if (mdp.getText().equals(cofirmMdp.getText())){
                   ServiceUtilisateur o =new ServiceUtilisateur();
                   o.update((mdp.getText()), Integer.parseInt(lab_id.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Votre mdp a été changé avec succés!");
        alert.show();
        
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AceuilUser.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Brainovation");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ChangerPasseController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        
             }else {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Ces mots de passe ne correspondent pas. Veuillez réessayer.!");
        alert.show();
             }
        });
    }
    
    public void setId(int a){
        this.id= a;
        lab_id.setText(a+"");
      
        
    }
    
}
