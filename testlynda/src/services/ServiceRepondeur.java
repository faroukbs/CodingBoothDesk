/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Repondeur;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MaConnexion;
import entities.Utilisateur;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lylyy
 */
public class ServiceRepondeur implements Irepondeur{
  //var
    Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public boolean ajouterPersonne(Repondeur p) {
        String request = "INSERT INTO `repondeur`( `cin`, `vehicule`,`id_user`) VALUES ('" + p.getCin()+ "','" + p.getVehicule()+ "','" + p.getIdUser()+ "')";
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

        String req = "SELECT * from utilisateur JOIN repondeur WHERE utilisateur.idUser= repondeur.idUser;";

        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while (rs.next()) {
                personnes.add(new Repondeur(rs.getString("vehicule"), rs.getString("cin"), rs.getInt("idUser"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getString("numTel"), rs.getString("image"),
                        rs.getString("roles"), rs.getDate("date_naissance")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personnes;
    }

    @Override
    public boolean modifierPersonne(Repondeur p) {
           String req = "UPDATE `repondeur` SET `vehicule`='" + p.getVehicule() + "',`cin`='" + p.getCin() + "' WHERE `idUser` = " + p.getIdUser()+ " ";
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
           String req = "DELETE FROM `repondeur` WHERE `idUser` = " + p.getIdUser()+ " ";
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
