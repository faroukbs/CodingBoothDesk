/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Reservation;
import Entities.Ticket;
import Services.ServiceResrvation;
import Services.ServiceTicket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class TicketFrontController implements Initializable {

    @FXML
    private TableView<Ticket> ticketclient;
 @FXML
    private TableColumn<Ticket, Integer> idv;
   @FXML
    private TableColumn<Ticket, String> descriptionv;
    @FXML
    private TableColumn<Ticket, String> typev;
    @FXML
    private TableColumn<Ticket, Integer> prixv;
        @FXML
    private TableColumn<Ticket, Integer> nbrticketv;
      private TableColumn<Ticket, Button> participerCol;
 ServiceTicket ec = new  ServiceTicket();
    private Button reserver;
    int iduser = 2;

   ServiceResrvation pc = new  ServiceResrvation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
         initCols();
        loadEvenements();
    }    
    
    private void initCols() {
        idv = new TableColumn<>("ticket");//nom de l'afichage
       idv.setCellValueFactory(new PropertyValueFactory<>("idticket"));
     descriptionv = new TableColumn<>("description");//nom de l'afichage
       descriptionv.setCellValueFactory(new PropertyValueFactory<>("description"));

        typev = new TableColumn<>("typeticket");//nom de l'afichage
        typev.setCellValueFactory(new PropertyValueFactory<>("typeticket"));

        prixv = new TableColumn<>("prix");//nom de l'afichage
         prixv.setCellValueFactory(new PropertyValueFactory<>("prix"));

       nbrticketv = new TableColumn<>("Nombres de tickets disponible");//nom de l'afichage
       nbrticketv.setCellValueFactory(new PropertyValueFactory<>("nbrticket"));

     
        /*
       Callback<TableColumn<Evenement,String>,TableCell<Evenement,String>> cellFactory=(param)->{
final TableCell<Evenement,String> cell=new TableCell<Evenement,String>(){


}
}
         */

        ticketclient.getColumns().addAll(descriptionv, prixv,typev,nbrticketv,idv);
        addButtonToTable();
    }
      public void loadEvenements() {

        List<Ticket> evenements = ec.recuperer();
        ticketclient.getItems().clear();
        //afficher par objet dans une boucle   
        for (Ticket e : evenements) {
            ticketclient.getItems().add(e);
        }

    }

    private void addButtonToTable() {
        TableColumn<Ticket, Void> participer = new TableColumn("Reservation");

        Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>> cellFactory = new Callback<TableColumn<Ticket, Void>, TableCell<Ticket, Void>>() {
            @Override
            public TableCell<Ticket, Void> call(final TableColumn<Ticket, Void> param) {
                final TableCell<Ticket, Void> cell = new TableCell<Ticket, Void>() {

                    private final Button btn = new Button("reserver");

                    {
                        btn.setOnMouseEntered(a -> {
                               Ticket data = getTableView().getItems().get(getIndex());
                          
                           if (pc.verifReservation(iduser, data.getIdticket())) {
                                btn.setDisable(true);
                            }
                         
                        });
                        btn.setOnAction((ActionEvent event) -> {
                                      Ticket data = new Ticket();
                               data = ticketclient.getSelectionModel().getSelectedItem();
            data.setIdticket(ticketclient.getSelectionModel().getSelectedItem().getIdticket());
           System.out.println("selectedData: " + data);
                            Reservation p = new Reservation(data.getIdticket(),1);
                            
                            btn.setDisable(true);
                            pc.ajouter(p);
                

                        });
                         btn.setStyle(" -fx-text-fill: black;\n"
                + "    -fx-font-family: \"Arial\";\n"
                + "    -fx-font-weight: bold;\n"
                + "    -fx-background-color: linear-gradient(#61a2b1, #ED0000);\n"
                +"-fx-background-radius:50;"
                + "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );");
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

        participer.setCellFactory(cellFactory);

        ticketclient.getColumns().add(participer);

    }
}
