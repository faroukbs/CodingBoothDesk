/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Home
 */
public class Reservation {

           private  int idreser;
      private  int idticket;
private int quantity;
    private int iduser;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
private Date datereser;

    @Override
    public String toString() {
        return "Reservation{" + "iduser=" + iduser + ", idreser=" + idreser + ", idticket=" + idticket + ", quantity=" + quantity + ", datereser=" + datereser + '}';
    }



    public int getIdreser() {
        return idreser;
    }

    public void setIdreser(int idreser) {
        this.idreser = idreser;
    }

    public int getIdticket() {
        return idticket;
    }

    public void setIdticket(int idticket) {
        this.idticket = idticket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDatereser() {
        return datereser;
    }

    public void setDatereser(Date datereser) {
        this.datereser = datereser;
    }

    public Reservation(int idticket, int iduser) {
        this.idticket = idticket;
        this.iduser = iduser;
    }

    public Reservation(int idreser, int idticket, int iduser) {
        this.idreser = idreser;
        this.idticket = idticket;
        this.iduser = iduser;
    }
    
}
