/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lylyy
 */
public class MaConnexion {
    final String URL="jdbc:mysql://127.0.0.1:3306/java-ly";
    final String USERNAME="root";
    final String PWD="";
    
    private Connection cnx;
    //1
    static MaConnexion instance = null;
    
    //constructeur
    //2
    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PWD);
            //System.out.println("succès");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    //3
    public static MaConnexion getInstance() {
        
        if(instance == null){
            instance = new MaConnexion();
        }
        return instance;
    }
                
}
