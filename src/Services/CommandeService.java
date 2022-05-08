/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;

/**
 *
 * @author aicha
 */
public class CommandeService implements ICService<Commande>{
    Connection cnx;
    public CommandeService(){
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouterCommande(Commande t) {
        
        String req="insert into commande(nom_client,prenom_client,telephone,adresse,montant,mode_paiement,etat_commande)"+"values(?,?,?,?,?,?,?)";
      try {
           PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,t.getNom_client());
        ps.setString(2,t.getPrenom_client());
        ps.setString(3,t.getTelephone());
        ps.setString(4,t.getAdresse());
        ps.setString(5,t.getMontant());
        ps.setString(6,t.getMode_paiement());
        ps.setInt(7,t.getEtat_commande());
        ps.executeUpdate();
            System.out.println("Commande ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
//        
    }

    @Override
    public void modifierCommande(Commande t) {
        try{
            String req = "UPDATE commande SET nom_client = ?,prenom_client = ?, telephone= ?, adresse = ?  WHERE idcommande= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
       
        ps.setString(1,t.getNom_client());
        ps.setString(2,t.getPrenom_client());
        ps.setString(3,t.getTelephone());
        ps.setString(4,t.getAdresse());
        
        ps.setInt(5,t.getIdcommande());
         System.out.println("Modification...");
         ps.executeUpdate();
      
        System.out.println("Une ligne modifiée dans la table...");
       }
       catch(SQLException e){
           
       }
    }

    @Override
    public void supprimerCommande(int idcommande) {
       try{
            String req = "DELETE FROM commande WHERE idcommande = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            System.out.println("Suppression...");
            ps.setInt(1,idcommande);

            ps.executeUpdate();
            System.out.println("Une ligne SUPPRIMER dans la table...");
       }
       catch(SQLException e){
           
       }
    }  
        
     
    

    @Override
    public List<Commande> recupererCommande() {
        List<Commande> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM commande ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
               Commande r = new Commande();
                r.setIdcommande(rs.getInt("idcommande"));
                r.setNom_client(rs.getString("nom_client"));
                r.setPrenom_client(rs.getString("prenom_client"));
               r.setTelephone(rs.getString("telephone"));
                r.setAdresse(rs.getString("adresse"));
                 r.setMontant(rs.getString("montant"));
                 r.setMode_paiement(rs.getString("mode_paiement"));
                 r.setEtat_commande(rs.getInt("etat_commande"));

                
                list.add(r);
                
                
            }
            
    }

  
        catch(SQLException c){
            System.out.println(c.getMessage());
            
        }
        return list ;

    }

    public Commande recuperer(int idcommande) {
        Commande r = new Commande();
        try {
            String req = "SELECT * FROM commande WHERE idcommande = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,idcommande);
            ResultSet rs = pst.executeQuery();
            rs.next();
            r.setIdcommande(rs.getInt("idcommande"));
                r.setNom_client(rs.getString("nom_client"));
                r.setPrenom_client(rs.getString("prenom_client"));
               r.setTelephone(rs.getString("telephone"));
                r.setAdresse(rs.getString("adresse"));
                 r.setMontant(rs.getString("montant"));
                 r.setMode_paiement(rs.getString("mode_paiement"));
                 r.setEtat_commande(rs.getInt("etat_commande"));
        
            
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return r;
        
         
    }
    public List<Commande> RechercherCommande(String nom) {
         List<Commande> list = new ArrayList<>();
        try{
            String req = "SELECT * FROM commande where nom_client LIKE '"+nom+"%'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Commande r = new Commande();
              r.setIdcommande(rs.getInt("idcommande"));
                r.setNom_client(rs.getString("nom_client"));
                r.setPrenom_client(rs.getString("prenom_client"));
               r.setTelephone(rs.getString("telephone"));
                r.setAdresse(rs.getString("adresse"));
                 r.setMontant(rs.getString("montant"));
                 r.setMode_paiement(rs.getString("mode_paiement"));
                 r.setEtat_commande(rs.getInt("etat_commande"));


                
                list.add(r);
            }
    }
        catch(SQLException c){
            
        }
        return list ; 
    }
    public List<Commande> AfficherCommande(Commande c) {
        List<Commande> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commande ";
            Statement pst = MyDB.getInstance().getConnection().createStatement();
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Commande r = new Commande();
                r.setIdcommande(rs.getInt("idcommande"));
                r.setNom_client(rs.getString("nom_client"));
                r.setPrenom_client(rs.getString("prenom_client"));
               r.setTelephone(rs.getString("telephone"));
                r.setAdresse(rs.getString("adresse"));
                 r.setMontant(rs.getString("montant"));
                 r.setMode_paiement(rs.getString("mode_paiement"));
                 r.setEtat_commande(rs.getInt("etat_commande"));


                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLSTATE: " + ex.getSQLState());
            System.out.println("VnedorError: " + ex.getErrorCode());
        }
        return list;
    }

//    @Override
//    public Commande TrouverById(int id) {
//        C R = null;
//        String Req = "select * from commande where idcommande=" + id + "";
//        try {
//            Ste = Cox.createStatement();
//            Rs = Ste.executeQuery(Req);
//            while (Rs.next()) {
//                R = new Reservation(Rs.getInt("idReservation"), Rs.getDate("Date"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return R;
//    }
    public int calculnb(String modeP) {

        PreparedStatement pre;
        int count = 19;
        try {
            Statement stmt = cnx.createStatement();

            String query = "SELECT COUNT(*) FROM commande WHERE mode_paiement='"+modeP+"'";

            ResultSet rs = stmt.executeQuery(query);

            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;

    }


    
    
}
