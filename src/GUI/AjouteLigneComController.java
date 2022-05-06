/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Lignecommande;
import java.io.File;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import service.LignecommandeService;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class AjouteLigneComController implements Initializable {
    Connection cnx;
    ObservableList<Lignecommande> list;
LignecommandeService st = new LignecommandeService();
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    
    @FXML
    private TableView<Lignecommande> TableView;
    @FXML
    private TableColumn<Lignecommande, Integer> idcom;
    @FXML
    private TableColumn<Lignecommande, Integer> idprod;
    @FXML
    private TableColumn<Lignecommande, Integer> qtes;
    @FXML
    private Button modify1;
    @FXML
    private Button supprimer1;
    @FXML
    private Button miseAjour;
    @FXML
    private ComboBox<Integer> idc;
    @FXML
    private ComboBox<Integer> idp;
    @FXML
    private TextField qte;
    @FXML
    private Button Ajout;
    @FXML
    private Button home;
    @FXML
    private Button ligne;
    @FXML
    private Button commande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idc.setItems(st.affecterCom());
        idc.getSelectionModel().selectFirst();
        idp.setItems(st.affecterProd());
        idp.getSelectionModel().selectFirst();
showAzer();    }    


    @FXML
    private void modifierCommande(ActionEvent event) {
       Lignecommande voy = new Lignecommande();
        LignecommandeService sv = new LignecommandeService();
        voy = TableView.getSelectionModel().getSelectedItem();
       // Lignecommande lc = sv.recuperer(TableView.getSelectionModel().getSelectedItem().getIdlignecommande());
       // System.out.println(lc.toString());
        //int var = (Integer.parseInt(id));
        voy.setIdcommande(idc.getValue());
        voy.setIdproduit(idp.getValue());
        voy.setQuantite(Integer.parseInt(qte.getText()));

        

        sv.modifier(voy);
        showAzer(); //// raifrach table view ///
    }

    public void delete() {
        LignecommandeService SV = new LignecommandeService();
        SV.supprimer(TableView.getSelectionModel().getSelectedItem().getIdlignecommande());
        System.out.println(TableView.getSelectionModel().getSelectedItem().getIdlignecommande());

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
    }

    @FXML
    private void AjouteLigne(ActionEvent event) {
        if ( qte.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            idc.setEffect(in);
            idp.setEffect(in);
            qte.setEffect(in);

            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if ( testTel()) {
            int idcom = idc.getSelectionModel().getSelectedItem();

            int idprod = idp.getSelectionModel().getSelectedItem();
            int quantity = Integer.parseInt(qte.getText());

            LignecommandeService ps = new LignecommandeService();
            Lignecommande v = new Lignecommande(idcom, idprod, quantity );

            ps.ajouter(v);
            showAzer(); //// raifrach table view ///

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succes");
            alert.setHeaderText(null);
            alert.setContentText("ligne commande ajouter");
            alert.showAndWait();
        }
    }
    private Boolean testSaisie() {
        String erreur = "";
        
        if (!testTel()) {
            erreur = erreur + ("Champ doit avoir 4 chiffres et ne doit pas contenir des caracteres \n");
        }
        
        
        

        if ( !testTel()  ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
        }

        return  testTel() ;
    }
    private Boolean testTel() {
        if (qte.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < qte.getText().trim().length(); i++) {
                char ch = qte.getText().charAt(i);
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
        } else if (qte.getText().trim().length() != 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut saisir au moins 1 chiffre  ");
            alert.showAndWait();
            return false;
        }

        return true;

    }
    public ObservableList<Lignecommande> getLignelist() {
        ObservableList<Lignecommande> eventlist = FXCollections.observableArrayList();

        String req = "SELECT * FROM lignecommande";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            Lignecommande ligne;
            while (rs.next()) {
                Lignecommande l = new Lignecommande(rs.getInt("idcommande"), rs.getInt("idproduit"), rs.getInt("quantite"));
                eventlist.add(l);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return eventlist;
    }
    @FXML
    private void showAzer() {
        LignecommandeService ps = new LignecommandeService();
        List<Lignecommande> ligne = ps.recuperer();
        ObservableList list = FXCollections.observableArrayList(ligne);
        TableView.setItems(list);
        idcom.setCellValueFactory(new PropertyValueFactory<>("idcommande"));
        idprod.setCellValueFactory(new PropertyValueFactory<>("idproduit"));

        qtes.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        
    }

    @FXML
    private void Liste_ligne(MouseEvent event) {
        try {
            Lignecommande ligne = TableView.getSelectionModel().getSelectedItem();
            qte.setText(valueOf(ligne.getQuantite()));


        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void retourLigne(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLigneCom.fxml"));
            Parent root = loader.load();
            ligne.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourCom(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCom.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
