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
public class Repondeur extends Utilisateur {
    private String vehicule;
    private String cin;

    public Repondeur(String vehicule, String cin, int id) {
        super(id);
        this.vehicule = vehicule;
        this.cin = cin;
    }

    

    public Repondeur(String vehicule, String cin, int id, String nom, String prenom, int num_tel, String password, String date_naissance, String email, String roles) {
        super(id, nom, prenom, num_tel, password, date_naissance, email, roles);
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
