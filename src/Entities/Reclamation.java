/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author bouss
 */
public class Reclamation {

    private int id;
    private String nom, message, sujet, email, etat, type;

    public Reclamation() {
    }

    public Reclamation(int id, String nom, String message, String sujet, String email, String etat, String type) {
        this.id = id;
        this.nom = nom;
        this.message = message;
        this.sujet = sujet;
        this.email = email;
        this.etat = etat;
        this.type = type;
    }

    public Reclamation(String nom, String message, String sujet, String email, String etat, String type) {
        this.nom = nom;
        this.message = message;
        this.sujet = sujet;
        this.email = email;
        this.etat = etat;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", message=" + message + ", sujet=" + sujet + ", email=" + email + ", etat=" + etat + ", type=" + type + '}';
    }

}
