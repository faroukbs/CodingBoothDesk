/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
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
import util.MyDB;

/**
 *
 * @author ahmed
 */
public class CoursService implements IService<Cours> {

    Connection cnx;

    public CoursService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Cours t) {

        String req = "INSERT INTO calendar (id , title, idcategorie,idcoach ,idsalle , description , imagecours, start, end) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, t.getIdcours());
            pst.setString(2, t.getTitle());
            pst.setInt(3, t.getIdcategorie());
            pst.setInt(4, t.getIdcoach());
            pst.setInt(5, t.getIdsalle());
            pst.setString(6, t.getDescription());
            pst.setString(7, t.getImagecours());
            pst.setDate(8, t.getStart());
            pst.setDate(9, t.getEnd());
            pst.executeUpdate();
            System.out.println("Cours Ajouté avec succés");
            ResultSet rs = pst.getGeneratedKeys();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Cours t) {
        try {
            String req = "update calendar set title = ?, idcategorie = ?, idcoach = ?,idsalle = ?, description = ?, imagecours = ?, start = ?, end = ? WHERE id=? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTitle());
            ps.setInt(2, t.getIdcategorie());
            ps.setInt(3, t.getIdcoach());
            ps.setInt(4, t.getIdsalle());
            ps.setString(5, t.getDescription());
            ps.setString(6, t.getImagecours());
            ps.setDate(7, t.getStart());
            ps.setDate(8, t.getEnd());
            ps.setInt(9, t.getIdcours());
            ps.executeUpdate();
            System.out.println("Cours modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int idcours) {
        String req = "delete from calendar where id=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, idcours);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Cours supprimé");
    }

    @Override
    public List<Cours> recuperer() {
        List<Cours> courss = new ArrayList<>();
        try {
            String req = "select id,title,description,idcategorie,idcoach,idsalle,start,end from calendar";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Cours c = new Cours();
                c.setIdcours(rs.getInt(1));
                c.setTitle(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setIdcategorie(rs.getInt(4));
                c.setIdcoach(rs.getInt(5));
                c.setIdsalle(rs.getInt(6));

                c.setStart(rs.getDate(7));
                c.setEnd(rs.getDate(8));

                courss.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return courss;
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

    public ObservableList<Integer> affecterSalle() {
        ObservableList<Integer> listSalle = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT idsalle FROM salle";
            ResultSet rs;
            rs = st.executeQuery(req);
            while (rs.next()) {
                listSalle.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //   return null;
        }
        return listSalle;
    }

    public ObservableList<Integer> affecterCoach() {
        ObservableList<Integer> listcoach = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT id FROM `utilisateur` WHERE  roles like '%COACH%'";
            ResultSet rs;
            rs = st.executeQuery(req);
            while (rs.next()) {
                listcoach.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //   return null;
        }
        return listcoach;
    }

    @Override
    public Cours recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
