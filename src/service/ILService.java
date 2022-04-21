/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author aicha
 */
public interface ILService<T> {
    void ajouter(T t);
    void modifier(T t);
    void supprimer(int idlignecommande);
    List<T> recuperer();
    T recuperer(int idlignecommande);
}
