/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import java.util.List;
import static javafx.scene.input.KeyCode.U;
import entities.Utilisateur;


/**
 *
 * @author lylyy
 */
public interface Iuser<Utilisateur> {
public List<Utilisateur> afficher();
}
