/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bouss
 */
public class MyDb {
    private final String url ="jdbc:mysql://localhost:3306/pidev";
    private final String username ="root";
    private final String password ="";
    private Connection connection;
    private static MyDb instance;
    private MyDb(){
        try {
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("connexion etablie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  public static  MyDb getInstance(){
      if (instance == null){
          instance = new MyDb();}
      return  instance;
      
  }

    public Connection getConnection() {
        return connection;
    }
    
}
