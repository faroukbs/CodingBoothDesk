/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import entities.Categorie;
import java.io.File;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CrudCategorieController implements Initializable {

    @FXML
    private TextField nomcategorietx;
    @FXML
    private TableView<Categorie> tablecategories;
    @FXML
    private TableColumn<Categorie, Integer> idcategoriev;
    @FXML
    private TableColumn<Categorie, String> nomcategoriev;
    @FXML
    private Button Add;
    @FXML
    private Button ModCat;
    @FXML
    private Button SupprimerCat;

    ObservableList<Categorie> List = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowCat();
    }

    @FXML
    private void ShowCat() {
        CategorieService cs = new CategorieService();
        List<Categorie> categories = cs.recuperer();
        ObservableList<Categorie> List = FXCollections.observableArrayList(categories);
        tablecategories.setItems(List);
        idcategoriev.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));
        nomcategoriev.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
    }

    @FXML
    private void Add(ActionEvent event) {
        if (nomcategorietx.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else {

            String nomCategorie = nomcategorietx.getText();
            CategorieService cs = new CategorieService();
            Categorie c = new Categorie(nomCategorie);
            cs.ajouter(c);
            ShowCat();
            raifraichir();
        }
    }

    @FXML
    private void ModCat(ActionEvent event) {
        if (nomcategorietx.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else {
            Categorie c = new Categorie();
            CategorieService cs = new CategorieService();
            c = tablecategories.getSelectionModel().getSelectedItem();
            c.setIdcategorie(tablecategories.getSelectionModel().getSelectedItem().getIdcategorie());
            c.setNomcategorie(nomcategorietx.getText());
            cs.modifier(c);
            ShowCat();
            raifraichir();
        }
    }

    @FXML
    private void SupprimerCat(ActionEvent event) {
        CategorieService cs = new CategorieService();
        cs.supprimer(tablecategories.getSelectionModel().getSelectedItem().getIdcategorie());
        System.out.println(tablecategories.getSelectionModel().getSelectedItem().getIdcategorie());
        ShowCat(); //// raifrach table view ///
        tablecategories.getItems().removeAll(tablecategories.getSelectionModel().getSelectedItem());

    }

    @FXML
    private void Liste_categories(MouseEvent event) {
        try {
            Categorie c = tablecategories.getSelectionModel().getSelectedItem();
            nomcategorietx.setText(c.getNomcategorie());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    private void raifraichir() {
        nomcategorietx.setText("");

    }

}
