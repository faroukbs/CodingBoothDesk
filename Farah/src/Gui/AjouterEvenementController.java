/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Eventl;
import Services.ServiceEventl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class AjouterEvenementController implements Initializable {
    final FileChooser fc = new FileChooser();
    @FXML
    private TextField titretx;
    @FXML
    private TextField descriptiontx;
    @FXML
    private TextField villetx;
    @FXML
    private DatePicker datedebuttx;
    @FXML
    private DatePicker datefintx;
    @FXML
    private Button ajoutere;
  Eventl e = new Eventl();

    ServiceEventl ec = new  ServiceEventl();
    @FXML
    private ImageView img;
      File file;
    File file1;
    @FXML
    private Button uploadphoto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(ActionEvent event)throws FileNotFoundException, IOException {
          if (titretx.getText().isEmpty()
                || descriptiontx.getText().isEmpty() || villetx.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            titretx.setEffect(in);
            descriptiontx.setEffect(in);
            villetx.setEffect(in);
            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if (TestVille() & TestText() & TestDescription() & TestDate()) {

            String titre = titretx.getText();

            String description = descriptiontx.getText();
     String ville = villetx.getText();
          
            java.sql.Date datedebut = java.sql.Date.valueOf(datedebuttx.getValue());

            java.sql.Date datefin = java.sql.Date.valueOf(datefintx.getValue());

         

           

            FileInputStream fl = new FileInputStream(file);

            byte[] data = new byte[(int) file.length()];
            String fileName = file.getName();
            String path = fileName;
            fl.read(data);
            fl.close();
            // OutputStream out = new FileOutputStream(new File(path));

//        OutputStream out = new FileOutputStream(new File(path));
//        out.write(data);
//        out.close();
          Eventl  e = new Eventl( titre, description, datedebut, datefin, ville, path);

            ServiceEventl cs = new ServiceEventl();
            //     System.out.println("hh");
            cs .ajouter(e);

            final Stage dialog = new Stage();
            dialog.initModality(Modality.WINDOW_MODAL);

            //  dialog.initOwner(primaryStage);
            VBox dialogVbox = new VBox(10);
            dialogVbox.getChildren().add(new Text("Evenement  Ajputé avec success"));
            Scene dialogScene = new Scene(dialogVbox, 200, 200);
            dialog.setScene(dialogScene);
            dialog.show();

//                try {
//                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherPersonne.fxml"));
//                        Parent root = loader.load();
//                        
//                        GetAllOffersController c =loader.getController ();
//                        
//                        //c.setLname(offresService.getAllHoreca().toString());
//                        System.out.println(offresService.getAllHoreca().toString());
//                        System.out.println("fff");
//                       // txtnom.getScene().setRoot(root);
//                } 
//                catch (IOException ex) {
//                    System.out.println("gg");
//                System.out.println (ex.getMessage ());
//
//            }
        }
}
      @FXML
    private File uploadphoto(ActionEvent event) {

        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\piweb\\FirstPi1\\public\\images";
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
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
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
        return file;
    }
    private boolean TestText() {
        Pattern p = Pattern.compile("[a-zA-Z0-9]*[a-zA-Z0-9]*");
        Pattern p1 = Pattern.compile("[s][+][0-9]*");
        Matcher m = p.matcher(titretx.getText());
        Matcher m1 = p.matcher(titretx.getText());
        if (m.find() && m.group().equals(titretx.getText()) || m1.find() && m1.group().equals(titretx.getText())) {
            return true;
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir un nom valide");
            alert.showAndWait();

            return false;
        }
    }

   

    private boolean TestDescription() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*");
        Matcher m = p.matcher(descriptiontx.getText());
        if (m.find() && m.group().equals(descriptiontx.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une description  valide");
            alert.showAndWait();

            return false;
        }
    }

    private boolean TestVille() {
       Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9]*");
        Matcher m = p.matcher(villetx.getText());
        if (m.find() && m.group().equals(villetx.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une description  valide");
            alert.showAndWait();

            return false;
        }
    }

//    ImageTypeSpecifier createFromRenderedImage(RenderedImage image) throws IOException {
//        String fileNmaeExt = file.getName();
//
//        File initialImage = new File("C:\\Users\\user\\Desktop\\iheb");
//        if (image == null) {
//            throw new IllegalArgumentException("image == null!");
//        }
//
//        if (image instanceof BufferedImage) {
//            write(image, fileNmaeExt, initialImage);
//
//        }
//        return new ImageTypeSpecifier(image);
//    }
     private boolean TestDate() {
        java.sql.Date datedebut = java.sql.Date.valueOf(datedebuttx.getValue());

        java.sql.Date datefin = java.sql.Date.valueOf(datefintx.getValue());
        if (datefin.compareTo(datedebut) > 0) {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            datedebuttx.setEffect(in);
            datefintx.setEffect(in);
            return true;

        } else {
            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            datedebuttx.setEffect(in);
            datefintx.setEffect(in);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Syntaxe Email");
            alert.setHeaderText(null);
            alert.setContentText("S'il vous plait saisir une date valide");
            alert.showAndWait();

            return false;
        }
    }

}


   

