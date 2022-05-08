/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author doghm
 */
public final class Cart {

    public static Cart instance;

    private final ArrayList<Product> c;

    public Cart() {
        c = new ArrayList<Product>();

    }
     public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public ArrayList<Product> getCartList() {
        return c;
    }
    
  


    public void AddProduct(Product e) {
        this.c.add(e);
    }

    public void RemoveProduct(Product e) {
        
       int productid = e.getId_produit();
        for(int i=0 ; i<this.c.size();i++){
            Product product = this.c.get(i);
            if(product.getNomprod().equals(e.getNomprod())){
                this.c.remove(i);
            }
        }
    }
    public void RemoveAll(){
    this.c.clear();
    }

    public ArrayList<Product> getC() {
        return c;
    }

   

    public void cleanCartSession() {
        instance = null;
    }

    @Override
    public String toString() {
        return "Cart{"
                + "c=" + c
                + '}';
    }
    public double total(){
        double total=0;
     for(Product product : this.c){
                    total = total + (product.getPrix()*product.getQuantity());
                }
    return total;
    }
}
