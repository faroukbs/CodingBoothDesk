/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author aicha
 */
public interface ICService<T> {
    void ajouterCommande(T t);
    void modifierCommande(T t);
    void supprimerCommande(int idcommande);
    List<T> recupererCommande();
}