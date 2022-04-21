/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aicha
 */
public class MyDBPi {
    
    private final String url="jdbc:mysql://localhost:3306/pidev";
    private final String username="root";
    private final String password="";
    private Connection connection;
    private static MyDBPi instance;
    
    private MyDBPi() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
      
    }
    
    
      public static MyDBPi getInstance(){
            if(instance == null)
                instance = new MyDBPi();
            return instance;
        }

    public Connection getConnection() {
        return connection;

    }
    
}
