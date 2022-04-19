/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Home
 */
public class Eventl {private  int idevent;
      private  String titre;
        private  String ville;
          private  String adresse;
           private  String description;
            private  Date datedebut;
               private  Date datefin;
                  private  String photo;
                   private  String heure;
               private  int evaluation;
               private  int view;
               private  int nombreParticipants;

    public Eventl(int idevent, String titre, String ville) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
    }

    public Eventl() {
        
    }
     public Eventl(int idevent) {
        this.idevent = idevent;
    }

    public Eventl(int idevent, String titre, String ville, String photo) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
        this.photo = photo;
    }

    public Eventl(String titre, String description, Date datedebut, Date datefin, String ville, String photo) {
 
        this.titre = titre;
           this.description =description;
           this.datedebut = datedebut;
           this.datefin = datefin;
        this.ville = ville;
        this.photo = photo;
        
    }

   
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getNombreParticipants() {
        return nombreParticipants;
    }

    public void setNombreParticipants(int nombreParticipants) {
        this.nombreParticipants = nombreParticipants;
    }

    public Eventl( int idevent,String  titre,Date datefin, String description ,String ville, Date datedebut ,String photo) {
         this.titre = titre;
        this.idevent = idevent;
        this.ville = ville;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.photo = photo;
    }

    public Eventl(int idevent, String titre, String ville, Date datedebut, String photo) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
        this.datedebut = datedebut;
        this.photo = photo;
    }

    public Eventl(int idevent, String titre, String ville, String description, Date datedebut, String photo) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
        this.description = description;
        this.datedebut = datedebut;
        this.photo = photo;
    }

    public Eventl(String titre, String ville, String description, Date datedebut, Date datefin, String photo) {
        this.titre = titre;
        this.ville = ville;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.photo = photo;
    }

    public Eventl(String titre, String ville, String description, String photo) {
        this.titre = titre;
        this.ville = ville;
        this.description = description;
        this.photo = photo;
    }

    public Eventl(String titre, Date datedebut, Date datefin,String ville, String description) {
        this.titre = titre;
        this.ville = ville;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
    
    }

 

    @Override
    public String toString() {
        return "Eventl{" + "idevent=" + idevent + ", titre=" + titre + ", ville=" + ville + ", adresse=" + adresse + ", description=" + description + ", datedebut=" + datedebut + ", datefin=" + datefin + ", photo=" + photo + ", heure=" + heure + ", evaluation=" + evaluation + ", view=" + view + ", nombreParticipants=" + nombreParticipants + '}';
    }

    public Eventl(int idevent, String titre, String ville, String adresse, String description, Date datedebut, Date datefin, String photo) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
        this.adresse = adresse;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.photo = photo;
    }

   

  

  

    public Eventl(int idevent, String titre, String ville, String adresse, String description, Date datedebut, Date datefin, String photo, String heure, int evaluation, int view, int nombreParticipants) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
        this.adresse = adresse;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.photo = photo;
        this.heure = heure;
        this.evaluation = evaluation;
        this.view = view;
        this.nombreParticipants = nombreParticipants;
    }

    public Eventl(String titre, String ville) {
        this.titre = titre;
        this.ville = ville;
    }

    public Eventl(String titre, String ville, String photo) {
        this.titre = titre;
        this.ville = ville;
        this.photo = photo;
    }



  
    
}
