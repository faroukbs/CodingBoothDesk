///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package GUI;
//
//import entities.Cart;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Label;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import service.CartService;
//
///**
// * FXML Controller class
// *
// * @author aicha
// */
//public class CartController implements Initializable {
//
//    @FXML
//    private VBox chosenFruitCard;
//    @FXML
//    private Label fruitNameLable;
//    @FXML
//    private Label fruitPriceLabel;
//    @FXML
//    private ImageView fruitImg;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        List<Cart> entries = CartService.getInstance().getEntries();
//        chosenFruitCard.getChildren().clear();
//        if (entries.isEmpty()){
//            Label emptyLabel = new Label("Empty Cart");
//            chosenFruitCard.getChildren().add(emptyLabel);
//            
//        }
//        else{
//            Label fruitName = new Label("Shopping cart");
//            chosenFruitCard.getChildren().add(fruitName);
//            for(Cart cart:entries){
//                HBox hBox = new HBox();
//                Label productName = new Label(Cart.getProduct().getNomprod());
//                hBox.getChildren().add(productName);
//                chosenFruitCard.getChildren().add(hBox);
//            }
//        }
//    }    
//    
//}
