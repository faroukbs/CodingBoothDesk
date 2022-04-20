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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
            //raifraichir();
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
            //raifraichir();
        }
    }

    @FXML
    private void SupprimerCours(ActionEvent event) {
        CoursService cs = new CoursService();
        cs.supprimer(tablecours.getSelectionModel().getSelectedItem().getIdcours());
        System.out.println(tablecours.getSelectionModel().getSelectedItem().getIdsalle());
        ShowCours(); //// raifrach table view ///
        tablecours.getItems().removeAll(tablecours.getSelectionModel().getSelectedItem());
    }

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
