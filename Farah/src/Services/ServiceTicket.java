/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Ticket;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Home
 */
public class ServiceTicket implements IService<Ticket> {
     Connection cnx;
 public ServiceTicket(){
 cnx = MyDB.getInstance().getConnection();
 
 
 
 }

    @Override
    public void ajouter(Ticket t) {
                  String req ="insert into ticket(typeticket,idevent,prix,description,nbrticket)"+"values('"+t.getTypeticket()+"',"+t.getIdevent()+","+t.getPrix()+",'"+t.getDescription()+"',"+t.getNbrticket()+")";
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
                       String req ="update ticket set typeticket= ? , description= ? , prix = ? where idticket = ?";     
     try {
       
         PreparedStatement ps = cnx.prepareStatement(req);
         
          ps.setString(1, t.getTypeticket());
          
       ps.setString(2, t.getDescription());
                ps.setInt(3, t.getPrix());
              ps.setInt(4, t.getIdticket());
               ps.executeUpdate();
     } catch (SQLException ex) {
  System.out.println(ex.getMessage());
     }
    }

    @Override
    public void supprimer(int idticket ) {
       
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
              List<Ticket> tickets = new ArrayList <>();
      
     try {
           String req=" select *from ticket";
         Statement st= cnx.createStatement();
         ResultSet rs=st.executeQuery(req);
         while(rs.next()){
         
         
         Ticket p= new Ticket();
     
               p.setTypeticket(rs.getString("typeticket"));
                p.setDescription(rs.getString("description"));
               p.setPrix(rs.getInt("prix"));
                
        
                 
                 tickets.add(p);
                 }
     } catch (SQLException ex) {
       
     }
     return tickets;  
    }

    @Override
    public Ticket recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
