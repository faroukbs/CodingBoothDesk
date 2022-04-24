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
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowCat();
        rechercher();
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
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
        CategorieService cs = new CategorieService();
        cs.supprimer(tablecategories.getSelectionModel().getSelectedItem().getIdcategorie());
        System.out.println(tablecategories.getSelectionModel().getSelectedItem().getIdcategorie());
        ShowCat(); //// raifrach table view ///
        tablecategories.getItems().removeAll(tablecategories.getSelectionModel().getSelectedItem());

    }}

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
    private void rechercher(){
      
      CategorieService cs = new CategorieService();
        List<Categorie> categories = cs.recuperer();
      ObservableList<Categorie> dataList = FXCollections.observableArrayList(categories);
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Categorie> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(categorie -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if (categorie.getNomcategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches nom categorie.
				}
				else if (String.valueOf(categorie.getIdcategorie()).indexOf(lowerCaseFilter)!=-1){
					return true; // Filter matches id categorie.
				}
                               
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Categorie> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tablecategories.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tablecategories.setItems(sortedData);
     
      }

}
