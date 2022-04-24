/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Cours;
import entities.Salle;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.CoursService;
import services.SalleService;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CrudCoursController implements Initializable {

    @FXML
    private ComboBox<Integer> categoriebox;
    @FXML
    private ComboBox<Integer> sallebox;
    @FXML
    private ComboBox<Integer> coachbox;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField titletx;
    @FXML
    private TextField descriptiontx;
    @FXML
    private TableView<Cours> tablecours;
    @FXML
    private TableColumn<Cours, Integer> idcoursv;
    @FXML
    private TableColumn<Cours, String> titlev;
    @FXML
    private TableColumn<Cours, String> descriptionv;
    @FXML
    private TableColumn<Cours, Integer> idcategoriev;
    @FXML
    private TableColumn<Cours, Integer> idcoachv;
    @FXML
    private TableColumn<Cours, Integer> idsallev;
    @FXML
    private TableColumn<Cours, Date> datedebutv;
    @FXML
    private TableColumn<Cours, Date> datefinv;
    @FXML
    private Button Add;
    @FXML
    private Button ModCours;
    @FXML
    private Button SupprimerCours;

    CoursService cs = new CoursService();
    @FXML
    private TextField filterField;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriebox.setItems(cs.affectercategorie());
        categoriebox.getSelectionModel().selectFirst();

        sallebox.setItems(cs.affecterSalle());
        sallebox.getSelectionModel().selectFirst();

        coachbox.setItems(cs.affecterCoach());
        coachbox.getSelectionModel().selectFirst();
        ShowCours();
        rechercher();
    }

    @FXML
    private void ShowCours() {
        CoursService cs = new CoursService();
        List<Cours> cours = cs.recuperer();
        ObservableList<Cours> List = FXCollections.observableArrayList(cours);
        tablecours.setItems(List);
        idcoursv.setCellValueFactory(new PropertyValueFactory<>("idcours"));
        titlev.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));
        idcategoriev.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));
        idcoachv.setCellValueFactory(new PropertyValueFactory<>("idcoach"));
        idsallev.setCellValueFactory(new PropertyValueFactory<>("idsalle"));
        datedebutv.setCellValueFactory(new PropertyValueFactory<>("start"));
        datefinv.setCellValueFactory(new PropertyValueFactory<>("end"));  
    }
     private void raifraichir(){
      titletx.setText("");
      descriptiontx.setText("");
      
    }
    
      private void rechercher(){
      
      CoursService cs = new CoursService();
        List<Cours> courses = cs.recuperer();
      ObservableList<Cours> dataList = FXCollections.observableArrayList(courses);
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Cours> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(cours -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				 if (cours.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches title.
				}
				else if (String.valueOf(cours.getIdcours()).indexOf(lowerCaseFilter)!=-1){
					return true; // Filter matches id cours.
				}
                                else if (String.valueOf(cours.getIdcoach()).indexOf(lowerCaseFilter)!=-1){
					return true; // Filter matches id coach.
				}
                                else if (String.valueOf(cours.getIdsalle()).indexOf(lowerCaseFilter)!=-1){
					return true; // Filter matches id salle.
				}
                                else if (String.valueOf(cours.getIdcategorie()).indexOf(lowerCaseFilter)!=-1){
					return true; // Filter matches id salle.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Cours> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tablecours.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tablecours.setItems(sortedData);
     
      }
     
    @FXML
    private void Add(ActionEvent event) {
        if (titletx.getText().isEmpty() || descriptiontx.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else if (datedebut.getValue().isBefore(LocalDate.now()) || datefin.getValue().isBefore(datedebut.getValue())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText("Date deb et fin erronée");
            alert.show();
        } else {
            String titre = titletx.getText();
            String description = descriptiontx.getText();
            int categorieId = categoriebox.getSelectionModel().getSelectedItem();
            int coachid = coachbox.getSelectionModel().getSelectedItem();
            int salleid = sallebox.getSelectionModel().getSelectedItem();
            LocalDate dateDebut = datedebut.getValue();
            LocalDate dateFin = datefin.getValue();
            CoursService cs = new CoursService();
            Cours c = new Cours(titre, categorieId, coachid, salleid, description, java.sql.Date.valueOf(dateDebut), java.sql.Date.valueOf(dateFin));
            cs.ajouter(c);
            ShowCours();
            raifraichir();
        }

    }

    @FXML
    private void ModCours(ActionEvent event) {
        if (titletx.getText().isEmpty() || descriptiontx.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else if (datedebut.getValue().isBefore(LocalDate.now()) || datefin.getValue().isBefore(datedebut.getValue())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText("Date deb et fin erronée");
            alert.show();
        } else {
            Cours c = new Cours();
            CoursService cs = new CoursService();
            c = tablecours.getSelectionModel().getSelectedItem();
            c.setIdcours(tablecours.getSelectionModel().getSelectedItem().getIdcours());
            c.setTitle(titletx.getText());
            c.setDescription(descriptiontx.getText());
            c.setIdcategorie(categoriebox.getValue());
            c.setIdcoach(coachbox.getValue());
            c.setIdsalle(sallebox.getValue());
            c.setStart(java.sql.Date.valueOf(datedebut.getValue()));
            c.setEnd(java.sql.Date.valueOf(datefin.getValue()));
            cs.modifier(c);
            ShowCours();
            raifraichir();
        }
    }

    @FXML
    private void SupprimerCours(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
        CoursService cs = new CoursService();
        cs.supprimer(tablecours.getSelectionModel().getSelectedItem().getIdcours());
        System.out.println(tablecours.getSelectionModel().getSelectedItem().getIdsalle());
        ShowCours(); //// raifrach table view ///
        tablecours.getItems().removeAll(tablecours.getSelectionModel().getSelectedItem());
        raifraichir();
    }}

    @FXML
    private void list_cours(MouseEvent event) {
        try {
            Cours c = tablecours.getSelectionModel().getSelectedItem();
            titletx.setText(c.getTitle());
            descriptiontx.setText(c.getDescription());
            datedebut.setValue(c.getStart().toLocalDate());
            datefin.setValue(c.getEnd().toLocalDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

}
