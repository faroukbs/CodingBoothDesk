/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Eventl;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Utils.MyDB;
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
/**
 *
 * @author Home
 */
public class ServiceEventl implements IService<Eventl>  {
 Connection cnx;
 public ServiceEventl(){
 cnx = MyDB.getInstance().getConnection();
 
 }
    @Override
    public void ajouter(Eventl t) {
               String req ="insert into eventl(titre,ville,description,datedebut,datefin,photo)"+"values('"+t.getTitre()+"','"+t.getVille()+"','"+t.getDescription()+"','"+t.getDatedebut()+"','"+t.getDatefin()+"','"+t.getPhoto()+"')";
     try {
         Statement st = cnx.createStatement();
         st.executeUpdate(req);
         System.out.println("hi  ddd");
     } catch (SQLException ex) {
  System.out.println(ex.getMessage());
     }
    }

    @Override
    public void modifier(Eventl t) {
                String req ="update eventl set titre= ? , datefin =?, description = ?,ville= ? ,datedebut = ?,photo = ? where idevent = ?";     
     try {
       
         PreparedStatement ps = cnx.prepareStatement(req);
         
          ps.setString(1, t.getTitre());
               ps.setDate(2,t.getDatefin());
                ps.setString(3, t.getDescription());
              ps.setString(4, t.getVille());
      
              ps.setDate(5,t.getDatedebut());
           
          ps.setString(6, t.getPhoto());
              ps.setInt(7, t.getIdevent());
           
                 
               ps.executeUpdate();
     } catch (SQLException ex) {
  System.out.println(ex.getMessage());
     }
    }

    @Override
    public void supprimer(int idevent) {
     

        String req = "delete from eventl where idevent=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, idevent);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Evenement supprimé");
    }
    

    @Override
    public List<Eventl> recuperer() {
            
        List<Eventl> events = new ArrayList <>();
      
     try {
           String req=" select *from eventl";
         Statement st= cnx.createStatement();
         ResultSet rs=st.executeQuery(req);
         while(rs.next()){
         
         
         Eventl p= new Eventl();
         p.setIdevent(rs.getInt(1));
               p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
            
             
                   p.setVille(rs.getString("ville"));
                  p.setPhoto(rs.getString("photo"));
    p.setDatedebut(rs.getDate("datedebut"));
                  p.setDatefin(rs.getDate("datefin"));
                 
                 events.add(p);
                 }
     } catch (SQLException ex) {
       
     }
     return events;   
    }
    public Eventl afficherEvenement(int idE){
        Eventl e = new Eventl();
        try {
            String req = "SELECT * FROM eventl WHERE idevent = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idE);
            ResultSet rs = pst.executeQuery();
            rs.next();
        e.setTitre(rs.getString(1));
            e.setDescription(rs.getString(2));
       
          
            e.setDatedebut(rs.getDate(3));
            e.setDatefin(rs.getDate(4));
        
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }
    

    @Override
    public Eventl recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public Eventl afficherEvenements(int idE){
        Eventl e = new Eventl();
        try {
            String req = "SELECT * FROM eventl WHERE idevent = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            e.setIdevent(rs.getInt(1));
         e.setTitre(rs.getString(2));
            e.setDescription(rs.getString(4));
            e.setVille(rs.getString(5));
        
            e.setDatedebut(rs.getDate(3));
            e.setDatefin(rs.getDate(8));
  e.setPhoto(rs.getString(9));
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return e;
    }

      
           public ObservableList<Eventl> chercherTitreplat(String chaine){
          String sql="SELECT * FROM eventl WHERE (titre LIKE ? or ville LIKE ? or description LIKE ? )";
            
           cnx = MyDB.getInstance().getConnection();
            String ch="%"+chaine+"%";

            ObservableList<Eventl> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
            stee.setString(3, ch);
   
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                Eventl v = new Eventl ();
                 v.setTitre(rs.getString(2));
            v.setDescription(rs.getString(4));
            v.setVille(rs.getString(5));
        
            v.setDatedebut(rs.getDate(3));
            v.setDatefin(rs.getDate(8));
  v.setPhoto(rs.getString(9));
                myList.add(v);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }
   
 }

