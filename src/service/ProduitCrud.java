/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Categorie;
import entities.Product;
import service.IJoueur;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.MyDBPi;

/**
 *
 *
 */
public class ProduitCrud implements IProduit_1<Product> {

    @Override
    public List<Product> AfficherProduit(Product t) {
        List<Product> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM produit ";
            Statement pst = MyDBPi.getInstance().getConnection().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Product r = new Product();
                r.setId(rs.getInt("id_produit"));
                r.setProduct_name(rs.getString("nonprod"));
                r.setImg(rs.getString("image"));
               r.setPrice(rs.getDouble("prix"));
                r.setStock(rs.getInt("quantity"));
                 


                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return list;
    }

 

}
