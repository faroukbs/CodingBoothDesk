/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Evaluation;
import Entities.Eventl;
import Services.ServiceEvaluation;
import Services.ServiceEventl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import static sun.rmi.transport.TransportConstants.Version;

import com.restfb.Version;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;


/**
 * FXML Controller class
 *
 * @author wassim
 */
public class EventCardController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label id;
    @FXML
    private ImageView image;
    @FXML
    private Label description;
    @FXML
    private Label date_fin;
    @FXML
    private Label date_debut;
    @FXML
    private Label nb_applyed;
    @FXML
    private Label nombre_participant;
    Eventl e= new Eventl();
   double p = 0;
      private Eventl poste;
     private MyListener1 myListener;
    @FXML
    private Rating rating;
    private String[] colors = {
        "#2596be",
        "#154c79",
        "#063970",
        "#abdbe3",
        "#76b5c5",
        "#le8lb0",
        "#eeeee4"
    };

    private ServiceEventl serviceEvent;
    @FXML
    private VBox box;
    @FXML
    private Button applyButton;
    
    private int parsedId;
    @FXML
    private AnchorPane eventCardContainer;
    @FXML
    private Label villetxt;
private Evaluation evaluation;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.e = e;
        this.myListener = myListener;
        serviceEvent = new ServiceEventl();
    }

    @FXML
    private void apply(MouseEvent event) {
                 try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("TicketFront.fxml"));
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

    private void delete(MouseEvent event) {
        parsedId = Integer.parseInt(id.getText());
        
        try {
            serviceEvent.supprimer(parsedId);
            eventCardContainer.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
       
    }

    private void update(MouseEvent event) {
        parsedId = Integer.parseInt(id.getText());
        Eventl ev = serviceEvent.getEventById(parsedId);
        
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Updatevent.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage current = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(new Scene(root1));  
        secondaryStage.setTitle("Update Event") ; 
       // UpdateventController controller = fxmlLoader.getController();
       // controller.initData(ev);
        current.close();
        secondaryStage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
        

    }

    public void setData(Eventl event) throws FileNotFoundException {

        this.id.setText("" + event.getIdevent());

        this.nom.setText(event.getTitre());

        this.description.setText(event.getDescription());
    this.villetxt.setText(event.getVille());
        this.date_fin.setText(event.getDatedebut().toString());

        this.date_debut.setText(event.getDatefin().toString());
        

//        String nbApplyed = "" + serviceEvent.getNombre_participants_byevent(event.getId());
//        this.nb_applyed.setText(nbApplyed);
//
//        this.nombre_participant.setText("" + event.getNombre_participants());

        Image i = new Image(new FileInputStream(""+ event.getPhoto()));
        
        
        image.setImage(i);
       // box.setStyle("-fx-background-color: " + colors[(int) Math.random() * 7]);
        
        setVisibility();
    }
    
    private void setVisibility(){
//        int parsedId;
//        try {
//            parsedId = Integer.parseInt(id.getText());
//
//            if (serviceEvent.user_is_applyed_toevent(1, parsedId)) {
//                applyButton.setVisible(false);
//            } else {
//                applyButton.setVisible(true);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    private void ratethis(MouseEvent event) {
           parsedId = Integer.parseInt(id.getText());
       
         ServiceEvaluation r = new ServiceEvaluation();
              Evaluation rate = new Evaluation();
              
        r.ajouter(new Evaluation( rating.getRating(),evaluation.getC()));
        Alert a = new Alert(Alert.AlertType.INFORMATION, "rating succed");
    
        

    }
    
    private void onclick(MouseEvent event) {
                 myListener.onClickListener(e);

    }
   
}
