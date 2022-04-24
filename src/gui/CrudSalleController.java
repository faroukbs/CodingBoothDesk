/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Salle;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.CategorieService;
import services.SalleService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CrudSalleController implements Initializable {

    @FXML
    private TextField nomsalletx;
    @FXML
    private TextField descriptiontx;
    @FXML
    private ComboBox<Integer> categoriebox;
    @FXML
    private TableView<Salle> tablesalles;
    @FXML
    private TableColumn<Salle, Integer> idsallev;
    @FXML
    private TableColumn<Salle, String> descriptionv;
    
    @FXML
    private Button Add;
    @FXML
    private Button ModSalle;
    @FXML
    private Button SupprimerSalle;
    SalleService ss = new SalleService();
    @FXML
    private TableColumn<Salle, String> nomsallev;
    @FXML
    private TableColumn<Salle, Integer> categoriev;
    @FXML
    private TextField filterField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriebox.setItems(ss.affectercategorie());
        categoriebox.getSelectionModel().selectFirst();
        ShowSalles();
        rechercher();
    }    

    @FXML
    private void liste_salle(MouseEvent event) {
         try {
            Salle s = tablesalles.getSelectionModel().getSelectedItem();
            nomsalletx.setText(s.getNomsalle());
            descriptiontx.setText(s.getDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void ShowSalles() {
         SalleService ss = new SalleService();
        List<Salle> salles = ss.recuperer();
        ObservableList<Salle> List = FXCollections.observableArrayList(salles);
        tablesalles.setItems(List);
        idsallev.setCellValueFactory(new PropertyValueFactory<>("idsalle"));
        nomsallev.setCellValueFactory(new PropertyValueFactory<>("nomsalle"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoriev.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));
    }

    @FXML
    private void Add(ActionEvent event) {
         if (nomsalletx.getText().isEmpty() || descriptiontx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
         }else{
        String nomSalle = nomsalletx.getText();
        String description = descriptiontx.getText();
        int categorieId = categoriebox.getSelectionModel().getSelectedItem();
        SalleService cs = new SalleService();
        Salle s = new Salle(nomSalle, description, categorieId);
        cs.ajouter(s);
        ShowSalles();
        raifraichir();
         }
    }

    @FXML
    private void ModSalle(ActionEvent event) {
        if (nomsalletx.getText().isEmpty() || descriptiontx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
         }else{
          Salle c = new Salle();
        SalleService cs = new SalleService();
        c = tablesalles.getSelectionModel().getSelectedItem();
        c.setIdcategorie(tablesalles.getSelectionModel().getSelectedItem().getIdcategorie());
        c.setNomsalle(nomsalletx.getText());
        c.setDescription(descriptiontx.getText());
        c.setIdcategorie(categoriebox.getValue());
        cs.modifier(c);
        ShowSalles();
        raifraichir();
        } 
    }

    @FXML
    private void SupprimerSalle(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
            SalleService ss = new SalleService();
        ss.supprimer(tablesalles.getSelectionModel().getSelectedItem().getIdsalle());
        System.out.println(tablesalles.getSelectionModel().getSelectedItem().getIdsalle());
        ShowSalles(); 
        tablesalles.getItems().removeAll(tablesalles.getSelectionModel().getSelectedItem());
    }}
    private  void raifraichir(){
        nomsalletx.setText("");
        descriptiontx.setText("");
    }
    private void rechercher(){
      
      SalleService cs = new SalleService();
        List<Salle> salles = cs.recuperer();
      ObservableList<Salle> dataList = FXCollections.observableArrayList(salles);
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Salle> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(salle -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if (salle.getNomsalle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches nom salle.
				}
				else if (String.valueOf(salle.getIdsalle()).indexOf(lowerCaseFilter)!=-1){
					return true; // Filter matches id salle.
				}
                                else if (String.valueOf(salle.getIdcategorie()).indexOf(lowerCaseFilter)!=-1){
					return true; // Filter matches id categorie.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Salle> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tablesalles.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tablesalles.setItems(sortedData);
     
      }
    
}
