/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Utilisateur;
import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import utils.DataSource;

/**
 *
 * @author GhAlone
 */

public class ServiceAdmin implements AService<Utilisateur> {
            boolean x;
            
              Connection cnx=DataSource.getInstance().getCnx();

    @Override
    public boolean connecter(Utilisateur a) {
            boolean ok=true;      
                try {
                String req="select email , password from utilisateur where ( email=? and password=? )";
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setString(1,a.getEmail());
                pst.setString(2,a.getPassword());
                ResultSet rs = pst.executeQuery();
                 if(rs.next()){ 
                    System.out.println("connected");
                }   
            } catch (SQLException ex) {
                System.out.println("connexion echouer"); 
            }
        return ok;    }

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
                System.out.println(ex.getMessage());      
    }
    }
/*
            @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> list = new ArrayList<>();
        try {
            String req = "Select * from utilisateur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Utilisateur u = new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getInt("num_tel"),rs.getString("date_naissance"),
                        rs.getString("email"), rs.getString("password"), rs.getString("image"),
                        rs.getString("roles"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    
    */
    
    
  @Override
    public List<Utilisateur> afficher() {
          List<Utilisateur> list =new ArrayList<>();

        try {
            String req = "select * from utilisateur  " ;
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

    @Override
    public void delete(Utilisateur a) {
                
                try {
                    String req="DELETE from utilisateur where id=? and roles!='Admin' ";
                    PreparedStatement pst=cnx.prepareStatement(req);
                    pst.setInt(1,a.getId());
                    pst.executeUpdate();
                    System.out.println("deleted");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
                
                }
    public List<Utilisateur> rechercher(String index) {
	List<Utilisateur> result = afficher().stream().filter(line -> index.equals(line.getEmail())).collect(Collectors.toList());
	System.out.println("----------");
	result.forEach(System.out::println);
	return result;
    }
    public List<Utilisateur> trier() {
	List<Utilisateur> sortedByName = afficher().stream().sorted(Comparator.comparing(Utilisateur::getNom)).collect(Collectors.toList());
	sortedByName.forEach(System.out::println);
	return sortedByName;
    }

    public List<Utilisateur> trierMulti() {
	Comparator<Utilisateur> compareByName = Comparator.comparing(Utilisateur::getNom).thenComparing(Utilisateur::getNum_tel);

	List<Utilisateur> sortedByNameAndTel = afficher().stream()
		.sorted(compareByName)
		.collect(Collectors.toList());

	sortedByNameAndTel.forEach(System.out::println);
	return sortedByNameAndTel;
    }
    public List<Utilisateur> trierID() {
	List<Utilisateur> sortedByName = afficher().stream().sorted(Comparator.comparing(Utilisateur::getId)).collect(Collectors.toList());
	sortedByName.forEach(System.out::println);
	return sortedByName;
    }
    public void bloquer(Utilisateur t) {
        try {
                    String req="UPDATE utilisateur SET is_verified=" +false +" where id=? ";
                    PreparedStatement pst=cnx.prepareStatement(req);
                    pst.setInt(1,t.getId());
                    pst.executeUpdate();
                    System.out.println("bloquer");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
    }
    public void debloquer(Utilisateur t) {
        try {
                    String req="UPDATE utilisateur SET is_verified=" +true +" where id=? ";
                    PreparedStatement pst=cnx.prepareStatement(req);
                    pst.setInt(1,t.getId());
                    pst.executeUpdate();
                    System.out.println("debloquer");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
    }
}
   
    

