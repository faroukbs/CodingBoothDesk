/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import entities.Salle;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;
import services.SalleService;
import util.BadWordFilter;
import util.MailAPI;
import util.pdf;
import javafx.scene.control.Pagination;

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
    @FXML
    private TableColumn<Salle, String> imagev;
    @FXML
    private Button AddImage;
    @FXML
    private TextField URLImage;
    @FXML
    private ImageView Image;
    @FXML
    private Button bntPDF;
    @FXML
    private Pagination pagination;
    
    
    private final static int rowsPerPage = 3;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        categoriebox.setItems(ss.affectercategorie());
        categoriebox.getSelectionModel().selectFirst();
        pagination.setPageFactory(this::createPage);
        pagination.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);
        ShowSalles();
        rechercher();
        
    }
    
    private Node createPage(int pageIndex) {
        SalleService ss = new SalleService();
        List<Salle> salles = ss.recuperer();
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, salles.size());
        tablesalles.setItems(FXCollections.observableArrayList(salles.subList(fromIndex, toIndex)));
        return tablesalles;
    }
    
    @FXML
    private void liste_salle(javafx.scene.input.MouseEvent event) {
        try {
            Salle s = tablesalles.getSelectionModel().getSelectedItem();
            nomsalletx.setText(s.getNomsalle());
            descriptiontx.setText(s.getDescription());
            URLImage.setText(s.getImage());
            String path = s.getImage();
            File file = new File(path);
            Image img = new Image(file.toURI().toString());
            Image.setImage(img);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void ShowSalles() {
        SalleService ss = new SalleService();
        List<Salle> salles = ss.recuperer();
        ObservableList<Salle> List = FXCollections.observableArrayList(salles);
        idsallev.setCellValueFactory(new PropertyValueFactory<>("idsalle"));
        nomsallev.setCellValueFactory(new PropertyValueFactory<>("nomsalle"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));
        categoriev.setCellValueFactory(new PropertyValueFactory<>("idcategorie"));
        imagev.setCellValueFactory(new PropertyValueFactory<>("image"));
        tablesalles.setItems(List);
    }

    @FXML
    private void Add(ActionEvent event) {
        if (nomsalletx.getText().isEmpty() || descriptiontx.getText().isEmpty() || URLImage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else if (BadWordFilter.filterText(descriptiontx.getText()) || BadWordFilter.filterText(nomsalletx.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Ajout refusée!");
            alert.setContentText("Vos données contient des mots vulguéres!");
            alert.show();
        } else {
            String nomSalle = nomsalletx.getText();
            String description = descriptiontx.getText();
            int categorieId = categoriebox.getSelectionModel().getSelectedItem();
            String imageUrl = URLImage.getText();
            SalleService cs = new SalleService();
            //System.out.println(URLImage.getText());
            Salle s = new Salle(nomSalle, description, categorieId, imageUrl);
            cs.ajouter(s);
            ShowSalles();
            raifraichir();
        }
    }

    @FXML
    private void ModSalle(ActionEvent event) {
        if (nomsalletx.getText().isEmpty() || descriptiontx.getText().isEmpty() || URLImage.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else if (BadWordFilter.filterText(descriptiontx.getText()) || BadWordFilter.filterText(nomsalletx.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Modification refusée!");
            alert.setContentText("Vos données contient des mots vulguéres!");
            alert.show();
        } else {
            Salle c = new Salle();
            SalleService cs = new SalleService();
            c = tablesalles.getSelectionModel().getSelectedItem();
            c.setIdcategorie(tablesalles.getSelectionModel().getSelectedItem().getIdcategorie());
            c.setNomsalle(nomsalletx.getText());
            c.setDescription(descriptiontx.getText());
            c.setIdcategorie(categoriebox.getValue());
            c.setImage(URLImage.getText());
            cs.modifier(c);
            ShowSalles();
            raifraichir();
        }
    }

    @FXML
    private void SupprimerSalle(ActionEvent event) {

        SalleService ss = new SalleService();
        Salle e = tablesalles.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
            tablesalles.getItems().remove(e);
            List<String> emails = ss.getEmails();
            ss.supprimer(e.getIdsalle());
            
            
            for (String mail : emails) {
                try {
                    MailAPI.sendMail(mail, "Cours supprimé", "Le cours " + e.getNomsalle() + " avec la référence " + e.getIdsalle() + " a été supprimé");
                } catch (MessagingException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            
            ShowSalles();
            tablesalles.getItems().removeAll(tablesalles.getSelectionModel().getSelectedItem());
            raifraichir();
        }
    }

    private void raifraichir() {
        nomsalletx.setText("");
        descriptiontx.setText("");
        URLImage.setText("");
    }

    private void rechercher() {

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
                } else if (String.valueOf(salle.getIdsalle()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id salle.
                } else if (String.valueOf(salle.getIdcategorie()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id categorie.
                } else {
                    return false; // Does not match.
                }
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

    @FXML
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\Users\\\\ahmed\\\\Desktop\\\\ahmedjebridesc\\\\src\\\\image" + x + ".jpg";

        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path = file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            Image.setImage(img);
            URLImage.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();

        } else {
            System.out.println("error");

        }

    }

    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
        pdf Pdf = new pdf();
        Pdf.add("salle.pdf");
    }

}
