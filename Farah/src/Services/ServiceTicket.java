/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Eventl;
import Entities.Ticket;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Home
 */
public class ServiceTicket implements IService<Ticket> {

    Connection cnx;

    public ServiceTicket() {
        cnx = MyDB.getInstance().getConnection();

    }

    @Override
    public void ajouter(Ticket t) {
        String req = "insert into ticket(typeticket,idevent,prix,description,nbrticket)" + "values('" + t.getTypeticket() + "'," + t.getIdevent() + "," + t.getPrix() + ",'" + t.getDescription() + "'," + t.getNbrticket() + ")";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("hi  ddd");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Ticket t) {
        String req = "update ticket set  idevent= ? , typeticket= ? , prix = ? ,description =? ,nbrticket=? where idticket = ?";
        try {

            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, t.getIdevent());
            ps.setString(2, t.getTypeticket());
            ps.setInt(3, t.getPrix());
            ps.setString(4, t.getDescription());
                 ps.setInt(5, t.getNbrticket());
            ps.setInt(6, t.getIdticket());
             
            ps.executeUpdate();
            System.out.println(" modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                       

        }
    }

    @Override
    public void supprimer(int idticket) {

        String req = "delete from ticket where idticket=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, idticket);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("ticket supprimé");
    }

    @Override
    public List<Ticket> recuperer() {
        List<Ticket> tickets = new ArrayList<>();

        try {
            String req = " select *from ticket";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Ticket p = new Ticket();
                p.setIdticket(rs.getInt("idticket"));
                p.setIdevent(rs.getInt("idevent"));
                p.setTypeticket(rs.getString("typeticket"));
                p.setDescription(rs.getString("description"));
                p.setPrix(rs.getInt("prix"));
     p.setNbrticket(rs.getInt("nbrticket"));
                tickets.add(p);
            }
        } catch (SQLException ex) {

        }
        return tickets;
    }

    @Override
    public Ticket recuperer(int id) {
      List <Ticket> ticketts = new ArrayList <>();
      
     try {
           String req=" select *from Ticket where idevent=?";
         Statement st= cnx.createStatement();
         ResultSet rs=st.executeQuery(req);
         while(rs.next()){
         
         
         Ticket p= new Ticket();
         p.setIdticket(rs.getInt(1));
               p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
            
         
                 
                 ticketts.add(p);
                 }
     } catch (SQLException ex) {
       
     }
     return (Ticket) ticketts;  
    }
    

    public List<String> getAll() {
        List<String> list = new ArrayList<String>();
        try {
            String requetee = "SELECT titre FROM eventl";
            PreparedStatement pst = cnx.prepareStatement(requetee);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs.toString());

            while (rs.next()) {
                list.add(rs.getString("idevent"));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public int chercherVoy(String titre) throws SQLException {
        int id = 0;
        String requetee = "SELECT idevent FROM eventl where titre='" + titre + "';";
        PreparedStatement pst = cnx.prepareStatement(requetee);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            id = rs.getInt("id");

        }
        return id;
    }

    public void AjouterReserverVoyage(Ticket r) {
        try {
            String req = "insert into ticket(idevent,description,typeticket, prix)"
                    + "values(" + r.getIdevent() + ",'" + r.getDescription() + "','" + r.getTypeticket() + "'," + r.getPrix() + ")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Voyage ajouter avec succ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Integer> affecterEvent() {
        ObservableList<Integer> listevent = FXCollections.observableArrayList();

        try {
            Statement st = cnx.createStatement();
            String req = "SELECT idevent FROM eventl";
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
    public ObservableList<Ticket> chercherTitreTicket(String chaine){
          String sql="SELECT * FROM ticket WHERE (typeticket LIKE ? or prix LIKE ? or description LIKE ? )";
            
           cnx = MyDB.getInstance().getConnection();
            String ch="%"+chaine+"%";

            ObservableList<Ticket> myList= FXCollections.observableArrayList();
        try {
           
            Statement ste= cnx.createStatement();
           // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee =cnx.prepareStatement(sql);  
            stee.setString(1, ch);
            stee.setString(2, ch);
            stee.setString(3, ch);
   
            ResultSet rs = stee.executeQuery();
            while (rs.next()){
                Ticket v = new Ticket ();
                   v.setIdticket(rs.getInt("idticket"));
                v.setIdevent(rs.getInt("idevent"));
                    v.setTypeticket(rs.getString("typeticket"));
            
            v.setDescription(rs.getString("description"));

                v.setPrix(rs.getInt("prix"));
                myList.add(v);
                System.out.println("titre trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
      }

}
