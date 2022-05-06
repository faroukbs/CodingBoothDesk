/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commande;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.CommandeService;
import util.MyDBPi;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class ListComController implements Initializable {
    CommandeService Sp = new CommandeService();
    ObservableList<Commande> list;
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
    private Button modify1;
    @FXML
    private Button supprimer1;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    Button commande = null ;
    
    ObservableList<Commande>  CommandeList = FXCollections.observableArrayList();
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Button home;
    @FXML
    private Button ligne;
    @FXML
    private Button excel;

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

    @FXML
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
    private void retourLigne(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLigneCom.fxml"));
            Parent root = loader.load();
            ligne.getScene().setRoot(root);
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

//    @FXML
//    private void exportExcel(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
////        Connection cnx = Myconnexion.getInstance().getCnx();
//        String query = "Select * from commande";
//         PreparedStatement pst = cnx.prepareStatement(query);
//            ResultSet rs = pst.executeQuery();
//            XSSFWorkbook wb = new XSSFWorkbook();
//            XSSFSheet sheet = wb.createSheet("Détails commande");
//            XSSFRow header = sheet.createRow(0);
//            header.createCell(0).setCellValue("ID");
//            header.createCell(1).setCellValue("Nom Client");
//            header.createCell(2).setCellValue("Prenom Client");
//            header.createCell(3).setCellValue("Telephone");
//             header.createCell(4).setCellValue("Adresse");
//             header.createCell(5).setCellValue("Montant");
//             header.createCell(6).setCellValue("Mode de paiement");
//             header.createCell(7).setCellValue("Etat commande");
//        
//              
//            
//            int index = 1;
//            while(rs.next()){
//                XSSFRow row = sheet.createRow(index);
//                row.createCell(0).setCellValue(rs.getInt("idcommande"));
//                row.createCell(1).setCellValue(rs.getString("nom_client"));
//                row.createCell(2).setCellValue(rs.getString("prenom_client"));
//                row.createCell(3).setCellValue(rs.getString("telephone"));
//                row.createCell(4).setCellValue(rs.getString("adresse"));
//                row.createCell(5).setCellValue(rs.getString("montant"));
//                row.createCell(6).setCellValue(rs.getString("mode_paiement"));
//                row.createCell(7).setCellValue(rs.getString("etat_commande"));
//                System.out.println(rs.getString("nom_client"));
//                
//               
//                index++;
//            }
            
//             crudCommande.AfficherCommande(commande).forEach(e
//                    -> {
//               for (int i=0 ; i<5 ;i++){
//                XSSFRow row = sheet.createRow(i);
//                row.createCell(0).setCellValue(e.getIdcommande());
//                row.createCell(1).setCellValue(e.getNom_client());
//                row.createCell(2).setCellValue(e.getPrenom_client());
//                row.createCell(3).setCellValue(e.getAdresse());
//                row.createCell(4).setCellValue(e.getAdresse());
//                  System.out.println(e.getNom_client());
//               }
//                
//                
//            }
//            );
            
//            FileOutputStream file = new FileOutputStream("Détailscommande.xlsx");
//            wb.write(file);
//            file.close();
//            
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Exportation effectuée!!!");
//            alert.showAndWait();
//            pst.close();
//            rs.close();
//            
//            File myFile = new File("C:/Users/aicha/Documents/NetBeansProjects/PiJava/Détailscommande.xlsx");
//             Desktop.getDesktop().open(myFile);
//    }
    
}
