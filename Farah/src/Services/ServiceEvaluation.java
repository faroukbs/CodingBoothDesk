/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evaluation;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Home
 */
public class ServiceEvaluation implements IService1<Evaluation> {
       Connection cnx;
     
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

 public ServiceEvaluation(){
 cnx = MyDB.getInstance().getConnection();
 
 }

    @Override
    public void ajouter(Evaluation t) {
             String req = "INSERT INTO evaluation (idevent,note) values(?,?)";
    try {
          pst = cnx.prepareStatement(req);
       
        pst.setInt(1, t.getIdevent());
        pst.setDouble(2, t.getNote());
        pst.execute();
         System.out.println("hi  ddd");
     } catch (SQLException ex) {
  System.out.println(ex.getMessage());
     }
    }
      
     public double recuperernbrRating(int id) throws SQLException {
        double arr = 0;
        ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery("select AVG(note)  from evaluation where idevent='" + id + "'");
        while (rs.next()) {
            double c = rs.getDouble(1);
            arr=c;
        }
        return arr;
    }
     
     
    
      public void modifier(int id,  double nvrat) throws SQLException {
                String req = "UPDATE `evaluation` SET `note` = '" + nvrat + "' where idevent='" +id + "' ";
                
       pst = cnx.prepareStatement(req);
        pst.execute();
    }

 
   

    @Override
    public void supprimer(int idevent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Evaluation> recuperer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Evaluation recuperer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

   
}
