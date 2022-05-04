/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;

import entities.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MaConnexion;

/**
 *
 * @author lylyy
 */
public class ServiceCommande implements Icommande{
    Connection cnx = MaConnexion.getInstance().getCnx();

    public List<Commande> recupererCommande() {
        List<Commande> commande = new ArrayList<Commande>();

        String req="SELECT * FROM `commande`";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                commande.add(new Commande(rs.getInt("idCommande"),rs.getString("nom_client"),rs.getString("prenom_client"),rs.getString("telephone"),rs.getString("adresse"),rs.getString("montant"),rs.getString("mode_paiement"),rs.getInt("etat_commande")));
            }
  
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return commande;
    
    }
    
}
