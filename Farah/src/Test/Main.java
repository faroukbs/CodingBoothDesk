/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entities.Evaluation;
import Entities.Eventl;
import Entities.Reservation;
import Entities.Ticket;
import Services.ServiceEvaluation;

import Services.ServiceEventl;
import Services.ServiceResrvation;
import Services.ServiceTicket;

import Utils.MyDB;
import java.sql.Date;
import java.sql.SQLException;



/**
 *
 * @author Home
 */
public class Main {
    
    public static void main(String[] args) throws SQLException {
  //Personne p=new Personne(22,"farah","hasnaoui")  ;
  // Eventl p=new Eventl(7,"farah","fafa","farlll","hasnaoui")  ;
  //  Ticket p=new Ticket("farah",5,200,"hh",5);
  Date date = new Date(1998, 12, 1);
        Date date2 = new Date(1998, 12, 2);
    Eventl p=new Eventl(5,"farah", date,"farlll","fffff",date2,"fff")  ;
        ServiceEventl ps =new  ServiceEventl();
      //  ServiceTicket ps =new  ServiceTicket();
        //  System.out.println("vvvvv");
 //ps.ajouter(p);
  ps.modifier(p);
  // ps.supprimer(5);
 //System.out.println( ps.recuperer());      
 /////////////Resrvation///////////
 //Reservation p=new Reservation(8,8);
  // ServiceResrvation ps =new  ServiceResrvation();
       
        //  ps.ajouter(p);

 // ps.ajouter(p);
  /////////////evaluation///////////
  
// Evaluation p=new Evaluation(5);
  // Eventl p1=new Eventl("farah","fafa","farlll","hasnaoui")  ;
    //ServiceEvaluation ps =new  ServiceEvaluation();
         // System.out.println("vvvvv");
  // ps.ajouter(p);
  // System.out.println( ps.recuperernbrRating(1));  
 //ps.modifier(1,3.7);
         
    }
        
    
}
