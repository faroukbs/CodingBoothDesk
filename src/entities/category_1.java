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
public class category_1 {
    //attribut
    private int idcategory;
    private String nom ; 
    
    //constructor 

    public category_1() {
    }

    public category_1(String nom) {
        this.nom = nom;
    }

    public category_1(int idcategory, String nom) {
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
