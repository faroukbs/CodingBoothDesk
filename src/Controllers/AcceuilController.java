/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author GhAlone
 */
public class AcceuilController {
   
    @FXML
    private BorderPane Bpadmin;
    @FXML
    private Label l;
    @FXML
    private Pane acpane;

    
  
    @FXML
    void changeUser(ActionEvent event) throws IOException {
        Pane p = FXMLLoader.load(getClass().getResource("/Interfaces/profilUser.fxml"));
        acpane.getChildren().add(p);

    }

public BorderPane getborder(){
    return Bpadmin;
}

public void setLabel(String s){
     l.setText(s);
}

    
  
   
}
