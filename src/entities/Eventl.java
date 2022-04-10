/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author bouss
 */
public class Eventl {private  int idevent;
      private  String titre;
        private  String ville;
          private  String adresse;
           private  String description;
            private  String datedebut;
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

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
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

    public Eventl(int idevent, String titre, String ville, String datedebut, String photo) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
        this.datedebut = datedebut;
        this.photo = photo;
    }

    public Eventl(int idevent, String titre, String ville, String description, String datedebut, String photo) {
        this.idevent = idevent;
        this.titre = titre;
        this.ville = ville;
        this.description = description;
        this.datedebut = datedebut;
        this.photo = photo;
    }

    public Eventl(String titre, String ville, String description, String photo) {
        this.titre = titre;
        this.ville = ville;
        this.description = description;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Eventl{" + "idevent=" + idevent + ", titre=" + titre + ", ville=" + ville + ", description=" + description + ", datedebut=" + datedebut + ", photo=" + photo + '}';
    }

  

  

    public Eventl(int idevent, String titre, String ville, String adresse, String description, String datedebut, Date datefin, String photo, String heure, int evaluation, int view, int nombreParticipants) {
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
