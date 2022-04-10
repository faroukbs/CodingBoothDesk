/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author bouss
 */
public class Utilisateur {

    private int id;
    private String nom, prenom,email,password,numTel,image;
    private String roles="[]";
    private Date date_naissance;
    private int is_verified=1;
    

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String email,String password,String numTel, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password=password;
        this.numTel=numTel;
        this.image=image;
       // this.date_naissance=date_naissance;
   //  this.roles=roles;   
        
        
    }
    
    
    public Utilisateur(String nom,String prenom,String email,String password,String numTel,String image,Date date_naissance) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password=password;
        this.numTel=numTel;
        this.image=image;
        this.date_naissance=date_naissance;
       // this.roles=roles;
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

    public void setpassword(String password) {
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
     
     public int getisVerified() {
        return is_verified;
    }

    public void setIsVerified(int isVerified) {
        this.is_verified = is_verified;
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
    
    
    
      public Date getDateNaissance() {
        return date_naissance;
    }

    public void setDateNaissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }
    
    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", email=" + email + ", password=" + password + ",is_verified=" + is_verified + ", roles=" + roles +", image=" + image + ", numTel=" + numTel + ", date_naissance=" + date_naissance + ", nom=" + nom + ", prenom=" + prenom + '}';
    }

}