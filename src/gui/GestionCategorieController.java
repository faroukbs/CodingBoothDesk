/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.category;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CategoryProdService;


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
                //DataValidation validator = new DataValidation();
                category cat = new category(tfNom.getText());
    
//            if (validator.isNotEmpty(tfNom) ) 
            
            Sc.Add(cat);
            tblCat.setItems(FXCollections.observableArrayList(Sc.GetAll()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Categorie added");
            alert.setContentText("Categorie added succesfuuly!");
            tblCat.refresh();
        
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
        final category selectedItem = tblCat.getSelectionModel().getSelectedItem();
        category cat = Sc.GetById(selectedItem.getIdcategory());
        Sc.Delete(cat.getIdcategory());

        list.remove(selectedItem);
        tblCat.setItems(FXCollections.observableArrayList(Sc.GetAll()));
        tblCat.refresh();
    }

    @FXML
    private void EdC(ActionEvent event) {
        final category selectedItem = tblCat.getSelectionModel().getSelectedItem();
        category cat = Sc.GetById(selectedItem.getIdcategory());
        cat.setNom(tfNom.getText());
        
        Sc.Update(cat);
        tblCat.setItems(FXCollections.observableArrayList(Sc.GetAll()));
        tblCat.refresh();
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
}
