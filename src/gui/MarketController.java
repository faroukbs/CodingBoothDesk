package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import gui.MyListener;
import entities.Product;
import entities.RateProduct;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import org.controlsfx.control.Rating;
import service.ProductService;
import service.RateService;

public class MarketController implements Initializable {

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

   

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    ProductService service = new ProductService();
    RateService rates = new RateService();

    private Image image;
    private MyListener myListener;
    private List<Product> products = new ArrayList<>();
    @FXML
    private TextField search;

    private List<Product> getData() {
        return service.GetAll();

    }

    private void setChosenProduct(Product product) {
        fruitNameLable.setText(product.getNomprod());
        fruitPriceLabel.setText(String.valueOf(product.getPrix()));
       String path = "/img/" + product.getImage();
        Image image = new Image(getClass().getResourceAsStream(path));
       fruitImg.setImage(image);
//        chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
//                "    -fx-background-radius: 30;");
        System.out.println(product);
      

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        products.addAll(getData());
        if (products.size() > 0) {
            setChosenProduct(products.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {
                    setChosenProduct(product);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(products.get(i), myListener);

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
//     private void rechercher() {
//
//        ProductService cs = new ProductService();
//        List<Product> products = cs.GetAll();
//        ObservableList<Product> dataList = FXCollections.observableArrayList(products);
//        // Wrap the ObservableList in a FilteredList (initially display all data).
//        FilteredList<Product> filteredData = new FilteredList<>(dataList, b -> true);
//
//        // 2. Set the filter Predicate whenever the filter changes.
//        search.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(Product -> {
//                // If filter text is empty, display all persons.
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                // Compare first name and last name of every person with filter text.
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (Product.getNomprod().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches nom .
//                } else if (String.valueOf(Product.getId_produit()).indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches id .
//                } else if (String.valueOf(Product.getCategoryprod_id()).indexOf(lowerCaseFilter) != -1) {
//                    return true; // Filter matches id .
//                } else {
//                    return false; // Does not match.
//                }
//            });
//        });
//
//        // 3. Wrap the FilteredList in a SortedList. 
//        SortedList<Product> sortedData = new SortedList<>(filteredData);
//
//        // 4. Bind the SortedList comparator to the TableView comparator.
//        // 	  Otherwise, sorting the TableView would have no effect.
//        sortedData.comparatorProperty().bind(tblProd.comparatorProperty());
//
//        // 5. Add sorted (and filtered) data to the table.
//        tblProd.setItems(sortedData);
//
//    }

    

}
