/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Eventl;
import Services.ServiceEventl;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.nio.file.WatchEvent.Modifier;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class DashboardController implements Initializable {

    private TableView<Eventl> evenementtable;
      private TableColumn<Eventl,String> titre;
    
  


  
   ServiceEventl ec = new ServiceEventl();
    private TableColumn<Eventl,String> description;
    private TableColumn<Eventl,String> ville;
    private TableColumn<Eventl,String> photo;
    private TableColumn<Eventl,Date> datedebut;
    private TableColumn<Eventl,Date> datefin;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        ServiceEventl ps =new ServiceEventl () ;
//         List<Eventl>  events =ps.recuperer();
//         ObservableList list= FXCollections.observableArrayList(events);
//         evenementtable.setItems(list);
//         titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
//                 description.setCellValueFactory(new PropertyValueFactory<>("description"));
//                 
//                        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
//                       
//       photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
//                      datedebut.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
//                       
//       datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
//        // list.remove(p)
    }    
     
    


    private void AJOUTER(ActionEvent event) {
                Stage primaryStage = new Stage();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
            
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ajouter Evenement");
            primaryStage.setScene(scene);
            primaryStage.show();
            
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private void supprimer(ActionEvent event) {
        
        Eventl e = evenementtable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure !");
            //alert.show();
         Optional <ButtonType> action = alert.showAndWait();
         if(action.get() == (ButtonType.OK)){
             evenementtable.getItems().remove(e);
             ec.supprimer(e.getIdevent());
         }
        
    }

    @FXML
    private void event(ActionEvent event) {
                     try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("CrudNew.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("/images/logo.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void ticket(ActionEvent event) {
             try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("CrudNEWticket.fxml"));
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            //stage.getIcons().add(new Image("/images/logo.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

}
