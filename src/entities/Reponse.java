/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author bouss
 */
public class Reponse {

    int id;
    String sujet, message;
    Date createdAt;

    public Reponse(int id, String sujet, String message, Date createdAt) {
        this.id = id;
        this.sujet = sujet;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Reponse(String sujet, String message, Date createdAt) {
        this.sujet = sujet;
        this.message = message;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", sujet=" + sujet + ", message=" + message + ", createdAt=" + createdAt + '}';
    }

}
