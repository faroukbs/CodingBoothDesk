/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import entities.Repondeur;
import entities.Reponse;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import services.ServiceCommande;
import services.ServiceRepondeur;
import services.ServiceReponse;

/**
 * FXML Controller class
 *
 * @author lylyy
 */
public class RepadminController implements Initializable {
    String action = "";
    @FXML
    private Button affecterBtn;
    @FXML
    private Button supprimerbtn;
    @FXML
    private TextField recherche;
    @FXML
    private TableView<Object> livTableau;
    @FXML
    private TableColumn<Reponse, String> Date_liv;
    @FXML
    private TableColumn<Reponse, String> etat_livraison;
    @FXML
    private Button ajouterbtn;
    @FXML
    private AnchorPane formLivraison;
    @FXML
    private TextField idlivForm;
    @FXML
    private ComboBox<Object> idcommandeform;
    @FXML
    private Button enregistrer;
    @FXML
    private ComboBox<Object> idlivreurform;
    @FXML
    private ComboBox<Object> liste_livreur;
    @FXML
    private ComboBox<Object> etatLivraison;
    @FXML
    private Label etatLivraisonlabel;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button gestionRecBTN;
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField idnom;

    @FXML
    private TextField idsujet;

    @FXML
    private TextField idmessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       affecterBtn.setDisable(true);
        supprimerbtn.setDisable(true);
        modifierbtn.setDisable(true);
        formLivraison.setOpacity(0);
        ServiceReponse sl = new ServiceReponse();
        ServiceCommande sC = new ServiceCommande();

        ServiceRepondeur serviceLivreur = new ServiceRepondeur();
        List<Reponse> livFromdatabase = sl.afficherReponse();
           List<Repondeur> LivreurFromDatabase = serviceLivreur.afficherPersonnes();
        List<Commande> CommandeFromDataBase = sC.recupererCommande();
        
        ObservableList<Object> ListeLivreurData = FXCollections.observableArrayList();
        ListeLivreurData.addAll(LivreurFromDatabase);
        
        ObservableList<Object> CommandeData = FXCollections.observableArrayList();
        CommandeData.addAll(CommandeFromDataBase);

        liste_livreur.setItems(ListeLivreurData);
        idlivreurform.setItems(ListeLivreurData);
        liste_livreur.setDisable(true);
        idcommandeform.setItems(CommandeData);

        ObservableList<Object> listLivData = FXCollections.observableArrayList();

