/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Repondeur;
import java.util.List;

/**
 *
 * @author lylyy
 */
public interface Irepondeur {
      public boolean ajouterPersonne(Repondeur p);
    
    //lister
    public List<Repondeur> afficherPersonnes();
    
    public boolean modifierPersonne(Repondeur p);
    
    public boolean supprimerPersonne(Repondeur p);
}
