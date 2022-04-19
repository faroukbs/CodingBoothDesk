/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Eventl;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class EvenementController implements Initializable {

    @FXML
  private ImageView image;
    @FXML
    private Label titrefx;
    @FXML
    private Label descriptionfx;
    @FXML
    private Label datedebutfx;
    @FXML
    private Label dafintx;
   private Eventl event;
   
    private MyListener mylistener;
        private Eventl eventl;
  @FXML
    private void click(MouseEvent mouseEvent) {
        mylistener.onClickListener(eventl);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public  List<File> findAllFilesInFolder(File folder) {
          List<File> list=new ArrayList<>();
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				list.add(file);
                                          
                            
			} else {
				findAllFilesInFolder(file);
			}
		}
                return list;
	}
        public void setData(Eventl event) throws IOException {
      
            this.event=event;
            
            titrefx.setText(event.getTitre());
            datedebutfx.setText(event.getDatedebut().toString());
           dafintx.setText(event.getDatefin().toString());
           
          
           
  
                 Circle cir2 = new Circle(50, 50, 50);
        File folder = new File("C:\\Users\\Home\\Documents\\NetBeansProjects\\Farah\\src\\image");
		       
                             
        
//          Image imge = new Image(new FileInputStream("C:\\xampp\\htdocs\\uploads\\images\\"
//                  + user.GetUserByCin(offre.getIdhost()).getImage_cin()));
//                    HostPic.setFill(new ImagePattern(imge));
//                    cir2.setFill(new ImagePattern(imge));
//             
            
             for(int i=0;i<findAllFilesInFolder(folder).size();i++)
             {
               
                      
                 if(findAllFilesInFolder(folder).get(i).getName().equals(event.getPhoto()))
                 {
                   

               
                     Image imgee = new Image(new FileInputStream("C:\\Users\\Home\\Documents\\NetBeansProjects\\Farah\\src\\image"+event.getPhoto()));
                     image.setImage(imgee);
                 }

             }

        
        }



 
    
}
