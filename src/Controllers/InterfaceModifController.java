package Controllers;

import Entities.Utilisateur;
import Services.ServiceAdmin;
import Services.ServiceUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author GhAlone
 */
public class InterfaceModifController implements Initializable{
    
     @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_prenom;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_genre;

    @FXML
    private TextField txt_num;

     @FXML
    private ComboBox type;

     @FXML
    private PasswordField txt_mdp;


    @FXML
    private Label id_lab;

    @FXML
    private Label lid;

     @FXML
    private ImageView imageView;
    
    
    
    

    

        private File selectedFile;

    ServiceAdmin admin = new ServiceAdmin();
    ServiceUtilisateur userser = new ServiceUtilisateur();
    private Button upph;

    
   
  
    
    
    
    public void setuser(Utilisateur user){
    txt_nom.setText(user.getNom());
    txt_prenom.setText(user.getPrenom());
    txt_genre.setText(user.getDate_naissance());
    txt_email.setText(user.getEmail());
    txt_num.setText(String.valueOf(user.getNum_tel()));
    lid.setText(valueOf(user.getId()));
    
    }
    
    public ImageView getim(){
    return imageView;
    }
    
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
     
     
     @FXML
    void modifier(ActionEvent event) {
    
        Utilisateur upuser = new Utilisateur(Integer.parseInt(lid.getText()), txt_nom.getText(), txt_prenom.getText(), Integer.parseInt(txt_num.getText()), txt_mdp.getText(), txt_genre.getText(), txt_email.getText());
        userser.modifier(upuser);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("modification avec succés!");
        alert.show();
    }
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
   type.getItems().addAll("Admin","user","partenaire");
        System.out.println(lid.getText());
     
    } }

               


   

