/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.category;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CategoryProdService;


/**
 *
 * @author bouss
 */
public class GestionCategorieController implements Initializable {
    CategoryProdService Sc = new CategoryProdService();

    
    @FXML
    private TableView<category> tblCat;
    @FXML
    private TableColumn<?, ?> tbNom;
    @FXML
    private TextField tfNom;
    @FXML
    private Button AddC;
    @FXML
    private Button DelC;
    @FXML
    private Button EdC;
    ObservableList<category> list;
    private int selectedCatID;
    @FXML
    private Button home;
    @FXML
    private Button ligne;
    @FXML
    private Button commande;
    @FXML
    private Button ligne1;
    @FXML
    private Button ligne11;
    @FXML
    private Button ligne111;
    @FXML
    private Button ligne1111;
    @FXML
    private Button ligne11111;
    @FXML
    private Button ligne111111;
    @FXML
    private Button ligne1111111;
    @FXML
    private Button ligne11111111;
    @FXML
    private Button ligne111111111;
    @FXML
    private Button ligne112;

    @FXML
    private void returnb(ActionEvent event) throws IOException {
                     Parent root = FXMLLoader.load(getClass().getResource("GestProduits.fxml"));
  Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  Scene scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
         System.out.println("fffffff");
    }
     @FXML
    private void AddC(ActionEvent event) {
                DataValidation validator = new DataValidation();
            if (tfNom.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else {
                
                
                category cat = new category(tfNom.getText());
    
//            if (validator.isNotEmpty(tfNom) ) 

            
            Sc.Add(cat);
            tblCat.setItems(FXCollections.observableArrayList(Sc.GetAll()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Categorie added");
            alert.setContentText("Categorie added succesfuuly!");
            tblCat.refresh();
        
    }
    }

//    @FXML
//    private void AddC(ActionEvent event) {
//        if (tfNom.getText().isEmpty()) {
//            tfNom.setText("Verifier les entrées s'il vous plait");
//        } else {// FIXME: change the id user from 1 to the current logged in user.
//            category cat = new category(tfNom.getText());
//            Sc.Add(cat);
//            tblCat.setItems(FXCollections.observableArrayList(Sc.GetAll()));
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Categorie added");
//            alert.setContentText("Categorie added succesfuuly!");
//            tblCat.refresh();
//        }
//    }

    @FXML
    private void DelC(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
         if (action.get() == (ButtonType.OK)) {
        final category selectedItem = tblCat.getSelectionModel().getSelectedItem();
        category cat = Sc.GetById(selectedItem.getIdcategory());
        Sc.Delete(cat.getIdcategory());

        list.remove(selectedItem);
        tblCat.setItems(FXCollections.observableArrayList(Sc.GetAll()));
        tblCat.refresh();
    }
    }

    @FXML
    private void EdC(ActionEvent event) {
        if (tfNom.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else {
            
      
        final category selectedItem = tblCat.getSelectionModel().getSelectedItem();
        category cat = Sc.GetById(selectedItem.getIdcategory());
        cat.setNom(tfNom.getText());
        
        Sc.Update(cat);
        tblCat.setItems(FXCollections.observableArrayList(Sc.GetAll()));
        tblCat.refresh();
    }
          }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      list = FXCollections.observableArrayList(Sc.GetAll());

        tbNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        

        tblCat.setItems(list);

        tblCat.setRowFactory(tv -> {

            TableRow<category> row = new TableRow<>();

            row.setOnMouseClicked(event -> {

                if (!row.isEmpty()) {
                    final category selectedItem = tblCat.getSelectionModel().getSelectedItem();

                    tfNom.setText(selectedItem.getNom());
                    

                    selectedCatID = selectedItem.getIdcategory();
                }
            });

            return row;
        });



       
    }

   
    @FXML
    private void retourLigne(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLigneCom.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
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

    @FXML
    private void retourEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudNew.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudNewticket.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourSalle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudSalle.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourCours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudCours.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestProduits.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourCatCours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudCategorie.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retoutCatProd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestCat.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourReclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionReclmation.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourReponses(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Repadmin.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourUtilisaateur(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Interfaces/modifierUsers.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
