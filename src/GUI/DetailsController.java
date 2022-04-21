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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import service.CommandeService;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class DetailsController implements Initializable {
    int idcommande ;

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }
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
    private Button retourBtn;
    @FXML
    private TextField mode;
    @FXML
    private TextField etat;
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
    private Button retourBtn1;
    @FXML
    private Button retourBtn2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeService cs = new CommandeService();
        
        System.out.print("*******************"+idcommande);
        c = cs.recuperer(idcommande);
        nom.setText(c.getNom_client());
        prenom.setText(c.getPrenom_client());
        tel.setText(c.getTelephone());
        adr.setText(c.getAdresse());
        mont.setText(c.getMontant());
        mode.setText(c.getMode_paiement());
        etat.setText(String.valueOf(c.getEtat_commande()));  
    }   
    

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PasseCommande.fxml"));
            Parent root = loader.load();
            retourBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void setIdCommande(int idcommande) {
        this.idcommande = idcommande;
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void payer(ActionEvent event) {
    }

    @FXML
    private void modif(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommande.fxml"));
            Parent root = loader.load();
            retourBtn2.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    
}
