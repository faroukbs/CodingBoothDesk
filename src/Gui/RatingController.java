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
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import org.controlsfx.control.Rating;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class RatingController implements Initializable {

    @FXML
    private GridPane grid;
    double p = 0;
    @FXML
    private AnchorPane root;
      
 
    /**
     * Initializes the controller class.
     */
    ServiceEventl cs = new ServiceEventl ();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ServiceEvaluation rs = new ServiceEvaluation();

        List<Eventl> lst = new ArrayList<Eventl>();
      ServiceEventl cb = new ServiceEventl();
      lst = cb.recuperer();
        int i = 0;
        int j = 0;
        for (Eventl c : lst) {
            final Rating rating = new Rating();
            try {
                
                    rating.setRating(rs.recuperernbrRating(c.getIdevent()));
                
          
            } catch (SQLException ex) {
                Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
            }

            rating.setUpdateOnHover(false);
            rating.setPartialRating(false);
            rating.setMax(5);

            rating.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {

                    System.out.println(rating.getRating());
                    p = rating.getRating();

                }
            });
            System.out.println(p);
            VBox vbox = new VBox();
            System.out.println(c.getIdevent());
            Label name = new Label(c.getTitre());
            Label dmn = new Label(c.getDescription());
           
dmn.setStyle("-fx-padding:5px; -fx-font-size: 20px;\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-text-fill: #ED0000;\n"
                + "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
    
      
 name.setStyle("-fx-padding:5px; -fx-font-size: 20px;\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-text-fill: #333333;\n"
                + "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
    
            Button btn = new Button("voter");
        btn.setStyle(" -fx-text-fill: black;\n"
                + "    -fx-font-family: \"Arial\";\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-background-color: linear-gradient(#61a2b1, #ED0000);\n"
                +"-fx-background-radius:50;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
            btn.setOnAction((event) -> {
               

                Evaluation rate = new Evaluation();
                rate.setC(c);
                rate.setNote(p);
             
                ServiceEvaluation rtd = new   ServiceEvaluation();
                rtd.ajouter(rate);

            });
            vbox.getChildren().addAll(name, dmn, rating, btn);
            grid.add(vbox, i, j);
            i++;
            if (i == 3 ) {
         
                i = 0;
                j++;

            }

        }

    }

}
