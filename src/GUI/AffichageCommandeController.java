/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import service.CommandeService;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class AffichageCommandeController implements Initializable {
    CommandeService Sp = new CommandeService();
    ObservableList<Commande> list;

    @FXML
    private TableView<Commande> TableView;
    
    @FXML
    private TableColumn<Commande, String> nom;
    @FXML
    private TableColumn<Commande, String> prenom;
    @FXML
    private TableColumn<Commande, String> tel;
    @FXML
    private TableColumn<Commande, String> adr;
    @FXML
    private TableColumn<Commande, String> mont;
    @FXML
    private TableColumn<Commande, String> mode;
    @FXML
    private TableColumn<Commande, Integer> etat;
    @FXML
    private Button miseAjour;
    @FXML
    private Button modify1;
    @FXML
    private Button supprimer1;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Commande commande = null ;
    
    ObservableList<Commande>  CommandeList = FXCollections.observableArrayList();
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeService ps = new CommandeService();
        List<Commande> Commandes = ps.recupererCommande();
        ObservableList list = FXCollections.observableArrayList(Commandes);
        TableView.setItems(list);
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mont.setCellValueFactory(new PropertyValueFactory<>("montant"));
        mode.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));
    }    

    @FXML
    private void modifierCommande(ActionEvent event) {
        Commande voy = new Commande();
        CommandeService sv = new CommandeService();
        voy = TableView.getSelectionModel().getSelectedItem();
        voy.setIdcommande(TableView.getSelectionModel().getSelectedItem().getIdcommande());
        voy.setNom_client(nom.getText());

        voy.setPrenom_client(prenom.getText());
        voy.setTelephone(tel.getText());
        voy.setAdresse(adr.getText());
        voy.setMontant(mont.getText());
        voy.setMode_paiement(mode.getText());
        voy.setEtat_commande(Integer.parseInt(etat.getText()));

        

        sv.modifierCommande(voy);
        showAzer(); //// raifrach table view ///
    }
     public void delete() {
        CommandeService SV = new CommandeService();
        SV.supprimerCommande(TableView.getSelectionModel().getSelectedItem().getIdcommande());
        System.out.println(TableView.getSelectionModel().getSelectedItem().getIdcommande());

    }

    @FXML
    private void supprimerCommande(ActionEvent event) {
        delete();
        showAzer(); //// raifrach table view ///
        TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
        System.out.println(TableView);
    }

    @FXML
    private void reload(ActionEvent event) {
        
         
        try {
            CommandeList.clear();
            
            query = "SELECT * FROM `commande`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                CommandeList.add(new  Commande(
                        resultSet.getInt("idcommande"),
                        resultSet.getString("nom_client"),
                        resultSet.getString("prenom_client"),
                resultSet.getString("telephone"),
                resultSet.getString("adresse"),
                resultSet.getString("montant"),
                resultSet.getString("mode_paiement"),
                resultSet.getInt("etat_commande")));
                TableView.setItems(CommandeList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichageCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void showAzer() {
        CommandeService ps = new CommandeService();
        List<Commande> Commandes = ps.recupererCommande();
        ObservableList list = FXCollections.observableArrayList(Commandes);
        TableView.setItems(list);
        
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mont.setCellValueFactory(new PropertyValueFactory<>("montant"));
        mode.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));
        
    }
    
}
