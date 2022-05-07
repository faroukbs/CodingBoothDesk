/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceReclamation;
import services.ServiceReponse;
import services.ServiceUser;
import utils.Emailer;
import utils.JavamailUtil;

/**
 * FXML Controller class
 *
 * @author lylyy
 */
public class GestionReclmationController implements Initializable {

    @FXML
    private TableView<Object> tableReclamation;
    @FXML
    private TableColumn<Reclamation, String> message;
    @FXML
    private Button Retour;
    @FXML
    private Button Supprimer;
    @FXML
    private Button warn;
    @FXML
    private Button SMS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Supprimer.setDisable(true);
        warn.setDisable(true);
        
        ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> livRec= sr.afficherReclamation();
        ObservableList<Object> ListRecData = FXCollections.observableArrayList();
        ListRecData.addAll(livRec);
        message.setCellValueFactory(new PropertyValueFactory<>("message"));

tableReclamation.setItems(ListRecData);
ObservableList selectedCells = tableReclamation.getSelectionModel().getSelectedCells();
        System.out.println(selectedCells);
  selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(ListChangeListener.Change c) {
                
               Reclamation ReclamationSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
                System.out.println(ReclamationSelected);
               if(ReclamationSelected!=null){
                   
                    Supprimer.setDisable(false);
                    if(ReclamationSelected.isWarn()){
                                        warn.setDisable(false);

                    }else{
                    warn.setDisable(false);}
                }else{ Supprimer.setDisable(true);
                    warn.setDisable(true);

                }
            }      
        });

        
    }    

    @FXML
    private void Retour(ActionEvent event) {
          try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Repadmin.fxml"));
           Parent root= loader.load();
            Retour.getScene().setRoot(root);
            } catch (IOException ex) {
            Logger.getLogger(RepadminController.class.getName()).log(Level.SEVERE, null, ex);
     
    }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
          Reclamation ReclamationSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
        ServiceReclamation sr = new ServiceReclamation();
sr.supprimerReclamation(ReclamationSelected);
miseajour();
    }
    void miseajour(){
     ServiceReclamation sr = new ServiceReclamation();
        List<Reclamation> livRec= sr.afficherReclamation();
        ObservableList<Object> ListRecData = FXCollections.observableArrayList();
        ListRecData.addAll(livRec);
        message.setCellValueFactory(new PropertyValueFactory<>("message"));

tableReclamation.setItems(ListRecData);}

    @FXML
    private void warnLiv(ActionEvent event) {
           Reclamation ReclamationSelected = (Reclamation) tableReclamation.getSelectionModel().getSelectedItem();
  ServiceReclamation sr = new ServiceReclamation();
    
        sr.warn(ReclamationSelected);
ServiceReponse SL= new ServiceReponse();
        Optional<Reponse> L = SL.afficherReponse().stream().filter(l->l.getIdReponse()==ReclamationSelected.getReponse().getIdReponse()).findFirst();
       ServiceUser us = new ServiceUser();
        System.out.print("hey"+L.get().getUser().getId());
     
   String email = us.getById(L.get().getUser().getId());
      Utilisateur UserToSent = us.afficher().stream().filter(e->e.getId()==L.get().getUser().getId()).findFirst().get();
       
        try {
            Emailer.sendMail(ReclamationSelected.getEmail(), "Warning   "+ReclamationSelected.getMessage());
//          JavamailUtil.sendMailaide( email, "WARNING", "SERVICE RECLAMATION",ReclamationSelected.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(GestionReclmationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    @FXML
    private void sms(ActionEvent event) throws IOException {
      SMS.getScene().getWindow().hide();
            Stage location = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/gui/sendSms.fxml"));
            Scene scene = new Scene(root);
            location.setScene(scene);
            location.show();
            location.setResizable(false);
    
    }
    
}
