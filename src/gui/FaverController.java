/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Product;
import entities.wishlist;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import service.ProductService;

/**
 * FXML Controller class
 *
 * @author bouss
 */
public class FaverController implements Initializable {

    ProductService ps = new ProductService();
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    
    private List<wishlist> products = new ArrayList<>();
    private List<Product> prod = new ArrayList<>();
    private MyListener myListener;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();

    }

    @FXML
    private void goshop(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("market.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void show() {
        
products.addAll(ps.GetAllf(1));
for (int i = 0; i < products.size(); i++) {
            prod.add(ps.GetById(products.get(i).getIdproduit()));
        }
if (prod.size() > 0) {
               // setChosenProduct(products.get(0));
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Product product) {
                     //   setChosenProduct(product);
                    }
                };
            }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < prod.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(prod.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
