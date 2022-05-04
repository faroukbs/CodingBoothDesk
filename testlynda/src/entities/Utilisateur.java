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
public class Utilisateur {
    private int idUser; 
    private String nom,prenom,email,password,numTel,image;
    private String roles;
    private Date date_naissance;
    private int is_verified=1;

    public Utilisateur() {
    }

    public Utilisateur(int idUser) {
        this.idUser = idUser;
    }

    public Utilisateur(String nom, String prenom, String email, String password, String numTel, String image, Date date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.image = image;
        this.date_naissance = date_naissance;
    }

    public Utilisateur(int idUser, String nom, String prenom, String email, String password, String numTel, String image, String roles, Date date_naissance) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numTel = numTel;
        this.image = image;
        this.roles = roles;
        this.date_naissance = date_naissance;
    }
     public Utilisateur(int idUser, String email, String roles, String password, String nom, String prenom, Date date_naissance, String num_tel, String image, int is_verified) {
        this.idUser = idUser;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.numTel = numTel;
        this.image = image;
        this.is_verified = is_verified;
       
        
       
      
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", numTel=" + numTel + ", image=" + image + ", roles=" + roles + ", date_naissance=" + date_naissance + ", is_verified=" + is_verified + '}';
    }
    
    
}
