/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Repondeur;
import Entities.Repondeur;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Utils.MyDB;
import Entities.Utilisateur;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lylyy
 */
public class ServiceRepondeur implements Irepondeur{
  //var
    Connection cnx = MyDB.getInstance().getConnection();
    @Override
    public boolean ajouterPersonne(Repondeur p) {
        String request = "INSERT INTO `repondeur`( `cin`, `vehicule`,`id`) VALUES ('" + p.getCin()+ "','" + p.getVehicule()+ "','" + p.getId()+ "')";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(request) == 1) {
                return true;
            }

            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Repondeur> afficherPersonnes() {
       List<Repondeur> personnes = new ArrayList<Repondeur>();

        String req = "SELECT * from utilisateur JOIN repondeur WHERE utilisateur.id= repondeur.id;";

        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while (rs.next()) {
                personnes.add(new Repondeur(rs.getString("vehicule"), rs.getString("cin"), rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("num_tel"), rs.getString("password"), rs.getString("date_naissance"),
                        rs.getString("email"),rs.getString("roles")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personnes;
    }

    @Override
    public boolean modifierPersonne(Repondeur p) {
           String req = "UPDATE `repondeur` SET `vehicule`='" + p.getVehicule() + "',`cin`='" + p.getCin() + "' WHERE `id` = " + p.getId()+ " ";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } 
    }

    @Override
    public boolean supprimerPersonne(Repondeur p) {
           String req = "DELETE FROM `repondeur` WHERE `id` = " + p.getId()+ " ";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
}
