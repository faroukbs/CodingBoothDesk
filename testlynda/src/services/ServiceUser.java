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
import java.util.ArrayList;
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
        String req = "SELECT * from `utilisateur` WHERE  utilisateur.idUser= '" + email + "' ";
        Utilisateur ad = new Utilisateur();
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
//                String[] adr= rs.getString("adresse").split("/"); ;
//                Adresse adresse=new Adresse(adr[0], adr[1], adr[2], adr[3]);
                ad = new Utilisateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getInt("num_tel"), rs.getString("password"), rs.getString("date_naissance"), rs.getString("email"), rs.getString("roles"));
                
//                Date d =Date.valueOf(LocalDate.now());
//                System.out.println((d.getYear())-(rs.getDate("date_naissance").getYear()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ad.getEmail();

    }

    @Override
    public List <Utilisateur>  afficher() {
    
   
        List<Utilisateur> personnes = new ArrayList<Utilisateur>();
        String req = "SELECT * FROM `utilisateur`";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next())
            {
              personnes.add( new Utilisateur(rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getInt("num_tel"), rs.getString("password"), rs.getString("date_naissance"), rs.getString("email"), rs.getString("roles")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }
    }
