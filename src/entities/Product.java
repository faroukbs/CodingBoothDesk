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
public class Product {
    // attribut
    protected int id_produit;
    protected String nomprod ; 
    protected String description;
    protected String image;
    protected float prix;
    protected int quantity ; 
    protected int categoryprod_id;
    //constructor 

    public Product() {
    }

    public Product(int id_produit, String nomprod, String description, String image, float prix, int quantity, int categoryprod_id) {
        this.id_produit = id_produit;
        this.nomprod = nomprod;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.quantity = quantity;
        this.categoryprod_id = categoryprod_id;
    }

    public Product(String nomprod, String description, String image, float prix, int quantity) {
        this.nomprod = nomprod;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.quantity = quantity;
    }

    public Product(int id_produit, String nomprod, String description, String image, float prix, int quantity) {
        this.id_produit = id_produit;
        this.nomprod = nomprod;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.quantity = quantity;
    }

    public Product(int id_produit, String nomprod, String image, float prix, int quantity) {
        this.id_produit = id_produit;
        this.nomprod = nomprod;
        this.image = image;
        this.prix = prix;
        this.quantity = quantity;
    }
    

    @Override
    public String toString() {
        return "Product{" + "id_produit=" + id_produit + ", nomprod=" + nomprod + ", description=" + description + ", image=" + image + ", prix=" + prix + ", quantity=" + quantity + ", categoryprod_id=" + categoryprod_id + '}';
    }

    public Product(String nomprod, String description, String image, float prix, int quantity, int categoryprod_id) {
        this.nomprod = nomprod;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.quantity = quantity;
        this.categoryprod_id = categoryprod_id;
    }
    

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNomprod() {
        return nomprod;
    }

    public void setNomprod(String nomprod) {
        this.nomprod = nomprod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryprod_id() {
        return categoryprod_id;
    }

    public void setCategoryprod_id(int categoryprod_id) {
        this.categoryprod_id = categoryprod_id;
    }

   
    
    
}
