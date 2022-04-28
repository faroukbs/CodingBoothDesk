/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import entities.Cart;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gorgi
 */
public class PaiementController implements Initializable {

   @FXML
    private TextField carte;
    @FXML
    private TextField month;
    @FXML
    private TextField cvc;
    @FXML
    private TextField year;
    @FXML
    private TextField prix;
    @FXML
    private Button valider;
    @FXML
    private Button Annuler;

    public void setPrix(String prix) {
       this.prix.setText(prix);
    }

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // prix.setDisable(true);
        // TODO
//                this.prix.setText(String.valueOf(CommandeFrontController.prix));
        valider.setOnAction((ActionEvent event) -> {
           
            
            try {
               
             if (controleDeSaisi()) {
              
            if (carte.getText().isEmpty()) {
                carte.setText("");
            }
            if (month.getText().isEmpty()) {
                month.setText("");
            }
            if (year.getText().isEmpty()) {
                year.setText("");
            }
            if (cvc.getText().isEmpty()) {
                cvc.setText("");
            }   
            
               }
                
//                    Stripe.apiKey="pk_test_51IlOujL98Jk5CJT1y7Gd7aSBwsykcD7lfqDHAko9w30lTbemSmIPBSkq9f6xxoHjeE0uADs7IDkiK9wjeWUwwQW9001V0UeGJy";
//        
//        
//        Customer a =Customer.retrieve("cus_JOBwZLoRc0VfHh");
                
        Stripe.apiKey="sk_test_51KYdqTD3kS5EqQroQHhrVRxUvLmzPnw74ZrOFhTamDzsE5OzQY4xCfaWTNqtpcxEojtoOSYQ4NbrsMJ5N4QelWS000AqAqrRv4";
      
                   Map<String, Object> cardParams = new HashMap<String, Object>();
            cardParams.put("number", Long.parseLong(carte.getText()));
            cardParams.put("exp_month", Integer.parseInt(month.getText()));
            cardParams.put("exp_year", Integer.parseInt(year.getText()));
            cardParams.put("cvc",  Integer.parseInt(cvc.getText()));

            Map<String, Object> tokenParams = new HashMap<String, Object>();
            tokenParams.put("card", cardParams);
        
                    Token token = Token.create(tokenParams);

                    
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", Integer.parseInt(prix.getText()));
            
            chargeParams.put("currency", "eur");
            chargeParams.put("source", "tok_visa");
            
            
            
            // ^ obtained with Stripe.js

            Map<String, String> initialMetadata = new HashMap<>();
            initialMetadata.put("order_id", "6735");
            chargeParams.put("metadata", initialMetadata);

            Charge.create(chargeParams);
                    System.out.print("transaction reussie");

        showAlert(Alert.AlertType.CONFIRMATION, "Données Valide", "Success", "Payment avec succes!");
        
       
        
                
            } catch (StripeException ex) {
            }
                     
            
        
        });
        
        
       Annuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                     Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();

            }

        });
        
    }
    
     private boolean controleDeSaisi() {  

        if (carte.getText().isEmpty() || month.getText().isEmpty() || year.getText().isEmpty()
                || cvc.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Veuillez bien remplir tous les champs !");
            return false;
        } else {

            if (!Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]", carte.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "Vérifiez la reference ! ");
                carte.requestFocus();
                carte.selectEnd();
                return false;
            }

           if (!Pattern.matches("[0-9]*", month.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le Mois ! ");
                month.requestFocus();
                month.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", year.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez l'année ! ");
                year.requestFocus();
                year.selectEnd();
                return false;
            }if (!Pattern.matches("[0-9]*", cvc.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le cvc ! ");
                cvc.requestFocus();
                cvc.selectEnd();
                return false;
            }
           
        }
        return true;
         
    }
    
    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }
}