/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author bouss
 */
public class PidevDesk extends Application {

     @Override
    public void start(Stage primaryStage) {
        //*********************************      LYNDA   ******************************************************//
         //********************************  REPONSE ADMIN  *********************************************************//
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Repadmin.fxml")); //load view

            Parent root = loader.load();
            Scene scene = new Scene(root);

            primaryStage.setTitle("GoGym");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
           //********************************  Client Reclamation  *********************************************************//
//              try {
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ClientReclamationController.fxml")); //load view
//
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//
//            primaryStage.setTitle("GoGym");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
 //********************************  Gestion Reclamation *********************************************************//
    
//            try {
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GestionReclmation.fxml")); //load view
//
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//
//            primaryStage.setTitle("GoGym");
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         launch(args);
      
    }

}
