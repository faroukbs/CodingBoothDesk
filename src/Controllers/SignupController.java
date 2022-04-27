/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;



import Entities.Utilisateur;
import Services.ControleSaisie;
import Services.ServiceUtilisateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import static javafx.scene.AccessibleRole.NODE;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;




/**
 *
 * @author GhAlone
 */
public class SignupController implements Initializable{
    
   @FXML
    private TextField num;

    @FXML
    private TextField txt_mdp;

    @FXML
    private Button signup;

    @FXML
    private CheckBox check;

    @FXML
    private Button btn_retour;

    @FXML
    private ImageView home;

    @FXML
    private Label passe;

   

    @FXML
    private TextField prenom;

    @FXML
    private TextField nom;

    @FXML
    private TextField email;

    @FXML
    private PasswordField mdp;

    
    
    @FXML
    private ImageView imageView;

    @FXML
    private Label img;
    
    private File selectedFile;
     

    ServiceUtilisateur us= new ServiceUtilisateur();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // type.getItems().addAll("ROLE_USER","partenaire");
         
      
        txt_mdp.setVisible(false);
       check.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_value, Boolean newValue) -> {
       if(check.isSelected()){
           txt_mdp.setText(mdp.getText());
           txt_mdp.setVisible(true);
           mdp.setVisible(false);
           return;
           
       }
      mdp.setText(txt_mdp.getText());
       mdp.setVisible(true);
       txt_mdp.setVisible(false);
       
       });
    }
    
    @FXML
    public void onChoseFile(ActionEvent event){
        FileChooser fc = new FileChooser();
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                imageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Hey");
        }
    }
    
    public void register(){
        
            
         
             if(ControleSaisie.isNull(nom.getText()) || ControleSaisie.isNull(prenom.getText()) || ControleSaisie.isNull(num.getText()) || ControleSaisie.isNull(mdp.getText()) || ControleSaisie.isNull(email.getText()) || selectedFile == null  )
           {
             Alert alerts = new Alert(Alert.AlertType.WARNING);
        alerts.setTitle("Warning");
        alerts.setHeaderText(null);
        alerts.setContentText("Veuillez remplir les champs!");
        alerts.show();
           }else {
                 
             if(ControleSaisie.validemail(email.getText())== false){
                email.setText("Veuillez saisir une adresse email valide !");
             } if(ControleSaisie.isnum(num.getText())==false){
                num.setText("numero invalide !");
                 
             }
            else if(ControleSaisie.validemail(email.getText())&&ControleSaisie.isnum(num.getText()) && selectedFile != null){
            try {
              //  RadioButton selectedRadioButton = (RadioButton) groupGender.getSelectedToggle();
            //String toogleGroupValue = selectedRadioButton.getText();
                Path from = Paths.get(selectedFile.toURI());
                Path to = Paths.get("C:\\Users\\MSI\\Desktop\\montasser-pidevDesktop\\montasser-pidevDesktop\\src\\Images/"+selectedFile.getName());
                
                //Files.copy(from,to);
                Utilisateur user = new Utilisateur(nom.getText(),prenom.getText(),Integer.parseInt(num.getText()),hashPassword(mdp.getText()) , email.getText(), to.normalize().toString());
                us.inscrir(user);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("succés!");
                 Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Sign_in.fxml"));
        email.getScene().setRoot(page1);
        //alert.setOnCloseRequest(event -> { 
            
             //   System.exit(0);
                
       // });
        alert.showAndWait();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
         }
         
    }
    
    private static String hashPassword(String txtpassword){
    return BCrypt.hashpw(txtpassword, BCrypt.gensalt());
    
}
}
