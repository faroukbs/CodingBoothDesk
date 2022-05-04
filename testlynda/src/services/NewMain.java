/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import entities.Reponse;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import utils.MaConnexion;

/**
 *
 * @author lylyy
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     Connection cnx = MaConnexion.getInstance().getCnx();
     String str = "2018-03-21";
     
        Date date = Date.valueOf(str);
        //  ******************************************* SERVICES *******************************************  //
//        Iuser sp = new ServiceUser();
         Ireclamation sr = new ServiceReclamation();
         
     Reclamation r;
     Reponse reponse = new Reponse();
     Utilisateur user = new Utilisateur();
//        r = new Reclamation("lyly", "message", "sujet", "email", date , reponse, user);
//   sr.ajouterReclamation(r);
        System.out.println(sr.afficherReclamation());
             }
    
}
