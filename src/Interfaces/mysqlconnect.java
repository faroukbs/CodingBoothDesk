/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author GhAlone
 */
public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb(){
      
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pidev","root","");
        JOptionPane.showMessageDialog(null,"Connection established");
        return conn;
        
        
        }catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                    return null;
        }
    }
}
