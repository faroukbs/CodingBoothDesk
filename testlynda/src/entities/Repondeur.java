/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author lylyy
 */
public class Repondeur extends Utilisateur {
    private String vehicule;
    private String cin;

    public Repondeur(String vehicule, String cin, int idUser) {
        super(idUser);
        this.vehicule = vehicule;
        this.cin = cin;
    }

    public Repondeur(String vehicule, String cin, int idUser, String nom, String prenom, String email, String password, String numTel, String image, String roles, Date date_naissance) {
        super(idUser, nom, prenom, email, password, numTel, image, roles, date_naissance);
        this.vehicule = vehicule;
        this.cin = cin;
    }


    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }
    
         @Override
    public String toString() {
        return " email=" + getEmail();
    }
    
}
