/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Eventl;
import Entities.Reservation;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Home
 */
public class ServiceResrvation implements IService<Reservation>{
    Connection cnx;
 public ServiceResrvation (){
 cnx = MyDB.getInstance().getConnection();
 
 }

    @Override
    public void ajouter(Reservation t) {
     if (verifNbrTicket(t.getIdticket()) ) {
     String req ="insert into reservation(iduser,idticket)"+"values("+t.getIduser()+","+t.getIdticket()+")";
     try {
         Statement st = cnx.createStatement();
         st.executeUpdate(req);
         System.out.println("hi  ddd");
     } catch (SQLException ex) {
  System.out.println(ex.getMessage());
     }
     } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Resrvation");
            alert2.setHeaderText("le nombre des tickets sont occupées");
            alert2.show();

            System.out.println("le nombre des tickets sont occupées");
        }
    }

    @Override
    public void modifier(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int idevent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getNbrTicket(int idticket) {
        String req = "SELECT nbrticket FROM ticket WHERE idticket = ? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idticket);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return -1; //-1 une erreur dans la methode
    }
    
     public boolean verifNbrTicket(int idticket) { //
        String req = "SELECT count(*) FROM reservation WHERE idticket = ? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idticket);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) < getNbrTicket(idticket);  
                
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
     
    // vérifier si le client a participer deja ou bien nn 
    public boolean verifReservation(int iduser,int idticket) {
        String req = "SELECT * FROM reservation WHERE iduser = ? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, iduser);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void reserver(int iduser, int idticket) {
        if (verifReservation(iduser,idticket)) {
            System.out.println("le client a reservé deja ");
        } else {
            Reservation p = new Reservation(iduser, idticket);
            ajouter(p);
        }
    }
    public List<String> getParticipEmails(int iduser) {
        List<String> myList = new ArrayList();
        String req = "SELECT email FROM utilisateur WHERE iduser = ?";
        try {

            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, iduser);
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
