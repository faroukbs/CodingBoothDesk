/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Commande;
import entities.Lignecommande;
import service.CommandeService;
import service.LignecommandeService;
import util.MyDBPi;

/**
 *
 * @author aicha
 */
public class mainTest {
    public static void main(String[] args) {
        
                 MyDBPi.getInstance(); 

//        Commande c = new Commande("wali","ines","27896414","ariana","100","cash",0);
//        CommandeService ps = new CommandeService();
//        ps.ajouterCommande(c);

//        Commande c = new Commande(52,"ouali","imen","55161266","ariana");
//        CommandeService ps = new CommandeService();
//        ps.modifierCommande(c);
//        Commande c = new Commande();
//        CommandeService ps = new CommandeService();
//        System.out.println(ps.recuperer(43));

//        Commande c = new Commande();
//        CommandeService ps = new CommandeService();
//        System.out.println(ps.recupererCommande());
//        
//        Commande c = new Commande();
//        CommandeService ps = new CommandeService();
//        ps.supprimerCommande(51);
        
        
////        Commande c = new Commande();
////        CommandeService ps = new CommandeService();
////        System.out.println(ps.RechercherCommande("aicha"));


//          Lignecommande l = new Lignecommande(42,1,25);
//         LignecommandeService ps = new LignecommandeService();
//         ps.ajouter(l);
         
//         Lignecommande l = new Lignecommande(12,43,2,25);
//         LignecommandeService ps = new LignecommandeService();
//         ps.modifier(l);

         Lignecommande l = new Lignecommande();
         LignecommandeService ps = new LignecommandeService();
         ps.supprimer(12);
        

//         Lignecommande l = new Lignecommande();
//         LignecommandeService ps = new LignecommandeService();
//         System.out.println(ps.recuperer());
    }
    
    
    
}
