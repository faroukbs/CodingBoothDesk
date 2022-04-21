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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author GhAlone
 */
public class PasseController implements Initializable {
     @FXML
    private Label lab_email;
    @FXML
    private TextField txt_code;
    private int id;
    private String code;
    private String email;
    @FXML
    private Button btn_confirm;
    @FXML
    private Label lab_code;
    @FXML
    private Label lab_msg;
    @FXML
    private Label lab_id;
    
      
    
    public void setId(int a){
        this.id= a;
        lab_id.setText(a+"");
      
        
    }
     public void setEmail(String a){
        this.email= a;
        lab_email.setText(a);
      
        
    }
     
   public void setCode(String b){
      
          this.code= b;
          lab_code.setText(b);
      
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_confirm.setOnAction(event -> {
             
             ServiceUtilisateur op =new ServiceUtilisateur();
             String code =op.recCode(Integer.parseInt(lab_id.getText()));
             System.out.println(txt_code.getText().equals(lab_code.getText()));
             System.out.println(lab_code.getText());
             if (txt_code.getText().equals(code)){
             try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/changerpasse.fxml"));
        
            Parent root = loader.load();
              ChangerPasseController cont = loader.getController();
           
            cont.setId(Integer.parseInt(lab_id.getText()));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Réinitialiser mot de passe");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PasseController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        
             }else {
                 lab_msg.setText("Le code inséré est incorrect !");
             }
        });
    }
    
}
