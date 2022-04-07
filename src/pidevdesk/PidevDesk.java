/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevdesk;

import entities.Product;
import entities.category;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import service.CategoryProdService;
import service.ProductService;

/**
 *
 * @author bouss
 */
public class PidevDesk extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // launch(args);
        //product crud test
        //  ProductService ps = new ProductService();
//        Product p1 = new Product("pc", "msi", "iiiiiiii", 1234, 32, 1);
//        ps.Add(p1);
        // Product p2 = new Product(8,"pcerts", "msiet", "dfghjk", 1234, 32, 1);
        // ps.Delete(8);
        // System.out.println(ps.GetAll());
        // System.out.println(ps.GetById(3));

        //category crud test
        // CategoryProdService cs = new CategoryProdService();
        //category c = new category(2, "alimentaire");
        //cs.Add(c);
        //cs.Update(c);
        //  cs.Delete(2);
        //System.out.println(cs.GetAll());
        //   System.out.println(cs.GetById(1));
    }

}
