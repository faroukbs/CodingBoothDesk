/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Entities.Reservation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import Entities.Ticket;
import Services.ServiceResrvation;
import Services.ServiceTicket;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class CrudNEWticketController implements Initializable {

    @FXML
    private ComboBox<Integer> eventbox;
    @FXML
    private ComboBox<String> typeticketbox;
    @FXML
    private JFXTextField descriptiontx;
    @FXML
    private JFXTextField prixtx;
        private int parsedId;
    @FXML
    private TableView<Ticket> tabletickets;
ServiceResrvation pc = new ServiceResrvation();
    @FXML
    private TableColumn<Ticket, Integer> eventv;
    @FXML
    private TableColumn<Ticket, String> descriptionv;
    @FXML
    private TableColumn<Ticket, String> typev;
    @FXML
    private TableColumn<Ticket, Integer> prixv;
      private TableColumn<Ticket,Button> reserverCol;
    @FXML
          private Button reserver;
              int iduser = 2 ;
    @FXML
    private Button Add;
    @FXML
    private Button Modticket;
    @FXML
    private Button Supprimerticket;
    ServiceTicket st = new ServiceTicket();
    @FXML
    private TableColumn<Ticket, Integer> idticketv;
    @FXML
    private Pagination pagination;
    @FXML
    private TextField Recherche;
    @FXML
    private JFXTextField nbrticketx;
    @FXML
    private TableColumn<Ticket,Integer> nbrticketv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Vip", "moyenne", "demi");
        typeticketbox.setItems(list);
        eventbox.setItems(st.affecterEvent());
        eventbox.getSelectionModel().selectFirst();
        
        ShowTicket();
    }

    @FXML
    private void liste_ticket(MouseEvent event) {
        try {
            Ticket s = tabletickets.getSelectionModel().getSelectedItem();

            descriptiontx.setText(s.getDescription());
            prixtx.setText((s.getPrix() + ""));
   nbrticketx.setText((s.getNbrticket() + ""));
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void ShowTicket() {
        ServiceTicket st = new ServiceTicket();
        List<Ticket> tickets = st.recuperer();
        ObservableList<Ticket> List = FXCollections.observableArrayList(tickets);
        tabletickets.setItems(List);
        idticketv.setCellValueFactory(new PropertyValueFactory<>("idticket"));
        eventv.setCellValueFactory(new PropertyValueFactory<>("idevent"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));
        typev.setCellValueFactory(new PropertyValueFactory<>("typeticket"));
           prixv.setCellValueFactory(new PropertyValueFactory<>("prix"));
           nbrticketv.setCellValueFactory(new PropertyValueFactory<>("nbrticket"));
          
    }

    @FXML
    private void Add(ActionEvent event) {

        if (descriptiontx.getText().isEmpty() || prixtx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else if (Integer.parseInt(prixtx.getText()) < 1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" nombre de participants doit etre superieur à 1");
            alert.show();
        } else {
            int eventboxx = eventbox.getSelectionModel().getSelectedItem();
            String typeticketboxx = typeticketbox.getSelectionModel().getSelectedItem();
            String descriptionticket = descriptiontx.getText();
            int prixticket = Integer.parseInt(prixtx.getText());
               int nbrticket = Integer.parseInt(nbrticketx.getText());
            ServiceTicket st = new ServiceTicket();
            Ticket t = new Ticket(eventboxx, typeticketboxx, prixticket, descriptionticket,nbrticket);
            st.ajouter(t);
            ShowTicket();
            raifraichir();
        }
    }

    @FXML
    private void Modticket(ActionEvent event) {
        if (descriptiontx.getText().isEmpty() || prixtx.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" Champ vide!");
            alert.show();
        } else if (Integer.parseInt(prixtx.getText()) < 1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText(" le prix ne peut pas étre negative ");
            alert.show();
        } else {
            Ticket c = new Ticket();
            ServiceTicket cs = new ServiceTicket();
            c = tabletickets.getSelectionModel().getSelectedItem();
            c.setIdticket(tabletickets.getSelectionModel().getSelectedItem().getIdticket());
            c.setIdevent(eventbox.getValue());
            c.setTypeticket(typeticketbox.getValue());
            c.setPrix(Integer.parseInt(prixtx.getText()));
            c.setDescription(descriptiontx.getText());
             c.setNbrticket(Integer.parseInt(nbrticketx.getText()));
            cs.modifier(c);
            ShowTicket();
            raifraichir();
        }
    }

    @FXML
    private void Supprimerticket(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure !");
        //alert.show();
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == (ButtonType.OK)) {
        ServiceTicket ss = new ServiceTicket();
        ss.supprimer(tabletickets.getSelectionModel().getSelectedItem().getIdticket());
        System.out.println(tabletickets.getSelectionModel().getSelectedItem().getIdticket());
        ShowTicket(); //// raifrach table view ///
        tabletickets.getItems().removeAll(tabletickets.getSelectionModel().getSelectedItem());
    }}
           

    private void raifraichir() {

        descriptiontx.setText("");
        prixtx.setText("");
    }

    @FXML
    private void Recherche(KeyEvent event) {
             ServiceTicket bs = new ServiceTicket();
        Ticket b = new Ticket();
        ObservableList<Ticket> filter = bs.chercherTitreTicket(Recherche.getText());
        populateTable(filter);
    }

    private void populateTable(ObservableList<Ticket> branlist) {
             tabletickets.setItems(branlist);
    }
private void addButtonToTable() {
        TableColumn<Ticket, Void> reserver = new TableColumn("Reservation");

        Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>> cellFactory = new Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>>() {
            @Override
            public TableCell<Ticket, Void> call(final TableColumn<Ticket, Void> param) {
                final TableCell<Ticket, Void> cell = new TableCell<Ticket, Void>() {

                    private final Button btn = new Button("Reserver");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            
                            Ticket data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            Reservation p = new Reservation(iduser ,data.getIdticket());
                            pc.ajouter(p);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        reserver.setCellFactory(cellFactory);

       tabletickets.getColumns().add(reserver);

    }

    @FXML
    private void reserver(ActionEvent event) {
               
                            Ticket data = new Ticket();
                               data = tabletickets.getSelectionModel().getSelectedItem();
            data.setIdticket(tabletickets.getSelectionModel().getSelectedItem().getIdticket());
         
                            
                            Reservation p = new Reservation(data.getIdticket(),1);
                            reserver.setDisable(true);
                            pc.ajouter(p);
                
    }
       

    }

  


