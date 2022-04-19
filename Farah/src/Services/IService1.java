/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Home
 */

    public interface IService1 <T>{
    void ajouter(T t);

     void supprimer (int idevent);
     List<T> recuperer();
     T recuperer(int id);
     
     
    

}
