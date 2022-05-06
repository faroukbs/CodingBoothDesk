/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Cart;
import entities.Commande;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;

import service.CommandeService;
import service.Mailling;

/**
 * FXML Controller class
 *
 * @author aicha
 */
public class PasseCommandeController implements Initializable {

    
    @FXML
    private Button confirmer;
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
    private ComboBox<String> mode;
    @FXML
    private ComboBox<Integer> etat;
    String erreur;
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
    private Button confirmer1;
    Commande r = new Commande();
    @FXML
    private Button confirmer2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> list = FXCollections.observableArrayList (0,1); 
        ObservableList<String> list1 = FXCollections.observableArrayList ("En ligne","CASH"); 
        
        etat.setItems(list);
        mode.setItems(list1);    } 
    

    @FXML
    private void AjouterCommande(ActionEvent event) {
//        if ( nom.getText().isEmpty()
//                || prenom.getText().isEmpty() || tel.getText().isEmpty() || adr.getText().isEmpty() 
//                || mont.getText().isEmpty()) {
//
//            InnerShadow in = new InnerShadow();
//            in.setColor(Color.web("#f80000"));
//            nom.setEffect(in);
//            prenom.setEffect(in);
//            tel.setEffect(in);
//            adr.setEffect(in);
//            mont.setEffect(in);
//            mode.setEffect(in);
//            etat.setEffect(in);
//
//            //txtnom.setStyle("-fx-border-color: red " );
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Erreur");
//            alert.setHeaderText(null);
//            alert.setContentText("Il faut remplir les champs obligatoires ");
//            alert.showAndWait();
//
//        } else if (testNom()& testPrenom() & testTel() ) {
//            
//         
//            String nomClient = nom.getText();
//
//            String prenomClient = prenom.getText();
//            String phone = tel.getText();
//            String adresse = adr.getText();
//           
//            String montant = mont.getText();
//            String modeP = mode.getSelectionModel().getSelectedItem();
//            Integer etatC = etat.getSelectionModel().getSelectedItem();  
//            
//        Double d  = Cart.getInstance().total() ;
//        String montants=String.valueOf(d);  
//        System.out.println(montant);
//
//            CommandeService ps = new CommandeService();
//            
//            Commande v = new Commande(nomClient, prenomClient, phone, adresse, montant, modeP, etatC );
//
//            ps.ajouterCommande(v);
//
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("succes");
//            alert.setHeaderText(null);
//            alert.setContentText("Commande ajouter");
//            alert.showAndWait();
//            String message ="Votre commande votre commande est bien prise en compte "; 
//             //SMSController smsc= new SMSController();
//             sms("aicha97", "Aicha1997", "216"+tel, message);
//             System.out.println("216"+tel);

            try{
            Parent root = FXMLLoader.load(getClass().getResource("Paiement.fxml"));
            nom.getScene().setRoot(root);
//            DetailsController Dc = new DetailsController();
//            
//            Dc.setIdCommande(v.getIdcommande());
            }catch (IOException ex) {
            System.err.println(ex.getMessage());
            }
            
        
//        }
//        Commande p = new Commande();
//        
//        p.setNom_client(nom.getText());
//        p.setPrenom_client(prenom.getText());
//        p.setTelephone(tel.getText());
//        p.setAdresse(adr.getText());
//        p.setMontant(mont.getText());
//        p.setMode_paiement(mode.getText());
//
//        p.setEtat_commande(Integer.parseInt(etat.getText()));
//        CommandeService ps = new CommandeService();
//        ps.ajouterCommande(p);
//        try {
//            //alert
////        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
////        alert.setTitle("Success");
////        alert.setContentText("Personne ajoutée");
////        alert.show();
//        Parent root = FXMLLoader.load(getClass().getResource("AffichageCommande.fxml"));
//        nom.getScene().setRoot(root);
//              
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        
    
    }
//    }

public void sms(String username, String password, String to,String message){
            try{
        
        String myURI = "https://api.movider.co/v1/sms";

    // change these values to match your own account
    // new compte pour envoyer sms ***********************************************************
    String myUsername = "aicha";
    String myPassword = "Aicha1997";

    // the details of the message we want to send
    String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+message+"\"}";

    // if your message does not contain unicode, the "encoding" is not required:
    // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    } catch (IOException ex) {
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    }
    request.disconnect();
        
    }catch(Exception e)
    {
        
        
        System.out.println(e);
    }}
    

    private Boolean testSaisie() {
        String erreur = "";
        
        if (!testTel()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        
        
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }

        if ( !testTel()  ||  !testNom() || !testPrenom()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
        }

        return  testTel()  && testNom() && testPrenom();
    }
    private Boolean testTel() {
        if (tel.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < tel.getText().trim().length(); i++) {
                char ch = tel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                
                return true;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("le numero doit etre en chiffre ");
            alert.showAndWait();
                return false;

            }
        } else if (tel.getText().trim().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut saisir 8 chiffre  ");
            alert.showAndWait();
            return false;
        }

        return true;

    }

   
    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            
            return true;
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
            return false;

        }

    }

    private Boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < prenom.getText().trim().length(); i++) {
            char ch = prenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenom.getText().trim().length() >= 3) {
            return true;
        } else {
Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs correctement ");
            alert.showAndWait();
            return false;

        }

    }


   


    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void select1(ActionEvent event) {
               String s = mode.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    private void select(ActionEvent event) {
               Integer e = etat.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void mail(ActionEvent event) {
        if ( nom.getText().isEmpty()
                || prenom.getText().isEmpty() || tel.getText().isEmpty() || adr.getText().isEmpty() 
                || mont.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            nom.setEffect(in);
            prenom.setEffect(in);
            tel.setEffect(in);
            adr.setEffect(in);
            mont.setEffect(in);
            mode.setEffect(in);
            etat.setEffect(in);

            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if (testNom()& testPrenom() & testTel() ) {
            
         
            String nomClient = nom.getText();

            String prenomClient = prenom.getText();
            String phone = tel.getText();
            String adresse = adr.getText();
            String modeP = mode.getSelectionModel().getSelectedItem();
            Integer etatC = etat.getSelectionModel().getSelectedItem();            

            Double montaDouble  = Cart.getInstance().total() ;
            String montant=String.valueOf(montaDouble);  
            System.out.println(montant);

            CommandeService ps = new CommandeService();
            
            Commande v = new Commande(nomClient, prenomClient, phone, adresse, montant, modeP, etatC );

            ps.ajouterCommande(v);


            Mailling M = new Mailling() ;
            M.envoyer(v);
            
            Alert alertt = new Alert(Alert.AlertType.INFORMATION);
            alertt.setTitle("Success");
            alertt.setContentText(" un Email a été enoyer avec succes ");
            alertt.show();
        }
    }
    
    
}
