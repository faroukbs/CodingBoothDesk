/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import service.CommandeService;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class PasseCommandeController implements Initializable {

    
    @FXML
    private Button confirmer;
    @FXML
    private TextField adr;
    @FXML
    private TextField mont;
    @FXML
    private TextField tel;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField mode;
    @FXML
    private TextField etat;
    String erreur;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button confirmer1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    

    @FXML
    private void AjouterCommande(ActionEvent event) {
        if (nom.getText().isEmpty()
                || prenom.getText().isEmpty() || tel.getText().isEmpty() || adr.getText().isEmpty() 
                || mont.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            nom.setEffect(in);
            prenom.setEffect(in);
            tel.setEffect(in);
            adr.setEffect(in);
            mont.setEffect(in);
            mode.setEffect(in);
            etat.setEffect(in);

            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if (testNom()& testPrenom() & testTel() ) {
            String nomClient = nom.getText();

            String prenomClient = prenom.getText();
            String phone = tel.getText();
            String adresse = adr.getText();
            String montant = mont.getText();
            String modeP = mode.getText();
            Integer etatC =  Integer.parseInt(etat.getText());
            

            

            CommandeService ps = new CommandeService();
            
            Commande v = new Commande(nomClient, prenomClient, phone, adresse, montant, modeP, etatC );

            ps.ajouterCommande(v);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succes");
            alert.setHeaderText(null);
            alert.setContentText("Commande ajouter");
            alert.showAndWait();
            try{
            Parent root = FXMLLoader.load(getClass().getResource("AffichageCommande.fxml"));
            nom.getScene().setRoot(root);
//            DetailsController Dc = new DetailsController();
//            
//            Dc.setIdCommande(v.getIdcommande());
            }catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
//        }
//        Commande p = new Commande();
//        
//        p.setNom_client(nom.getText());
//        p.setPrenom_client(prenom.getText());
//        p.setTelephone(tel.getText());
//        p.setAdresse(adr.getText());
//        p.setMontant(mont.getText());
//        p.setMode_paiement(mode.getText());
//
//        p.setEtat_commande(Integer.parseInt(etat.getText()));
//        CommandeService ps = new CommandeService();
//        ps.ajouterCommande(p);
//        try {
//            //alert
////        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
////        alert.setTitle("Success");
////        alert.setContentText("Personne ajoutée");
////        alert.show();
//        Parent root = FXMLLoader.load(getClass().getResource("AffichageCommande.fxml"));
//        nom.getScene().setRoot(root);
//              
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        
    }
    }
    private Boolean testSaisie() {
        String erreur = "";
        
        if (!testTel()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        
        
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }

        if ( !testTel()  ||  !testNom() || !testPrenom()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
        }

        return  testTel()  && testNom() && testPrenom();
    }
    private Boolean testTel() {
        if (tel.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < tel.getText().trim().length(); i++) {
                char ch = tel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("le numero doit etre en chiffre ");
            alert.showAndWait();
                return false;

            }
        } else if (tel.getText().trim().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut saisir 8 chiffre  ");
            alert.showAndWait();
            return false;
        }

        return true;

    }

   
    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            
            return true;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
            return false;

        }

    }

    private Boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < prenom.getText().trim().length(); i++) {
            char ch = prenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenom.getText().trim().length() >= 3) {
            return true;
        } else {
Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
            return false;

        }

    }


   
//    private boolean TestNom() {
//        Pattern p = Pattern.compile("[a-zA-Z0-9]*[a-zA-Z0-9]*");
//        Pattern p1 = Pattern.compile("[s][+][0-9]*");
//        Matcher m = p.matcher(nom.getText());
//        Matcher m1 = p.matcher(nom.getText());
//        if (m.find() && m.group().equals(nom.getText()) || m1.find() && m1.group().equals(nom.getText())) {
//            return true;
//        } else {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Syntaxe Email");
//            alert.setHeaderText(null);
//            alert.setContentText("S'il vous plait saisir un nom valide");
//            alert.showAndWait();
//
//            return false;
//        }
//    }
//    private boolean TestPrenom() {
//        Pattern p = Pattern.compile("[a-zA-Z0-9]*[a-zA-Z0-9]*");
//        Pattern p1 = Pattern.compile("[s][+][0-9]*");
//        Matcher m = p.matcher(prenom.getText());
//        Matcher m1 = p.matcher(prenom.getText());
//        if (m.find() && m.group().equals(prenom.getText()) || m1.find() && m1.group().equals(prenom.getText())) {
//            return true;
//        } else {
//
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Syntaxe Email");
//            alert.setHeaderText(null);
//            alert.setContentText("S'il vous plait saisir un nom valide");
//            alert.showAndWait();
//
//            return false;
//        }
//       
//    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
    
    
}