        Date_liv.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        etat_livraison.setCellValueFactory(new PropertyValueFactory<>("etat"));
        listLivData.addAll(livFromdatabase);
        livTableau.setItems(listLivData);
        ObservableList selectedCells = livTableau.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                Reponse livSelected = (Reponse) livTableau.getSelectionModel().getSelectedItem();
                System.out.println("selected value " + livSelected);
                if (livSelected != null) {
                    if (livSelected.getUser().getId()!= 0 || livSelected.getUser() == null) {
                        affecterBtn.setText("Annuler l'affectation");
                    } else {
                        affecterBtn.setText("Affecter");

                    }
                    liste_livreur.setDisable(true);
                    modifierbtn.setDisable(false);
                    affecterBtn.setDisable(false);
                    supprimerbtn.setDisable(false);

                } else {

                    affecterBtn.setDisable(false);
                    supprimerbtn.setDisable(false);
                }
            }
        });

    }  
      void mise_a_jourbase() {
        ServiceReponse sl = new ServiceReponse();
        List<Reponse> livFromdatabase = sl.afficherReponse();
        ObservableList<Object> listLivData = FXCollections.observableArrayList();

        Date_liv.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        etat_livraison.setCellValueFactory(new PropertyValueFactory<>("etat"));
        listLivData.addAll(livFromdatabase);
        livTableau.setItems(listLivData);
      }

    @FXML
    private void affecterAction(ActionEvent event) {
          Reponse livSelected = (Reponse) livTableau.getSelectionModel().getSelectedItem();
        if (livSelected.getUser().getId()!= 0 || livSelected.getUser() == null) {
            livSelected.setUser(null);
            ServiceReponse sl = new ServiceReponse();
            sl.annulerAffectation(livSelected);
            mise_a_jourbase();

        } else {
            liste_livreur.setDisable(false);

        }
    }

    @FXML
    private void SupprimerLiv(ActionEvent event) {
         Reponse livSelected = (Reponse) livTableau.getSelectionModel().getSelectedItem();
        ServiceReponse sl = new ServiceReponse();
        sl.supprimerReponse(livSelected);
        mise_a_jourbase();
    }

    @FXML
    private void Chercher(ActionEvent event) {
         ServiceReponse sl = new ServiceReponse();
        ObservableList<Object> list = FXCollections.observableArrayList();

        list.addAll(sl.afficherReponse().stream().
                filter(l -> l.getEtat().contains(recherche.getText())).collect(Collectors.toList())
        );
        livTableau.setItems(list);
    }

    @FXML
    private void ajouter(ActionEvent event) {
          action = "ajouter";
        formLivraison.setOpacity(1);
        idlivForm.setText("");
        idlivForm.setDisable(false);
        etatLivraisonlabel.setOpacity(1);
        etatLivraison.setOpacity(1);
    }
     private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    @FXML
    private void enregistrer(ActionEvent event) {
          if (idlivForm.getText().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "id reponse vide ");

        } else if (idcommandeform.getValue() == null) {
             showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "id commande  vide ");
         
        } else if (idnom.getText().isEmpty()) {
             showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "nom  vide ");
         
        }else if (idsujet.getText().isEmpty()) {
             showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "sujet  vide ");
         
        }else if (idmessage.getText().isEmpty()) {
             showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "nom  vide ");
         
        }
         
        else if (idlivreurform.getValue() == null) {
             showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!", "mail repondeur  vide ");
       
        }
        else{
        if (action.compareTo("ajouter") == 0) {
            Commande CommandeSelected = (Commande) idcommandeform.getSelectionModel().getSelectedItem();
            Utilisateur LivreurSelected = (Repondeur) idlivreurform.getSelectionModel().getSelectedItem();
            

            String EtatLiv = (String) etatLivraison.getSelectionModel().getSelectedItem();
           
            Reponse l = new Reponse(Integer.valueOf(idlivForm.getText()),idnom.getText(),idsujet.getText(),idmessage.getText(),java.sql.Date.valueOf(date.getValue()),  "En cours", LivreurSelected,CommandeSelected);
            ServiceReponse sl = new ServiceReponse();
            sl.ajouterReponse(l);
            mise_a_jourbase();

        } else {

            Reponse livSelected = (Reponse) livTableau.getSelectionModel().getSelectedItem();
            Commande CommandeSelected = (Commande) idcommandeform.getSelectionModel().getSelectedItem();
            Utilisateur LivreurSelected = (Repondeur) idlivreurform.getSelectionModel().getSelectedItem();

            livSelected.setCommande(CommandeSelected);
            livSelected.setUser(LivreurSelected);
            String EtatLiv = (String) etatLivraison.getSelectionModel().getSelectedItem();
            livSelected.setEtat(EtatLiv);
            ServiceReponse sl = new ServiceReponse();
            sl.modifierReponse(livSelected);
            mise_a_jourbase();
        }
        formLivraison.setOpacity(0);
        idlivForm.setText("");
        idlivForm.setDisable(false);
        etatLivraisonlabel.setOpacity(0);
        etatLivraison.setOpacity(0);
        }
    }

    @FXML
    private void Modifier(ActionEvent event) {
          action = "modifier";
        formLivraison.setOpacity(1);
        Reponse livSelected = (Reponse) livTableau.getSelectionModel().getSelectedItem();
        idlivForm.setText(String.valueOf(livSelected.getIdReponse()));
        idlivForm.setDisable(true);
        etatLivraisonlabel.setOpacity(1);
        etatLivraison.setOpacity(1);
        etatLivraison.getItems().add(0, "En cours");
        etatLivraison.getItems().add(1, "Confirmee");

    }

    @FXML
    private void gotToREC(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReclmation.fxml"));
            Parent root = loader.load();
            gestionRecBTN.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GestionReclmationController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    }
    
