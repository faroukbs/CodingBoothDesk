/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.DetailsController.numeroPDF;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javax.imageio.ImageIO;
import service.CommandeService;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class AffichageCommandeController implements Initializable {
    CommandeService Sp = new CommandeService();
         List<Commande> lt = Sp.recupererCommande();

    ObservableList<Commande> list;
    public static int numeroPDF = 0;
    Document doc = new Document();
    Connection cnx;


    @FXML
    private TableView<Commande> TableView;
    
    @FXML
    private TableColumn<Commande, String> nom;
    @FXML
    private TableColumn<Commande, String> prenom;
    @FXML
    private TableColumn<Commande, String> tel;
    @FXML
    private TableColumn<Commande, String> adr;
    @FXML
    private TableColumn<Commande, String> mont;
    @FXML
    private TableColumn<Commande, String> mode;
    @FXML
    private TableColumn<Commande, Integer> etat;
    @FXML
    private Button miseAjour;
    @FXML
    private AnchorPane chartNode;
    @FXML
    private Button modify1;
    @FXML
    private Button supprimer1;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Commande commande = null ;
    CommandeService crudCommande = new CommandeService();
    
    ObservableList<Commande>  CommandeList = FXCollections.observableArrayList();
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
    private TextField getRecherche;
    @FXML
    private Button helpp;
    @FXML
    private Button retourBtn2;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommandeService ps = new CommandeService();
        List<Commande> Commandes = ps.recupererCommande();
        ObservableList list = FXCollections.observableArrayList(Commandes);
        TableView.setItems(list);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mont.setCellValueFactory(new PropertyValueFactory<>("montant"));
        mode.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));

    }    

    @FXML
    private void modifierCommande(ActionEvent event) {
        Commande voy = new Commande();
        CommandeService sv = new CommandeService();
        voy = TableView.getSelectionModel().getSelectedItem();
        voy.setIdcommande(TableView.getSelectionModel().getSelectedItem().getIdcommande());
        voy.setNom_client(nom.getText());

        voy.setPrenom_client(prenom.getText());
        voy.setTelephone(tel.getText());
        voy.setAdresse(adr.getText());
        voy.setMontant(mont.getText());
        voy.setMode_paiement(mode.getText());
        voy.setEtat_commande(Integer.parseInt(etat.getText()));

        

        sv.modifierCommande(voy);
        showAzer(); //// raifrach table view ///
    }
     public void delete() {
        CommandeService SV = new CommandeService();
        SV.supprimerCommande(TableView.getSelectionModel().getSelectedItem().getIdcommande());
        System.out.println(TableView.getSelectionModel().getSelectedItem().getIdcommande());

    }

    @FXML
    private void supprimerCommande(ActionEvent event) {
        delete();
        showAzer(); //// raifrach table view ///
        TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
        System.out.println(TableView);
    }

    private void reload(ActionEvent event) {
        
         
        try {
            CommandeList.clear();
            
            query = "SELECT * FROM `commande`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                CommandeList.add(new  Commande(
                resultSet.getInt("idcommande"),
                resultSet.getString("nom_client"),
                resultSet.getString("prenom_client"),
                resultSet.getString("telephone"),
                resultSet.getString("adresse"),
                resultSet.getString("montant"),
                resultSet.getString("mode_paiement"),
                resultSet.getInt("etat_commande")));
                TableView.setItems(CommandeList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AffichageCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void showAzer() {
        CommandeService ps = new CommandeService();
        List<Commande> Commandes = ps.recupererCommande();
        ObservableList list = FXCollections.observableArrayList(Commandes);
        TableView.setItems(list);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mont.setCellValueFactory(new PropertyValueFactory<>("montant"));
        mode.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));

        
    }

    @FXML
    private void details(ActionEvent event) {
         try {
            Commande p =TableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
            Parent root = loader.load();
            DetailsController dc = loader.getController();
            dc.setPersonne(p);
            TableView.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
    }

    @FXML
    private void Rechercher(ActionEvent event) {
   
    CommandeService tt = new CommandeService();
        lt = tt.RechercherCommande(getRecherche.getText());
          System.out.println("Recherche");
        System.out.println(getRecherche.getText());
        TableView.setEditable(true);
        
        
        
        
       ObservableList<Commande> datalist = FXCollections.observableArrayList(lt);
        nom.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        tel.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mont.setCellValueFactory(new PropertyValueFactory<>("montant"));
        mode.setCellValueFactory(new PropertyValueFactory<>("mode_paiement"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat_commande"));


        TableView.setItems(datalist);
        
         
       
    };
    @FXML
    public void help(){
            System.out.println("heelp");
            AudioClip note = new AudioClip(this.getClass().getResource("VPS.wav").toString());
            note.play();
        };

    @FXML
    private void convertirPDF(ActionEvent event) {
        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("DateLyoummmmmmmmmmmmmmmmmmmmm   " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Rapport Pour :" + DateRapport);
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(6);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("Id");
            table.addCell("Nom Client");
            table.addCell("Prenom Client");
            table.addCell("Adress");
            table.addCell("Phone");
            table.addCell("Montant");


            //     table.addCell("Image : ");

            crudCommande.AfficherCommande(commande).forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getIdcommande()));
                table.addCell(e.getNom_client());
                table.addCell(e.getPrenom_client());
                table.addCell(e.getAdresse());
                table.addCell(e.getTelephone());
                table.addCell(String.valueOf(e.getMontant()));
            }
            );
            document.add(ph1);
            document.add(ph2);
            document.add(table);
            //  document.addAuthor("Bike");
            // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        if (file.exists()) //checks file exists or not  
        {
            Desktop desktop = Desktop.getDesktop();      
            try {
                desktop.open(file); //opens the specified file   
            } catch (IOException ex) {
                Logger.getLogger(AffichageCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}