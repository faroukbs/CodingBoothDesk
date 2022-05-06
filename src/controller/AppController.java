/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import GUI.Classe;
import entities.Cart;
import entities.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
public class AppController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private BorderPane body;
    @FXML
    private VBox sideArea;
    @FXML
    private VBox sideNav;
    @FXML
    private Region navHome;
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
    private Label totalPriceLabel;
    

    
    ProductService ev = new ProductService();
   
    private Product o;
    @FXML
    private GridPane productGridPane;
    @FXML
    private GridPane productGridPane1;
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
    @FXML
    private Button commander;


    /**
     * Initializes the controller class.
     */
   // @Override
//    public void initialize(URL url, ResourceBundle rb) {
//         List<Product> liste = Cart.getInstance().getCartList();
//
//        if ( liste.isEmpty())
//        {
//        Label label3 = new Label("Your cart is empty");
//        label3.setPadding(new Insets(5,5,5,5));
//        productGridPane.add(label3,1,0);
//        }
//        else 
//        {    
//        
//        HBox item = new HBox();
//            box.getChildren().add(item);
//           liste=ev.AfficherProduit(o);
//        int taille=liste.size();
//          for(Product p : liste)
//            {
//               
//               
//                double total = 0 ; 
//                total = total + (p.getPrice()*p.getQuantity());
//                VBox content = new VBox();
//                File file=new File( p.getImg());
//                Image image = new Image(file.toURI().toString());
//                 Image image = new Image( file);
//                ImageView ImageView = new ImageView(image);
//                Label title = new Label();
//                o=liste.get(i);
//                Label nom = new Label((p.getProduct_name()));
//                nom.setStyle("-fx-strikethrough: true");
//                nom.getStyleClass().add("barre");
//               nom.setStyle("-fx-font-weight: bold");
//                prix.getStyleClass().add("barre");
//                ImageView.setFitHeight(100);
//                        ImageView.setFitWidth(100);
//                content.getChildren().addAll(ImageView,nom);

//                Button remove= new Button("Remove from cart");
//                content.getChildren().add(remove);
//                

//                Button additionquantity = new Button("+");
//                content.getChildren().add(additionquantity);
//
//                
//                Button substractquantity = new Button("-");               
//                content.getChildren().add(substractquantity);

//                Label quantityValue = new Label(Integer.toString(p.getQuantity()));

//                Label priceproductvalue = new Label(Double.toString(p.getPrice()*p.getQuantity()));

//                content.getChildren().addAll(quantityValue,priceproductvalue);

