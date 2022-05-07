/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import entities.Reponse;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MaConnexion;
import services.Ireclamation;

/**
 *
 * @author lylyy
 */
public class ServiceReclamation implements Ireclamation{
   Connection cnx = MaConnexion.getInstance().getCnx();

    public boolean ajouterReclamation(Reclamation r) {
        String request = "INSERT INTO `Reclamation`(`nom`, `message`, `sujet`,`email`,`createdAt`,`idReponse`,`idUser`) VALUES ('"+r.getNom()+"','"+r.getMessage()+"','"+r.getSujet()+"','"+r.getEmail()+"','"+r.getCreatedAt()+"','"+r.getReponse().getIdReponse()+"','"+r.getUser().getId()+"')";
//          String request = "INSERT INTO `Reclamation`(`nom`, `createdAt`,`idReponse`,`idUser`) VALUES ('"+r.getNom()+"','"+r.getMessage()+"','"+r.getSujet()+"','"+r.getEmail()+"','"+r.getCreatedAt()+"','"+r.getReponse().getIdReponse()+"','"+r.getUser().getIdUser()+"')";
try {
            Statement st = cnx.createStatement();
            if (st.executeUpdate(request) == 1 )
                return true;
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    } 

    @Override
    public List<Reclamation> afficherReclamation() {
  List<Reclamation> reclamation = new ArrayList<Reclamation>();

        String req="SELECT * FROM `reclamation`";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                reclamation.add(new Reclamation(rs.getInt("idReclamation"),rs.getString("nom"),rs.getString("message"),rs.getString("sujet"),rs.getString("email"),rs.getDate("createdAt"),new Reponse(rs.getInt("idReponse")),new Utilisateur(rs.getInt("idUser")),rs.getBoolean("warn")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return reclamation;
    }
    public boolean warn(Reclamation r) {

       String req = "UPDATE `reclamation` SET `warn`=1  WHERE `idReclamation` = "+r.getIdReclamation()+" ";
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
    public boolean modifierReclamation(Reclamation r) {
           String req = "UPDATE `reclamation` SET `nom`='"+r.getNom()+"',`message`='"+r.getMessage()+"',`sujet`='"+r.getSujet()+"',`email`='"+r.getEmail()+"',`createdAt`='"+r.getCreatedAt()+"',`idReponse`='"+r.getReponse().getIdReponse()+"',`idUser`='"+r.getUser().getId()+"' WHERE `idReclamation` = "+r.getIdReclamation()+" ";
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
    public boolean supprimerReclamation(Reclamation r) {
        String req = "DELETE FROM `reclamation` WHERE `idReclamation` = "+r.getIdReclamation()+" ";

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
    public List<Reclamation> rechercheReclamationParUser(Utilisateur u) {
        List<Reclamation> reclamation = new ArrayList<Reclamation>();

        String req="SELECT * FROM `reclamation` where `idUser`="+u.getId()+" ";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                reclamation.add(new Reclamation(rs.getInt("idReclamation"),rs.getString("nom"),rs.getString("message"),rs.getString("sujet"),rs.getString("email"),rs.getDate("createdAt"),new Reponse(rs.getInt("idReponse")),new Utilisateur(rs.getInt("idUser"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return reclamation;
    }

    @Override
    public List<Reclamation> triReclamationParUser() {
           List<Reclamation> reclamation = new ArrayList<Reclamation>();

        String req="SELECT * FROM `reclamation` order by `idUser` desc ";
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //
            while(rs.next()){
               reclamation.add(new Reclamation(rs.getInt("idReclamation"),rs.getString("nom"),rs.getString("message"),rs.getString("sujet"),rs.getString("email"),rs.getDate("createdAt"),new Reponse(rs.getInt("idReponse")),new Utilisateur(rs.getInt("idUser"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return reclamation;
    }
     public List<Reclamation> ReclamationParRepondeur(int idRepondeur) {
        List<Reclamation> reclamation = new ArrayList<Reclamation>();

        String req="SELECT * FROM reclamation r , reponse l where (l.idReponse = r.idReponse) and r.WARN=1 and l.idUser="+idRepondeur;
        Statement st = null;
        try {
            st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            //SOB HEDHA FI HEDHA
            while(rs.next()){
                 reclamation.add(new Reclamation(rs.getInt("idReclamation"),rs.getString("nom"),rs.getString("message"),rs.getString("sujet"),rs.getString("email"),rs.getDate("createdAt"),new Reponse(rs.getInt("idReponse")),new Utilisateur(rs.getInt("idUser")),rs.getBoolean("warn")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return reclamation;
    }
}
