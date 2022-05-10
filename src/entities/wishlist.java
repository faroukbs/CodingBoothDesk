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
public class wishlist {
    int id , idproduit, iduser;

    public wishlist(int id, int idproduit, int iduser) {
        this.id = id;
        this.idproduit = idproduit;
        this.iduser = iduser;
    }

    public wishlist(int idproduit, int iduser) {
        this.idproduit = idproduit;
        this.iduser = iduser;
    }

    public wishlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "wishlist{" + "id=" + id + ", idproduit=" + idproduit + ", iduser=" + iduser + '}';
    }
    
    
}
