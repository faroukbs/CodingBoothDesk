/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Cart;
import entities.Product;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import service.ProductService;

/**
 * FXML Controller class
 *
 * @author doghm
 */
public class homeController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private BorderPane body;
    @FXML
    private VBox sideArea;
    @FXML
    private VBox sideNav;
    @FXML
    private ImageView navHome;
    @FXML
    private Region navCart;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private BorderPane contentPane;
    @FXML
    private VBox componentBox;
    @FXML
    private Pane handPaneMac;
    @FXML
    private VBox box;
    
    private ImageView ima;
    private Label nom;
    private Label description;
    private ImageView ImageView;
    

    
    ProductService ev = new ProductService();
    List<Product> liste = new ArrayList<>();
    private Product o;
 Button[] btn = new Button[100];
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
         HBox item = new HBox();
            box.getChildren().add(item);
           liste=ev.AfficherProduit(o);
        int taille=liste.size();
       
          for( int i=0;i<taille;i++)
            {
               
                if(i % 3 == 0){
                    item = new HBox();
                    box.getChildren().add(item);
                }
                VBox content = new VBox();
                
                File file=new File( liste.get(i).getImage());
                Image image = new Image(file.toURI().toString());
                // Image image = new Image( file);
                ImageView ImageView = new ImageView(image);
                Label title = new Label();
                o=liste.get(i);
                Label nom = new Label((liste.get(i).getNomprod()));
                nom.setStyle("-fx-strikethrough: true");
               // nom.getStyleClass().add("barre");
               nom.setStyle("-fx-font-weight: bold");
                Label description = new Label((liste.get(i).getDescription()));
               description.setStyle("-fx-strikethrough: true");
                //prix.getStyleClass().add("barre");
                ImageView.setFitHeight(100);
                        ImageView.setFitWidth(100);
                content.getChildren().addAll(ImageView,nom,description);
                btn[i] = new Button("tesstt",content);
              
                btn[i].setPrefWidth(150);
                item.getChildren().add(btn[i]);
                box.setSpacing(80);
                item.setSpacing(80);
                btn[i].setOnAction(this::handleButtonAction);
    }
        
    }


    private void showHomeView(MouseEvent event) throws IOException {
         Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
         navHome.getScene().setRoot(root);
    }

    @FXML
    private void showCartView(MouseEvent event) throws IOException {
          Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/cart-ui.fxml"));
         navCart.getScene().setRoot(root);
    }
    
     private void handleButtonAction (ActionEvent event)
    {int taille=liste.size();
         for (int i = 0; i <taille; i++) {
            // Button a = supprimerb[i];
            
             if (event.getSource() == btn[i])
             {
                 System.out.println(liste.get(i).getId_produit());
                    int test = 0;
                for(Product pi : Cart.getInstance().getC()){
                    if(pi.getNomprod().equals(liste.get(i).getNomprod())){
                        test = 1;
                        int quantity = pi.getQuantity() + 1 ;
                        pi.setQuantity(quantity);
                        
                    }
                    
                }
                
                if(test == 0){
                    Product pp = new Product(liste.get(i).getId_produit() ,liste.get(i).getNomprod(), liste.get(i).getDescription(),liste.get(i).getImage(),liste.get(i).getPrix(),liste.get(i).getQuantity());
                   System.out.println(pp);
                    Cart.instance.AddProduct(pp);
                    
                }
             //   Dialog.show("Success", "Item added to cart !", new Command("OK"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Item Added");
            alert.showAndWait();                    
             }
            
                 
         } 
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }
}