//                Label totalelabel = new Label();
//                totalelabel.setText(Double.toString(total));
//                content.getChildren().add(totalelabel);
//
//
//
//
//               
//                
//                Button btn = new Button("ADD",content);
//                btn.setOnAction(event -> {
//                   
//               });
//                
//              
//                remove.setOnAction(event -> {
//                    Cart.instance.RemoveProduct(p);
//                    Parent root;
//                    try {
//                        root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/cart-ui.fxml"));
//                        navCart.getScene().setRoot(root);
//                    } catch (IOException ex) {
//                        Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                   
//                    
//                });
//                additionquantity.setOnAction(event -> {
//                    
//                    if(p.getQuantity()<=p.getStock()-1){
//                        substractquantity.setEnabled(true);
//                        double test = Double.parseDouble(totalelabel.getText());
//                    
//                    int quantity = p.getQuantity()+1;
//                    p.setQuantity(quantity);
//                    quantityValue.setText(Integer.toString(p.getQuantity()));
//                    priceproductvalue.setText(Double.toString(p.getQuantity()*p.getPrice()));
//                    totalelabel.setText(Double.toString(test+p.getPrice()));
//                    }
//                    else{
//                        additionquantity.setEnabled(false);
//                    }
//                    
//                });
//                substractquantity.setOnAction(event -> {
//                    if(p.getQuantity()>=2){
//                        additionquantity.setEnabled(true);
//                        double test = Double.parseDouble(totalelabel.getText());
//                    
//                    int quantity = p.getQuantity()-1;
//                    p.setQuantity(quantity);
//                    quantityValue.setText(Integer.toString(p.getQuantity()));
//                    priceproductvalue.setText(Double.toString(p.getQuantity()*p.getPrice()));
//                    totalelabel.setText(Double.toString(test-p.getPrice()));
//                    }
//                    else{
//                        substractquantity.setEnabled(false);
//                    }
//                });
//               
//                
//
//                
//                btn.setPrefWidth(10);
//                item.getChildren().add(btn);
//                box.setSpacing(80);
//                item.setSpacing(80);
//    
//    }
//    }    
//    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        List<Product> liste = Cart.getInstance().getCartList();
        box.getChildren().clear();
          
        if ( liste.isEmpty())
        {
        Label label3 = new Label("Your cart is empty");
       // label3.setPadding(new Insets(5,5,5,5));
        box.getChildren().add(label3);
        }
        else 
        {   
             HBox item = new HBox();
           box.getChildren().add(item);
          Label ShoppingCartTitle = new Label("Shopping cart");
          item.getChildren().add(ShoppingCartTitle);
          
          for(Product p : liste)
            {
                 try {
                     HBox content = new HBox();

                    HBox image2 = cartyEntryView(p);
                    image2.setPrefWidth(80);

                    Label productName = new Label(p.getNomprod());
                    productName.setPrefWidth(300);
                    productName.setStyle("-fx-font-size:15pt; -fx-padding:5px");

                    Label quantityValue = new Label(Integer.toString(p.getQuantity()));
                    quantityValue.setStyle("-fx-padding:5px");

                    Button additionquantity = new Button("+");
                    additionquantity.setStyle("-fx-padding:8px");
                    //  plusButton.setUserData(p.getProduct_name());

                    Button substractquantity = new Button("-");
                    substractquantity.setStyle("-fx-padding:8px");

                    Button remove= new Button("Remove");
                    


                    Label price = new Label(Double.toString(p.getPrix()*p.getQuantity()));
                    price.setPrefWidth(100);
                    price.setStyle("-fx-padding:8px");

                    content.getChildren().addAll(image2,productName,additionquantity,quantityValue,substractquantity,price,remove);
                    box.getChildren().add(content);
                    additionquantity.setOnAction(event -> {
                     
                        if(p.getQuantity()<=p.getQuantity()-1){
                            //   substractquantity.setE(true);
                            double test = Double.parseDouble(totalPriceLabel.getText());

                            int quantity = p.getQuantity()+1;
                            p.setQuantity(quantity);
                            quantityValue.setText(Integer.toString(p.getQuantity()));
                            price.setText(Double.toString(p.getQuantity()*p.getPrix()));
                            totalPriceLabel.setText(Double.toString(test+p.getPrix()));
                        }
                        else{
                            // additionquantity.setEnabled(false);
                        }

                    });

                    remove.setOnAction(event -> {
                        Cart.instance.RemoveProduct(p);
                        Parent root;
                        try {
                            root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/cart-ui.fxml"));
                            navCart.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                    substractquantity.setOnAction(event -> {
                        if(p.getQuantity()>=2){
                            //  additionquantity.setEnabled(true);
                            double test = Double.parseDouble(totalPriceLabel.getText());

                            int quantity = p.getQuantity()-1;
                            p.setQuantity(quantity);
                            quantityValue.setText(Integer.toString(p.getQuantity()));
                            price.setText(Double.toString(p.getQuantity()*p.getPrix()));
                            totalPriceLabel.setText(Double.toString(test-p.getPrix()));
                        }
                        else{
                            //   substractquantity.setEnabled(false);
                        }
                    });
                                     } catch (FileNotFoundException ex) {
                                         Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                                     }











                                }

                              Separator separator = new Separator();
                              separator.setOrientation(Orientation.HORIZONTAL);
                              box.getChildren().add(separator);


                            HBox totalView = totalView(Cart.getInstance().total());
                            box.getChildren().add(totalView);

                              box.setSpacing(80);
                              item.setSpacing(80);
       
       }
    }
    
      
    private HBox totalView(Double totalPrice) 
     {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        
       Label totalLabel = new Label(" Total : ");
       totalLabel.setStyle("-fx-font-size:15;");
       
       this.totalPriceLabel = new Label(String.valueOf(totalPrice));
       layout.getChildren().addAll(totalLabel,this.totalPriceLabel);
        return layout;
        
    }
    
    
    private HBox cartyEntryView(Product o) throws FileNotFoundException 
     {
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER_LEFT);
       FileInputStream input = new FileInputStream("C:/Users/aicha/Documents/NetBeansProjects/PiJava/src/ressource/"+o.getImage());
                                        
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
  
        layout.getChildren().addAll(imageView);  

        return layout;
        
    }
     


    private void showHomeView(MouseEvent event) throws IOException {
       // contentPane.setCenter(new HomeView().getView());
          Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
         navHome.getScene().setRoot(root);

    }

    @FXML
    private void showCartView(MouseEvent event) throws IOException {
    Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("../GUI/cart-ui.fxml"));
    navCart.getScene().setRoot(root);    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void Commande(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/PasseCommande.fxml"));
            Parent root = loader.load();
            commander.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
