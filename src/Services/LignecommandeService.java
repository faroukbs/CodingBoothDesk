/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Lignecommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.MyDB;

/**
 *
 * @author aicha
 */
public class LignecommandeService implements services.ILService<Lignecommande>{
    
    Connection cnx;
    public LignecommandeService(){
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Lignecommande t) {
        try{
        String req="insert into lignecommande(idcommande,id_produit,quantite)"+"values("+t.getIdcommande()+","+t.getIdproduit()+","+t.getQuantite()+")";
      Statement statement = cnx.createStatement();
//            System.out.println("Insertion...");
           statement.executeUpdate(req);            
            System.out.println("Une ligne insérée dans la table...");
            
        }
        
        catch(SQLException e){
//            Logger.getLogger(ProduitCrud.class.getTitre);  
         
     }
    }

    @Override
    public void modifier(Lignecommande t) {
        try{
            String req = "UPDATE lignecommande SET idcommande = ?, id_produit = ?, quantite= ? WHERE idlignecommande= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setInt(1,t.getIdcommande());
        ps.setInt(2,t.getIdproduit());
        ps.setInt(3,t.getQuantite());
        
        ps.setInt(4,t.getIdlignecommande());
         System.out.println("Modification...");
         ps.executeUpdate();
      
        System.out.println("Une ligne modifiée dans la table...");
       }
       catch(SQLException e){
           
       }
    }

    @Override
    public void supprimer(int idlignecommande) {
        try{
            String req = "DELETE FROM lignecommande WHERE idlignecommande = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1,idlignecommande);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
       }
       catch(SQLException e){
           System.out.println(e.getMessage());
           System.out.println(idlignecommande);
       }
    }

    @Override
    public List<Lignecommande> recuperer() {
        List<Lignecommande> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM lignecommande ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               Lignecommande r = new Lignecommande();
               r.setIdlignecommande(rs.getInt("idlignecommande"));
                r.setIdcommande(rs.getInt("idcommande"));
                r.setIdproduit(rs.getInt("id_produit"));
                r.setQuantite(rs.getInt("quantite"));
                

                
                list.add(r);
                
                
            }
            
    }

  
        catch(SQLException c){
            System.out.println(c.getMessage());
            
        }
        return list ;
    }

    public Lignecommande recuperer(int idlignecommande) {
        Lignecommande r = new Lignecommande();
        try {
            String req = "SELECT * FROM lignecommande WHERE idlignecommande = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idlignecommande);
            ResultSet rs = pst.executeQuery();
            rs.next();
            r.setIdlignecommande(rs.getInt("idlignecommande"));
            r.setIdcommande(rs.getInt("idcommande"));
            r.setIdproduit(rs.getInt("id_produit"));
            r.setQuantite(rs.getInt("quantite"));

        
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return r;
    }
    public ObservableList<Integer> affecterCom() {
        ObservableList<Integer> listevent = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT idcommande FROM commande";
            ResultSet rs;
            rs = st.executeQuery(req);
            while (rs.next()) {
                listevent.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //   return null;
        }
        return listevent;
    }
    public ObservableList<Integer> affecterProd() {
        ObservableList<Integer> listevent = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT id_produit FROM produit";
            ResultSet rs;
            rs = st.executeQuery(req);
            while (rs.next()) {
                listevent.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //   return null;
        }
        return listevent;
    }
}
