/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Entities.Eventl;
import Services.ServiceEventl;
import java.io.IOException;
import java.net.URL;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author wassim
 */
public class AllEventsListController implements Initializable {

    @FXML
    private HBox eventsLayout;

    
    private List<Eventl> allEvents;
    private ServiceEventl serviceEvent;
    @FXML
    private Label time;
 
   private volatile boolean stop=false;
    @FXML
    private VBox sideNav;
    @FXML
    private ImageView navHome;
    @FXML
    private Button navCart;
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
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timenow();
        serviceEvent = new ServiceEventl();
        allEvents = new ArrayList<Eventl>(serviceEvent.recuperer());

        try {
            for (int i = 0; i < allEvents.size(); i++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("EventCard.fxml"));
                
                AnchorPane eventBox = loader.load();
                
                EventCardController EventCardController = loader.getController();
                EventCardController.setData(allEvents.get(i));
                
                eventsLayout.getChildren().add(eventBox);
            }
        } catch (IOException ioe) {
            System.out.println("Error While Adding All Events cards : " + ioe.getMessage());
        }
    }    
     private void Timenow () {
Thread thread = new Thread (() ->{
SimpleDateFormat sdf = new SimpleDateFormat ("hh:mm:ss");
while (!stop) {
try{
Thread.sleep (1000);
} catch (Exception e) {
System.out.println (e);}
  final String timenow = sdf. format (new Date());
Platform. runLater (()->{
time.setText (timenow) ;
});
        }
});
thread.start();
}

    @FXML
    private void avis(ActionEvent event) {
                    try {
                   
            Parent parent = FXMLLoader.load(getClass().getResource("Rating.fxml"));
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
    private void showCartView(MouseEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
}
