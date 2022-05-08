/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Home
 */
public class Classe extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Parent root;
        try {
        root = FXMLLoader.load(getClass().getResource("AllEventsList.fxml"));
           
                //  root = FXMLLoader.load(getClass().getResource("AllEventsList.fxml"));
                  // root = FXMLLoader.load(getClass().getResource("CrudNewticket.fxml"));
                        Scene scene = new Scene(root, 1310, 845);
        
        primaryStage.setTitle("GO GYM!");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
   System.err.println(ex.getMessage());
        }
        
                
        
  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
