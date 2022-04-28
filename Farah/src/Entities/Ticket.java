/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Home
 */
public class Ticket {

    private int idticket;
    private String titre;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    public Ticket(int idticket, String titre, String typeticket, String description, int prix) {
        this.idticket = idticket;
        this.titre = titre;
        this.typeticket = typeticket;
        this.prix = prix;
        this.description = description;
    }

    public Ticket(String titre, String typeticket, int prix, String description) {
        this.titre = titre;
        this.typeticket = typeticket;
        this.prix = prix;
        this.description = description;
    }

    public Ticket(int idevent, String typeticket, int prix, String description,int nbrticket) {
        this.titre = titre;
        this.typeticket = typeticket;
        this.idevent = idevent;
        this.prix = prix;
            this.nbrticket= nbrticket;
        this.description = description;
    }
    private String typeticket;
    private int idevent;
    private int nbrticket;

    public int getNbrticket() {
        return nbrticket;
    }

    public void setNbrticket(int nbrticket) {
        this.nbrticket = nbrticket;
    }

    public Ticket(int idticket, String typeticket, int idevent, int prix, String description) {
        this.idticket = idticket;
        this.typeticket = typeticket;
        this.idevent = idevent;
        this.prix = prix;
        this.description = description;
    }

    public Ticket() {
    }
    private int prix;
    private String description;

    public Ticket(String typeticket, int idevent, int prix, String description) {
        this.typeticket = typeticket;
        this.idevent = idevent;
        this.prix = prix;
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ticket(int idticket, String typeticket, int idevent) {
        this.idticket = idticket;
        this.typeticket = typeticket;
        this.idevent = idevent;
    }

    public Ticket(String typeticket, int idevent) {
        this.typeticket = typeticket;
        this.idevent = idevent;
    }

    public int getIdticket() {
        return idticket;
    }

    public void setIdticket(int idticket) {
        this.idticket = idticket;
    }

    public String getTypeticket() {
        return typeticket;
    }

    public void setTypeticket(String typeticket) {
        this.typeticket = typeticket;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    @Override
    public String toString() {
        return "Ticket{" + "idticket=" + idticket + ", titre=" + titre + ", typeticket=" + typeticket + ", idevent=" + idevent + ", nbrticket=" + nbrticket + ", prix=" + prix + ", description=" + description + '}';
    }

   

}
