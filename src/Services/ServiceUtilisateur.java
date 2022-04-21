/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Utilisateur;
import Interfaces.MainJavaFX;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import utils.DataSource;

/**
 *
 * @author GhAlone
 */

public class ServiceUtilisateur implements UService<Utilisateur> {
    boolean x;
            
        Connection cnx=DataSource.getInstance().getCnx();

    /**
     *
     * @param u
     */
    @Override
    public boolean connecter(Utilisateur u) {
        boolean ok=true;      
        try {
                String req="select email , password  from utilisateur where ( email=? and password=?  )";
                PreparedStatement pst=cnx.prepareStatement(req);
                ResultSet rs = pst.executeQuery();
                if(rs==null){
                    ok=false;   
                }   
            } catch (SQLException ex) {
                System.out.println("connexion echouer"); 
            }
        return ok;
    }

    
    
    @Override
    public void profil(Utilisateur u) {
            try {
                String req="update utilisateur set nom=?,prenom=?,num_tel=?,date_naissance=?,email=?, where id=?";
                PreparedStatement pst=cnx.prepareStatement(req);
                 pst.setInt(6,u.getId());
                pst.setString(1,u.getNom());
                pst.setString(2,u.getPrenom());
                pst.setInt(3,u.getNum_tel());
                pst.setString(4,u.getDate_naissance());
                 pst.setString(5,u.getEmail());
                pst.setString(6, u.getImage());
                pst.executeUpdate();
                System.out.println("modifier profil");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());    
            }
    }
    
    @Override
    public void changer_pass(Utilisateur u) {
            try {
                String req="update utilisateur set password=? where id=?";
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setInt(2,u.getId());
                pst.setString(1, u.getPassword());
                pst.executeUpdate();
                System.out.println("password se change");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());    
            }
    }

    @Override
    public void modifier(Utilisateur u) {
            try {
                String req="update utilisateur set nom=?,prenom=?,num_tel=?,email=?,image=? where id=?";
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setInt(6,u.getId());
                pst.setString(1,u.getNom());
                pst.setString(2,u.getPrenom());
                pst.setInt(3,u.getNum_tel());
              //  pst.setString(4,u.getPassword());
             //   pst.setString(5,u.getDate_naissance());
                pst.setString(4,u.getEmail());
             //  pst.setString(7,u.getRoles());
                pst.setString(5, u.getImage());
                pst.executeUpdate();
                System.out.println("modifier");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());            }
            
    }

    @Override
    public void inscrir(Utilisateur u) {
              try {/*
                String req ="insert into utilisateur (nom,prenom,num_tel,password,date_naissance,email,roles,image) values (?,?,?,?,?,?,?,?)";
                PreparedStatement pst= cnx.prepareStatement(req);
                pst.setString(1, u.getNom());
                pst.setString(2, u.getPrenom());
                pst.setInt(3, u.getNum_tel());
                pst.setString(4, u.getPassword());
                pst.setString(5, u.getDate_naissance());
                pst.setString(6, u.getEmail());
                pst.setString(7, u.getRoles());
                pst.setString(8, u.getImage());
               // pst.setInt(9, u.getisVerified());
                pst.executeUpdate();
                System.out.println("s'inscrire");*/
                  String req = "insert into utilisateur (email,roles,password,date_naissance,num_tel,image,nom,prenom,is_verified)"
                    + "values( '" + u.getEmail() + "', '" + u.getRoles() + "','" + u.getPassword() + "','" + u.getDate_naissance() + "','" + u.getNum_tel() + "','" + u.getImage() + "','" + u.getNom() + "','" + u.getPrenom() + "'," + u.getisVerified() + ")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur ajoutée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @Override
    public ObservableList<Utilisateur> afficher() {
                    ObservableList <Utilisateur> list = FXCollections.observableArrayList();

        try {
            String req = "select * from utilisateur where roles!='[\"ROLE_ADMIN\"]'";
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add
        (new Utilisateur(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getInt(10)));
            }   
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());       } 
        return list;
    }
  
    
    public String recEmail (String user) {
        String req="select email from utilisateur where email ='"+user+"'";
        String res="null";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
               res=rs.getString("email");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
    
    public int recId (String user) {
        String req="select id from utilisateur where email ='"+user+"'";
      int res=0;
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
               res=rs.getInt("id");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
    
    public String envoyerCode(int id){
         Random r = new Random();
         return ""+r.nextInt(100)+id+r.nextInt(100);
         
       //return ;
     }
    
     public boolean updateCode(String code,int id) {
         
        String qry = "UPDATE utilisateur SET code = '"+code+"' WHERE id = "+id;
         
        try {
            PreparedStatement pst=cnx.prepareStatement(qry);
            if (pst.executeUpdate() > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
     public String recCode (int id) {
        String req="select code from utilisateur where id ='"+id+"'";
        String res="null";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
               res=rs.getString("code");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
     
      public boolean update(String pwd,int id) {
         
        String qry = "UPDATE utilisateur SET password = '"+pwd+"' WHERE id = "+id;
         
        try {
                PreparedStatement pst=cnx.prepareStatement(qry);

            if (pst.executeUpdate() > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
    public Utilisateur getById() {
        String req = "select * from user where id =" + MainJavaFX.loggedInID;
        Utilisateur p = new Utilisateur();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setEmail(rs.getString("email"));
            p.setPassword(rs.getString("password"));
            p.setRoles(rs.getString("roles"));
            p.setNum_tel(rs.getInt("num_tel"));
            p.setImage(rs.getString("image"));
            p.setIsVerified(rs.getInt("is_verified"));
            

            //}  
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;
    }

   
    
      

}
