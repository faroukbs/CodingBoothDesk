/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.SigninController.PHOTOU;
import static Controllers.SigninController.idu;
import Entities.Utilisateur;
import Services.ServiceAdmin;
import Services.ServiceUtilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.L;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.DataSource;

/**
 *
 * @author GhAlone
 */
public class ProfileUserController implements Initializable {

    private Pane acpane;

    @FXML
    private Button bt_modifier;

    @FXML
    private Button bt_img;

    @FXML
    private Label Lnom;

    @FXML
    private Label Lprenom;

    @FXML
    private Label LAdress;

    @FXML
    private Label Lnum;

    

    @FXML
    private ImageView imageView;

    @FXML
    private Label lid;
    String nom, prenom, email, image;
    int num;
 
   
    private File selectedFile;
    String image1;

    ServiceUtilisateur userser = new ServiceUtilisateur();

    Utilisateur user = new Utilisateur();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Utilisateur user = userser.getById();
        String path = "/Image/" + userser.getById().getImage();
        Image image= new Image(getClass().getResourceAsStream(path));
        imageView.setImage(image);*/
        user.getNom();
        System.out.println(lid.getText());
        affichu();
        
       
             /*   Path to = Paths.get(PHOTOU);
        System.out.println(to);
       image1 = new Image(String.valueOf(to));
       imageView.setImage(image1);
      System.out.println(image1);*/
       
    }

    public void affichu() {
        Connection cnx = DataSource.getInstance().getCnx();

        try {
            String req = "select * from utilisateur where id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idu);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom = rs.getString("nom");
                System.out.println(nom);
                Lnom.setText(nom);
                
                prenom=rs.getString("prenom");
                Lprenom.setText(prenom);
                
                 num=rs.getInt("num_tel");
                Lnum.setText(String.valueOf(num));
                
                email=rs.getString("email");
                LAdress.setText(email);
               
                //image=rs.getImage();
       // Image image = new Image(getClass().getResourceAsStream(path));
        //imageView.setImage(image);
                
              // image=rs.getString("image");
              
            // imageView.setImage(image);
              
                
                
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        
    }

    public void setuser() {
        /* Lnom.setText(user.getNom());
    Lprenom.setText(user.getPrenom());
    Lemail.setText(user.getAdress_email());
    Lgenre.setText(user.getGenre());
    lid.setText(valueOf(user.getId()));*/

        System.out.println();

    }

    public ImageView getImage() {
        return imageView;
    }

    @FXML
    public void onChoseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                imageView.setImage(image);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Hey");
        }
    }

    @FXML
    void modifier(ActionEvent event) throws IOException {
       /* Path from = Paths.get(selectedFile.toURI());
        Path to = Paths.get("C:\\Users\\MSI\\Desktop\\montasser-pidevDesktop\\montasser-pidevDesktop\\src\\Images/" + selectedFile.getName());
        Utilisateur upuser = new Utilisateur(Integer.parseInt(lid.getText()), Lnom.getText(), Lprenom.getText(),Integer.parseInt(Lnum.getText()),LAdress.getText(), to.normalize().toString());
        userser.profil(upuser);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("modification avec succés!");
        alert.show();*/
      
                Parent root =FXMLLoader.load(getClass().getResource("/Interfaces/profilmodif.fxml"));    
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    void changeUser(ActionEvent event) throws IOException {
        Pane p = FXMLLoader.load(getClass().getResource("/Interfaces/modifierUsers.fxml"));
        acpane.getChildren().add(p);

    }
     @FXML
    void reload(MouseEvent event) {
      
    }
    

}
