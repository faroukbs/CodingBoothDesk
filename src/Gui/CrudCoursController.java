/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.google.zxing.BarcodeFormat;
import com.itextpdf.text.DocumentException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.WriterException;
import entities.Cours;
import entities.Salle;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.mail.MessagingException;
import services.CoursService;
import Utils.MailAPI;
import Utils.pdfc;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utils.BadWordFilter;

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
    @FXML
    private TableColumn<Salle, String> imagev;
    @FXML
    private TextField URLImage;
    @FXML
    private Button AddImage;
    @FXML
    private ImageView Image;
    @FXML
    private Button bntPDF;
    @FXML
    private Button recupererFP;
    @FXML
    private ImageView QRCode;
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
        imagev.setCellValueFactory(new PropertyValueFactory<>("imagecours"));
        datedebutv.setCellValueFactory(new PropertyValueFactory<>("start"));
        datefinv.setCellValueFactory(new PropertyValueFactory<>("end"));
    }

    private void raifraichir() {
        titletx.setText("");
        descriptiontx.setText("");

    }

    @FXML
    private void rechercher() {

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
                } else if (String.valueOf(cours.getIdcours()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id cours.
                } else if (String.valueOf(cours.getIdcoach()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id coach.
                } else if (String.valueOf(cours.getIdsalle()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id salle.
                } else if (String.valueOf(cours.getIdcategorie()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id salle.
                } else {
                    return false; // Does not match.
                }
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
        if (titletx.getText().isEmpty() || descriptiontx.getText().isEmpty() || URLImage.getText().isEmpty()) {
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
        } else if (BadWordFilter.filterText(descriptiontx.getText()) || BadWordFilter.filterText(titletx.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Ajout refusée!");
            alert.setContentText("Vos données contient des mots vulguéres!");
            alert.show();
        } else {
            String titre = titletx.getText();
            String description = descriptiontx.getText();
            int categorieId = categoriebox.getSelectionModel().getSelectedItem();
            int coachid = coachbox.getSelectionModel().getSelectedItem();
            int salleid = sallebox.getSelectionModel().getSelectedItem();
            String imageUrl = URLImage.getText();
            LocalDate dateDebut = datedebut.getValue();
            LocalDate dateFin = datefin.getValue();
            CoursService cs = new CoursService();
            Cours c = new Cours(titre, categorieId, coachid, salleid, description, imageUrl, java.sql.Date.valueOf(dateDebut), java.sql.Date.valueOf(dateFin));
            cs.ajouter(c);
            ShowCours();
            raifraichir();
        }

    }

    @FXML
    private void ModCours(ActionEvent event) {
        if (titletx.getText().isEmpty() || descriptiontx.getText().isEmpty() || URLImage.getText().isEmpty()) {
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
        } else if (BadWordFilter.filterText(descriptiontx.getText()) || BadWordFilter.filterText(titletx.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Modification refusée!");
            alert.setContentText("Vos données contient des mots vulguéres!");
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
            c.setImagecours(URLImage.getText());
            c.setStart(java.sql.Date.valueOf(datedebut.getValue()));
            c.setEnd(java.sql.Date.valueOf(datefin.getValue()));
            cs.modifier(c);
            ShowCours();
            raifraichir();
        }
    }

    @FXML
    private void SupprimerCours(ActionEvent event) {
        CoursService cs = new CoursService();
        Cours e = tablecours.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
            tablecours.getItems().remove(e);
            List<String> emails = cs.getEmails();
            cs.supprimer(e.getIdcours());

            for (String mail : emails) {
                try {
                    MailAPI.sendMail(mail, "Cours supprimé", "Le cours " + e.getTitle() + " avec la référence " + e.getIdcours() + " a été supprimé");
                } catch (MessagingException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            //System.out.println(tablecours.getSelectionModel().getSelectedItem().getIdsalle());
            ShowCours(); //// raifrach table view ///
            tablecours.getItems().removeAll(tablecours.getSelectionModel().getSelectedItem());
            raifraichir();

        }
    }

    @FXML
    private void list_cours(MouseEvent event) {
        try {
            Cours c = tablecours.getSelectionModel().getSelectedItem();
            titletx.setText(c.getTitle());
            descriptiontx.setText(c.getDescription());
            URLImage.setText(c.getImagecours());
            String path = c.getImagecours();
            File file = new File(path);
            Image img = new Image(file.toURI().toString());
            Image.setImage(img);
            datedebut.setValue(c.getStart().toLocalDate());
            datefin.setValue(c.getEnd().toLocalDate());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
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
        String DBPath = "C:\\\\Users\\\\Home\\\\Documents\\\\NetBeansProjects\\\\Farah\\\\src\\\\image\\\\" + x + ".jpg";

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
    private void pdfc(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
        pdfc Pdf = new pdfc();
        Pdf.add("cours.pdf");
    }

    public void start(Cours p) {

        QRCodeWriter QRCodeWriter;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = p.getDescription();
        int width = 300;
        int height = 300;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            System.out.println("");
        }

        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        QRCode.setImage(qrView.getImage());

        //StackPane root = new StackPane();
        /*root.getChildren().add(qrView);
        
        Scene scene = new Scene(root, 350, 350);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    @FXML
    private void recupererOnF(ActionEvent event) {
        Cours l1 = tablecours.getSelectionModel().getSelectedItem();
        if (l1 == null) {
            //veuillez selectionner une liiiiiiiiiiiiiiiigne
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Erreur !");
            alert1.setHeaderText(null);
            alert1.setContentText("veuillez selectionner une ligne du tableau puis appuyez sur le bouton recuperer");
            alert1.show();

        } else {

            start(l1);

        }
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
