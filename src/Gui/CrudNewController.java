/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Eventl;
import Services.Pdf;
import Services.ServiceEventl;

import Utils.MyDB;
import com.itextpdf.text.DocumentException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.BadWordFilter;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class CrudNewController implements Initializable {
  private AnchorPane report88;
    Connection cnx;
    @FXML
    private TextField titretx;
    @FXML
    private TextField villetx;
    @FXML
    private TextField descriptiontx;
    @FXML
    private DatePicker datedebuttx;
    @FXML
    private TextField URLImage;
    @FXML
    private DatePicker datefintx;
    @FXML
    private Button Add;
    @FXML
    private Button AddImage;
    @FXML
    private TableView<Eventl> tableevenement;

    @FXML
    private TableColumn<Eventl, String> titrev;
    @FXML
    private TableColumn<Eventl, String> descriptionv;
    @FXML
    private TableColumn<Eventl, Date> datedebutv;
    @FXML
    private TableColumn<Eventl, Date> datefinv;
    @FXML
    private TableColumn<Eventl, ImageView> Vimage;
    @FXML
    private Button ModVoy;
    @FXML
    private Button SupprimerVoyage;
    @FXML
    private ImageView Image;
    @FXML
    private TableColumn<Eventl, String> villev;
    Eventl ss = new Eventl();
    private ComboBox<String> combox;
    private Statement ste;
    private Eventl v;
    String query = null;
   
    Double latitude;
    Double   longitude ;
    Eventl e = null;
    ResultSet resultSet = null;
    private Eventl event;
    ObservableList<Eventl> List = FXCollections.observableArrayList();
    @FXML
    private TextField Recherche;
   @FXML
    private WebView webmap;
    private WebEngine webengine;
    @FXML
    private Pagination pagination;
        private final static int dataSize = 100;


    
    private final static int rowsPerPage = 10;    
   private volatile boolean stop=false;
    @FXML
    private Button stat1;
    @FXML
    private Button Notif;
    @FXML
    private Button pdf;
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
   
             pagination.setPageFactory(this::createPage);   
            webengine = webmap.getEngine();

        url = this.getClass().getResource("map/index.html");
        webengine.load(url.toString());

        showAzer(); //// raifrach table view ///

    }
 
     
       //method to create page inside pagination view
    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
       // int toIndex = Math.min(fromIndex + rowsPerPage);
    //    tableevenement.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        return tableevenement;
    }

    @FXML
    private void Liste_Voyage(javafx.scene.input.MouseEvent event) {

        try {
            Eventl eventl = tableevenement.getSelectionModel().getSelectedItem();
            titretx.setText(eventl.getTitre());
            descriptiontx.setText(eventl.getDescription());
            villetx.setText(eventl.getVille());
            datedebuttx.setValue(eventl.getDatedebut().toLocalDate());
            datefintx.setValue(eventl.getDatefin().toLocalDate());
            URLImage.setText(eventl.getPhoto());
            String path = eventl.getPhoto();
            File file = new File(path);
            Image img = new Image(file.toURI().toString());
            Image.setImage(img);
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void delete() {
        
        ServiceEventl SV = new ServiceEventl();
        SV.supprimer(tableevenement.getSelectionModel().getSelectedItem().getIdevent());
        System.out.println(tableevenement.getSelectionModel().getSelectedItem().getIdevent());

    }

    @FXML
    private void SupprimerVoyage(ActionEvent event) {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
        delete();
        showAzer(); //// raifrach table view ///
        tableevenement.getItems().removeAll(tableevenement.getSelectionModel().getSelectedItem());
        System.out.println(tableevenement);
//      tableevenement.refresh();
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
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\piweb\\\\FirstPi1\\\\public\\\\images\\\\" + x + ".jpg";

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
    private void Add(ActionEvent event) {
//        latitude =  Double.parseDouble(webmap.getEngine().executeScript("lat").toString());
//             
//            longitude =  Double.parseDouble(webmap.getEngine().executeScript("lon").toString());
//             
//             
//                     System.out.println("Lat AjoutCom: " + latitude);
//                System.out.println("LOn AjoutCom" + longitude);

       int nombreAleatoire = 1000 + (int) (Math.random() * ((1000000 - 1000) + 1));
        if (titretx.getText().isEmpty()
                || descriptiontx.getText().isEmpty() || villetx.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            titretx.setEffect(in);
            descriptiontx.setEffect(in);
            villetx.setEffect(in);

            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if (TestVille() & TestText() & TestDescription() & TestDate()) {
            String titre = titretx.getText();

            String description = descriptiontx.getText();
            String ville = villetx.getText();

            java.sql.Date datedebut = java.sql.Date.valueOf(datedebuttx.getValue());

            java.sql.Date datefin = java.sql.Date.valueOf(datefintx.getValue());

            ServiceEventl ps = new ServiceEventl();
            System.out.println(URLImage.getText());
            Eventl v = new Eventl(titre, description, datedebut, datefin, ville, URLImage.getText(),latitude, longitude);

            ps.ajouter(v);
            showAzer(); //// raifrach table view ///

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succes");
            alert.setHeaderText(null);
            alert.setContentText("evenement ajouter");
            alert.showAndWait();
        }
    }

    @FXML
    private void ModVoy(ActionEvent event) {
        Eventl voy = new Eventl();
        ServiceEventl sv = new ServiceEventl();
        voy = tableevenement.getSelectionModel().getSelectedItem();
        voy.setIdevent(tableevenement.getSelectionModel().getSelectedItem().getIdevent());
        voy.setTitre(titretx.getText());

        voy.setVille(villetx.getText());
        voy.setDescription(descriptiontx.getText());

        voy.setDatedebut(java.sql.Date.valueOf(datedebuttx.getValue()));
        voy.setDatefin(java.sql.Date.valueOf(datefintx.getValue()));
        voy.setPhoto(URLImage.getText());

       sv.modifier(voy);
        showAzer(); //// raifrach table view ///

    }

    @FXML
    private void Recherche(KeyEvent event) {
        ServiceEventl bs = new ServiceEventl();
        Eventl b = new Eventl();
        ObservableList<Eventl> filter = bs.chercherTitreplat(Recherche.getText());
        populateTable(filter);
    }

    private void populateTable(ObservableList<Eventl> branlist) {
        tableevenement.setItems(branlist);

    }

    private boolean TestText() {
        Pattern p = Pattern.compile("[a-zA-Z0-9]*[a-zA-Z0-9]*");
        Pattern p1 = Pattern.compile("[s][+][0-9]*");
        Matcher m = p.matcher(titretx.getText());
        Matcher m1 = p.matcher(titretx.getText());
        if (m.find() && m.group().equals(titretx.getText()) || m1.find() && m1.group().equals(titretx.getText())) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir un nom valide");
            alert.showAndWait();

            return false;
        }
    }

    private boolean TestDescription() {
             if (descriptiontx.getText().isEmpty()) {
                descriptiontx.setText("Erreur, Champ vide!");
            }else if(BadWordFilter.filterText(descriptiontx.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur");
                alert.setHeaderText("Commentaire refusé!");
                alert.setContentText("Votre commentaire contient des mots vulguéres!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Commenter?");
                alert.setHeaderText("Passer votre commentaire?");
                alert.setContentText("Acceptez pour passer votre commentaire");

    } return false;
}

    private boolean TestVille() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*");
        Matcher m = p.matcher(villetx.getText());
        if (m.find() && m.group().equals(villetx.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une description  valide");
            alert.showAndWait();

            return false;
        }
    }

//    ImageTypeSpecifier createFromRenderedImage(RenderedImage image) throws IOException {
//        String fileNmaeExt = file.getName();
//
//        File initialImage = new File("C:\\Users\\user\\Desktop\\iheb");
//        if (image == null) {
//            throw new IllegalArgumentException("image == null!");
//        }
//
//        if (image instanceof BufferedImage) {
//            write(image, fileNmaeExt, initialImage);
//
//        }
//        return new ImageTypeSpecifier(image);
//    }
    private boolean TestDate() {
        java.sql.Date datedebut = java.sql.Date.valueOf(datedebuttx.getValue());

        java.sql.Date datefin = java.sql.Date.valueOf(datefintx.getValue());
        if (datefin.compareTo(datedebut) > 0) {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            datedebuttx.setEffect(in);
            datefintx.setEffect(in);
            return true;

        } else {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            datedebuttx.setEffect(in);
            datefintx.setEffect(in);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une date valide");
            alert.showAndWait();

            return false;
        }
    }

    public ObservableList<Eventl> geteventlist() {
        ObservableList<Eventl> eventlist = FXCollections.observableArrayList();

        String req = "SELECT * FROM eventl";
        Statement st;
        ResultSet rs;
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            Eventl Eventl;
            while (rs.next()) {
                Eventl = new Eventl(rs.getString("titre"), rs.getString("description"), rs.getString("ville"), rs.getDate("datedebut"), rs.getDate("datefin"), rs.getString("photo"));
                eventlist.add(Eventl);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return eventlist;
    }

    //// rafraich table view /////
    @FXML
    private void showAzer() {
        ServiceEventl ps = new ServiceEventl();
        List<Eventl> events = ps.recuperer();
        ObservableList list = FXCollections.observableArrayList(events);
        tableevenement.setItems(list);
        titrev.setCellValueFactory(new PropertyValueFactory<>("titre"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));

        villev.setCellValueFactory(new PropertyValueFactory<>("ville"));
        datedebutv.setCellValueFactory(new PropertyValueFactory<>("datedebut"));
        datefinv.setCellValueFactory(new PropertyValueFactory<>("datefin"));

        Vimage.setCellValueFactory(new PropertyValueFactory<>("photo"));
    }

private void notiff()
    {
        ServiceEventl sv = new ServiceEventl();
        Eventl v = new Eventl();
                String ville = villetx.getText();
        int y=sv.calculnb((villetx.getText()));
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("attention");
        tray.setMessage("il existe "+y+ " evenement a "+ville+"");
tray.setNotificationType(NotificationType.INFORMATION);
tray.showAndDismiss(Duration.millis(2000));
    }
   
    @FXML
    private void notif(ActionEvent event) {
            notiff();
    }
    @FXML
    private void OnStatClicked(ActionEvent event) {
                try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("EventStat.fxml"));
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
    private void pdf(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification");
                alert.setHeaderText("Voulez vous recevoir la liste des produits par mail?");
                //alert.setContentText("");

                Optional<ButtonType> option = alert.showAndWait();
                //confirmation 
                if (option.get() == ButtonType.OK) {
                    Pdf.createAndSendList("farah.hasnaoui22@gmail.com");
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Information!");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Mail et pdf envoyés");
                    alert2.show();
                } else {
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setTitle("Erreur!");
                    alert3.setHeaderText(null);
                    alert3.setContentText("Erreur d'envoi!");
                    alert3.show();
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

