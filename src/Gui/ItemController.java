package Gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import gui.MyListener;
import entities.Product;
import entities.RateProduct;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.controlsfx.control.Rating;
import services.RateService;

public class ItemController implements Initializable {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private Rating rate;
    
    RateService Rs = new RateService();
    @FXML
    private Label qty;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(product);
    }

    private Product product;
    private MyListener myListener;

    public void setData(Product product, MyListener myListener) {
        this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getNomprod());
        priceLable.setText(String.valueOf( product.getPrix()));
        String path = "/img/" + product.getImage();
        Image image = new Image(getClass().getResourceAsStream(path));
        img.setImage(image);
        rate.setRating(Rs.GetByProduct(product.getId_produit()).getNote());
        qty.setText(String.valueOf(product.getQuantity()));
    }

    @FXML
    private void addRate(MouseEvent event) {
        RateProduct r = new RateProduct(product.getId_produit(), rate.getRating());
           System.out.println("hgg");
        System.out.println(Rs.GetByProduct(product.getId_produit()));
        if (Rs.GetByProduct(product.getId_produit()) != null){
            
        Rs.Add(r);
        } else {
        Rs.Update(r);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rate.setPartialRating(true);
    }
}