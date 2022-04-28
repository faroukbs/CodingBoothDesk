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
public class category {
    //attribut
    private int idcategory;
    private String nom ; 
    
    //constructor 

    public category() {
    }

    public category(int idcategory, String nom) {
        this.idcategory = idcategory;
        this.nom = nom;
    }
    

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "category{" + "idcategory=" + idcategory + ", nom=" + nom + '}';
    }
    
    
}
