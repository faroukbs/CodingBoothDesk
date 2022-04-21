/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import util.MyDBPi;

/**
 *
 * @author bouss
 */
public class ProductService implements IProduit<Product> {

    Connection cnx;

    public ProductService() {
        cnx = MyDBPi.getInstance().getConnection();
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

}
