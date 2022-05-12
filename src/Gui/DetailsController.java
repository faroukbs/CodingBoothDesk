/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entities.Commande;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.CommandeService;
import service.ServicePayment;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class DetailsController implements Initializable {
Stage dialogStage = new Stage();

    
    Connection cnx;

    
    @FXML
    private TextField adr;
    @FXML
    private TextField mont;
    @FXML
    private TextField tel;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private Button retourBtn;
    @FXML
    private TextField mode;
    @FXML
    private TextField etat;
    Commande c;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button retourBtn1;
    @FXML
    private Button retourBtn2;
    public static int numeroPDF = 0;
    Document doc = new Document();
    @FXML
    private DatePicker datee;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnMenus11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void setPersonne (Commande p){
        nom.setText(p.getNom_client());
        prenom.setText(p.getPrenom_client());
        tel.setText(p.getTelephone());
        adr.setText(p.getAdresse());
        mont.setText(p.getMontant());
        mode.setText(p.getMode_paiement());
        etat.setText(String.valueOf(p.getEtat_commande())); 
    }
    

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageCommande.fxml"));
            Parent root = loader.load();
            retourBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void payer(ActionEvent event) throws IOException {
         ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(() -> {
            ServicePayment P=new ServicePayment();
            
            P.RetrieveCustomer ();
                Integer Dueamount = Integer.parseInt(mont.getText());
    P.payement (Dueamount);

        }
);
        emailExecutor.shutdown();
        Node source = (Node) event.getSource();
        dialogStage = (Stage) source.getScene().getWindow();
        dialogStage.close();
      //  scene = new Scene (FXMLLoader.load(getClass().getResource("Shop.fxml")));
        
       // dialogStage.setScene(scene);

        String title = "Payement succesful";
        String message = "You payment  has been Approved";

    

        dialogStage.show();
    }

    @FXML
    private void convertirPDF(ActionEvent event) throws FileNotFoundException, DocumentException, BadElementException, IOException {
    }
@FXML
    private void produit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }
    

    @FXML
    private void productCart(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }

    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("ClientReclamationController.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }

    @FXML
    private void Account(ActionEvent event) {
    }

    @FXML
    private void hhhh(ActionEvent event) {
    }
}
