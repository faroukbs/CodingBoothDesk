/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 *
 */
public class Joueur {

    int idJoueur;
    String NomJoueur;
    String PrenomJoueur;
    String Nomcategorie;
    Date DateDeNaissance;
    int Age;
    String Sexe;
    String Ville;
    ImageView imgJoueur;
    ////
    String imgJ;
    int myid;

    public Joueur() {
    }

//    public Joueur(int idJoueur, String NomJoueur, String PrenomJoueur, String Nomcategorie, Date DateDeNaissance, int Age, String Sexe, String Ville, ImageView imgJoueur) {
//        this.idJoueur = idJoueur;
//        this.NomJoueur = NomJoueur;
//        this.PrenomJoueur = PrenomJoueur;
//        this.Nomcategorie = Nomcategorie;
//        this.DateDeNaissance = DateDeNaissance;
//        this.Age = Age;
//        this.Sexe = Sexe;
//        this.Ville = Ville;
//        this.imgJoueur = imgJoueur;
//    }

    public Joueur(int idJoueur, String NomJoueur, String PrenomJoueur, String Nomcategorie, Date DateDeNaissance, int Age, String Sexe, String Ville, String imgJoueur,int myid) {
        this.idJoueur = idJoueur;
        this.NomJoueur = NomJoueur;
        this.PrenomJoueur = PrenomJoueur;
        this.Nomcategorie = Nomcategorie;
        this.DateDeNaissance = DateDeNaissance;
        this.Age = Age;
        this.Sexe = Sexe;
        this.Ville = Ville;
        this.imgJ = imgJ;
        this.myid = myid;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return NomJoueur;
    }

    public void setNomJoueur(String NomJoueur) {
        this.NomJoueur = NomJoueur;
    }

    public String getPrenomJoueur() {
        return PrenomJoueur;
    }

    public void setPrenomJoueur(String PrenomJoueur) {
        this.PrenomJoueur = PrenomJoueur;
    }

    public Date getDateDeNaissance() {
        return DateDeNaissance;
    }

    public void setDateDeNaissance(Date DateDeNaissance) {
        this.DateDeNaissance = DateDeNaissance;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public ImageView getImgJoueur() {
        return imgJoueur;
    }

    public void setImgJoueur(ImageView imgJoueur) {
        this.imgJoueur = imgJoueur;
    }

    public String getImgJ() {
        return imgJ;
    }

    public void setImgJ(String imgJ) {
        this.imgJ = imgJ;
    }

    public String getCategorie() {
        return Nomcategorie;
    }

    public void setCategorie(String Nomcategorie) {
        this.Nomcategorie = Nomcategorie;
    }

    public int getMyid() {
        return myid;
    }

    public void setMyid(int myid) {
        this.myid = myid;
    }

    @Override
    public String toString() {
        return "Joueur{" + "idJoueur=" + idJoueur + ", NomJoueur=" + NomJoueur + ", PrenomJoueur=" + PrenomJoueur + ", Nomcategorie=" + Nomcategorie + ", DateDeNaissance=" + DateDeNaissance + ", Age=" + Age + ", Sexe=" + Sexe + ", Ville=" + Ville + ", imgJoueur=" + imgJoueur + ", imgJ=" + imgJ + ", myid=" + myid + '}';
    }
    
    



}
