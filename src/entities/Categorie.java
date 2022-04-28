/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 *
 */
public class Categorie {
    
    int idCategorie;
    String NomCategorie;
    String Genre;

    public Categorie() {}

    public Categorie(int idCategorie, String NomCategorie, String Genre) {
        this.idCategorie = idCategorie;
        this.NomCategorie = NomCategorie;
        this.Genre = Genre;
    }
    

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    @Override
    public String toString() {
        return "Categorie{" + "idCategorie=" + idCategorie + ", NomCategorie=" + NomCategorie + ", Genre=" + Genre + '}';
    }
   
}
