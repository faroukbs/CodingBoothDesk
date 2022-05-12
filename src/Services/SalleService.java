/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Salle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.MyDB;

/**
 *
 * @author ahmed
 */
public class SalleService implements IService_1<Salle> {

    Connection cnx;

    public SalleService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Salle t) {

        String req = "INSERT INTO salle (idsalle , nomsalle , description , idcategorie,image) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, t.getIdsalle());
            pst.setString(2, t.getNomsalle());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getIdcategorie());
            pst.setString(5, t.getImage());

            pst.executeUpdate();
            System.out.println("Salle Ajoutée avec succés");
            ResultSet rs = pst.getGeneratedKeys();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Salle t) {
        try {
            String req = " update salle set nomsalle = ? , description = ? , idcategorie = ? , image = ?  WHERE idsalle=? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNomsalle());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getIdcategorie());
            ps.setString(4, t.getImage());
            ps.setInt(5, t.getIdsalle());
            //
            ps.executeUpdate();
            System.out.println("Salle modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int idsalle) {
        String req = "delete from salle where idsalle=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, idsalle);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Salle supprimée");
    }

    @Override
    public List<Salle> recuperer() {
        List<Salle> salles = new ArrayList<>();
        try {
            String req = "select idsalle,nomsalle,description,idcategorie,image  from salle";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Salle c = new Salle();
                c.setIdsalle(rs.getInt(1));
                c.setNomsalle(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setIdcategorie(rs.getInt(4));
                c.setImage(rs.getString(5));

//               c.setIdsalle(rs.getInt(3));
                salles.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return salles;
    }

    public ObservableList<Integer> affectercategorie() {
        ObservableList<Integer> listcategorie = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT idcategorie FROM categorie";
            ResultSet rs;
            rs = st.executeQuery(req);
            while (rs.next()) {
                listcategorie.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //   return null;
        }
        return listcategorie;
    }

    @Override
    public Salle recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<String> getEmails() {
        List<String> myList = new ArrayList();
        String req = "SELECT email FROM `utilisateur` WHERE roles like '%COACH%'";
      
         try {

            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                myList.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }
}
