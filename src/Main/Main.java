package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entities.Utilisateur;
import Services.ServiceAdmin;
import Services.ServiceUtilisateur;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author GhAlone
 */
public class Main {


    
 /*   @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say Hello World");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
*/
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        ServiceUtilisateur sp2 = new ServiceUtilisateur();
        //System.out.println(sp2.connecter(new Utilisateur("ghassen","ghg","User")));
        //sp2.modifier(new Utilisateur(1,"ghassen","dhif",545646,"ljdkaz","wom","ljk@hj","hgklj","azdhjkahzdlkjazm"));
        //sp2.inscrir(new Utilisateur("gahssen","hassen",456,"jkhfghj","azfhgjgd","dqjksh@qsdb","djqhs","kjdaz"));
        //sp2.changer_pass(new Utilisateur(2,"ghassen"));
       // System.out.println(sp2.afficher());
        ServiceAdmin sa2= new ServiceAdmin();
        //System.out.println(sa2.connecter(new Utilisateur("ghassen","ghg","Admin")));
        //sa2.modifier(new Utilisateur(1,"lmcwxklmk","wcvx",545646,"ljdkaz","wom","ljk@hj","bgbb","azdhjkahzdlkjazm"));
        //System.out.println(sa2.afficher());
     //   sa2.delete(new Utilisateur(11));
    }
}