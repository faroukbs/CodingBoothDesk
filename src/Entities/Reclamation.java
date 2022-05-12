/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author lylyy
 */
public class Reclamation {
   private int idReclamation; 
   private String nom,message,sujet,email;
   private Date createdAt; 
   private Reponse reponse; 
   private Utilisateur user;
   private boolean warn;

    public Reclamation() {
    }

    public Reclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public Reclamation(String nom, String message, String sujet, String email, Date createdAt, Reponse reponse, Utilisateur user) {
        this.nom = nom;
        this.message = message;
        this.sujet = sujet;
        this.email = email;
        this.createdAt = createdAt;
        this.reponse = reponse;
        this.user = user;
    }
    
    public Reclamation(int idReclamation, String nom, String message, String sujet, String email, Date createdAt, Reponse reponse, Utilisateur user) {
        this.idReclamation = idReclamation;
        this.nom = nom;
        this.message = message;
        this.sujet = sujet;
        this.email = email;
        this.createdAt = createdAt;
        this.reponse = reponse;
        this.user = user;
    }
    

    public boolean isWarn() {
        return warn;
    }

    public void setWarn(boolean warn) {
        this.warn = warn;
    }

    public Reclamation(int idReclamation, String nom, String message, String sujet, String email, Date createdAt, Reponse reponse, Utilisateur user, boolean warn) {
        this.idReclamation = idReclamation;
        this.nom = nom;
        this.message = message;
        this.sujet = sujet;
        this.email = email;
        this.createdAt = createdAt;
        this.reponse = reponse;
        this.user = user;
        this.warn = warn;
    }

 

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", nom=" + nom + ", message=" + message + ", sujet=" + sujet + ", email=" + email + ", createdAt=" + createdAt + ", reponse=" + reponse + ", user=" + user + '}';
    }
   
   
}
