/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Categorie;
import entities.Cours;
import entities.Salle;
import java.sql.Date;
import javax.mail.MessagingException;
import services.SalleService;
import services.CategorieService;
import services.CoursService;
import util.MailAPI;
import util.MyDB;

/**
 *
 * @author ahmed
 */
public class Main {

    public static void main(String[] args) {
        //Salle s = new Salle(1,"ahmed","oussema",1);
        //SalleService ss = new SalleService();
        //System.out.println(ss.recuperer());
        //ss.ajouter(s);
        //ss.modifier(s);
        //ss.supprimer(1);
        
        //Categorie c = new Categorie("oussema");
        //CategorieService cs = new CategorieService();
        ////////////////  Crud Categorie  ///////////////////////////
        
        //System.out.println(cs.recuperer());
        //cs.ajouter(c);
        //cs.modifier(c);
        //cs.supprimer(8);
//        Date date = new Date(1998, 12, 1);
//        Date date2 = new Date(1998, 12, 2);
//        Cours cr = new Cours(1 , "karate", 1,1 ,8 , "entrain karate" , "aaaaaaaa", date, date2);
//        CoursService crs = new CoursService();
        ////////////////  Crud Cours  ///////////////////////////
        
        //System.out.println(crs.recuperer());
        //crs.ajouter(cr);
        //crs.modifier(cr);
//        crs.supprimer(1);
String mail ="amir.bensalah@esprit.tn";


try {
                    MailAPI.sendMail(mail, "Evennement Annulé", "L'évennement");
                }catch(MessagingException ex) {
                    System.err.println(ex.getMessage());
                }
    }
}
