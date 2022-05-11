/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Utilisateur;
import Services.ServiceAdmin;
import services.ServiceUtilisateur;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.DataSource;

/**
 *
 * @author GhAlone
 */
public class UserController implements Initializable {
    
    
    @FXML
    private TableView<Utilisateur> apprenants;

    @FXML
    private TableColumn<Utilisateur, Integer> iduser;

    @FXML
    private TableColumn<Utilisateur, String> nom;
    
    @FXML
    private TextField rechercherMembre;
    @FXML
    private Button bann;
    @FXML
    private Button unbann;
    @FXML
    private TableColumn<Utilisateur, String> nomp;

    @FXML
    private TableColumn<Utilisateur, Integer> num;

    @FXML
    private TableColumn<Utilisateur, String> passe;

    @FXML
    private TableColumn<Utilisateur, String> genre;

    @FXML
    private TableColumn<Utilisateur, String> email;

    @FXML
    private TableColumn<Utilisateur, String> role;

    @FXML
    private TableColumn<Utilisateur, String> image;
     @FXML
    private CheckBox trierNomMembre;
    @FXML
    private CheckBox trierTelMembre;

    @FXML
    private Button btn_supp;
    @FXML
    private Button home;
    @FXML
    private Button ligne;
    @FXML
    private Button commande;
    @FXML
    private Button ligne1;
    @FXML
    private Button ligne11;
    @FXML
    private Button ligne111;
    @FXML
    private Button ligne1111;
    @FXML
    private Button ligne11111;
    @FXML
    private Button ligne111111;
    @FXML
    private Button ligne1111111;
    @FXML
    private Button ligne11111111;
    @FXML
    private Button ligne111111111;
    @FXML
    private Button ligne112;
  

  
   public Button getbann(){
   return bann;
   }
   
   

    public Button getunbann(){
    return unbann;
   }

    @FXML
    private TextField txt_search;
    
     
    
    ServiceUtilisateur user = new ServiceUtilisateur();
    ServiceAdmin admin = new ServiceAdmin();
    ObservableList<Utilisateur> dataList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataList = user.afficher();
        iduser.setCellValueFactory(new PropertyValueFactory<>("id"));       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        nomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));   
          passe.setCellValueFactory(new PropertyValueFactory<>("password")); 
        num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));        
      
        genre.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));  
        email.setCellValueFactory(new PropertyValueFactory<>("email")); 
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));  
       image.setCellValueFactory(new PropertyValueFactory<>("image"));  
        
        
        
        apprenants.setItems(dataList);
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Utilisateur> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txt_search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(employee.getDate_naissance()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		      SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(apprenants.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		apprenants.setItems(sortedData);
                
                
        
    }
    
    
   @FXML
    void click(ActionEvent event) {
                       try {
                           Utilisateur person = apprenants.getSelectionModel().getSelectedItem();
                System.out.println(person);
                FXMLLoader loader =new FXMLLoader(getClass().getResource("/Interfaces/modification.fxml"));  
                Parent root = loader.load();
                InterfaceModifController c = loader.getController();
                Image image = new Image(new FileInputStream(person.getImage()));
                c.setuser(person);
                c.getim().setImage(image);
                
                Scene scene = new Scene(root);
               // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("modifier");
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    
      @FXML
    void delete(ActionEvent event) {
        Utilisateur person = apprenants.getSelectionModel().getSelectedItem();
        admin.delete(person);
    }
    
    void reload(MouseEvent event) {
        apprenants.setItems(user.afficher());
    }
    
    
    @FXML
    public void affichageTabMembre() {
	if (rechercherMembre.getText() == null || rechercherMembre.getText().trim().isEmpty()) {
	    if (trierNomMembre.isSelected() == false && trierTelMembre.isSelected() == false) {
		ServiceAdmin sa = new ServiceAdmin();
		ObservableList<Utilisateur> list = FXCollections.observableArrayList(sa.afficher());
		iduser.setCellValueFactory(new PropertyValueFactory<>("id"));       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        nomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));   
           passe.setCellValueFactory(new PropertyValueFactory<>("password")); 
        num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));        
      
        genre.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));  
        email.setCellValueFactory(new PropertyValueFactory<>("email")); 
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));  
		apprenants.setItems(list);
                System.out.println(rechercherMembre.getText() + "test");

	    } else if (trierNomMembre.isSelected() == true) {
		if (trierTelMembre.isSelected() == false) {
		 //   ServiceAdmin se = new ServiceAdmin();
		    ServiceAdmin sa = new ServiceAdmin();
		    ObservableList<Utilisateur> list = FXCollections.observableArrayList(sa.trier());
		   iduser.setCellValueFactory(new PropertyValueFactory<>("id"));       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        nomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));   
           passe.setCellValueFactory(new PropertyValueFactory<>("password")); 
        num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));        
      
        genre.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));  
        email.setCellValueFactory(new PropertyValueFactory<>("email")); 
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));  
		apprenants.setItems(list);
	System.out.println(rechercherMembre.getText() + "test");
		} else {
		    ServiceAdmin sa = new ServiceAdmin();
		    ObservableList<Utilisateur> list = FXCollections.observableArrayList(sa.trier());
		   iduser.setCellValueFactory(new PropertyValueFactory<>("id"));       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        nomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));   
           passe.setCellValueFactory(new PropertyValueFactory<>("password")); 
        num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));        
      
        genre.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));  
        email.setCellValueFactory(new PropertyValueFactory<>("email")); 
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));  
		
        apprenants.setItems(list);
