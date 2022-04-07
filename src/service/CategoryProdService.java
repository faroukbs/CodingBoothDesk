/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Product;
import entities.category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDb;



/**
 *
 * @author bouss
 */
public class CategoryProdService implements IProduit<category> {
    Connection cnx;

    public CategoryProdService() {
        cnx = MyDb.getInstance().getConnection();
    }

    @Override
    public void Add(category t) {
       try {
            String req = "insert into category(nom) Values(?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setString(1, t.getNom());
          
            st.executeUpdate();
            System.out.println("category ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Update(category t) {
try {
            String request
                    = "UPDATE category SET nom = ? WHERE id = ?";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setString(1, t.getNom());
            st.setInt(2, t.getIdcategory());
           
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
 try {
            String request = "DELETE FROM category WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(request);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("category supprimée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public  category GetById(int id) {
        return GetAll().stream().filter(e -> e.getIdcategory() == id).findFirst().get();

    }

    @Override
    public List<category> GetAll() {
        List<category> list = new ArrayList<>();
	try {
	    String request = "SELECT* FROM category";
	    PreparedStatement st = cnx.prepareStatement(request);
	    ResultSet rs = st.executeQuery();
	    while (rs.next()) {
		list.add(new category(rs.getInt("id"), rs.getString("nom")));
	    }
	} catch (SQLException ex) {
	    System.err.println(ex.getMessage());
	}
	return list;

    }
    
}
