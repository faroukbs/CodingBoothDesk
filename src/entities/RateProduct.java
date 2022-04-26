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
public class RateProduct {
    private int id, id_produit;
    private String nom ;
    private double note;

    public RateProduct(int id_produit, double note) {
        this.id_produit = id_produit;
        this.note = note;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public RateProduct() {
    }

    @Override
    public String toString() {
        return "RateProduct{" + "id=" + id + ", id_produit=" + id_produit + ", nom=" + nom + ", note=" + note + '}';
    }
    
}
