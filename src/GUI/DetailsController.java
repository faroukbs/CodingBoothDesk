/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import entities.Commande;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import service.CommandeService;
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
            try {
            
            OutputStream file =new FileOutputStream(new File("test.pdf"));
            Document document = new Document();
            
            PdfWriter.getInstance(document, file);
            
            document.open();
              List<Commande> res= new ArrayList<>();
            

              CommandeService rs =new CommandeService();
            res=rs.recupererCommande();
            
            PdfPTable tab =new PdfPTable(5);
            
            String txt="<!-- #######  YAY, I AM THE SOURCE EDITOR! #########-->\n" +
"<h1 style=\"color: #5e9ca0;\"> <span style=\"color: #2b2301;\"></span> </h1>\n" +
"<h2 style=\"color: #2e6c80;\"></h2>\n" +
"<p> <br /></p>\n" +
"<p> <span style=\"background-color: #2b2301; color: #fff; display: inline-block; padding: 3px 10px; font-weight: bold; border-radius: 5px;\"></span> </p>\n" +
"<h2 style=\"color: #2e6c80;\"></h2>\n" +
"<ol style=\"list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;\">\n" +
"<li style=\"clear: both;\">&nbsp;</li>\n" +
"</ol>\n" +
"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\n" +
"<h2 style=\"color: #2e6c80;\"></h2>\n" +
"<p>&nbsp;</p>\n" +
"<p><strong > </strong><br /><strong>Enjoy!</strong></p>\n" +
"<p><strong>&nbsp;</strong></p>";
           document.setHtmlStyleClass(txt);
           
            String url1 = getClass().getResource("images/" +"LOGO1.png").toString();
                
                

                
                Image img1 = Image.getInstance(url1);
                img1.setAlignment(Image.RIGHT);
                
                document.add(img1);
            
            tab.addCell("Nom");
            tab.addCell("prenom");
            tab.addCell("Num");
            tab.addCell("Adresse");
            tab.addCell("Montant"); 
            tab.addCell("mode de paiement");
            tab.addCell("etat commande");
            
            Paragraph p =new Paragraph("                                               LISTE DES COMMANDES :");
            
           
            p.setSpacingBefore(50);
            document.add(p);
            document.add(new Paragraph("                                             -------------------------------------------"));
             document.add(new Paragraph("                      "));
            document.add(new Paragraph("                      "));
            document.add(new Paragraph("                      "));
           
             document.add(new Paragraph("                      "));
            
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.setHtmlStyleClass(txt);
           
            
            
            
            for(int i=0;i<res.size();i++)
            {
               
                Commande resto =res.get(i);
                String nom =resto.getNom_client();
                String prenom=String.valueOf(resto.getPrenom_client());
                String tel=String.valueOf(resto.getTelephone());
                String adr=String.valueOf(resto.getAdresse());
                String mont=String.valueOf(resto.getMontant());
                
                String monde=String.valueOf(resto.getMode_paiement());
                
                
                //String imageFile = resto.getImage() ;
               // ImageData data = ImageDataFactory.create(imageFile); 
                
                
                

                
//                Image img = Image.getInstance(url);
                //System.out.println(resto.getImage());
                
                tab.addCell(nom);
                tab.addCell(prenom);
                tab.addCell(tel); 
                tab.addCell(adr);
                tab.addCell(mont);
                
                
                
                
                
                
            
               
            }
            document.add(tab);
            
           
        
            
            document.close();
            
            System.out.println("your pdf has been created");
            file.close();
        } catch (Exception e) {
        }
        
         
        
    }
            
        
   
    }

