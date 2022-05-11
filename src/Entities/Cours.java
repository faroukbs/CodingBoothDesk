/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author ahmed
 */
public class Cours {
    private int idcours;
    private String title;
    private int idcategorie;
    private int idcoach;
    private int idsalle;
    private String description;
    private String imagecours;
    private Date start;
    private Date end;	

    public int getIdcours() {
        return idcours;
    }

    public void setIdcours(int idcours) {
        this.idcours = idcours;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public int getIdcoach() {
        return idcoach;
    }

    public void setIdcoach(int idcoach) {
        this.idcoach = idcoach;
    }

    public int getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(int idsalle) {
        this.idsalle = idsalle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagecours() {
        return imagecours;
    }

    public void setImagecours(String imagecours) {
        this.imagecours = imagecours;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Cours(String title, int idcategorie, int idcoach, int idsalle, String description, String imagecours, Date start, Date end) {
        this.title = title;
        this.idcategorie = idcategorie;
        this.idcoach = idcoach;
        this.idsalle = idsalle;
        this.description = description;
        this.imagecours = imagecours;
        this.start = start;
        this.end = end;
    }

    
    
    
    public Cours() {
    }

    public Cours(int idcours, String title, int idcategorie, int idcoach, int idsalle, String description, String imagecours, Date start, Date end) {
        this.idcours = idcours;
        this.title = title;
        this.idcategorie = idcategorie;
        this.idcoach = idcoach;
        this.idsalle = idsalle;
        this.description = description;
        this.imagecours = imagecours;
        this.start = start;
        this.end = end;
    }

   

    public Cours(String title, int idcategorie, int idcoach, int idsalle, String description, Date start, Date end) {
        this.title = title;
        this.idcategorie = idcategorie;
        this.idcoach = idcoach;
        this.idsalle = idsalle;
        this.description = description;
        this.start = start;
        this.end = end;
    }

  

    @Override
    public String toString() {
        return "Cours{" + "idcours=" + idcours + ", title=" + title + ", idcategorie=" + idcategorie + ", idcoach=" + idcoach + ", idsalle=" + idsalle + ", description=" + description + ", imagecours=" + imagecours + ", start=" + start + ", end=" + end + '}';
    }

   
   


    

}
