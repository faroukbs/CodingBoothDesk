/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author lylyy
 */
public class SendSmsController implements Initializable {

    @FXML
    private TextField txtapi;
    @FXML
    private TextField txtnumber;
    @FXML
    private TextField txtsender;
    @FXML
    private TextArea txtmess;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Sendsms(ActionEvent event) {
   
    try {
			// Construct data
			String apiKey = "apikey=" + txtapi.getText();
			String message = "&message=" + txtmess.getText();
			String sender = "&sender=" + txtsender.getText();
			String numbers = "&numbers=" + txtnumber.getText();
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                                JOptionPane.showConfirmDialog( null,"message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			//System.out.println("Error SMS "+e);
			//return "Error "+e;
                         JOptionPane.showConfirmDialog( null,e);
		}
          Notifications notificationBuilder = Notifications.create()
                .title("MESSAGE").text("MESSAGE ENVOYEE AVEC SUCCES").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.CENTER_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();

    }
    }
    