System.out.println(rechercherMembre.getText() + "test");
		}
	    } else if (trierTelMembre.isSelected() == true) {
		if (trierNomMembre.isSelected() == false) {
		    ServiceAdmin sa = new ServiceAdmin();
		    ObservableList<Utilisateur> list = FXCollections.observableArrayList(sa.trierID());
		  iduser.setCellValueFactory(new PropertyValueFactory<>("id"));       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        nomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));   
           passe.setCellValueFactory(new PropertyValueFactory<>("password")); 
        num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));        
      
        genre.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));  
        email.setCellValueFactory(new PropertyValueFactory<>("email")); 
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));  
		apprenants.setItems(list);
                System.out.println(rechercherMembre.getText() + "test");
		} else {
		    ServiceAdmin sa = new ServiceAdmin();
		    ObservableList<Utilisateur> list = FXCollections.observableArrayList(sa.trierID());
		   iduser.setCellValueFactory(new PropertyValueFactory<>("id"));       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        nomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));   
           passe.setCellValueFactory(new PropertyValueFactory<>("password")); 
        num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));        
      
        genre.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));  
        email.setCellValueFactory(new PropertyValueFactory<>("email")); 
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));  
		apprenants.setItems(list);
                System.out.println(rechercherMembre.getText() + "test");
		}
	    }

	} else {

	    ServiceAdmin sa = new ServiceAdmin();
	    ObservableList<Utilisateur> list = FXCollections.observableArrayList(sa.rechercher(rechercherMembre.getText()));
	       iduser.setCellValueFactory(new PropertyValueFactory<>("id"));       
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));        
        nomp.setCellValueFactory(new PropertyValueFactory<>("prenom"));   
           passe.setCellValueFactory(new PropertyValueFactory<>("password")); 
        num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));        
      
        genre.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));  
        email.setCellValueFactory(new PropertyValueFactory<>("email")); 
        role.setCellValueFactory(new PropertyValueFactory<>("roles"));  
		apprenants.setItems(list);
                System.out.println(rechercherMembre.getText() + "test");

	}

    }
     private void executeQuery(String req) {
        Connection cnx = DataSource.getInstance().getCnx();
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    @FXML
         public void bann() {
        Utilisateur person = apprenants.getSelectionModel().getSelectedItem();
        admin.bloquer(person);
        
    }
    @FXML
               public void unbann() {
        Utilisateur person = apprenants.getSelectionModel().getSelectedItem();
        admin.debloquer(person);
        
    }

     @FXML
    private void retourLigne(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/AjouterLigneCom.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourCom(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/ListCom.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/CrudNew.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/CrudNewticket.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourSalle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/CrudSalle.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourCours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/CrudCours.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourProduit(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/GestProduits.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourCatCours(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/CrudCategorie.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retoutCatProd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/GestCat.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourReclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/GestionReclmation.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourReponses(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Gui/Repadmin.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void retourUtilisaateur(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierUsers.fxml"));
            Parent root = loader.load();
            commande.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
