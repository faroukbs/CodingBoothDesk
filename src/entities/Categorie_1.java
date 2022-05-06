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
public class Categorie_1 {
    private int idcategorie;
    private String nomcategorie;

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public Categorie_1(int idcategorie, String nomcategorie) {
        this.idcategorie = idcategorie;
        this.nomcategorie = nomcategorie;
    }

    public Categorie_1(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
    
    public Categorie_1(int idcategorie) {
        this.idcategorie = idcategorie;
    }
    public Categorie_1() {
    }
@Override
    public String toString() {
        return "Categorie{" + "idcategorie=" + idcategorie + ", nomcategorie=" + nomcategorie + '}';
    }

}
