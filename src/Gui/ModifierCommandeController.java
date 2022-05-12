/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class ModifierCommandeController implements Initializable {

    @FXML
    private TextField adr;
    @FXML
    private TextField tel;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    Commande c;

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
    private Button confirme;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnMenus11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeService cs = new CommandeService();
        int idcommande;
        
        
//        c = cs.recuperer(idcommande);
//        nom.setText(c.getNom_client());
//        prenom.setText(c.getPrenom_client());
//        tel.setText(c.getTelephone());
//        adr.setText(c.getAdresse());
        
    }    
    



//    @FXML
//    private void modifierCom(ActionEvent event) {
//                CommandeService cs = new CommandeService();
//
//        if (nom.getText().isEmpty() || prenom.getText().isEmpty()|| tel.getText().isEmpty() || adr.getText().isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Erreur!");
//            alert.setHeaderText(null);
//            alert.setContentText(" Champ vide!");
//            alert.show();
//        } else {
//                  c.setNom_client(nom.getText());
//               c.setPrenom_client(prenom.getText());
//        c.setTelephone(tel.getText());
//        c.setAdresse(adr.getText());
//        
//        
//  
//        cs.modifierCommande(c);
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Succesful");
//            alert.setHeaderText(null);
//            alert.setContentText(" Evenement modifié avec succéez!");
//            alert.show();
//            try{
//            Parent root = FXMLLoader.load(getClass().getResource("PasseCommande.fxml"));
//            nom.getScene().setRoot(root);
//            }catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
//        
//    }}

    @FXML
    private void modifierCom(ActionEvent event) {
    }
@FXML
    private void handleClicks(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("AllEventsList.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }

    @FXML
    private void produit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }
    

    @FXML
    private void productCart(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("ClientReclamationController.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }

    @FXML
    private void Account(ActionEvent event) {
    }

    @FXML
    private void hhhh(ActionEvent event) {
    }
}