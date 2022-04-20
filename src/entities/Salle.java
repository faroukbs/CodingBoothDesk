/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ahmed
 */
public class Salle {

    private int idsalle;
    private String nomsalle;
    private String description;
    private String materiel;
    private String image;
    private int idcategorie;

    public Salle(int idsalle, String nomsalle, String description, String materiel, int idcategorie) {
        this.idsalle = idsalle;
        this.nomsalle = nomsalle;
        this.description = description;
        this.materiel = materiel;
        this.idcategorie = idcategorie;
    }

    public Salle(int idsalle, String nomsalle, String description, String materiel, String image, int idcategorie) {
        this.idsalle = idsalle;
        this.nomsalle = nomsalle;
        this.description = description;
        this.materiel = materiel;
        this.image = image;
        this.idcategorie = idcategorie;
    }

    
    
    
    public Salle(String nomsalle, String description, int idcategorie) {
        this.nomsalle = nomsalle;
        this.description = description;
        this.idcategorie = idcategorie;
    }

   
    public Salle(String nomsalle, String description, String materiel, int idcategorie) {
        this.nomsalle = nomsalle;
        this.description = description;

       
        if (materiel.equals("bicyclette") || materiel.equals("tapis roulante")) {
            this.materiel = materiel;

        } else {
            System.out.println("le materiel doit etre b ou t");
        }
         this.idcategorie = idcategorie;

    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public int getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(int idsalle) {
        this.idsalle = idsalle;
    }

    public String getNomsalle() {
        return nomsalle;
    }

    public void setNomsalle(String nomsalle) {
        this.nomsalle = nomsalle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMateriel() {
        return materiel;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Salle() {
    }

    @Override
    public String toString() {
        return "Salle{" + "idsalle=" + idsalle + ", nomsalle=" + nomsalle + ", description=" + description + ", materiel=" + materiel + ", image=" + image + '}';
    }

}
