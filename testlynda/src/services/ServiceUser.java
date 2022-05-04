/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.activation.DataSource;
import utils.MaConnexion;


/**
 *
 * @author lylyy
 */
public class ServiceUser implements Iuser  {
      Connection cnx = MaConnexion.getInstance().getCnx();
  
 public String getById(int email) {
        String req = "SELECT * from user WHERE  user.idUser= '" + email + "' ";
        Utilisateur ad = new Utilisateur();
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
//                String[] adr= rs.getString("adresse").split("/"); ;
//                Adresse adresse=new Adresse(adr[0], adr[1], adr[2], adr[3]);
                ad = new Utilisateur(rs.getInt("idUser"), rs.getString("email"), rs.getString("roles"),
                        rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("date_naissance"), rs.getString("numTel"), rs.getString("image"), rs.getInt("is_verified"));
                
                Date d =Date.valueOf(LocalDate.now());
                System.out.println((d.getYear())-(rs.getDate("date_naissance").getYear()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ad.getEmail();

    }

    @Override
    public ObservableList <Utilisateur>  afficher() {
             ObservableList <Utilisateur> list = FXCollections.observableArrayList();

        try {
            String req = "select * from utilisateur where roles!='[\"ROLE_ADMIN\"]'";
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add
        (new Utilisateur(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDate(7),rs.getString(8),rs.getString(9),rs.getInt(10)));
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());       } 
        return list;
    }
}