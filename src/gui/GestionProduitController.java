package gui;

import entities.Product;
import static java.lang.String.valueOf;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import service.CategoryProdService;
import service.ProductService;
import utils.Smsapi;

/**
 *
 * @author bouss
 */
public class GestionProduitController implements Initializable {

    ProductService Sp = new ProductService();
    @FXML
    private TextField search;
    @FXML
    private Button retour;
    @FXML
    private TableView<Product> tblProd;
    @FXML
    private TableColumn<Product, String> tbNom;
    @FXML
    private TableColumn<Product, Float> tbPrix;
    @FXML
    private TableColumn<Product, Integer> tbQuant;
    @FXML
    private TableColumn<Product, String> tbCat;

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfQuant;
    private TextField tfimg;
    @FXML
    private Button AddP;
    @FXML
    private Button DelP;
    @FXML
    private Button EdP;

    @FXML
    private ComboBox<String> combCat;

    ObservableList<Product> list;

    CategoryProdService servCat;

    File file;

    private int selectedProdID;
    @FXML
    private TextField tfdescription;
    @FXML
    private TableColumn<Product, String> tbDes;
    ObservableList<Product> List = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showproduit();
        rechercher();

        tblProd.setRowFactory(tv -> {

            TableRow<Product> row = new TableRow<>();

            row.setOnMouseClicked(event -> {

                if (!row.isEmpty()) {
                    final Product selectedItem = tblProd.getSelectionModel().getSelectedItem();

                    tfNom.setText(selectedItem.getNomprod());
                    tfdescription.setText(selectedItem.getDescription());
                    tfPrix.setText(valueOf(selectedItem.getPrix()));
                    tfQuant.setText(valueOf(selectedItem.getQuantity()));
//                    tfimg.setText(selectedItem.getImage());
                    //combCat.setValue(selectedItem.getCategory());

                    selectedProdID = selectedItem.getId_produit();
                }
            });

            return row;
        });

        servCat = new CategoryProdService();

        ObservableList<String> catNames = FXCollections
                .observableArrayList(
                        servCat.GetAll().stream().map(c -> c.getNom()).collect(Collectors.toList())
                );

        System.out.println(catNames);

