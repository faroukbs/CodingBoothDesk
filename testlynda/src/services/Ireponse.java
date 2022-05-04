/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reponse;
import java.util.List;

/**
 *
 * @author lylyy
 */
public interface Ireponse {
      public boolean ajouterReponse(Reponse l);
    
    //lister
    public List<Reponse> afficherReponse();
    
    public boolean modifierReponse(Reponse l);
    
    public boolean supprimerReponse(Reponse l);
    
     public List<Reponse> rechercheReponseparEtat_Reponse(String etat);
      public List<Reponse> triLivraisonparEtat_Reponse();
}
