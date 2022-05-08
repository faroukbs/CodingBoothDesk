/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import services.CommandeService;
import services.Mailling;

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
    private ComboBox<String> mode;
    @FXML
    private ComboBox<Integer> etat;
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
    Commande r = new Commande();
    @FXML
    private Button confirmer2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> list = FXCollections.observableArrayList (0,1); 
        ObservableList<String> list1 = FXCollections.observableArrayList ("En ligne","CASH"); 
        
        etat.setItems(list);
        mode.setItems(list1);    } 
    

    @FXML
    private void AjouterCommande(ActionEvent event) {
//        if ( nom.getText().isEmpty()
//                || prenom.getText().isEmpty() || tel.getText().isEmpty() || adr.getText().isEmpty() 
//                || mont.getText().isEmpty()) {
//
//            InnerShadow in = new InnerShadow();
//            in.setColor(Color.web("#f80000"));
//            nom.setEffect(in);
//            prenom.setEffect(in);
//            tel.setEffect(in);
//            adr.setEffect(in);
//            mont.setEffect(in);
//            mode.setEffect(in);
//            etat.setEffect(in);
//
//            //txtnom.setStyle("-fx-border-color: red " );
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Il faut remplir les champs obligatoires ");
//            alert.showAndWait();
//
//        } else if (testNom()& testPrenom() & testTel() ) {
//            
//         
//            String nomClient = nom.getText();
//
//            String prenomClient = prenom.getText();
//            String phone = tel.getText();
//            String adresse = adr.getText();
//            String montant = mont.getText();
//            String modeP = mode.getSelectionModel().getSelectedItem();
//            Integer etatC = etat.getSelectionModel().getSelectedItem();  
//            
//
//
//            CommandeService ps = new CommandeService();
//            
//            Commande v = new Commande(nomClient, prenomClient, phone, adresse, montant, modeP, etatC );
//
//            ps.ajouterCommande(v);
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("succes");
//            alert.setHeaderText(null);
//            alert.setContentText("Commande ajouter");
//            alert.showAndWait();
            try{
            Parent root = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
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


   


    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void select1(ActionEvent event) {
               String s = mode.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    private void select(ActionEvent event) {
               Integer e = etat.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void mail(ActionEvent event) {
        if ( nom.getText().isEmpty()
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
            String modeP = mode.getSelectionModel().getSelectedItem();
            Integer etatC = etat.getSelectionModel().getSelectedItem();            


            CommandeService ps = new CommandeService();
            
            Commande v = new Commande(nomClient, prenomClient, phone, adresse, montant, modeP, etatC );

            ps.ajouterCommande(v);


            Mailling M = new Mailling() ;
            M.envoyer(v);
            
            Alert alertt = new Alert(Alert.AlertType.INFORMATION);
            alertt.setTitle("Success");
            alertt.setContentText(" un Email a été enoyer avec succes ");
            alertt.show();
        }
    }
    
    
}
