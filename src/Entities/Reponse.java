/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import Entities.Commande;


/**
 *
 * @author lylyy
 */
public class Reponse {
     private int idReponse;
     private String nom,sujet,message;
     private Date createdAt ;
       private String etat;
    private Utilisateur user;
     private Commande commande;
 
  

    public Reponse() {
    }

    public Reponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public Reponse(int idReponse, String nom, String sujet, String message, Date createdAt, String etat, Utilisateur user, Commande commande) {
        this.idReponse = idReponse;
        this.nom = nom;
        this.sujet = sujet;
        this.message = message;
        this.createdAt = createdAt;
        this.etat = etat;
        this.user = user;
        this.commande = commande;
    }

    public Reponse(String nom, String sujet, String message, Date createdAt, String etat, Utilisateur user, Commande commande) {
        this.nom = nom;
        this.sujet = sujet;
        this.message = message;
        this.createdAt = createdAt;
        this.etat = etat;
        this.user = user;
        this.commande = commande;
    }

    

 

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reponse{" + "idReponse=" + idReponse + ", nom=" + nom + ", sujet=" + sujet + ", message=" + message + ", createdAt=" + createdAt + ", etat=" + etat + ", user=" + user + ", commande=" + commande + '}';
    }

 
    
}
