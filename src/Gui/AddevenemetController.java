/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Eventl;
import Services.ServiceEventl;
import Utils.MyDB;
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
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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



/**
 * FXML Controller class
 *
 * @author Home
 */
public class AddevenemetController implements Initializable {
 Connection cnx;
    @FXML
    private TextField titretx;
    @FXML
    private TextField descriptiontx;
    @FXML
    private TextField villetx;
    @FXML
    private DatePicker datedebuttx;
    @FXML
    private DatePicker datefintx;
    @FXML
    private TextField URLImage;
    @FXML
    private Button AddImage;
    @FXML
    private Button Add;
    @FXML
    private ImageView Image;
Eventl ss = new Eventl();
   private Statement ste;
    private Eventl v;
    String query = null;
     Double latitude;
    Double   longitude ;
    Eventl e = null;
    ResultSet resultSet = null;
    private Eventl event;
       @FXML
    private WebView webmap;
    private WebEngine webengine;
    ObservableList<Eventl> List = FXCollections.observableArrayList();

 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               webengine = webmap.getEngine();

        url = this.getClass().getResource("map/index.html");
        webengine.load(url.toString());
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
        latitude =  Double.parseDouble(webmap.getEngine().executeScript("lat").toString());
             
            longitude =  Double.parseDouble(webmap.getEngine().executeScript("lon").toString());
             
             
                     System.out.println("Lat AjoutCom: " + latitude);
                System.out.println("LOn AjoutCom" + longitude);

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
           // showAzer(); //// raifrach table view ///

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("succes");
            alert.setHeaderText(null);
            alert.setContentText("evenement ajouter");
            alert.showAndWait();
        }
    }

    private boolean TestDescription() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*");
        Matcher m = p.matcher(descriptiontx.getText());
        if (m.find() && m.group().equals(descriptiontx.getText())) {
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
}
