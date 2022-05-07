/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import entities.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import org.controlsfx.control.Notifications;
import services.ServiceReclamation;
import services.ServiceReponse;

/**
 * FXML Controller class
 *
 * @author lylyy
 */
public class ClientReclamationController implements Initializable {

    @FXML
    private TextField idReclamation;
    @FXML
    private TextField idUser;
    @FXML
    private ComboBox<Object> idLivraison;
    @FXML
    private TextArea Description;
    @FXML
    private Button Enregistrer;
    @FXML
    private TextField idNom;
    @FXML
    private TextField idSujet;
    @FXML
    private TextField idSujet1;
    @FXML
    private DatePicker date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceReponse sl = new ServiceReponse();

   List<Reponse> livFromdatabase= sl.afficherReponse();
  ObservableList<Object> listLivData = FXCollections.observableArrayList();
        listLivData.addAll(livFromdatabase);
idLivraison.getItems().addAll(listLivData);
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
    private void Enregistrer(ActionEvent event) {
         if (idReclamation.getText().isEmpty())
     {
         showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","Id reclamation non valid ");
     
     }
     else if (idUser.getText().isEmpty())
     {
         showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","Id user non valid ");
     
     } 
     else if (idLivraison.getValue()==null)
     {
     showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","Id livraison non valid ");
     }
//     
     else  if (Description.getText().isEmpty())
     {
     showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","message vide ");
     }
       else  if (idNom.getText().isEmpty())
     {
     showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","nom non valide ");
     }
         else  if (idSujet.getText().isEmpty())
     {
     showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","sujet vide ");
     }
           else  if (idSujet1.getText().isEmpty())
     {
     showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","email non valide ");
     }
             else  if (date.getValue()== null)
     {
     showAlert(Alert.AlertType.ERROR, ((Node) event.getSource()).getScene().getWindow(),
                    "Error!","date non valide ");
     }
     else {
 
      Utilisateur u = new Utilisateur();
                u.setId(Integer.valueOf(idUser.getText()));
                Reponse LivSelected = (Reponse) idLivraison.getSelectionModel().getSelectedItem();

        Reclamation r = new Reclamation(Integer.valueOf(idReclamation.getText()),idNom.getText(),Description.getText(),idSujet.getText(),idSujet1.getText(),Date.valueOf(date.getValue()),LivSelected,u);
        ServiceReclamation rs = new ServiceReclamation();
        rs.ajouterReclamation(r);
        idReclamation.setText("");
        Description.setText("");}
  Notifications notificationBuilder = Notifications.create()
                .title("RECLAMATION").text("RECLAMATION AJOUTEE AVEC SUCCES").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
    
}
