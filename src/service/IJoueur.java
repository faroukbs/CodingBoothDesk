/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Categorie;
import entities.Joueur;
import java.util.List;

/**
 *
 *
 */
public interface IJoueur <J> {
    
    public boolean AjouterJoueur(J j);
    public boolean ModifierJoueur(J j);
    public boolean SupprimerJoueur(int idJoueur);
    public List<Joueur> AfficherForAdmin(Joueur t);
    public List<Categorie> MaxCategorieInJoueur();
}
