/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Product;
import entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import Utils.MyDB;

/**
 *
 * @author bouss
 */
public class ProductService implements IProduit<Product> {

    Connection cnx;

    public ProductService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void Add(Product t) {
        try {
            String req = "insert into produit(categoryprod_id,nonprod,description,image,prix,quantity) Values(?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, t.getCategoryprod_id());
            st.setString(2, t.getNomprod());
            st.setString(3, t.getDescription());
            st.setString(4, t.getImage());
            st.setFloat(5, t.getPrix());
            st.setInt(6, t.getQuantity());
            st.executeUpdate();
            System.out.println("Produit ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void Update(Product t) {
        try {
            String request
                    = "UPDATE produit SET nonprod = ?, description = ?, image = ?, prix = ?, quantity = ? , categoryprod_id = ? WHERE id_produit = ?";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setString(1, t.getNomprod());
            st.setString(2, t.getDescription());
            st.setString(3, t.getImage());
            st.setFloat(4, t.getPrix());
            st.setInt(5, t.getQuantity());
            st.setInt(6, t.getCategoryprod_id());
            st.setInt(7, t.getId_produit());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try {
            String request = "DELETE FROM produit WHERE id_produit=?";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("Produit supprimée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   

    @Override
    public List<Product>  GetAll() {
        List<Product> list = new ArrayList<>();
	try {
	    String request = "SELECT* FROM produit";
	    PreparedStatement st = cnx.prepareStatement(request);
	    ResultSet rs = st.executeQuery();
	    while (rs.next()) {
		list.add(new Product(rs.getInt("id_Produit"), rs.getString("nonprod"), rs.getString("description"), rs.getString("image"), rs.getFloat("prix"), rs.getInt("quantity"), rs.getInt("categoryprod_id")));
	    }
	} catch (SQLException ex) {
	    System.err.println(ex.getMessage());
	}
	return list;
    }
    
    public List<Product> AfficherProduit(Product t) {
        List<Product> list = new ArrayList<>();
        try {
            String requete = "SELECT id_Produit, nonprod, description, image, prix, quantity FROM produit ";
            Statement pst = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Product r = new Product();
                r.setId_produit(rs.getInt("id_Produit"));
                r.setNomprod(rs.getString("nonprod"));
                r.setDescription(rs.getString("description"));
                r.setImage(rs.getString("image"));
               r.setPrix(rs.getFloat("prix"));
                r.setQuantity(rs.getInt("quantity"));
                 


                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return list;
    }
     @Override
    public Product GetById(int id) {
        return GetAll().stream().filter(e -> e.getId_produit() == id).findFirst().get();
    }
   
    public Product findByname_Product(String name_Product) {

	return GetAll().stream().filter(Products -> name_Product.equals(Products.getNomprod())).findFirst().get();

    }
    
    public List<Product> sortByName() {

	return GetAll().stream().sorted((a, b) -> a.getNomprod().compareTo(b.getNomprod()))
		.collect(Collectors.toList());
    }
    public List<Product> paginateProducts(int pageNum, int pageSize) {

	int SKIP_COUNT = (pageNum - 1) * pageSize;

	int LIMIT_COUNT = 0;

	try {

	    LIMIT_COUNT = pageSize / SKIP_COUNT;

	} catch (ArithmeticException e) {
	    LIMIT_COUNT = 5;
	}

	return GetAll()
		.stream()
		.skip(SKIP_COUNT)
		.limit(LIMIT_COUNT).collect(Collectors.toList());
    }
    public ObservableList<Product> getProdbyfiltre(String search ){
        ObservableList<Product> list = FXCollections.observableArrayList();
        GetAll().stream().filter(x -> x.getNomprod().contains(search)||x.getNomprod().toLowerCase(Locale.ROOT).contains(search)
               ).forEach(x-> list.add(x));
        return  list;
    }
//    
//     public void Notificationmanager(int mode) {
//	Notifications not = Notifications.create()
//		.graphic(null)
//		.hideAfter(Duration.seconds(7))
//		.position(Pos.BOTTOM_RIGHT)
//		.onAction(new EventHandler<ActionEvent>() {
//		    @Override
//		    public void handle(ActionEvent event) {
//			System.out.println("clicked on notification");
//		    }
//		});
//	not.darkStyle();
//	switch (mode) {
//	    case 0:
//
//		not.title("Success");
//		not.text("Categorie crée");
//		not.showInformation();
//		break;
//	    case 1:
//
//		not.title("Success ");
//		not.text("Suppression terminer");
//		not.showWarning();
//		break;
//	    case 2:
//
//		not.title("Success");
//		not.text("Modification terminé");
//
//		not.showInformation();
//		break;
//	    case 3:
//
//		not.text("Produit  ajouté et notifié Par mail");
//		not.title("Success");
//		not.showConfirm();
//		break;
//
//	    case 4:
//		not.text("Code Promo ajouté");
//		not.title("Success");
//		not.showWarning();
//		break;
//	    case 5:
//		not.text("Mettez une entrée valide s'il vous plait");
//		not.title("Entrée Non Valide");
//		not.showWarning();
//		break;
//	    default:
//
//	}
//
//    }

    

}
