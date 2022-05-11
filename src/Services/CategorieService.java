/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.MyDB;

/**
 *
 * @author ahmed
 */
public class CategorieService implements IService_1<Categorie> {

    Connection cnx;

    public CategorieService() {
        cnx = MyDB.getInstance().getConnection();
    }

    
    @Override
    public void ajouter(Categorie t) {
        
        String req = "INSERT INTO categorie (idcategorie , nomcategorie) VALUES (?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, t.getIdcategorie());
            pst.setString(2, t.getNomcategorie());
           
            pst.executeUpdate();
            System.out.println("Categorie Ajouté avec succés");
            ResultSet rs = pst.getGeneratedKeys();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   
        
    }

    @Override
    public void modifier(Categorie t) {
        try {
            String req = "update categorie set nomcategorie = ? WHERE idcategorie=? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNomcategorie());
            ps.setInt(2, t.getIdcategorie());
            ps.executeUpdate();
            System.out.println("Categorie modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int idcategorie) {
        String req = "delete from categorie where idcategorie=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, idcategorie);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Categorie supprimé");
    }

    @Override
    public List<Categorie> recuperer() {
        List<Categorie> categories = new ArrayList<>();
        try {
            String req = "select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Categorie c = new Categorie();
                c.setIdcategorie(rs.getInt(1));
                c.setNomcategorie(rs.getString("nomcategorie"));

                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    @Override
    public Categorie recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