        combCat.setItems(catNames);
    }

    @FXML
    private void returnb(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AddP(ActionEvent event) throws FileNotFoundException, IOException {
        DataValidation validator = new DataValidation();

        if (tfNom.getText().isEmpty() || tfdescription.getText().isEmpty() || tfPrix.getText().isEmpty() || tfQuant.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else {
            if (validator.textNumeric(tfPrix) && validator.textNumeric(tfQuant)) {

                int idCategory = servCat.getIdByCategoryName(combCat.getValue());

                FileInputStream fl = new FileInputStream(file);

                byte[] data = new byte[(int) file.length()];
                String fileName = file.getName();
                String path = fileName;
                fl.read(data);
                fl.close();
                Product p = new Product(tfNom.getText(), tfdescription.getText(), path, Float.parseFloat(tfPrix.getText()), Integer.parseInt(tfQuant.getText()), idCategory);
                Sp.Add(p);
                Smsapi.sendSMS("Nous avons ajouté un produit"+p.getNomprod().toString()+"\n" +p.getDescription().toString()+" \n avec un prix de "+p.getPrix()+
                        " \n disponible en une quantité de "+p.getQuantity() );
                tblProd.setItems(FXCollections.observableArrayList(Sp.GetAll()));
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Product added");
                alert.setContentText("Product added succesfuuly!");
                tblProd.refresh();
            }
        }
    }

//    @FXML
//    private void AddP(ActionEvent event) {
//        DataValidation validator = new DataValidation();
//        int idCategory = servCat.getIdByCategoryName(combCat.getValue());
//        
//        if (tfPrix.getText().isEmpty()) {
//            error.setText("Verifier les entrées s'il vous plait");
//        } else {// FIXME: change the id user from 1 to the current logged in user.
//            Product l = new Product(tfNom.getText(), tfdescription.getText(), tfimg.getText(), Float.parseFloat(tfPrix.getText()), Integer.parseInt(tfQuant.getText()), idCategory);
//            Sp.Add(l);
//            tblProd.setItems(FXCollections.observableArrayList(Sp.GetAll()));
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Product added");
//            alert.setContentText("Product added succesfuuly!");
//            tblProd.refresh();
//        }
//
//    }
    @FXML
    private void DelP(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
            final Product selectedItem = tblProd.getSelectionModel().getSelectedItem();
            Product prod = Sp.GetById(selectedItem.getId_produit());
            Sp.Delete(prod.getId_produit());

            list.remove(selectedItem);
            tblProd.setItems(FXCollections.observableArrayList(Sp.GetAll()));
            tblProd.refresh();
        }
    }

    @FXML
    private void EdP(ActionEvent event) throws IOException {
        if (tfNom.getText().isEmpty() || tfdescription.getText().isEmpty() || tfPrix.getText().isEmpty() || tfQuant.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else {

            final int idCategory = servCat.getIdByCategoryName(combCat.getValue());

            // FIXME: change the id user from 1 to the current logged in user.
            final Product selectedItem = tblProd.getSelectionModel().getSelectedItem();
            Product prod = Sp.GetById(selectedItem.getId_produit());
            System.out.println(selectedItem.getId_produit());
            prod.setNomprod(tfNom.getText());
            prod.setPrix(Float.parseFloat(tfPrix.getText()));
            prod.setQuantity(Integer.parseInt(tfQuant.getText()));
            prod.setDescription(tfdescription.getText());
//        prod.setImage(tfimg.getText());
            prod.setCategoryprod_id(idCategory);

            FileInputStream fl = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            String path = fileName;
            fl.read(data);
            fl.close();
            prod.setImage(path);

            Sp.Update(prod);
            tblProd.setItems(FXCollections.observableArrayList(Sp.GetAll()));
            tblProd.refresh();

        }
    }

    @FXML
    private File chooseImage(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\Users\\bouss\\Desktop\\CodingBoothDesk-farouk\\src\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    java.nio.file.Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        System.out.println(file.getPath());
        return file;
    }

    @FXML
    void GotoCat(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("GestCat.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("fffffff");
    }

    private void showproduit() {
        ProductService ss = new ProductService();
        List<Product> Products = ss.GetAll();

        list = FXCollections.observableArrayList(ss.GetAll());
        ObservableList<Product> List = FXCollections.observableArrayList(Products);
        tblProd.setItems(List);
        tbNom.setCellValueFactory(new PropertyValueFactory<>("nomprod"));
        tbPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tbQuant.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        // tbCat.setCellValueFactory(new PropertyValueFactory<>("categoryprod_id"));
        Callback<TableColumn<Product, String>, TableCell<Product, String>> cellFactoryU
                = (TableColumn<Product, String> param) -> {
                    TableCell<Product, String> cell = new TableCell<Product, String>() {

                Label btn = new Label();
                String pe = "";

                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {

                        pe = servCat.GetById(getTableView().getItems().get(getIndex()).getCategoryprod_id()).getNom();
                        btn.setText(pe);

                        setGraphic(btn);
                        setText(null);
                    }
                }

            };
                    return cell;
                };
        //editer update
        tbCat.setCellFactory(cellFactoryU);
        tbDes.setCellValueFactory(new PropertyValueFactory<>("description"));

        tblProd.setItems(list);

    }
//    @FXML
//    private void shoo() {
//        ProductService ss = new ProductService();
//        List<Product> Products = ss.GetAll();
//        ObservableList<Product> List = FXCollections.observableArrayList(Products);
//        tblProd.setItems(List);
//        tbNom.setCellValueFactory(new PropertyValueFactory<>("nomprod"));
//        tbPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        tbQuant.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        tbCat.setCellValueFactory(new PropertyValueFactory<>("categoryprod_id"));
//        tbDes.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//        //tblProd.setItems(list);
//    }

    private void rechercher() {

        ProductService cs = new ProductService();
        List<Product> products = cs.GetAll();
        ObservableList<Product> dataList = FXCollections.observableArrayList(products);
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Product> filteredData = new FilteredList<>(dataList, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Product -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Product.getNomprod().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches nom .
                } else if (String.valueOf(Product.getId_produit()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id .
                } else if (String.valueOf(Product.getCategoryprod_id()).indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches id .
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Product> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tblProd.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tblProd.setItems(sortedData);

    }

}
