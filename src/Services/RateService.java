/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.RateProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Utils.MyDB;

/**
 *
 * @author bouss
 */
public class RateService  {
    Connection cnx;
     public RateService() {
        cnx = MyDB.getInstance().getConnection();
    }

   
    public void Add(RateProduct t) {
         try {
            String req = "insert into note (id_produit  , note) Values(?,?) ";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, t.getId_produit());
            st.setDouble(2, t.getNote());
            st.executeUpdate();
            System.out.println("note ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }

    }

    
    public void Update(RateProduct t) {
         try {
            String request
                    = "UPDATE note SET id_produit = ?,  note = ? WHERE id_produit = ?";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setInt(1, t.getId_produit());
          
            st.setDouble(2, t.getNote());
            st.setInt(3, t.getId_produit());
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public void Delete(int id) {
         try {
            String request = "DELETE FROM note WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("note supprimée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public RateProduct GetByProduct(int id) {
        RateProduct r = new RateProduct();
         try {
            String request = "SELECT * FROM note WHERE id_produit =?";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
		 r = new RateProduct(rs.getInt("id_produit"), rs.getDouble("note"));
	    }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return r;
    }

   
}

   

    

