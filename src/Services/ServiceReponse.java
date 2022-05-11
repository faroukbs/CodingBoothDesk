/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Commande;
import Entities.Repondeur;
import Entities.Reponse;
import Entities.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;
import services.Ireponse;


/**
 *
 * @author lylyy
 */
public class ServiceReponse implements Ireponse{
     //var
    Connection cnx = MyDB.getInstance().getConnection();
    @Override
    public boolean ajouterReponse(Reponse l) {
        String request = "INSERT INTO `reponse`(  `nom`, `sujet`, `message`, `createdAt`, `etat`, `id`, `idcommande`) VALUES ('"+l.getNom()+"','"+l.getSujet()+"','"+l.getMessage()+"','"+l.getCreatedAt()+"','"+l.getEtat()+"','"+l.getUser().getId()+"','"+l.getCommande().getIdcommande()+"')";
//         String request = "INSERT INTO `reponse`( `createdAt`, `etat`, `idUser`, `idCommande`) VALUES ("+l.getCreatedAt()","+l.getEtat()",'"+l.getUser().getIdUser()+"','"+l.getCommande().getIdCommande()+"')";
try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(request) == 1)
                return true;
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }  
    }

    @Override
    public List<Reponse> afficherReponse() {
       List<Reponse> reponse = new ArrayList<Reponse>();

        String req="SELECT * FROM `reponse`";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                                reponse.add(new Reponse(rs.getInt("idReponse"),rs.getString("nom"),rs.getString("sujet"),rs.getString("message"),rs.getDate("createdAt"),rs.getString("etat"),new Utilisateur(rs.getInt("id")),new Commande(rs.getInt("idcommande"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return reponse;
    }

    @Override
    public boolean modifierReponse(Reponse l) {
            String req = "UPDATE `reponse` SET `nom`='"+l.getNom()+"',`sujet`= "+l.getSujet()+"',`message`='"+l.getMessage()+"',`createdAt`='"+l.getCreatedAt()+"',`etat`='"+l.getEtat()+"',`id`='"+l.getUser().getId()+"',`idcommande`='"+l.getCommande().getIdcommande()+"' WHERE `idReponse` = "+l.getIdReponse()+" ";
        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean supprimerReponse(Reponse l) {
       String req = "DELETE FROM `reponse` WHERE `idReponse` = "+l.getIdReponse()+" ";

        try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }  
    }
     public boolean annulerAffectation(Reponse l){
     String req="UPDATE `reponse` SET `id`=NULL WHERE idReponse='"+l.getIdReponse()+"'";
     try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
      
        }
     }
     public boolean Affectation(Reponse l , Repondeur repondeurSelected){
     
    String req="UPDATE `reponse` SET `id`="+repondeurSelected.getId()+" WHERE idReponse= "+l.getIdReponse()+" ";

    
    try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) == 1)
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
 }
    @Override
    public List<Reponse> rechercheReponseparEtat_Reponse(String etat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reponse> triLivraisonparEtat_Reponse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
